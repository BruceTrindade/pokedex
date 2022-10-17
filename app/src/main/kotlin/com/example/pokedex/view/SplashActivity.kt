package com.example.pokedex.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

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
