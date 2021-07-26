package com.ivenavm.theskinny

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ReviewActivity : AppCompatActivity() {
    lateinit var pyunkang: TextView
    lateinit var avoskin: TextView
    lateinit var npure: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        pyunkang = findViewById(R.id.pyunkang)
        avoskin = findViewById(R.id.avoskin)
        npure = findViewById(R.id.npure)

        pyunkang.setOnClickListener {
            val pindah = Intent(this, PyunkangActivity::class.java)
            startActivity(pindah)
        }
        avoskin.setOnClickListener {
            val pindah = Intent(this, AvoskinActivity::class.java)
            startActivity(pindah)
        }
        npure.setOnClickListener {
            val pindah = Intent(this, NpureActivity::class.java)
            startActivity(pindah)
        }
    }
}