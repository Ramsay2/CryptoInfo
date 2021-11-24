package com.ramsay.cryptoInfo.views

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramsay.cryptoInfo.R
import com.ramsay.cryptoInfo.databinding.FragmentWatchListBinding
import com.ramsay.cryptoInfo.models.local.Coins
import com.ramsay.cryptoInfo.models.local.SearchItemClicked
import com.ramsay.cryptoInfo.viewModels.CoinViewModels
import com.ramsay.cryptoInfo.views.CoinsFragment.Companion.coinFavList


class WatchListFragment : Fragment(), SearchItemClicked {

    private lateinit var binding: FragmentWatchListBinding
    private lateinit var coinAdapter: CoinAdapter
    private var coinList = mutableListOf<Coins>()
    private val coinViewModels: CoinViewModels by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWatchListBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coinList = coinFavList
        setAdapter()
        //  getCoin()

    }


    private fun setAdapter() {
        if (coinList != null) {
            coinAdapter = CoinAdapter(requireContext(), coinList)
            binding.apply {
                recyclerViewSearch.layoutManager = LinearLayoutManager(context)
                recyclerViewSearch.adapter = coinAdapter

            }
        }
    }

    fun getCoin() {
        coinViewModels.getFavCoinFromDB().observe(viewLifecycleOwner, Observer {
            coinList.clear()
            coinList.addAll(coinList)
            coinAdapter.notifyDataSetChanged()

        })

    }


    override fun onSearchedItemClicked(coins: Coins) {
    }

    override fun onWatchedItemClicked(coins: Coins) {
        val intent = Intent(context, CoinDetailsActivity::class.java)
        intent.putExtra("coinAdded", coins)
        startActivity(intent)
    }
}