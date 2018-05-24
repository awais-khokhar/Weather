package top.maweihao.weather.view

import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

abstract class BaseActivity : RxAppCompatActivity() {

    protected val TAG = this.javaClass.simpleName

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(setContentView())

        initView(savedInstanceState)
        initData()
    }

    /**
     * 子类设置界面
     */
    protected abstract fun setContentView(): Int

    protected abstract fun initView(savedInstanceState: Bundle?)

    protected abstract fun initData()

}
