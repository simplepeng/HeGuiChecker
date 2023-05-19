package demo.simple.checker

import android.Manifest
import android.annotation.SuppressLint
import android.content.ClipboardManager
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
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
import me.simple.checker.HeGuiChecker
import java.net.NetworkInterface

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(this.layoutInflater) }

    private val telephonyManager by lazy { getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager }

    private val wifiManager by lazy { applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager }

    private val locationManager by lazy { applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager }

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
                    showWarn("授权失败")
                }
            })
            .request()
    }

    private fun reqReadPhone(onGranted: () -> Unit) {
        PermissionUtils.permission(Manifest.permission.READ_PHONE_STATE)
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
        PermissionUtils.permission(Manifest.permission.ACCESS_WIFI_STATE)
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
        reqPermission(
            Manifest.permission.READ_PHONE_STATE,
//            Manifest.permission.READ_PRIVILEGED_PHONE_STATE,
        ) {
            try {
                val deviceId = telephonyManager.deviceId
                showWarn("deviceId = $deviceId")
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("MissingPermission")
    fun getDeviceIdInt(view: View) {
        reqReadPhone {
            try {
                val deviceId = telephonyManager.getDeviceId(TelephonyManager.PHONE_TYPE_GSM)
                showWarn("deviceId = $deviceId")
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.O)
    fun getIMEI(view: View) {
        reqReadPhone {
            try {
                val imei = telephonyManager.imei
                showWarn("imei = $imei")
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.O)
    fun getIMEIInt(view: View) {
        reqReadPhone {
            try {
                val imei = telephonyManager.getImei(TelephonyManager.PHONE_TYPE_GSM)
                showWarn("imei = $imei")
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.O)
    fun getSubscriberId(view: View) {
        reqReadPhone {
            try {
                val subscriberId = telephonyManager.subscriberId
                showWarn("subscriberId = $subscriberId")
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.O)
    fun getSubscriberIdInt(view: View) {
        reqReadPhone {
            try {
//                val imei = telephonyManager.getSubscriberId(telephonyManager.subscriberId)
//                showWarn("subscriberId = $subscriberId")
            } catch (e: Throwable) {
                e.printStackTrace()
            }

        }
    }

    @SuppressLint("HardwareIds", "MissingPermission")
    fun getMacAddress(view: View) {
        reqWifi {
            reqPermission(Manifest.permission.ACCESS_FINE_LOCATION) {
                val macAddress = wifiManager.connectionInfo.macAddress
                showWarn("macAddress = $macAddress")
            }
        }
    }

    fun getSSID(view: View) {
        reqWifi {
            reqPermission(Manifest.permission.ACCESS_FINE_LOCATION) {
                val ssid = wifiManager.connectionInfo.ssid
                showWarn("ssid = $ssid")
            }
        }
    }

    @SuppressLint("HardwareIds")
    fun getHardwareAddress(view: View) {
        reqPermission(
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE
        ) {
            val networkInterfaces = NetworkInterface.getNetworkInterfaces() ?: return@reqPermission
            for (nf in networkInterfaces) {
                val hardwareAddress = nf.hardwareAddress
                showWarn("hardwareAddress = $hardwareAddress")
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun getLastKnownLocation(view: View) {
        reqPermission(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) {
            val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                ?: return@reqPermission
            showWarn("location = ${location.latitude} -- ${location.longitude}")
        }
    }

    @SuppressLint("MissingPermission")
    fun requestLocationUpdates(view: View) {
        reqPermission(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) {
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                2000,
                1f * 1000,
                object : LocationListener {
                    override fun onLocationChanged(location: Location) {
                        Log.d("MainActivity", "onLocationChanged")
                    }

                    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                    }

                    override fun onProviderEnabled(provider: String) {
                    }

                    override fun onProviderDisabled(provider: String) {
                    }
                })
        }
    }

    fun getInstalledPackages(view: View) {
        packageManager.getInstalledPackages(0)
    }

    fun getInstalledApplications(view: View) {
        packageManager.getInstalledApplications(0)
    }

    fun getInstallerPackageName(view: View) {
        packageManager.getInstallerPackageName("com.tencent.mm")
    }

    fun getPackageInfo(view: View) {
        packageManager.getPackageInfo("com.tencent.mm", 0)
    }

    private fun showWarn(text: String? = "") {
        Log.d("MainActivity", text!!)
        Toast.makeText(this.applicationContext, text, Toast.LENGTH_SHORT).show()
    }

    fun allowPolicy(view: View) {
        HeGuiChecker.allow(true)
    }

    private val clipboardManager by lazy { getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager }

    fun hasPrimaryClip(view: View) {
        clipboardManager.hasPrimaryClip()
    }

    fun getPrimaryClip(view: View) {
        clipboardManager.primaryClip
    }
}