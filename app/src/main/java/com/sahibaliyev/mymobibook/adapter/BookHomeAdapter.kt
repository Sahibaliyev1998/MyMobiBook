package com.sahibaliyev.mymobibook.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.sahibaliyev.mymobibook.activity.BookAboutActivity
import com.sahibaliyev.mymobibook.databinding.ItemHomeBinding
import com.sahibaliyev.mymobibook.model.BookModel
//import com.sahibaliyev.mymobibook.model.FavoriteEntity
import com.sahibaliyev.mymobibook.model.FilterHome
import com.squareup.picasso.Picasso

class BookHomeAdapter(var bookList: ArrayList<BookModel>, var onSelected: (Int, Boolean) -> Unit) :
    RecyclerView.Adapter<BookHomeAdapter.BookHolder>(),
    Filterable {


    private var filter: FilterHome? = null

    inner class BookHolder(val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root)


    interface Listener {
        fun onItemClick(bookModel: BookModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {

        val binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookHolder(binding)
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int ) {

        holder.binding.txtName.text = bookList[position].name
        holder.binding.txtAuthor.text = bookList[position].author

        holder.binding.cbFavorit.setOnCheckedChangeListener { _, isChecked ->
            onSelected(position, isChecked)
        }

        Picasso.get()
            .load(bookList[position].image)
            .into(holder.binding.imgBook)



        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, BookAboutActivity::class.java)
            intent.putExtra("book", bookList.get(position))
                .putExtra("image", bookList.get(position).image)
                .putExtra("name", bookList.get(position).name)
                .putExtra("description", bookList.get(position).description)
                /*.putExtra("category", bookList.get(position).category)*/
                .putExtra("pdf", bookList.get(position).pdf)
            holder.itemView.context.startActivity(intent)
        }


    }

    override fun getFilter(): Filter {

        if (filter == null) {

            filter = FilterHome(bookList, this)

        }
        return filter as FilterHome
    }

    override fun getItemCount(): Int {
        return bookList.count()
    }

    interface OnItemSelected{
        fun onItemSelected(id: Int, checked: Boolean)
    }

}