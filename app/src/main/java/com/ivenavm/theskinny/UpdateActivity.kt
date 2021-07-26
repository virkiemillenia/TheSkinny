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

class UpdateActivity : AppCompatActivity() {
    lateinit var btnSubmit: Button
    lateinit var btnCancel: Button
    lateinit var etName: EditText
    lateinit var etJenis: EditText
    lateinit var etHarga: EditText
    lateinit var valName: String
    lateinit var valJenis: String
    lateinit var valHarga: String
    lateinit var valId: String
    lateinit var apiService: ServiceInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        getMyData()
        declaration()
        myfunction()
    }

    fun declaration() {
        btnSubmit = findViewById(R.id.btn_up_submit)
        btnCancel = findViewById(R.id.btn_up_cancel)
        etName = findViewById(R.id.et_up_name)
        etJenis = findViewById(R.id.et_up_jenis)
        etHarga = findViewById(R.id.et_up_harga)
        apiService = Repository.getDataAPI().create(ServiceInterface::class.java)
    }

    fun getMyData() {
        val myValue = intent.extras
        if (myValue != null) {
            valName = myValue.getString("nama").toString()
            valJenis = myValue.getString("jenis").toString()
            valHarga = myValue.getString("harga").toString()
            valId = myValue.getString("id").toString()
        }
    }

    fun myfunction() {
        etName.setText(valName)
        etJenis.setText(valJenis)
        etHarga.setText(valHarga)
        val pindah = Intent(this, DataActivity::class.java)
        btnSubmit.setOnClickListener {
            apiService.updatedata(
                    valId.toInt(),
                    etName.text.toString(),
                    etJenis.text.toString(),
                    valHarga.toInt()
            ).enqueue(object : Callback<KontakData> {
                override fun onResponse(call: Call<KontakData>, response: Response<KontakData>) {
                    startActivity(pindah)
                    Toast.makeText(baseContext, "Update Data Success", Toast.LENGTH_SHORT).show()
                }
                override fun onFailure(call: Call<KontakData>, t: Throwable) {
                    Toast.makeText(baseContext, "Update Data Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
        btnCancel.setOnClickListener {
            startActivity(pindah)
        }
    }
}