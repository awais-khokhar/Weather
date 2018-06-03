package top.maweihao.weather.util.http

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import com.blankj.utilcode.util.LogUtils
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.flowable.FlowableCreate
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscription

/**
 * 网络帮助类
 * @param ResultType
 * @param RequestType
 * @property result MediatorLiveData<DataResult<ResultType>>
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
//    init {
//
//
//        if (this.shouldLoad4Cache()) {
//            val cacheData = this.load4Cache()
//
//            val c = MutableLiveData<ResultType>()
//            result.addSource(c) {
//                setValue(DataResult.cache(it))
//            }
//            c.value = cacheData
//            if (this.shouldFetch(cacheData)) {
//                fetchFromNetwork()
//            }
//
//        } else {
//            fetchFromNetwork()
//        }
//    }

    @MainThread
    private fun setValue(newValue: DataResult<ResultType>) {
        if (result.value != newValue) {
//            Handler(Looper.getMainLooper()).post {
            result.postValue(newValue)
            LogUtils.d("cache ok")
//            }
        }
    }

    private fun fetchFromNetwork(emitter: FlowableEmitter<DataResult<ResultType>>) {
//        setValue(DataResult.loading())

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

//        System.out.println("-------------------> fetchFromNetwork")
//        result.addSource(apiResponse) { response ->
//            result.removeSource(apiResponse)
//            when (response) {
//                is ApiSuccessResponse -> {
//                    ThreadUtils.executeByIo(object : ThreadUtils.SimpleTask<ResultType>() {
//                        override fun onSuccess(result: ResultType) {
//                            setValue(DataResult.success(result))
//                        }
//
//                        override fun doInBackground(): ResultType {
//                            return saveCallResultOrConvert(processResponse(response))
//                        }
//                    })
//
//                }
//                is ApiEmptyResponse   -> {
//                    ThreadUtils.executeByIo(object : ThreadUtils.SimpleTask<ResultType?>() {
//                        override fun onSuccess(result: ResultType?) {
//                            setValue(DataResult.cache(result))
//                        }
//
//                        override fun doInBackground(): ResultType? {
//                            return load4Cache()
//                        }
//                    })
//                }
//                is ApiErrorResponse   -> {
//                    val data = onFetchFailed()
//                    setValue(DataResult.error(response.errorMessage, data))
//
//                }
//            }
//        }
    }


    fun asFlowable(): Flowable<DataResult<ResultType>> {
        return FlowableCreate(flowableOnSubscribe, BackpressureStrategy.LATEST)
                .observeOn(AndroidSchedulers.mainThread())
    }

    protected open fun onFetchFailed(): ResultType? = null

    fun asLiveData() = result as LiveData<DataResult<ResultType>>

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