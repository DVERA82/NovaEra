package com.example.novaera

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewModelCellular (application: Application): AndroidViewModel(application) {

    private val repository: RepositoryCellular
    val cellularLiveDataFromDB: LiveData<List<Cellular>>

    init {

        val dao = CellularBD.getDaoCellular(application).getDaoCellular()
        repository= RepositoryCellular(dao)
        viewModelScope.launch {
            repository.getCellularWithCoroutines()
        }
        cellularLiveDataFromDB = repository.list
    }
    fun returnBindCellular(id:Int): LiveData<List<BindCellular>> = repository.getBindDB(id)

    fun getBindCellular(id: Int) = viewModelScope.launch {
        repository.getBindCellular(id)
    }

    fun updateFavouriteCellular(cellular: Cellular) = viewModelScope.launch {
        repository.updateFavouriteCellular(cellular)

    }
}