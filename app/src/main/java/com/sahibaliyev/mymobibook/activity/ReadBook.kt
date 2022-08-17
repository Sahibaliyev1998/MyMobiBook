package com.sahibaliyev.mymobibook.activity

import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sahibaliyev.mymobibook.MVVM.ReadBookMVVM
import com.sahibaliyev.mymobibook.databinding.ActivityReadBookBinding
import java.io.IOException

@Suppress("UNCHECKED_CAST", "RedundantSamConstructor")
class ReadBook : AppCompatActivity() {

    private lateinit var bin: ActivityReadBookBinding
    private lateinit var viewModel : ReadBookMVVM


    private var bookId = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bin = ActivityReadBookBinding.inflate(layoutInflater)
        setContentView(bin.root)

        bookId=intent.getStringExtra("bookId")!!

        initViewModel()
    }
    private fun initViewModel() {

        viewModel = ViewModelProvider(this, object : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ReadBookMVVM(
                    fileDir = applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)!!,
                    application = application
                ) as T

            }
        })[ReadBookMVVM::class.java]

        viewModel.isFileReadyObserver.observe(this, Observer {
            bin.pbReadBook.visibility = View.GONE

            if (!it) {

                Toast.makeText(this, "Failed to Download", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "PDF Download Successful", Toast.LENGTH_SHORT).show()
                try {

                    bin.pdfView.fromFile(viewModel.getPdfFileUri()).load()

                } catch (e: IOException) {
                    Toast.makeText(this, "Failed Download", Toast.LENGTH_SHORT).show()

                }
            }
        })


        intent.getStringExtra("pdf")?.let { viewModel.downloadPdfFile(it) }
    }

}