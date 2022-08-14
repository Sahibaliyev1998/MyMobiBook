package com.sahibaliyev.mymobibook.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sahibaliyev.mymobibook.activity.BookAboutActivity
import com.sahibaliyev.mymobibook.activity.BooksInCategoryActivity
import com.sahibaliyev.mymobibook.databinding.ItemCategoryBinding
import com.sahibaliyev.mymobibook.databinding.ItemHomeBinding
import com.sahibaliyev.mymobibook.model.BookModel
import com.squareup.picasso.Picasso

class BookCategoryAdapter(private val categoryList: ArrayList<BookModel>) : RecyclerView.Adapter<BookCategoryAdapter.BookHolder>() {



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
}