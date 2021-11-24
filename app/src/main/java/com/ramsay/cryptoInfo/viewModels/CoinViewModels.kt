package com.ramsay.cryptoInfo.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramsay.cryptoInfo.models.local.Coins
import com.ramsay.cryptoInfo.repository.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoinViewModels @Inject constructor( val coinRepository: CoinRepository):ViewModel() {


    fun getCoinFromApi(){
        coinRepository.getRemoteCoin()
    }
    fun getCoinFromDB(): LiveData<List<Coins>> {
      return coinRepository.getAllCoins()
    }

    fun getFavCoinFromDB(): LiveData<List<Coins>> {
      return coinRepository.getFavCoins()
    }
    fun getSearchedCoin() :LiveData<List<Coins>>{
        return coinRepository.getSearchedCoin()
    }

    fun addSearchedCoin(coin:Coins){
        viewModelScope.launch(Dispatchers.IO) {
            coinRepository.addSearchedItemToDB(coin)
        }
    }
}