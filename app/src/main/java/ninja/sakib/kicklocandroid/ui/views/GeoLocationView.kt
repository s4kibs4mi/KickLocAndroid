package ninja.sakib.kicklocandroid.ui.views

import com.arellomobile.mvp.MvpView
import ninja.sakib.kicklocandroid.models.GeoLocation

/**
 * := Coded with love by Sakib Sami on 8/28/17.
 * := s4kibs4mi@gmail.com
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

interface GeoLocationView : MvpView {
    fun onLocation(geoLocation: GeoLocation)
}
