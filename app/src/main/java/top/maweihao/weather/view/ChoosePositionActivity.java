package top.maweihao.weather.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.maweihao.weather.R;
import top.maweihao.weather.adapter.ChoosePositionRecyclerViewAdapter;
import top.maweihao.weather.contract.ChoosePositionActivityContract;
import top.maweihao.weather.db.City;
import top.maweihao.weather.db.County;
import top.maweihao.weather.db.Province;
import top.maweihao.weather.entity.dao.MLocation;
import top.maweihao.weather.presenter.ChoosePositionActivityPresenter;
import top.maweihao.weather.util.Constants;
import top.maweihao.weather.util.LogUtils;
import top.maweihao.weather.util.Utility;

public class ChoosePositionActivity extends AppCompatActivity
        implements SearchView.OnQueryTextListener, ChoosePositionActivityContract.View {

    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.positionon_RecyclerView)
    RecyclerView positionRecyclerView;

    private ProgressDialog progressDialog;
    private ChoosePositionRecyclerViewAdapter adapter;

    private LinearLayoutManager linearLayoutManager;

    private List<String> dataList = new ArrayList<>();
    private List<String> filterList = new ArrayList<>(); //筛选后的List

    public static List<Province> provinceList;
    public static List<City> cityList;
    public static List<County> countyList;

    public static Province selectedProvince;
    public static City selectedCity;
    public static int currentLevel;
    private String cityName;
    private MLocation mLocation;

    private ChoosePositionActivityContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_position);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        mLocation = new MLocation(MLocation.TYPE_CHOOSE);

        presenter = new ChoosePositionActivityPresenter(this);

        linearLayoutManager = new LinearLayoutManager(this);//RecyclerView布局管理器
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);//纵向item布局
        adapter = new ChoosePositionRecyclerViewAdapter(null);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
//        positionRecyclerView.addItemDecoration(
//                new HorizontalDividerItemDecoration.Builder(this)
//                        .color(ContextCompat.getColor(this, R.color.split_line))
//                        .sizeResId(R.dimen.recyclerView_divider_height)
//                        .marginResId(R.dimen.recyclerView_divider_leftmargin, R.dimen.recyclerView_divider_rightmargin)
//                        .build());//绘制分割线
        positionRecyclerView.setHasFixedSize(true);
        positionRecyclerView.setLayoutManager(linearLayoutManager);
        positionRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Utility.closeSoftInput(ChoosePositionActivity.this);//关闭软键盘
                if (currentLevel == LEVEL_PROVINCE) {
                    /*
                    通过比对原始list数据和筛选后所点击的name，名字相同则为当前点击的选项。
                     如果数组越界，则没有进行筛选，直接取出原始数据
                    */
                    try {
                        for (Province province : provinceList) {
                            if (province.getProvinceName().equals(filterList.get(position))) {
                                selectedProvince = province;
                                break;
                            }
                        }
                    } catch (IndexOutOfBoundsException e) {
                        selectedProvince = provinceList.get(position);
                    } finally {
                        filterList = new ArrayList<>();
                        presenter.queryCities();
                    }
                        LogUtils.i( "selectedProvince = " + selectedProvince.getProvinceName());
                } else if (currentLevel == LEVEL_CITY) {
                    try {
                        for (City city : cityList) {
                            if (city.getCityName().equals(filterList.get(position))) {
                                cityName = city.getCityName();
                                selectedCity = city;
                                break;
                            }
                        }
                    } catch (IndexOutOfBoundsException e) {
                        selectedCity = cityList.get(position);
                    } finally {
                        LogUtils.v( "selectedCity = " + selectedCity.getCityName());
                        filterList = new ArrayList<>();
                        presenter.queryCounties();
                    }

                } else if (currentLevel == LEVEL_COUNTY) {
                    String countyName = null;
                    try {
                        for (County county : countyList) {
                            if (county.getCountyName().equals(filterList.get(position))) {
                                countyName = county.getCountyName();
                                break;
                            }
                        }
                    } catch (IndexOutOfBoundsException e) {
                        countyName = countyList.get(position).getCountyName();
                    } finally {
                        LogUtils.d( "countyName = " + countyName);
                        Constants.isSetResultIntent = true;
                        mLocation.setCity(cityName);
                        mLocation.setCounty(countyName);
                        Intent intent = new Intent();
//                        intent.putExtra("countyName", countyName);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("location", mLocation);
                        intent.putExtras(bundle);
                        setResult(Constants.ChooseCode, intent);
//                        Toast.makeText(ChoosePositionActivity.this,
//                                getResources().getString(R.string.auto_locate_disabled), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });

        presenter.queryProvinces();
    }

    /**
     * 设置搜索框在Toolbar上
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        final MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        searchView.setInputType(InputType.TYPE_CLASS_TEXT);
        searchView.setQueryHint("地区名");
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        presenter.filterListData(dataList, newText);
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    /**
     * 捕获返回按键
     */
    @Override
    public void onBackPressed() {
        backListener();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            backListener();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void backListener() {
        Utility.closeSoftInput(ChoosePositionActivity.this); //关闭软键盘
        if (toolbar.getTitle().equals("中国"))
            finish();
        else if (currentLevel == LEVEL_COUNTY) {
            filterList = new ArrayList<>(); //清空筛选数据
            presenter.queryCities();
        } else if (currentLevel == LEVEL_CITY) {
            filterList = new ArrayList<>();
            presenter.queryProvinces();
        }
    }

    @Override
    public void showProgressDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (progressDialog == null) {
                    progressDialog = new ProgressDialog(ChoosePositionActivity.this);
                    progressDialog.setMessage("loading...");
                    progressDialog.setCanceledOnTouchOutside(false);
                }
                progressDialog.show();
            }
        });
    }

    @Override
    public void closeProgressDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }
            }
        });
    }

    @Override
    public void showToastMsg(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                closeProgressDialog();
                Toast.makeText(ChoosePositionActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setRecyclerViewData(final List<String> dataList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ChoosePositionActivity.this.dataList = dataList;
                adapter.setNewData(dataList); //给适配器设置新的数据
                linearLayoutManager.scrollToPosition(0);
            }
        });
    }

    @Override
    public void setToolBarTitle(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                toolbar.setTitle(str);
                setSupportActionBar(toolbar);
            }
        });
    }

    @Override
    public void setRecyclerViewFilterData(List<String> dataList) {
        this.filterList = dataList;
        adapter.setNewData(dataList);
        linearLayoutManager.scrollToPosition(0);
    }
}
