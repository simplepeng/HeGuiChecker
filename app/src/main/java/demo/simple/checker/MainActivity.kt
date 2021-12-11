package demo.simple.checker

import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.blankj.utilcode.util.PermissionUtils
import demo.simple.checker.databinding.ActivityMainBinding
import java.net.NetworkInterface

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(this.layoutInflater) }

    private val telephonyManager by lazy { getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager }

    private val wifiManager by lazy { applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    @SuppressLint("HardwareIds")
    fun getAndroidId(view: View) {
        val id: String? = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ANDROID_ID
        )
        showWarn(id)
    }

    private fun reqPermission(
        vararg permissions: String,
        onGranted: () -> Unit
    ) {
        PermissionUtils.permission(*permissions)
            .callback(object : PermissionUtils.SimpleCallback {

                @RequiresApi(Build.VERSION_CODES.M)
                @SuppressLint("HardwareIds")
                override fun onGranted() {
                    onGranted()
                }

                override fun onDenied() {
                }
            })
            .request()
    }

    private fun reqReadPhone(onGranted: () -> Unit) {
        PermissionUtils.permission(android.Manifest.permission.READ_PHONE_STATE)
            .callback(object : PermissionUtils.SimpleCallback {

                @RequiresApi(Build.VERSION_CODES.M)
                @SuppressLint("HardwareIds")
                override fun onGranted() {
                    onGranted()
                }

                override fun onDenied() {
                }
            })
            .request()
    }

    private fun reqWifi(onGranted: () -> Unit) {
        PermissionUtils.permission(android.Manifest.permission.ACCESS_WIFI_STATE)
            .callback(object : PermissionUtils.SimpleCallback {

                @RequiresApi(Build.VERSION_CODES.M)
                @SuppressLint("HardwareIds")
                override fun onGranted() {
                    onGranted()
                }

                override fun onDenied() {
                }
            })
            .request()
    }

    @SuppressLint("MissingPermission")
    fun getDeviceId(view: View) {
        reqReadPhone {
            val deviceId = telephonyManager.deviceId
            showWarn("deviceId = $deviceId")
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("MissingPermission")
    fun getDeviceIdInt(view: View) {
        reqReadPhone {
            val deviceId = telephonyManager.getDeviceId(TelephonyManager.PHONE_TYPE_GSM)
            showWarn("deviceId = $deviceId")
        }
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.O)
    fun getIMEI(view: View) {
        reqReadPhone {
            val imei = telephonyManager.imei
            showWarn("imei = $imei")
        }
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.O)
    fun getIMEIInt(view: View) {
        reqReadPhone {
            val imei = telephonyManager.getImei(TelephonyManager.PHONE_TYPE_GSM)
            showWarn("imei = $imei")
        }
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.O)
    fun getSubscriberId(view: View) {
        reqReadPhone {
            val subscriberId = telephonyManager.subscriberId
            showWarn("subscriberId = $subscriberId")
        }
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.O)
    fun getSubscriberIdInt(view: View) {
        reqReadPhone {
//            val imei = telephonyManager.getSubscriberId(telephonyManager.subscriberId)
//            showToast("imei = $imei")
        }
    }

    @SuppressLint("HardwareIds")
    fun getMacAddress(view: View) {
        reqWifi {
            val macAddress = wifiManager.connectionInfo.macAddress
            showWarn("macAddress = $macAddress")
        }
    }

    @SuppressLint("HardwareIds")
    fun getHardwareAddress(view: View) {
        reqPermission(
            android.Manifest.permission.READ_PHONE_STATE,
            android.Manifest.permission.ACCESS_WIFI_STATE,
            android.Manifest.permission.ACCESS_NETWORK_STATE
        ) {
            val networkInterfaces = NetworkInterface.getNetworkInterfaces() ?: return@reqPermission
            for (nf in networkInterfaces) {
                val hardwareAddress = nf.hardwareAddress
                showWarn("hardwareAddress = $hardwareAddress")
            }
        }
    }

    private fun showWarn(text: String? = "") {
        Log.d("MainActivity", text)
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}