package fr.isen.ROMAN.androidtoolbox

import android.bluetooth.*
import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bledevice.*

class BLEDeviceActivity : AppCompatActivity() {

    private var bluetoothGatt: BluetoothGatt? = null
    private var TAG:String = "MyActivity"

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bledevice)

        val device: BluetoothDevice = intent.getParcelableExtra("ble_device")
        bluetoothGatt = device.connectGatt(this, false, gattCallback)
    }

    private val gattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
            when (newState) {
                BluetoothProfile.STATE_CONNECTED -> {
                    runOnUiThread{
                        statutDeviceTextView.text = STATE_CONNECTED
                    }
                    bluetoothGatt?.discoverServices()
                    Log.i(TAG, "Connected to GATT server")
                }
                BluetoothProfile.STATE_DISCONNECTED -> {
                    runOnUiThread {
                        statutDeviceTextView.text = STATE_DISCONNECTED
                    }
                    Log.i(TAG, "Disconnected to GATT server")
                }
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            super.onServicesDiscovered(gatt, status)

            runOnUiThread{
                BLEService.adapter = BLEServiceAdapter(gatt?.services?.map{
                    BLEService(it.uuid.toString(),it.characteristics)
                }?.toMutableList() ?: arrayListOf()
                )
            }
        }
    }


    companion object {
        private const val STATE_DISCONNECTED ="Déconnecté"
        private const val STATE_CONNECTED = "Connecté"
        private const val STATE_CONNECTING = 1
        const val ACTION_GATT_CONNECTED = "com.example.bluetooth.le.ACTION_GATT_CONNECTED"
        const val ACTION_GATT_DISCONNECTED = "com.example.bluetooth.le.ACTION_GATT_DISCONNECTED"
        const val ACTION_GATT_SERVICES_DISCOVERED = "com.example.bluetooth.le.ACTION_GATT_SERVICES_DISCOVERED"
        const val ACTION_DATA_AVAILABLE = "com.example.bluetooth.le.ACTION_DATA_AVAILABLE"
        const val EXTRA_DATA = "com.example.bluetooth.le.EXTRA_DATA"
    }

}