package com.ramsay.cryptoInfo.views

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ramsay.cryptoInfo.R
import com.ramsay.cryptoInfo.databinding.ItemLayoutBinding
import com.ramsay.cryptoInfo.models.local.Coins


 class CoinAdapter(private val context: Context, private var coinList: MutableList<Coins>) :
    RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val layoutBinding: ItemLayoutBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_layout, parent, false)
        return CoinViewHolder(layoutBinding)

    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = coinList[position]

        holder.itemView.setOnClickListener {

            val intent = Intent(holder.itemView.context, CoinDetailsActivity::class.java)
            intent.putExtra("coin", coin)
            Log.d("TAG12345", "onBindViewHolder: 1234566")
            holder.itemView.context.startActivity(intent)
        }
        Log.d("TAG56789", holder.getData(coin).toString())
        holder.getData(coin)
    }

    override fun getItemCount(): Int {
        return coinList.size
    }
    fun setData(newData: MutableList<Coins>){
        coinList = newData
        notifyDataSetChanged()
    }


    inner class CoinViewHolder(private val itemLayoutBinding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(itemLayoutBinding.root) {


        fun getData(coinInfoDTOItem: Coins) {

            //  val firstApiFormat = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss")
            //val  = Instant.parse ( )


            itemLayoutBinding.coin = coinInfoDTOItem
            itemLayoutBinding.apply {
                if (coinInfoDTOItem.priceChangePercentage24h!! <= 0) {
                    textView2.setTextColor(Color.parseColor("#FF0808"))
                   ivLoss.visibility = VISIBLE
                   ivProfit.visibility = GONE

                }else{
                    textView2.setTextColor(Color.parseColor("#50F804"))
                    ivLoss.visibility = GONE
                    ivProfit.visibility = VISIBLE
                }
            }
            itemLayoutBinding.textView3.text = coinInfoDTOItem.lastUpdated!!
            Glide.with(itemLayoutBinding.ivIcon)
                .load(coinInfoDTOItem.image).into(itemLayoutBinding.ivIcon)
        }
    }
}