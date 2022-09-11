package com.sahibaliyev.mymobibook.fragment

import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.sahibaliyev.mymobibook.MVVM.HomeFragmentMVVM
import com.sahibaliyev.mymobibook.adapter.BookHomeAdapter
import com.sahibaliyev.mymobibook.databinding.FragmentHomeBinding
import com.sahibaliyev.mymobibook.databinding.ItemHomeBinding
import com.sahibaliyev.mymobibook.model.BookModel
import com.sahibaliyev.mymobibook.model.FavoriteEntity

class HomeFragment : Fragment(), BookHomeAdapter.Listener/*, BookHomeAdapter.OnItemSelected*/ {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var bookAdapter: BookHomeAdapter
    // private lateinit var appDatabase: AppDatabase
    private lateinit var viewModel: HomeFragmentMVVM


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding.rvHome.layoutManager = GridLayoutManager(context, 3)

        val bind =  ItemHomeBinding.inflate(LayoutInflater.from(context))

        bind.cbFavorit.setOnClickListener {
            View.OnClickListener() {
                val isChecked: Boolean = PreferenceManager.getDefaultSharedPreferences(context)
                    .getBoolean("cb1", false)
                bind.cbFavorit.isChecked

                if (isChecked) {
                    Toast.makeText(context, "Add WishList", Toast.LENGTH_SHORT).show()

                    // val db = AppDatabase.getAppDatabase(holder.itemView.context).favoriteDao()
                    //val fav : List<FavoriteEntity> = db.getAll()
                    //fav.get(position).id

                } else Toast.makeText(context, "Remove WishList", Toast.LENGTH_SHORT).show()
            }
        }


        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                try {
                    bookAdapter.filter.filter(s)
                } catch (e: Exception) {
                }
            }override fun afterTextChanged(p0: Editable?) {}
        })


        return binding.root
    }


    private fun observeData() {
        viewModel.bookLiveData.observe(viewLifecycleOwner) {

            bookAdapter = BookHomeAdapter(it, object : BookHomeAdapter.OnItemSelected {
                override fun onItemSelected(id: Int, checked: Boolean) {
//                    viewModel.dataSave()

                    val bookModel = viewModel.bookModels[id]
                    var favoriteBook = FavoriteEntity(bookModel.id, bookModel.name, bookModel.image, bookModel.author)

                    if(checked) viewModel.saveBook(favoriteBook)
                    else viewModel.removeBook(favoriteBook)
                }

            })
            binding.rvHome.adapter = bookAdapter
        }
    }


    override fun onItemClick(bookModel: BookModel) {}


    override fun onViewCreated(view: View, savedInstanceState: Bundle? ) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this)[HomeFragmentMVVM::class.java]

        viewModel.loadData()
        observeData()

    }

}