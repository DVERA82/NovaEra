package com.example.novaera

import android.util.Log
import androidx.lifecycle.LiveData

class RepositoryCellular(private val dao: DaoCellular) {
    val list: LiveData<List<Cellular>> = dao.getAllCellular()

    fun converter(converter: List<DataClassCellular>): List<Cellular> {
        val listCellular = mutableListOf<Cellular>()
        converter.map {
            listCellular.add(Cellular(
                    id = it.id, name = it.name, price = it.price, image = it.image))
        }
        return listCellular
    }

    fun converterBindCellular(id: Int,name:String,price:Int,image:String,description: String,
                             lastPrice:Int,credit: Boolean): List<BindCellular> {
        val listBindCellular:MutableList<BindCellular>  = mutableListOf()
        listBindCellular.add(
            BindCellular(
                id = id,
                name= name,
                price= price,
                image = image,
                description= description,
                lastPrice= lastPrice,
                credit= credit))

        return listBindCellular
    }
    suspend fun getCellularWithCoroutines() {
        Log.d("REPOSITORY", "UTILIZANDO COROUTINES")
        try {
            val response = RetrofitCellular.retrofitInstance().fetchCellular()
            when (response.isSuccessful) {
                true -> response.body()?.let {
                    dao.InsertAllDaoCellular(it)
                }

                false -> Log.d("ERROR", " ${response.code()} : ${response.errorBody()}")
            }

        } catch (t: Throwable) {
            Log.e("ERROR COROUTINA 1", t.message.toString())
        }

    }

    suspend fun getBindCellular(id: Int) {

        try {
            val response = RetrofitCellular.retrofitInstance().fetchBindCellular(id)
            when (response.isSuccessful) {
                true -> response.body()?.let {
                    dao.InsertBindCellular(converterBindCellular(id,it.name,it.price,it.image,
                        it.description,it.lastPrice, it.credit))
                }
                false -> Log.d("ERROR", " ${response.code()} : ${response.errorBody()}")
            }

        } catch (t: Throwable) {
            Log.e("ERROR COROUTINA 2", t.message.toString())
        }


    }

    fun getBindDB (id: Int): LiveData<List<BindCellular>> = dao.getBindCellular(id)



}