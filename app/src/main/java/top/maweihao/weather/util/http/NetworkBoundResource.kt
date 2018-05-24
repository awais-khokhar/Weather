package top.maweihao.weather.util.http

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.os.Handler
import android.os.Looper
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import kotlin.concurrent.thread

/**
 * 网络帮助类
 * @param ResultType
 * @param RequestType
 * @property result MediatorLiveData<DataResult<ResultType>>
 */
abstract class NetworkBoundResource<ResultType, RequestType>
@MainThread constructor() {

    private val result = MediatorLiveData<DataResult<ResultType>>()

    init {
        if (this.shouldLoad4Cache()) {
            val cacheData = this.load4Cache()
            setValue(DataResult.cache(cacheData))
            if (this.shouldFetch(cacheData)) {
                fetchFromNetwork()
            }

        } else {
            fetchFromNetwork()
        }
    }

    @MainThread
    private fun setValue(newValue: DataResult<ResultType>) {
        if (result.value != newValue) {
            Handler(Looper.getMainLooper()).post {
                result.value = newValue
            }
        }
    }

    private fun fetchFromNetwork() {
        setValue(DataResult.loading())

        val apiResponse = createCall()

        System.out.println("-------------------> fetchFromNetwork")
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            when (response) {
                is ApiSuccessResponse -> {

                    thread {
                        val data = saveCallResultOrConvert(processResponse(response))
                        setValue(DataResult.success(data))
                    }

                }
                is ApiEmptyResponse   -> {

                    thread {
                        val data = load4Cache()
                        setValue(DataResult.cache(data))
                    }

                }
                is ApiErrorResponse   -> {
                    val data = onFetchFailed()
                    setValue(DataResult.error(response.errorMessage, data))

                }
            }
        }
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
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}