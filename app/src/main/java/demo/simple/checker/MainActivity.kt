package demo.simple.checker

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import demo.simple.checker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(this.layoutInflater) }

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

    private fun showToast(text: String? = "") {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}