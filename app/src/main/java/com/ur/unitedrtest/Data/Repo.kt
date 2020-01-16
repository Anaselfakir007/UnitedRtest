package com.ur.unitedrtest.Data

import com.google.gson.annotations.SerializedName

data class Repo(@SerializedName("name") val name:String,@SerializedName("description") val desc:String,@SerializedName("score") val score:Double,@SerializedName("owner") val owner:Owner)