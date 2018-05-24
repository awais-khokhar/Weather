package top.maweihao.weather.util

import android.content.Context
import org.jetbrains.anko.toast
import top.maweihao.weather.BuildConfig


fun Context.debugToast(message: CharSequence) {
    if (BuildConfig.DEBUG) {
        toast(message)
    }
}
