package com.example.novaera

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitCellular {
    companion object{
        private const val URL_BASE ="https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/products/ "


        fun retrofitInstance(): ApiCellular {
            val retrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return  retrofit.create(ApiCellular::class.java)

        }


    }
}