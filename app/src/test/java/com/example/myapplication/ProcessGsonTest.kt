package com.example.myapplication


import com.google.gson.GsonBuilder
import junit.framework.Assert
import org.junit.Test

class ProcessGsonTest {

    val FAKESTARGAZERS  = "[\n" +
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

    @Test
    fun processGson()

    {
        var listAvatar= ArrayList<StargazersData>()
        val gson = GsonBuilder().create()
        val namefilelist: Array<NameFileGeneralListElement> = gson.fromJson(FAKESTARGAZERS, Array<NameFileGeneralListElement>::class.java)
        for(k in namefilelist){

            listAvatar.add(StargazersDataCreate().stargazersDataCreate(k.login,k.avatar_url))

        }

        Assert.assertEquals(listAvatar, ProcessGson().processGson(FAKESTARGAZERS))
    }
}




