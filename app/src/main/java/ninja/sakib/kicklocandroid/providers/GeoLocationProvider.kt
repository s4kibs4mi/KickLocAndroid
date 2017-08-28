package ninja.sakib.kicklocandroid.providers

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import ninja.sakib.kicklocandroid.models.GeoLocation
import ninja.sakib.kicklocandroid.utils.getMinimumLocationUpdateDistance
import ninja.sakib.kicklocandroid.utils.getMinimumLocationUpdateInterval
import ninja.sakib.kicklocandroid.utils.logD
import org.greenrobot.eventbus.EventBus

/**
 * := Coded with love by Sakib Sami on 8/28/17.
 * := s4kibs4mi@gmail.com
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

class GeoLocationProvider constructor(context: Context) : LocationListener {
    private var locationManager: LocationManager

    init {
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, getMinimumLocationUpdateInterval(),
                getMinimumLocationUpdateDistance(), this)
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

    }

    override fun onProviderEnabled(p0: String?) {

    }

    override fun onProviderDisabled(p0: String?) {

    }

    override fun onLocationChanged(p0: Location?) {
        if (p0 != null) {
            val geoLocation = GeoLocation()
            geoLocation.latitude = p0.latitude
            geoLocation.longitude = p0.longitude

            EventBus.getDefault().post(geoLocation)
            logD("OnLocationUpdate", geoLocation.toString())
        }
    }
}
