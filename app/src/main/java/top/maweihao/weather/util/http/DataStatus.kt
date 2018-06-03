package top.maweihao.weather.util.http

enum class DataStatus {
    SUCCESS,
    CACHE,
    LOADING;

    /**
     * Returns `true` if the [DataStatus] is loading else `false`.
     */
    fun isLoading() = this == LOADING
}