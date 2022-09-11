package com.sahibaliyev.mymobibook.model

import android.annotation.SuppressLint
import android.widget.Filter
import com.sahibaliyev.mymobibook.adapter.BookCategoryAdapter

class FilterCategory(
    private var filterList: ArrayList<BookModel>,
    private var adapterCategory: BookCategoryAdapter
) : Filter() {

    @SuppressLint("DefaultLocale")
    override fun performFiltering(constraint: CharSequence?): FilterResults {
        var constraint = constraint
        val result = FilterResults()

        if (constraint != null && constraint.isNotEmpty()) {

            constraint = constraint.toString().uppercase()

            val filteredModel: ArrayList<BookModel> = ArrayList()
            for (i in 0 until filterList.size) {
                if (filterList[i].category.toString().uppercase().contains(constraint)) {

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


        adapterCategory.categoryList = result.values as ArrayList<BookModel>

        adapterCategory.notifyDataSetChanged()


    }

}