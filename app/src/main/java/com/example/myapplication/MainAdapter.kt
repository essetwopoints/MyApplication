package com.example.myapplication


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*


class MainAdapter(val namefilelist: ArrayList<StargazersData>): RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        var layoutInflater= LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.list_item, parent,false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {

        return   namefilelist.count()
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val stargazersInfo = namefilelist.get(position)
        holder?.view?.name_Stargazers?.text= stargazersInfo.login

        val avatarImageView = holder?.view?.image_Stargazers
        print(stargazersInfo.avatar_url)
        Picasso.with(holder?.view?.context).load(stargazersInfo.avatar_url).into(avatarImageView)
    }

}

class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view)
