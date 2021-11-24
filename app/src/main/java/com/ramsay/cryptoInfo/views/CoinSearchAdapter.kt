package com.ramsay.cryptoInfo.views

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ramsay.cryptoInfo.R
import com.ramsay.cryptoInfo.databinding.CoinSearchItemLayoutBinding
import com.ramsay.cryptoInfo.models.local.Coins
import com.ramsay.cryptoInfo.models.local.SearchItemClicked


class CoinSearchAdapter(
    private val context: Context, var coinList: MutableList<Coins>,
    private val searchItemClicked: SearchItemClicked
) :
    RecyclerView.Adapter<CoinSearchAdapter.CoinSearchViewHolder>() {


    var backupCoinList = ArrayList<Coins>()

    init {
        backupCoinList = coinList as ArrayList<Coins>
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinSearchViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: CoinSearchItemLayoutBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.coin_search_item_layout, parent, false)
        return CoinSearchViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CoinSearchViewHolder, position: Int) {
        val coin = coinList[position]

        holder.cvSearchedCard.setOnClickListener {
            val intent = Intent(holder.itemView.context, CoinDetailsActivity::class.java)
            intent.putExtra("coin", coin)
            Log.d("TAG12345", "onBindViewHolder: 1234566")
            holder.itemView.context.startActivity(intent)

        }

        var addToFev = 1
        holder.btnAddCoin.setOnClickListener {
            addToFev = if(addToFev== 1){
                searchItemClicked.onSearchedItemClicked(coin)
                holder.btnAddCoin.setImageResource(R.drawable.ic_checked_tick)
                0
            }else{
                holder.btnAddCoin.setImageResource(R.drawable.ic_plus_svgrepo_com)
                1
            }

        }




      /*  holder.btnAddCoin.setOnClickListener {

             searchItemClicked.onSearchedItemClicked(coin)
             holder.btnAddCoin.setImageResource(R.drawable.ic_checked_tick)

        }*/

        holder.getCoin(coin)
    }


    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                var filteredList = ArrayList<Coins>()
                val keyword = p0.toString()
                if (keyword.isEmpty()) {
                    filteredList = backupCoinList
                } else {

                    for (obj in backupCoinList) {
                        if (obj.name?.lowercase()!!.contains(keyword.lowercase().trim())

                        ) {
                            filteredList.add(obj)
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                coinList = p1?.values as MutableList<Coins>
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return coinList.size
    }


    inner class CoinSearchViewHolder(private val binding: CoinSearchItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val btnAddCoin: ImageView = binding.btnAddCoinFromSearch
        val cvSearchedCard = binding.SearchProductCardView
        fun getCoin(coin: Coins) {

            binding.apply {
                coinDetails = coin
                binding.tvSearchItemName.text = coin.name
                Glide.with(binding.ivSearchCoinImage).load(
                    coin.image
                ).into(binding.ivSearchCoinImage)

            }

        }


    }
}
