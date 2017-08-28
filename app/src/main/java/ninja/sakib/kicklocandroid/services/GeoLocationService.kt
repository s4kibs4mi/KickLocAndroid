package ninja.sakib.kicklocandroid.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import ninja.sakib.kicklocandroid.models.GeoLocation
import ninja.sakib.kicklocandroid.providers.GeoLocationProvider
import ninja.sakib.kicklocandroid.utils.logD
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class GeoLocationService : Service() {
    private lateinit var geolocationProvider: GeoLocationProvider

    init {
        if (EventBus.getDefault().isRegistered(this).not()) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        logD("Where", "ServiceHasBeenStarted")

        registerToLocationProvider()
        return START_STICKY
    }

    private fun registerToLocationProvider() {
        geolocationProvider = GeoLocationProvider(applicationContext)
    }

    @Subscribe
    fun onLocation(geoLocation: GeoLocation) {
        logD("OnNewLocation", geoLocation.toString())
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder? = null
}
