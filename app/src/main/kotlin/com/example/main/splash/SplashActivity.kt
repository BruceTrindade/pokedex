package com.example.main.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.main.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed(
            Runnable {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            },
            2000
        )
    }
}
