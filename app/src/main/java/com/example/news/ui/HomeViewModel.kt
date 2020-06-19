package com.example.news.ui


import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.data.remote.ApiManager
import com.example.news.model.news.ArticlesItem
import com.example.news.model.news.NewsResponse
import com.example.news.model.sources.Response
import com.example.news.model.sources.SourcesItem
import com.google.android.material.tabs.TabLayout
import retrofit2.Call

class HomeViewModel() : ViewModel() {

    val newsSourcesLiveData = MutableLiveData<List<SourcesItem>>()
    val sourceNewsLiveData = MutableLiveData<List<ArticlesItem>>()
    val messageLiveData = MutableLiveData<String>()
    fun getNewSources() {
        ApiManager.apiService.getNewsSources(API_KEY,"en","us","sports").enqueue(object : retrofit2.Callback<Response>{
            override fun onFailure(call: Call<Response>, t: Throwable) {
              //  Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
                messageLiveData.postValue(t.message)
            }

            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if(response.isSuccessful){
                    response.body()?.sources?.let {
                        newsSourcesLiveData.postValue(it)
                  //
                    }
                }
            }

        })
    }

    fun getSourceNews(name: String) {
        ApiManager.apiService.getSourceNews(name, API_KEY).enqueue(object: retrofit2.Callback<NewsResponse>{
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                t.stackTrace
                messageLiveData.postValue(t.message)
            }

            override fun onResponse(
                call: Call<NewsResponse>, response: retrofit2.Response<NewsResponse>) {
                if(response.isSuccessful){
                    response.body()?.articles?.let {

                        sourceNewsLiveData.postValue(it)
                    }
                }
            }

        })
    }


}