package top.maweihao.weather.view;

import android.os.Bundle;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends RxAppCompatActivity {

//    protected final String TAG = this.getClass().getSimpleName();

    private Unbinder bind;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(setContentView());
        bind = ButterKnife.bind(this);

        initView(savedInstanceState);
    }

    /**
     * 子类设置界面
     */
    protected abstract int setContentView();

    protected abstract void initView(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
