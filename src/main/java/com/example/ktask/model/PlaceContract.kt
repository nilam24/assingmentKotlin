package com.example.ktask.model

import android.net.Uri
import android.provider.BaseColumns

class PlaceContract {

     val BASE_URI = "content://" as String
     val CONTENT_AUTHORITY = "com.example.ktask" as String
     val BASE_CONTENT_URI = Uri.parse(BASE_URI + CONTENT_AUTHORITY) as Uri

     class PlaceContract : BaseColumns{

         val  TABLE = "place" as String


    }
}