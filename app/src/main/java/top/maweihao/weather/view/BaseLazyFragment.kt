package top.maweihao.weather.view

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by limuyang on 17-8-7.
 */
abstract class BaseLazyFragment : Fragment() {
    protected var isInit = false
    protected var isLoad = false
    protected var isUserVisible = false

    protected val classTag: String = javaClass.simpleName

    //获取布局文件ID
    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun initViewProperty(view: View, savedInstanceState: Bundle?)

    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    protected abstract fun lazyLoad()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val rootView = inflater.inflate(getLayoutId(), container, false)
        initViewProperty(rootView, savedInstanceState)
        isInit = true
        /**初始化的时候去加载数据**/
        isCanLoadData()

        return rootView
    }

    override fun onDestroyView() {
        isInit = false
        isLoad = false
        super.onDestroyView()
    }

    /**
     * 视图是否已经对用户可见
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        isUserVisible = isVisibleToUser
        isCanLoadData()
    }

    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     * 3.数据从未加载过
     */
    private fun isCanLoadData() {
        if (!isInit) {
            return
        }

        if (userVisibleHint && !isLoad) {
            lazyLoad()
            isLoad = true
        }
    }


}