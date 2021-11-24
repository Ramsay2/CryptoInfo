package com.ramsay.cryptoInfo.models.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Coins::class],version = 17)
abstract class CoinRoomDataBase:RoomDatabase() {
    abstract fun getCoinDAO(): CoinsDAO

    companion object{
        private var INSTANCE: CoinRoomDataBase? = null

        fun getDataBaseObject(context: Context): CoinRoomDataBase {
            return if(INSTANCE == null){

                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    CoinRoomDataBase::class.java,
                    "coin_dataBase"
                )
                builder.fallbackToDestructiveMigration()
                INSTANCE = builder.build()
                INSTANCE!!
            }else
                INSTANCE!!
        }
    }

}