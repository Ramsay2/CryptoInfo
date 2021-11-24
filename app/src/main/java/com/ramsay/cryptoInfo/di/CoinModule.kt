package com.ramsay.cryptoInfo.di

import android.content.Context
import androidx.room.Room
import com.ramsay.cryptoInfo.models.ApiService
import com.ramsay.cryptoInfo.models.local.CoinRoomDataBase
import com.ramsay.cryptoInfo.models.local.CoinsDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CoinModule {

    @Singleton
    @Provides
    fun provideAPIService(): ApiService {
        val builder  = Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return builder.create(ApiService::class.java)
    }
@Singleton
@Provides
fun provideRoomDb(@ApplicationContext context: Context): CoinRoomDataBase {

    val builder = Room.databaseBuilder(
        context,
        CoinRoomDataBase::class.java,
        "coin_dataBase"
    )
    builder.fallbackToDestructiveMigration()
    return builder.build()
}
    @Singleton
    @Provides
    fun provideCoinDAO(dataBase: CoinRoomDataBase) : CoinsDAO {
        return dataBase.getCoinDAO()
    }


}