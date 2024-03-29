package com.rrzaniolo.movieapichallenge.presentation.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LifecycleOwner
import com.rrzaniolo.movieapichallenge.R

/**
 * Created by Rodrigo Rodrigues Zaniolo on 9/28/2019.
 * All rights reserved.
 */
abstract class BaseView: AppCompatActivity(), LifecycleOwner {

    private var viewCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.also {
            viewCount = 2
        } ?: run {
            this.overridePendingTransition(
                R.anim.anim_slide_in_left,
                R.anim.anim_slide_out_left
            )
        }
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onStart() {
        super.onStart()
        if (viewCount > 1) {
            this.overridePendingTransition(
                R.anim.anim_slide_in_right,
                R.anim.anim_slide_out_right
            )

        } else if (viewCount == 1) {
            viewCount++
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(viewCount > 1){
            this.overridePendingTransition(
                R.anim.anim_slide_in_left,
                R.anim.anim_slide_out_left
            )
            viewCount--
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}