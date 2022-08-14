package com.sahibaliyev.mymobibook.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sahibaliyev.mymobibook.MVVM.ReadBookMVVM
import com.sahibaliyev.mymobibook.databinding.ActivityReadBookBinding
import java.io.IOException

class ReadBook : AppCompatActivity() {
    //https://drive.google.com/file/d/1AXr8CEZ46j6qDKIFLhfNePDC1OoQjwC2/view
    private lateinit var bin: ActivityReadBookBinding
    private lateinit var viewModel : ReadBookMVVM


    private var bookId = ""



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bin = ActivityReadBookBinding.inflate(layoutInflater)
        setContentView(bin.root)

        bookId=intent.getStringExtra("bookId")!!

        //loadBookDetails()

        initViewModel()
    }

    /* private fun loadBookDetails(){
         bin.pdfView.fromUri(FileProvider.getUriForFile(applicationContext ,
             "com.sahibaliyev.mymobibook.fileprovider" ,
             viewModel.getPdfFileUri()))

             .swipeHorizontal(false)
             .onPageChange{page , pageCount->
                 val currentPage = page+1
                 *//*bin.subtitle.text = "$currentPage / $pageCount"*//*
            }
            .load()


    }*/


    @Suppress("UNCHECKED_CAST")
    private fun initViewModel(){


        viewModel = ViewModelProvider(this , object : ViewModelProvider.NewInstanceFactory(){
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ReadBookMVVM(fileDir=filesDir) as T
            }
        })[ReadBookMVVM::class.java]

        viewModel.isFileReadyObserver.observe(this , Observer {
            bin.pbReadBook.visibility = View.GONE

            if (!it){
                Toast.makeText(this , "Failed to Download" , Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this , "PDF Download Succesfull" , Toast.LENGTH_SHORT).show()
                try {

                    bin.pdfView.fromUri(FileProvider.getUriForFile(
                        applicationContext ,
                        "com.sahibaliyev.mymobibook.fileprovider" ,
                        viewModel.getPdfFileUri()))
                        .load()

                }catch (e:IOException){
                    Toast.makeText(this , "Failed Download" , Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.downloadPdfFile("https://firebasestorage.googleapis.com/v0/b/mobibook-2fd46.appspot.com/o/_Uyumsuz.pdf?alt=media&token=e71453ce-4551-490d-8089-850e8968d9a0")


    }

}