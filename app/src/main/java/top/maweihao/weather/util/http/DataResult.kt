package top.maweihao.weather.util.http

data class DataResult<ResultType>(var dataStatus: DataStatus, var data: ResultType? = null, var message: String? = null) {

    companion object {
        /**
         * Creates [DataResult] object with `SUCCESS` status and [data].
         */
        fun <ResultType> success(data: ResultType): DataResult<ResultType> = DataResult(DataStatus.SUCCESS, data)

        /**
         * Creates [DataResult] object with `LOADING` status to notify
         * the UI to showing loading.
         */
        fun <ResultType> loading(data: ResultType? = null): DataResult<ResultType> = DataResult(DataStatus.LOADING, data, null)

        /**
         * Creates [DataResult] object with `ERROR` status and [message].
         */
//        fun <ResultType> error(message: String?, data: ResultType? = null): DataResult<ResultType> = DataResult(Status.ERROR, data, message)

        fun <ResultType> cache(data: ResultType?): DataResult<ResultType> = DataResult(DataStatus.CACHE, data)

    }
}

