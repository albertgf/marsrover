package com.albertgf.core

import com.albertgf.core.models.RoverData
import com.squareup.moshi.Moshi

class DataParser {
    companion object {
        fun toString(data: RoverData ) : String {
            val moshi = Moshi.Builder().build()
            return moshi.adapter(RoverData::class.java).toJson(data)!!
        }

        fun fromString(data: String): RoverData {
            val moshi = Moshi.Builder().build()
            return moshi.adapter(RoverData::class.java).fromJson(data)!!
        }
    }
}