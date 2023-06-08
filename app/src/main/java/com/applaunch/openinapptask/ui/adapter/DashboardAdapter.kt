package com.applaunch.openinapptask.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.applaunch.openinapptask.Link
import com.applaunch.openinapptask.databinding.LayoutLinkItemBinding
import com.bumptech.glide.Glide
import java.util.*

class DashboardAdapter() : RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {
    var dataList: List<Link> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val binding = LayoutLinkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashboardViewHolder(binding,parent)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        holder.binding(dataList[position])

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(dataList: List<Link>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    inner class DashboardViewHolder(private var binding: LayoutLinkItemBinding, parent: ViewGroup) : RecyclerView.ViewHolder(binding.root) {
        fun binding(link: Link) {
            binding.link = link
           Glide.with(binding.root).load(link.original_image).into( binding.ivLink)
        }
    }
}