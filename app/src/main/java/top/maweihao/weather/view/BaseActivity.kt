package top.maweihao.weather.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    protected val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
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
