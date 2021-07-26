package com.ivenavm.theskinny

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Callback
import retrofit2.Response

class DataActivity : AppCompatActivity() {
    lateinit var rvdata: RecyclerView
    lateinit var apiService: ServiceInterface
    private var  ambilData: ArrayList<KontakData> = arrayListOf()
    lateinit var btnadd: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        rvdata = findViewById(R.id.rv_data)
        btnadd = findViewById(R.id.btn_main_add)
        btnadd.setOnClickListener {
            val pindah = Intent(this, AddActivity::class.java)
            startActivity(pindah)
        }

        apiService = Repository.getDataAPI().create(ServiceInterface::class.java)
        apiService.getData().enqueue(object : Callback<List<KontakData>> {
            override fun onResponse(
                    call: retrofit2.Call<List<KontakData>>,
                    response: Response<List<KontakData>>
            ) {
                if (response.isSuccessful){
                    val res = response.body()
                    ambilData.addAll(res!!)
                    rvdata.layoutManager = LinearLayoutManager(this@DataActivity)
                    rvdata.adapter = KontakAdapter(ambilData)
                }
            }
            override fun onFailure(call: retrofit2.Call<List<KontakData>>, t: Throwable) {
            }
        })

    }
}
