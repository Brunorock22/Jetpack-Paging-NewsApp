package com.brunets.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.brunets.newsapp.adapter.ArticleAdapter
import com.brunets.newsapp.data.model.Article
import com.brunets.newsapp.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: NewsViewModel
    private val articleAdapter = ArticleAdapter()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider.NewInstanceFactory().create(NewsViewModel::class.java)

        viewModel.articles.observe(this, {
            print(it)
        })
        binding.articlesList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
        }
//        viewModel.articles.observe(this, Observer<ArrayList<Article>>{
//            articleAdapter.submitList(it)
//        })

       lifecycleScope.launch {
           viewModel.articlesFlow.collectLatest {
               articleAdapter.submitData(it)
           }
       }

        binding.articlesList.setAdapter(articleAdapter)
    }
}