package com.example.myapplication



class CreateUrl {

    fun createUrl(user: String, rep: String): String {
        return "http://api.github.com/repos/$user/$rep/stargazers"
    }

}