package com.sahibaliyev.mymobibook.model

import java.io.Serializable

data class BookModel (val id: Int,
                      val name: String,
                      val author: String,
                      val image: String,
                      val category: String,
                      val description: String,
                      val pdf : String
                      ) : Serializable
