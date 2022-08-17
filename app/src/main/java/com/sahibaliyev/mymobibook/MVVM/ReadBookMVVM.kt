package com.sahibaliyev.mymobibook.MVVM

import android.app.Application
import android.os.Environment
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sahibaliyev.mymobibook.other.RetrofitInstance
import com.sahibaliyev.mymobibook.service.BookAPI
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*
import kotlin.concurrent.thread

class ReadBookMVVM (application: Application, val fileDir : File) : AndroidViewModel(application){
    private var pdfName : File
    private var dirPath :String
    private var fileName : String
    var isFileReadyObserver = MutableLiveData<Boolean>()

    init {
        dirPath = application.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).toString()
        val dirFile = File(dirPath)
        if(!dirFile.exists()){
            dirFile.mkdirs()
        }
        fileName ="Uyumsuz.pdf"
        val file = "$dirPath/$fileName"
        pdfName = File(file)
        if (pdfName.exists()){
            pdfName.delete()
        }
    }
    fun getPdfFileUri() : File = pdfName

    fun downloadPdfFile(pdfUrl:String){

        thread {
            val bookApi = RetrofitInstance.getRetrofitInstance().create(BookAPI::class.java)
            bookApi.downloadPdfFile(pdfUrl).enqueue(object  : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {

                        val result = response.body()?.byteStream()
                        result?.let {
                            writeToFile(it)
                        }?:kotlin.run {
                            isFileReadyObserver.postValue(false)
                        }
                    }
                    else isFileReadyObserver.postValue(false)
                }
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    isFileReadyObserver.postValue(false)
                }
            })
        }
    }

    private fun writeToFile(inputStream: InputStream){
        try {
            val fileReader = ByteArray(4098)
            var fileSizeDownloaded = 0
            val fos :OutputStream = FileOutputStream(pdfName)
            do{
                val read = inputStream.read(fileReader)
                if (read != -1){
                    fos.write(fileReader,0,read)
                    fileSizeDownloaded += read
                }
            }while (read != -1)
            fos.flush()
            fos.close()
            isFileReadyObserver.postValue(true)
        }catch (e : IOException){
            Log.e("===", "error", e)
            isFileReadyObserver.postValue(false)
        }
    }


}
