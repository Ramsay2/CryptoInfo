package com.ramsay.cryptoInfo.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ramsay.cryptoInfo.databinding.ActivityCoinDetailsBinding
import com.ramsay.cryptoInfo.models.local.Coins
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailsActivity : AppCompatActivity() {
    lateinit var coinDetailsBinding: ActivityCoinDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coinDetailsBinding = ActivityCoinDetailsBinding.inflate(layoutInflater)
        setContentView(coinDetailsBinding.root)

        val coin = intent.getSerializableExtra("coin")
        Log.d("TAG567", coin.toString())
        getData(coin as Coins)

        coinDetailsBinding.topAppBar.setNavigationOnClickListener {

            startActivity(
                Intent(
                    this, MainActivity
                    ::class.java
                )
            )
        }
    }

    private fun getData(coinInfoDTOItem: Coins) {

        coinDetailsBinding.coinDetails = coinInfoDTOItem
    }
}