package ninja.sakib.kicklocandroid.models

/**
 * := Coded with love by Sakib Sami on 8/28/17.
 * := s4kibs4mi@gmail.com
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

class GeoLocation constructor(var latitude: Double = 23.756, var longitude: Double = 90.370) {
    override fun toString(): String = "Lat = $latitude, Lon = $longitude"
}
