package com.ivenavm.theskinny

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KontakAdapter(private val listku: ArrayList<KontakData>): RecyclerView.Adapter<KontakAdapter.KontakViewHolder>(){
    inner class KontakViewHolder(viewku: View): RecyclerView.ViewHolder(viewku) {
        var tvId: TextView = viewku.findViewById(R.id.tv_id)
        var tvNama: TextView = viewku.findViewById(R.id.tv_nama)
        var tvJenis: TextView = viewku.findViewById(R.id.tv_jenis)
        var tvHarga: TextView = viewku.findViewById(R.id.tv_harga)
        var btnDelete: ImageButton = viewku.findViewById(R.id.btn_data_del)
        var btnUpdate: ImageButton = viewku.findViewById(R.id.btn_data_edit)
        var apiIterface: ServiceInterface? = Repository.getDataAPI().create(ServiceInterface::class.java)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KontakViewHolder {
        val viewView: View = LayoutInflater.from(parent.context).inflate(R.layout.activity_dataitem, parent, false)
        return KontakViewHolder(viewView)
    }

    override fun onBindViewHolder(holder: KontakViewHolder, position: Int) {
        val dataku = listku[position]
        holder.tvId.text = dataku.Id.toString()
        holder.tvNama.text = dataku.Nama
        holder.tvJenis.text = dataku.Jenis
        holder.tvHarga.text = dataku.Harga.toString()
        holder.btnUpdate.setOnClickListener {
            val bundle = Bundle()
            val pindah = Intent(holder.itemView.context, UpdateActivity::class.java)
            bundle.putString("id", dataku.Id.toString())
            bundle.putString("nama", dataku.Nama.toString())
            bundle.putString("jenis", dataku.Jenis.toString())
            bundle.putString("harga", dataku.Harga.toString())
            pindah.putExtras(bundle)
            holder.itemView.context.startActivity(pindah)
        }
        holder.btnDelete.setOnClickListener {
            holder.apiIterface!!.deletedata(dataku.Id!!).enqueue(object : Callback<KontakData> {
                override fun onResponse(call: Call<KontakData>, response: Response<KontakData>) {
                    if (response.isSuccessful){
                        listku.removeAt(position)
                        notifyDataSetChanged()
                        Toast.makeText(holder.itemView.context, "Delete Data Success", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<KontakData>, t: Throwable) {
                    Toast.makeText(holder.itemView.context, "Delete Data Failed", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

    override fun getItemCount(): Int {
        return listku.size
    }

}
