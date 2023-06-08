package com.applaunch.openinapptask

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.applaunch.openinapptask.databinding.DashboradActivityBinding
import com.applaunch.openinapptask.ui.DashboardViewModel
import com.applaunch.openinapptask.ui.adapter.DashboardAdapter
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import java.text.SimpleDateFormat
import java.util.*

class DashboardActivity : AppCompatActivity() {
    private lateinit var viewModel: DashboardViewModel
    private lateinit var binding: DashboradActivityBinding
    private lateinit var dashboardAdapter: DashboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.dashborad_activity)
        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        initObserver()
        initAdapter()
        setListeners()
    }

    private fun setListeners() {
        binding.apply {
            tabLink.addOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab?.id == R.id.tab_top_link) {
                        viewModel.dashboardLiveData.value?.data?.let {
                            dashboardAdapter.updateData(it.top_links)
                        }
                    } else {
                        viewModel.dashboardLiveData.value?.data?.let {
                            dashboardAdapter.updateData(it.recent_links)
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

            })

            btTalkWithUs.setOnClickListener {

            }

        }
    }

    private fun initObserver() {
        viewModel.dashboardLiveData.observe(this, Observer { dashboard ->
            binding.progressBar.visibility = View.GONE
            binding.dashboard = dashboard
            setChart(dashboard)
            dashboardAdapter = DashboardAdapter()
            binding.recyclerView.adapter = dashboardAdapter
            dashboardAdapter.updateData(dashboard.data.top_links)

        })
    }

    private fun initAdapter() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@DashboardActivity)
            binding.recyclerView.adapter = DashboardAdapter()
        }
    }

    private fun setChart(dashboardResponse: DashboardResponse) {
        dashboardResponse.data.overall_url_chart

        binding.lineChart.apply {

            val dateMap = dashboardResponse.data.overall_url_chart

            val entries = ArrayList<Entry>()
            val labels = mutableMapOf<Float, String>()

            val sdf = SimpleDateFormat("MMM", Locale.getDefault())

            dateMap.forEach { (date, clicks) ->
                val formattedDate = sdf.format(date)
                entries.add(Entry(date.month.toFloat(), clicks.toFloat()))
                if (labels.contains(date.month.toFloat()).not()) {
                    labels.put(date.month.toFloat(), formattedDate)
                }
            }

            val lineDataSet = LineDataSet(entries, "")
            lineDataSet.color = R.color.white

            // drawables only supported on api level 18 and above
            val drawable = ContextCompat.getDrawable(this@DashboardActivity, R.drawable.blue_fade)
            lineDataSet.fillDrawable = drawable
            //
            val lineData = LineData(lineDataSet)

            description.isEnabled = false
            xAxis.apply {
                mAxisRange
                position = XAxis.XAxisPosition.BOTTOM
                valueFormatter = MonthValueFormatter(labels)
                granularity = 1f
                isGranularityEnabled = true
                labelCount = dateMap.size
            }

            axisLeft.axisMinimum = 0f
            axisRight.isEnabled = false

            setData(lineData)
            invalidate()
        }
    }

}


class MonthValueFormatter(private val labels: Map<Float, String>) : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return labels[value] ?: ""
    }
}

