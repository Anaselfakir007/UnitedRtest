package com.ur.unitedrtest.Data

import com.google.gson.annotations.SerializedName

data class Owner(@SerializedName("login") val login:String,@SerializedName("avatar_url") val imgurl:String)