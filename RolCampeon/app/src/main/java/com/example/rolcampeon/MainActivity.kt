package com.example.rolcampeon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnRoles = findViewById<Button>(R.id.btnRoles)
        btnRoles.setOnClickListener {
            val intent = Intent(this, RolesActivity::class.java)
            startActivity(intent)
        }
        val btnChamps =findViewById<Button>(R.id.btnCampeones)
        btnChamps.setOnClickListener {
            val intent = Intent(this, ChampsActivity::class.java)

            startActivity(intent)
        }


    }
}
