package ninja.sakib.kicklocandroid.utils

import android.app.ActivityManager
import android.content.Context
import android.provider.Settings
import android.util.Log

/**
 * := Coded with love by Sakib Sami on 8/28/17.
 * := s4kibs4mi@gmail.com
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

fun getMapDefaultZoomLevel() = 18

fun getMinimumLocationUpdateInterval(): Long = 10000     // In Milli Seconds

fun getMinimumLocationUpdateDistance() = 10f     // In Meter

fun getMqttServerUri() = "tcp://sakib.ninja:1883"

fun getDeviceId(context: Context): String =
        Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

fun getMqLocationEndpoint() = "/kickloc/locations"

fun logD(tag: String, message: String) {
    Log.d(tag, message)
}

fun isServiceRunning(clazz: Any, context: Context): Boolean {
    val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    return activityManager.getRunningServices(Int.MAX_VALUE).any {
        it.service.className == clazz.javaClass.name
    }
}
