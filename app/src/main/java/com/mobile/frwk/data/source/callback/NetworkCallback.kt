package com.mobile.frwk.data.source.callback

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkCallback {
    companion object {
        fun getRetrofitInstance(path: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}
