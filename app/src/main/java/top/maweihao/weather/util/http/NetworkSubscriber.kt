package top.maweihao.weather.util.http

import io.reactivex.subscribers.DefaultSubscriber

abstract class NetworkSubscriber<T> : DefaultSubscriber<DataResult<T>>() {


    override fun onComplete() {
    }

    override fun onNext(data: DataResult<T>?) {
        data?.let {
            when (data.dataStatus) {
                DataStatus.SUCCESS -> onSuccess(it.data!!, false)
                DataStatus.CACHE   -> it.data?.let { onSuccess(it, true) }
                DataStatus.LOADING -> onLoading()
            }
        }
    }

    override fun onError(e: Throwable?) {
        onNetError(e?.message)
    }

    abstract fun onSuccess(data: T, isDbCache: Boolean)

    abstract fun onNetError(msg: String?)

    open fun onLoading() {}
}