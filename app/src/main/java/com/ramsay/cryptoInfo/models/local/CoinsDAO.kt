package com.ramsay.cryptoInfo.models.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ramsay.cryptoInfo.models.local.Coins
import kotlinx.coroutines.flow.Flow


@Dao
interface CoinsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCoins(coinInfoDTOItem: ArrayList<Coins>)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCoinsToWatchList(coinInfoDTOItem: Coins)

    @Query("select * from coins")
    fun getCoins() : LiveData<List<Coins>>


    @Query("select * from coins where fav= 1")
    fun getFavCoins() : LiveData<List<Coins>>


    @Query("select * from coins")
    fun getSearchedCoins() : LiveData<List<Coins>>


    @Query("select * from coins where coin= :coin")
    fun searchCoin(coin:String) :Flow<List<Coins>>

    @Query("delete from coins")
    fun deleteAll()

}