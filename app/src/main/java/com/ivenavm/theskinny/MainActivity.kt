package com.ivenavm.theskinny

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var Data: TextView
    lateinit var Review: TextView
    lateinit var About: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Data = findViewById(R.id.data)
        Review = findViewById(R.id.review)
        About = findViewById(R.id.about)

        Data.setOnClickListener {
            val pindah = Intent(this, DataActivity::class.java)
            startActivity(pindah)
        }
        Review.setOnClickListener {
            val pindah = Intent(this, ReviewActivity::class.java)
            startActivity(pindah)
        }
        About.setOnClickListener {
            val pindah = Intent(this, AboutActivity::class.java)
            startActivity(pindah)
        }
    }
}