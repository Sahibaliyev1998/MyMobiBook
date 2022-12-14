package com.sahibaliyev.mymobibook.model

import android.annotation.SuppressLint
import android.widget.Filter
import com.sahibaliyev.mymobibook.adapter.BookHomeAdapter
import java.util.*

class FilterHome(
    private var filterList: ArrayList<BookModel>,
    private var adapterHome: BookHomeAdapter
) : Filter() {

    @SuppressLint("DefaultLocale")
    override fun performFiltering(constraint: CharSequence?): FilterResults {
        var constraint = constraint
        val result = FilterResults()

        if (constraint != null && constraint.isNotEmpty()) {

            constraint = constraint.toString().lowercase(Locale.getDefault())

            val filteredModel: ArrayList<BookModel> = ArrayList()
            for (i in 0 until filterList.size) {
                if (filterList[i].name.lowercase(Locale.getDefault()).contains(constraint)) {

                    filteredModel.add(filterList[i])
                } else if (filterList[i].author.lowercase(Locale.getDefault())
                        .contains(constraint)
                ) {

                    filteredModel.add(filterList[i])
                }
            }
            result.values = filteredModel
            result.count = filteredModel.size
        } else {
            result.count = filterList.size
            result.values = filterList
        }

        return result
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun publishResults(constraint: CharSequence?, result: FilterResults) {
        adapterHome.bookList = result.values as ArrayList<BookModel>

        adapterHome.notifyDataSetChanged()


    }

}