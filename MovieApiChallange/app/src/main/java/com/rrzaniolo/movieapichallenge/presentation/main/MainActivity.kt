package com.rrzaniolo.movieapichallenge.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rrzaniolo.movieapichallenge.R
import com.rrzaniolo.movieapichallenge.presentation.main.home.HomeView
import kotlinx.android.synthetic.main.view_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_main)

        initTabAndPager()
    }

    private fun initTabAndPager(){
        val tabsAdapter =
            MainTabsAdapter(this.supportFragmentManager)
        tabsAdapter.add(HomeView.newInstance(), getString(R.string.home))
        tabsAdapter.add(HomeView.newInstance(), getString(R.string.home))

        viewPager.adapter = tabsAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
