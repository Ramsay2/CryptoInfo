package com.ramsay.cryptoInfo.views

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramsay.cryptoInfo.databinding.FragmentCoinsBinding
import com.ramsay.cryptoInfo.models.local.Coins
import com.ramsay.cryptoInfo.models.local.SearchItemClicked
import com.ramsay.cryptoInfo.viewModels.CoinViewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinsFragment : Fragment(), SearchItemClicked {

    private lateinit var binding: FragmentCoinsBinding
    private lateinit var coinAdapter: CoinAdapter
    private var coinList = mutableListOf<Coins>()
    private val coinViewModels: CoinViewModels by activityViewModels()
    private lateinit var coinSearchAdapter: CoinSearchAdapter


    companion object {
        var coinFavList = mutableListOf<Coins>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        getCoins()

    }

    private fun setAdapter() {
        coinAdapter = CoinAdapter(requireContext(), coinList)
        binding.apply {
            recyclerview.layoutManager = LinearLayoutManager(context)
            recyclerview.adapter = coinAdapter

        }
    }

    private fun getCoins() {

        binding.progressBar.visibility = View.VISIBLE
        binding.layoutProgress.visibility = View.VISIBLE
        coinViewModels.getCoinFromApi()

        coinViewModels.getCoinFromDB().observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = View.GONE
            binding.layoutProgress.visibility = View.GONE
            if (binding.recyclerview.visibility == View.VISIBLE) {
                val coin = it
                coinList.clear()
                coinList.addAll(coin)
                search()
                coinAdapter.notifyDataSetChanged()
            }
        })

    }

    private fun search() {
        coinSearchAdapter = CoinSearchAdapter(requireContext(), coinList, this)
        binding.apply {
            recyclerviewSearch.adapter = coinSearchAdapter
            recyclerviewSearch.layoutManager = LinearLayoutManager(context)

        }

        binding.etCoinSearchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.etCoinSearchBar.clearFocus()
                binding.recyclerviewSearch.visibility = View.GONE
                binding.recyclerview.visibility = View.VISIBLE
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.etCoinSearchBar.text.isEmpty()) {
                    binding.etCoinSearchBar.clearFocus()
                }

            }

            override fun afterTextChanged(s: Editable?) {

                if (s!!.isNotEmpty()) {
                    binding.recyclerviewSearch.visibility = View.VISIBLE
                    binding.recyclerview.visibility = View.GONE

                } else {

                    binding.recyclerviewSearch.visibility = View.GONE
                    binding.recyclerview.visibility = View.VISIBLE


                }
                coinSearchAdapter.getFilter().filter(s)
            }

        })


/*
                    binding.etCoinSearchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            binding.etCoinSearchBar.clearFocus()
                            return false
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            if (newText!!.isNotEmpty()) {
                                binding.recyclerviewSearch.visibility = View.VISIBLE
                                binding.recyclerview.visibility = View.GONE

                            } else {
                                binding.recyclerviewSearch.visibility = View.GONE
                                binding.recyclerview.visibility = View.VISIBLE

                            }
                            coinSearchAdapter.getFilter().filter(newText)
                            return false

                        }

                    })
*/


        /* binding.etCoinSearchBar.setOnQueryTextListener(object : OnQueryTextListener {
             override fun onQueryTextSubmit(query: String?): Boolean {
                 return false
             }

             override fun onQueryTextChange(newText: String?): Boolean {

                 if (newText!!.isNotEmpty()) {
                     binding.recyclerviewSearch.visibility = View.VISIBLE
                     binding.recyclerview.visibility = View.GONE

                 } else {
                     binding.recyclerviewSearch.visibility = View.GONE
                     binding.recyclerview.visibility = View.VISIBLE

                 }
                 coinSearchAdapter.getFilter().filter(newText)
                 return false

             }


         })*/


    }

    override fun onSearchedItemClicked(coins: Coins) {
        // coinViewModels.addSearchedCoin(coins)

        if (!coinFavList.contains(coins)) {
            coinFavList.add(coins)
        }

    }

    override fun onWatchedItemClicked(coins: Coins) {

    }


}