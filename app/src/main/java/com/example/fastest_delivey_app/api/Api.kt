package com.example.fastest_delivey_app.api

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @GET("/Menu")
    suspend fun getMenu():Menu

    @POST("/Registration")
    suspend fun registration(
        @Body body:RegisrtData
    ):Response<Unit?>

    @POST("/Authorization")
    suspend fun auth(
        @Body body:AuthData
    ):AuhtResult
}

fun createRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl("http://10.0.2.2:5000")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun createApi(retrofit: Retrofit = createRetrofit()):Api = retrofit.create<Api>()

data class Menu(
    val id:Int,
    val foods:List<MenuItem>,
    val drinks:List<MenuItem>,
    val snacks:List<MenuItem>,
    val sauces:List<MenuItem>
)

data class MenuItem(
    val id:Int,
    val name:String,
    val price:Int,
    val description:String,
    val photo:String
)

data class RegisrtData(
    val email:String,
    val fio:String,
    val phone:Int,
    val password:String
)

data class AuthData(
    val email:String,
    val password:String
)

data class AuhtResult(
    val access_token:String
)