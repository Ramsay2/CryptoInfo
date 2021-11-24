package com.ramsay.cryptoInfo.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "coins")
data class Coins(
    @ColumnInfo(name = "coin") var name: String?,
    @ColumnInfo(name = "coinCurrentPrice") var currentPrice: Float?,
    @ColumnInfo(name = "fullyDilutedValuation") var fullyDilutedValuation: String?,
    @ColumnInfo(name = "ath") var ath: String?,
    @ColumnInfo(name = "athDate") var athDate: String?,
    @ColumnInfo(name = "atl") var atl: String?,
    @ColumnInfo(name = "atlChangePercentage") var atlChangePercentage: String?,
    @ColumnInfo(name = "atlDate") var atlDate: String?,
    @ColumnInfo(name = "lastUpdated") var lastUpdated: String?,
    @ColumnInfo(name = "image") var image: String?,
    @ColumnInfo(name = "id") var id: String?,
    @ColumnInfo(name = "low24h") var low24h: String?,
    @ColumnInfo(name = "marketCap") var marketCap: String?,
    @ColumnInfo(name = "marketCapChange24h") var marketCapChange24h: String?,
    @ColumnInfo(name = "marketCapRank") var marketCapRank: String?,
    @ColumnInfo(name = "maxSupply") var maxSupply: String?,
    @ColumnInfo(name = "priceChange24h") var priceChange24h: String?,
    @ColumnInfo(name = "priceChangePercentage24h") var priceChangePercentage24h: Double?,
    @ColumnInfo(name = "symbol") var symbol: String?,
    @ColumnInfo(name = "totalSupply") var totalSupply: String?,
    @ColumnInfo(name = "totalVolume") var totalVolume: String?,
    @ColumnInfo(name = "high24h") var high24h: String?,
    @ColumnInfo(name = "fav") var fav: Boolean = false
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id1")
    var id1 :Int? = null



}