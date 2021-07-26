package com.ivenavm.theskinny

import retrofit2.Call
import retrofit2.http.*

interface ServiceInterface {
    @GET("Dataskincare")
    fun getData(): Call<List<KontakData>>

    @POST("Dataskincare")
    fun postdata(@Body KontakData: KontakData): Call<KontakData>

    @FormUrlEncoded
    @HTTP(method="PUT", path="Dataskincare", hasBody = true)
    fun updatedata(
            @Field("id") id: Int,
            @Field("nama") nama: String,
            @Field("jenis") jenis: String,
            @Field("harga") harga: Int): Call<KontakData>

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "Dataskincare ", hasBody = true)
    fun deletedata(@Field("id") id: Int): Call<KontakData>
}