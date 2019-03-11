package com.example.myapplication

import com.google.gson.GsonBuilder


class ProcessGson {

    fun processGson(body: String?) : ArrayList<StargazersData>

    {
        var listAvatar= ArrayList<StargazersData>()
        val gson = GsonBuilder().create()
        val namefilelist: Array<NameFileGeneralListElement> = gson.fromJson(body, Array<NameFileGeneralListElement>::class.java)
        for(k in namefilelist){

          listAvatar.add(StargazersDataCreate().stargazersDataCreate(k.login,k.avatar_url))

        }
        return listAvatar
    }
}

