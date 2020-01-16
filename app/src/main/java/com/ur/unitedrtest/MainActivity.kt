package com.ur.unitedrtest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.ur.unitedrtest.Adapter.AdapterRepo
import com.ur.unitedrtest.Data.Items
import com.ur.unitedrtest.Data.Repo
import com.ur.unitedrtest.Retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.alexbykov.nopaginate.paginate.NoPaginate
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    var page: Int = 1
    var lists: ArrayList<Repo> = ArrayList()
    val simpleDateFormat2 = SimpleDateFormat("yyyy-MM-dd")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recylerrepo.layoutManager = LinearLayoutManager(this)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""
        getRepos(true)

    }


    fun getRepos(iffirst: Boolean) {
        val date = Date()
        RetrofitClient.instance.getrepos("created:<" + simpleDateFormat2.format(date), page, "stars", "desc")
            .enqueue(object : Callback<Items> {
                override fun onFailure(call: Call<Items>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error occured", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<Items>, response: Response<Items>) {
                    if (response.isSuccessful) {
                        if (response.body()!!.items.isEmpty()) {
                            Toast.makeText(applicationContext, "No data to show", Toast.LENGTH_LONG).show()

                        } else {
                            lists.addAll(response.body()!!.items)
                            if (iffirst) {
                                recylerrepo.adapter = AdapterRepo(lists, applicationContext)
                                NoPaginate.with(recylerrepo)
                                    .setOnLoadMoreListener {
                                        getRepos(false)
                                    }
                                    .build()
                            } else {
                                recylerrepo.adapter?.notifyDataSetChanged()
                            }

                            page++
                        }
                    }
                }

            })
    }
}
