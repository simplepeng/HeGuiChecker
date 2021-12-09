package demo.simple.checker

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.telephony.TelephonyManager
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.blankj.utilcode.util.PermissionUtils
import demo.simple.checker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(this.layoutInflater) }

    private val telephonyManager by lazy { getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager }

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
        showToast(id)
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

    @SuppressLint("MissingPermission")
    fun getDeviceId(view: View) {
        reqReadPhone {
            val deviceId = telephonyManager.deviceId
            showToast("deviceId = $deviceId")
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("MissingPermission")
    fun getDeviceIdInt(view: View) {
        reqReadPhone {
            val deviceId = telephonyManager.getDeviceId(TelephonyManager.PHONE_TYPE_GSM)
            showToast("deviceId = $deviceId")
        }
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.O)
    fun getIMEI(view: View) {
        reqReadPhone {
            val imei = telephonyManager.imei
            showToast("imei = $imei")
        }
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.O)
    fun getIMEIInt(view: View) {
        reqReadPhone {
            val imei = telephonyManager.getImei(TelephonyManager.PHONE_TYPE_GSM)
            showToast("imei = $imei")
        }
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.O)
    fun getSubscriberId(view: View) {
        reqReadPhone {
            val subscriberId = telephonyManager.subscriberId
            showToast("subscriberId = $subscriberId")
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

    private fun showToast(text: String? = "") {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}