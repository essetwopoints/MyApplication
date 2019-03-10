package com.example.myapplication


import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.Test

import org.testng.Assert.assertEquals







 class GitActivityTesting {



    val FAKESTARGAZERS = "[\n" +
            "  {\n" +
            "    \"login\": \"octocat\",\n" +
            "    \"id\": 1,\n" +
            "    \"node_id\": \"MDQ6VXNlcjE=\",\n" +
            "    \"avatar_url\": \"https://github.com/images/error/octocat_happy.gif\",\n" +
            "    \"gravatar_id\": \"\",\n" +
            "    \"url\": \"https://api.github.com/users/octocat\",\n" +
            "    \"html_url\": \"https://github.com/octocat\",\n" +
            "    \"followers_url\": \"https://api.github.com/users/octocat/followers\",\n" +
            "    \"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n" +
            "    \"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n" +
            "    \"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n" +
            "    \"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n" +
            "    \"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n" +
            "    \"repos_url\": \"https://api.github.com/users/octocat/repos\",\n" +
            "    \"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n" +
            "    \"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n" +
            "    \"type\": \"User\",\n" +
            "    \"site_admin\": false\n" +
            "  }\n" +
            "]"


    private val client = OkHttpClient()

    @Test
    @Throws(Exception::class)
    fun run() {
        val request = Request.Builder()
            .url("http://api.github.com/repos/container/image/stargazers")
            .build()

        val response = client.newCall(request).execute()

        if(response.isSuccessful){

            assertEquals(200, response.code())

            assertEquals((ProcessGson().processGson(FAKESTARGAZERS)),ProcessGson().processGson(response.body()?.string()!!))

        }else
            assertEquals(404, response.code())

    }


}

