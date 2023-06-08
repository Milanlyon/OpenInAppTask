package com.applaunch.openinapptask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.applaunch.openinapptask.databinding.DashboardListLytBinding
import com.applaunch.openinapptask.databinding.DashboradActivityBinding
import java.util.*

class DashboardActivity : AppCompatActivity() {
    lateinit var viewModel: DashboardViewModel
    lateinit var binding: DashboradActivityBinding
    lateinit var dashboardAdapter :DashboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.dashborad_activity)
        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        initAdapter()
        viewModel.getAllData()

         binding.btTopLink.setOnClickListener (View.OnClickListener { v: View? ->
             initObserver()
         })
        binding.btRecentLink.setOnClickListener (View.OnClickListener { v: View? ->
            initObserver()
            dashboardAdapter.updateData(viewModel.liveData.value!!.data.recent_links)

        })

    }

    private fun initObserver() {
        viewModel.liveData.observe(this, Observer { users ->
            dashboardAdapter = DashboardAdapter()
            binding.recyclerView.adapter = dashboardAdapter
            dashboardAdapter.updateData(users.data.top_links)

        })
    }

    private fun initAdapter() {
        binding.recyclerView.setLayoutManager(LinearLayoutManager(this))
        binding.recyclerView.adapter = DashboardAdapter()    }
}