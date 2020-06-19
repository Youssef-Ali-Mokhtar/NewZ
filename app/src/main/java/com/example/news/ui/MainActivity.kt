package com.example.news.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.news.R
import com.example.news.data.remote.ApiManager
import com.example.news.model.news.NewsResponse
import com.example.news.model.sources.Response
import com.example.news.model.sources.SourcesItem
import com.example.news.uiimport.NewsAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import javax.security.auth.callback.Callback

const val API_KEY = "ca836b90f3024f56b0d0b7abfde07517"
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        newsAdapter = NewsAdapter{
            view, articlesItem, i ->
        }
        recycler_view_id.adapter = newsAdapter

        setupObservers()


        viewModel.getNewSources()

    }

    private fun setupObservers() {
        viewModel.newsSourcesLiveData.observe(this, Observer {
            setupTabLayout(it)
        })

        viewModel.sourceNewsLiveData.observe(this, Observer {
            newsAdapter.swapData(it)
        })

        viewModel.messageLiveData.observe(this, Observer {
            Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
        })
    }


    private fun setupTabLayout(it: List<SourcesItem>) {
            for(item in it){
                val tabItem = tablayout_id.newTab()
                tabItem.text = item.name
                tabItem.tag = item.id
                tablayout_id.addTab(tabItem)
            }
        tablayout_id.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab) {
                viewModel.getSourceNews(tab.tag.toString())
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewModel.getSourceNews(tab.tag.toString())
            }
        })
        tablayout_id.getTabAt(0)?.select()
    }

}

