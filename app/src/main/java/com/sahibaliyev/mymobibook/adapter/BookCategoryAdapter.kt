package com.sahibaliyev.mymobibook.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.sahibaliyev.mymobibook.activity.BooksInCategoryActivity
import com.sahibaliyev.mymobibook.databinding.ItemCategoryBinding
import com.sahibaliyev.mymobibook.model.BookModel
import com.sahibaliyev.mymobibook.model.FilterHome
import com.sahibaliyev.mymobibook.model.FilterCategory

class BookCategoryAdapter(var categoryList: ArrayList<BookModel>) : RecyclerView.Adapter<BookCategoryAdapter.BookHolder>() ,
    Filterable {

    private var filter: FilterCategory? = null

    class BookHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {

        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return BookHolder(binding)
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int ) {

        //holder.binding.txtName.text = categoryList.get(position).name
        holder.binding.txtCategoryItem.text = categoryList.get(position).category

      /*  Picasso.get()
            .load(categoryList[position].image)
            .into(holder.binding.imgBook)*/



        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, BooksInCategoryActivity::class.java)

            intent.putExtra("book",categoryList.get(position))
               // .putExtra("image" ,categoryList.get(position).image)
               // .putExtra("name" , categoryList.get(position).name)
               // .putExtra("description" , categoryList.get(position).description)
               // .putExtra("category" , categoryList.get(position).category)
            holder.itemView.context.startActivity(intent)
        }

    }
    override fun getItemCount(): Int {
        return categoryList.count()
    }

    override fun getFilter(): Filter {

        if (filter == null) {

            filter = FilterCategory(categoryList, this)

        }
        return filter as FilterCategory
    }
}