# KickLocAndroid
##### KickLocAndroid is a location sharing application using MQTT, Android Location API and OpenStreetMap.

![Screenshot](https://raw.githubusercontent.com/s4kibs4mi/KickLocAndroid/master/app/src/main/res/extra/ScreenShot1.JPG)

#### To Test
1. Install the app
2. Turn on GPS
3. Connect to host = sakib.ninja, port = 1883 from any MQTT client
4. Subscribe to `/kickloc/locations`
5. You will receive json formatted message
```json
{
  "latitude": 23.756262497875795,
  "longitude": 90.36661509693545,
  "deviceId": "73c1088d5e06f6b0"
}
```
