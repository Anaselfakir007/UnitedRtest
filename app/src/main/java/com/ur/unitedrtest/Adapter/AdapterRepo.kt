package com.ur.unitedrtest.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.ur.unitedrtest.Data.Repo
import com.ur.unitedrtest.R
import kotlinx.android.synthetic.main.item_user.view.*


class AdapterRepo(val items: ArrayList<Repo>, val context: Context) : RecyclerView.Adapter<ViewHolder2>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder2 {
        return ViewHolder2(LayoutInflater.from(context).inflate(R.layout.item_user, p0, false))
    }

    override fun onBindViewHolder(p0: ViewHolder2, p1: Int) {
        p0.namerepo.text = items.get(p1).name
        p0.desc.text = items.get(p1).desc
        p0.nameowner.text = items.get(p1).owner.login
        p0.stars.text = items.get(p1).score.toString()
        Picasso.get().load(items.get(p1).owner.imgurl).into(p0.img)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class ViewHolder2(view: View) : RecyclerView.ViewHolder(view) {
    val namerepo = view.reponame
    val desc = view.repodesc
    val img = view.imgowner
    val nameowner = view.nameowner
    val stars = view.stars


}