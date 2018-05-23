package top.maweihao.weather.util.http

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

abstract class NetworkBoundResource<ResultType, RequestType>
@MainThread constructor() {

    private val result = MediatorLiveData<DataResult<ResultType>>()

    init {


        @Suppress("LeakingThis")
//        val dbSource = load4Db()
////        fetchFromNetwork(dbSource)
//        result.addSource(load4Db()) { data ->
//            result.removeSource(dbSource)
//            if (shouldFetch(data)) {
//                fetchFromNetwork(dbSource)
//            } else {
//                result.addSource(dbSource) { newData ->
//                    setValue(Resource.success(newData))
//                }
//            }
//        }

        if (shouldLoad4Cache()) {
            System.out.println("-------------------> shouldLoad4Cache")
//            async(UI) {
//                System.out.println("-------------------> async")
//                val dbSource = load4Cache()
//                result.addSource(dbSource) { data ->
//                    result.removeSource(dbSource)
//                    System.out.println("-------------------> addSource")
//                    if (shouldFetch(data)) {
//                        System.out.println("-------------------> fetchFromNetwork")
//                        fetchFromNetwork(dbSource)
//                    } else {
//                        result.addSource(load4Cache()) { newData ->
//                            setValue(DataResult.success(newData))
//                        }
//                    }
//                }

//            Thread(Runnable {
//                val cacheData =  load4Cache()
//                Handler(Looper.getMainLooper()).post {
//
//                    if (shouldFetch(cacheData)) {
//                        setValue(DataResult.success(cacheData))
//                        fetchFromNetwork()
//                    } else {
//                        setValue(DataResult.success(cacheData))
////                    result.addSource(dbSource) { newData ->
////                        setValue(DataResult.success(newData))
////                    }
//                    }
//                }
//            })
            val cacheData = load4Cache()
            if (shouldFetch(cacheData)) {
//                setValue(DataResult.success(cacheData))
                fetchFromNetwork()
            } else {
                setValue(DataResult.cache(cacheData))
//                    result.addSource(dbSource) { newData ->
//                        setValue(DataResult.success(newData))
//                    }
            }

//            }
        } else {
//            async(UI){
//
//                Handler(Looper.getMainLooper()).post { fetchFromNetwork() }
//                fetchFromNetwork()
//
            fetchFromNetwork()
        }


    }

    @MainThread
    private fun setValue(newValue: DataResult<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork() {
        result.value = DataResult.loading()
        val apiResponse = createCall()
        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
//        result.addSource(dbSource) { newData ->
//            setValue(DataResult.loading(newData))
//        }
        System.out.println("-------------------> fetchFromNetwork")
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            System.out.println("-------------------> addSource")
            when (response) {
                is ApiSuccessResponse -> {
                    async(UI) {
                        System.out.println("-------------------> ApiSuccessResponse")
                        val data = bg { saveCallResultOrConvert(processResponse(response)) }.await()
                        setValue(DataResult.success(data))
//                        result.addSource(load4Cache()) { newData ->
//                            setValue(DataResult.success(newData))
//                        }
                    }
//                    val data = saveCallResultOrConvert(processResponse(response))
//                    setValue(DataResult.success(data))
//                    appExecutors.diskIO().execute {

//                    saveCallResultOrConvert(processResponse(response))

//                    setValue(Resource.success(response.body))
//                        appExecutors.mainThread().execute {
//                            // we specially request a new live data,
//                            // otherwise we will get immediately last cached value,
//                            // which may not be updated with latest results received from network.
//                    result.addSource(load4Cache()) { newData ->
//                        setValue(DataResult.success(newData))
//                    }
//                        }
//                    }
                }
                is ApiEmptyResponse   -> {
                    System.out.println("-------------------> ApiEmptyResponse")
//                    appExecutors.mainThread().execute {
//                        // reload from disk whatever we had

//                    result.addSource(load4Cache()) { newData ->
//                        setValue(DataResult.success(newData))
//                    }
                    async(UI) {
                        val data = bg { load4Cache() }.await()
                        setValue(DataResult.cache(data))
                    }

//                    }
                }
                is ApiErrorResponse   -> {
                    System.out.println("-------------------> ApiErrorResponse")
                    val data = onFetchFailed()
                    setValue(DataResult.error(response.errorMessage, data))
//                    result.addSource(dbSource) { newData ->
//                        setValue(DataResult.error(response.errorMessage))
//                    }
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

    @MainThread
    protected abstract fun shouldLoad4Cache(): Boolean

    @MainThread
    protected abstract fun load4Cache(): ResultType?

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}