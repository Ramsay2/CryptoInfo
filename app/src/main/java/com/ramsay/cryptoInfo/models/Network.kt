package com.ramsay.cryptoInfo.models

import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


 class Network {
            companion object {

              //  private val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

                fun instance(): Retrofit {
                    return Retrofit.Builder()
                        .baseUrl("https://api.coingecko.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(OkHttpClient.Builder().build())
                        .build()
                }
            }
        }



