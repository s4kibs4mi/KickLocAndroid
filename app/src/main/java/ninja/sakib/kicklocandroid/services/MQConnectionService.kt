package ninja.sakib.kicklocandroid.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.eclipsesource.json.JsonObject
import ninja.sakib.kicklocandroid.models.GeoLocation
import ninja.sakib.kicklocandroid.utils.getDeviceId
import ninja.sakib.kicklocandroid.utils.getMqttServerUri
import ninja.sakib.kicklocandroid.utils.logD
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttAsyncClient
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import kotlin.concurrent.thread

class MQConnectionService : Service(), MqttCallback {
    private lateinit var mqttClient: MqttAsyncClient

    init {
        if (EventBus.getDefault().isRegistered(this).not()) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        connectToServer()
        return START_STICKY
    }

    private fun connectToServer() {
        try {
            thread(start = true) {
                mqttClient = MqttAsyncClient(getMqttServerUri(), MqttAsyncClient.generateClientId(), MemoryPersistence())
                mqttClient.connect()
            }
        } catch (ex: Exception) {
            logD("MQConnection", ex.message!!)
        }
    }

    private fun reconnectToServer() {
        try {
            mqttClient.reconnect()
        } catch (ex: Exception) {
            logD("MQReConnection", ex.message!!)
        }
    }

    override fun messageArrived(topic: String?, message: MqttMessage?) {

    }

    override fun connectionLost(cause: Throwable?) {
        thread(start = true) {
            Thread.sleep(5000)
            reconnectToServer()
        }
    }

    @Subscribe
    fun onLocation(geoLocation: GeoLocation) {
        logD("OnNewLocation", geoLocation.toString())

        val locationPack = JsonObject()
                .add("latitude", geoLocation.latitude)
                .add("longitude", geoLocation.longitude)
                .add("deviceId", getDeviceId())

        if (isClientConnected()) {
            mqttClient.publish("", locationPack.toString().toByteArray(), 2, false)
        }
    }

    private fun isClientConnected(): Boolean {
        return true
    }

    override fun deliveryComplete(token: IMqttDeliveryToken?) {

    }

    override fun onBind(intent: Intent): IBinder? = null
}
