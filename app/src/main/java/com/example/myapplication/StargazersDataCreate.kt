package com.example.myapplication

class StargazersDataCreate {

   fun stargazersDataCreate(login: String, avatar_url: String): StargazersData{

       return StargazersData(login, avatar_url)

   }
}


data class StargazersData(var login: String, var avatar_url: String)