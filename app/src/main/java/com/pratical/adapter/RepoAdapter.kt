package com.pratical.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pratical.R
import com.pratical.databinding.RepoItemViewBinding
import com.pratical.model.RepoModel
import java.util.*
import kotlin.collections.ArrayList

class RepoAdapter(val context: Context) : RecyclerView.Adapter<RepoAdapter.RepoView>() {

    private var listItem = ArrayList<RepoModel.Items>()
    private var positionClick = -1
//    private var filterListItem = ArrayList<RepoModel.Items>()

    inner class RepoView(val mBinding: RepoItemViewBinding) :
        RecyclerView.ViewHolder(mBinding.root), View.OnClickListener {

        init {
            mBinding.clParentView.setOnClickListener(this)
        }

        fun setDataToView(items: RepoModel.Items) {
            mBinding.apply {

                if (items.isSelect!!) {

                    clBorderView.background =
                        ContextCompat.getDrawable(context, R.drawable.corner_radius_5_primary_color)

                } else {
                    clBorderView.background = null
                }

                Glide.with(context).load(items.avatars[0]).into(ivAvatar)
                tvName.text = items.repo
            }
        }

        override fun onClick(p0: View?) {
            when (p0?.id) {
                R.id.clParentView -> {
                    if (positionClick != -1) {
                        val data = listItem[positionClick]
                        data.isSelect = false
                    }

                    val data = listItem[adapterPosition]
                    data.isSelect = true

                    positionClick = adapterPosition

                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoView {
        val mBinding = RepoItemViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return RepoView(mBinding)
    }

    override fun onBindViewHolder(holder: RepoView, position: Int) {
        holder.setDataToView(listItem[position])
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    fun doRefresh(list: ArrayList<RepoModel.Items>) {
        listItem = list
//        filterListItem = listItem
        notifyDataSetChanged()
    }

   /* override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filterListItem = listItem
                } else {
                    val resultList = ArrayList<RepoModel.Items>()
                    for (data in listItem) {
                        if (data.repo.toString().lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(data)
                        }
                    }
                    filterListItem = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterListItem
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterListItem = results?.values as ArrayList<RepoModel.Items>
                notifyDataSetChanged()
            }
        }
    }*/
}