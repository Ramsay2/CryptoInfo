package com.ramsay.cryptoInfo.models

import com.ramsay.cryptoInfo.models.response.CoinInfoDTO
import retrofit2.http.GET


interface ApiService {
    @GET("/api/v3/coins/markets?vs_currency=inr&order=market_cap_desc")
   suspend fun get(): CoinInfoDTO
}