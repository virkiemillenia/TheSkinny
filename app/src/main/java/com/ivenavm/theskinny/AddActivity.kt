package com.ivenavm.theskinny

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddActivity : AppCompatActivity() {
    lateinit var btnSubmit: Button
    lateinit var btnCancel: Button
    lateinit var etName: EditText
    lateinit var etId: EditText
    lateinit var etJenis: EditText
    lateinit var etHarga: EditText
    lateinit var apiService: ServiceInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        declaration()
        myfunction()
    }

    fun declaration() {
        btnSubmit = findViewById(R.id.btn_add_submit)
        btnCancel = findViewById(R.id.btn_add_cancel)
        etId = findViewById(R.id.et_add_id)
        etName = findViewById(R.id.et_add_name)
        etJenis = findViewById(R.id.et_add_jenis)
        etHarga = findViewById(R.id.et_add_harga)
        apiService = Repository.getDataAPI().create(ServiceInterface::class.java)
    }

    fun myfunction() {
        val pindah = Intent(this, MainActivity::class.java)
        btnSubmit.setOnClickListener {
            val array = KontakData()
            array.Id = etId.text.toString().toInt()
            array.Nama = etName.text.toString()
            array.Jenis = etJenis.text.toString()
            array.Harga = etHarga.text.toString().toInt()
            apiService.postdata(array).enqueue(object : Callback<KontakData> {
                override fun onResponse(call: Call<KontakData>, response: Response<KontakData>) {
                    startActivity(Intent(this@AddActivity, DataActivity::class.java))
                    Toast.makeText(baseContext, "Add Data Success", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<KontakData>, t: Throwable) {
                    Toast.makeText(baseContext, "Add Data Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
        btnCancel.setOnClickListener {
            startActivity(pindah)
        }
    }
}