package com.rrzaniolo.movieapichallenge.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
class MainTabsAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val listFragments = ArrayList<Fragment>()
    private val listFragmentsTitle = ArrayList<String>()

    fun add(fragment: Fragment, title: String) {
        this.listFragments.add(fragment)
        this.listFragmentsTitle.add(title)
    }

    override fun getItem(position: Int): Fragment {
        return listFragments[position]
    }

    override fun getCount(): Int {
        return listFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listFragmentsTitle[position]
    }
}