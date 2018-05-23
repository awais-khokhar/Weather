package top.maweihao.weather.util.http

enum class Status {
    SUCCESS,
    CACHE,
    ERROR,
    LOADING;

    /**
     * Returns `true` if the [Status] is loading else `false`.
     */
    fun isLoading() = this == LOADING
}