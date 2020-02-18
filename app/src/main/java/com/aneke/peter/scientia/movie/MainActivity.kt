package com.aneke.peter.scientia.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.aneke.peter.scientia.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import me.ibrahimsn.lib.OnItemSelectedListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val movieViewModel : MovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieViewModel.refreshData()

        view_pager.isUserInputEnabled = false
        val adapter = ViewPagerFragmentAdapter(this)
        view_pager.adapter = adapter

        bottomBar.setOnItemSelectedListener(object: OnItemSelectedListener {
            override fun onItemSelect(pos: Int) {
               view_pager.setCurrentItem(pos, true)
            }
        })
    }

    override fun onBackPressed() {
        if (view_pager.currentItem == 0) {
            super.onBackPressed() //if I'm in the first Fragment just go back
        } else {
            view_pager.currentItem = view_pager.currentItem - 1 //just slide to the first Fragment
        }
    }
}
