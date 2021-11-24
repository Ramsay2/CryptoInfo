package com.ramsay.cryptoInfo.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.ramsay.cryptoInfo.models.ApiService
import com.ramsay.cryptoInfo.models.Network
import com.ramsay.cryptoInfo.models.local.Coins
import com.ramsay.cryptoInfo.models.local.CoinsDAO
import com.ramsay.cryptoInfo.models.response.CoinInfoDTO

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import io.reactivex.rxjava3.schedulers.Schedulers


class CoinRepository @Inject constructor(val coinDao: CoinsDAO) {



    private var apiService: ApiService = Network.instance().create(ApiService::class.java)


    fun getRemoteCoin() {

        Observable.timer(5000, TimeUnit.MILLISECONDS)
            .repeat() //to perform your task every 5 seconds
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {

                CoroutineScope(Dispatchers.IO).launch {
                    val response = apiService.get()
                    Log.d("TAG000", "response1")
                    saveDataToDb(response)
                }
            }
    }

    private fun saveDataToDb(coinInfoDTO: CoinInfoDTO) {
        val coinList = ArrayList<Coins>()
        coinInfoDTO.forEach {
            val coin = Coins(
                it.name,
                it.currentPrice,
                it.fullyDilutedValuation,
                it.ath,
                it.athDate,
                it.atl,
                it.atlChangePercentage,
                it.atlDate,
                it.lastUpdated,
                it.image,
                it.id,
                it.low24h,
                it.marketCap,
                it.marketCapChange24h,
                it.marketCapRank,
                it.maxSupply,
                it.priceChange24h,
                it.priceChangePercentage24h,
                it.symbol,
                it.totalSupply,
                it.totalVolume,
                it.high24h
            )

            coinList.add(coin)
        }
        coinDao.deleteAll()
        coinDao.addCoins(coinList)
    }

    fun getAllCoins(): LiveData<List<Coins>> {

        return coinDao.getCoins()
    }
    fun getFavCoins(): LiveData<List<Coins>> {

        return coinDao.getFavCoins()
    }

    fun getSearchedCoin(): LiveData<List<Coins>> {
        return coinDao.getSearchedCoins()
    }

    fun addSearchedItemToDB(coins: Coins){

        CoroutineScope(Dispatchers.IO).launch {
            coins.fav = true
            coinDao.addCoinsToWatchList(coins)

        }

    }



}
