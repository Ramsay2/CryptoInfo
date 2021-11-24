package com.ramsay.cryptoInfo.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ramsay.cryptoInfo.R
import com.ramsay.cryptoInfo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var selectedFragment: Fragment
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener)
        // searchOperation()
        //  getSearchedCoin()
      //  coinSearch = binding.etSearch.text.toString()

    }

  /*  private fun searchOperation() {
        binding.etSearch.setOnClickListener {
            binding.imageView2.visibility = GONE
        }

    }*/

    private val navListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home ->
                    selectedFragment = CoinsFragment()
                R.id.watchList -> {

                    selectedFragment = WatchListFragment()
                }

                else -> CoinsFragment()
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.flFragment, selectedFragment)
                .commit()
            true
        }
}

/*    fun getSearchedCoin() {

        binding.etSearch.setOnClickListener {
            if (coinSearch?.isNotEmpty() == true)
                coinViewModels.getSearchedCoin(coinSearch!!).observe(this, Observer {
                    coinList.clear()
                    coinList.addAll(it)

                })
        }

    }*/

/*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_view, menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
        return true
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        // it will triggered when
        // we submit the written test
        return true
    }

    // this function will triggered when we
// write even a single char in search view
    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            searchDatabase(query)
        }
        return true

    }

    // We have just created this function for searching our database
    private fun searchDatabase(query: String) {
        // %" "% because our custom sql query will require that
        val searchQuery = "%$query%"

        coinViewModels.getSearchedCoin(searchQuery).observe(this, { list->

            list.let {
            coinAdapter.setData(it as MutableList<Coins>)
            }
        })
    }
}*/


/* private fun setAdapter() {
     coinAdapter = CoinAdapter(this, coinList)
     binding.apply {
         recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
         recyclerview.adapter = coinAdapter

     }
 }

 fun getCoins() {

     coinViewModels.getCoinFromApi()
     coinViewModels.getCoinFromDB().observe(this, Observer {
         val coin = it
         coinList.clear()
         coinList.addAll(coin)
         coinAdapter.notifyDataSetChanged()
     })

 }*/
