package com.ramsay.cryptoInfo.models.local

import com.ramsay.cryptoInfo.models.local.Coins

interface SearchItemClicked {

    fun onSearchedItemClicked(coins: Coins)

    fun onWatchedItemClicked(coins: Coins)

}