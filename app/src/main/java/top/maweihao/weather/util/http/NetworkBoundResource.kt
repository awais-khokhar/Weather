package top.maweihao.weather.util.http

import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.flowable.FlowableCreate
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscription

/**
 * 网络帮助类
 * @param ResultType 需要返回的数据类型
 * @param RequestType 网络请求回来的数据类型
 */
abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = MediatorLiveData<DataResult<ResultType>>()

    private val flowableOnSubscribe: FlowableOnSubscribe<DataResult<ResultType>>

    init {
        flowableOnSubscribe = if (this.shouldLoad4Cache()) {
            FlowableOnSubscribe { emitter ->
                val cacheData = this.load4Cache()
                emitter.onNext(DataResult.cache(cacheData))

                if (this.shouldFetch(cacheData)) {
                    fetchFromNetwork(emitter)
                }
            }

        } else {
            FlowableOnSubscribe { emitter ->
                fetchFromNetwork(emitter)
            }
        }
    }

    private fun fetchFromNetwork(emitter: FlowableEmitter<DataResult<ResultType>>) {
        emitter.onNext(DataResult.loading())

        val flowable = createCall()

        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : FlowableSubscriber<RequestType> {
                    override fun onComplete() {
                        emitter.onComplete()
                    }

                    override fun onSubscribe(s: Subscription) {
                        s.request(Long.MAX_VALUE)
                    }

                    override fun onNext(t: RequestType) {

                        val res = saveCallResultOrConvert(t)
                        emitter.onNext(DataResult.success(res))
                    }

                    override fun onError(t: Throwable?) {
                       t?.let { emitter.onError(it) }
                    }
                })
    }


    fun asFlowable(): Flowable<DataResult<ResultType>> {
        return FlowableCreate(flowableOnSubscribe, BackpressureStrategy.LATEST)
                .observeOn(AndroidSchedulers.mainThread())
    }

    protected open fun onFetchFailed(): ResultType? = null

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    @WorkerThread
    protected abstract fun saveCallResultOrConvert(item: RequestType): ResultType

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    /**
     * 是否需要从缓存加载数据
     * @return Boolean false时，load4Cache()可以为null
     */
    @MainThread
    protected abstract fun shouldLoad4Cache(): Boolean

    @MainThread
    protected abstract fun load4Cache(): ResultType?

    @MainThread
    protected abstract fun createCall(): Flowable<RequestType>
}