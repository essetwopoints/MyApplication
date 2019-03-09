package com.example.myapplication


import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.git_activity.*
import okhttp3.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import java.io.IOException



class GitActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.git_activity)
        val recycleView = findViewById<RecyclerView>(R.id.Recycle_view)


      //val actionBar = supportActionBar
       // actionBar!!.title = "List Stargazers"
        //actionBar.setDisplayHomeAsUpEnabled(true)

        recycleView.layoutManager = LinearLayoutManager(this)
        var url = intent.getStringExtra("URL")

         var toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitle("List of Stargazers")
        toolbar.setTitleTextColor(Color.WHITE)


        doAsync {
            fetchJson(url)
            uiThread { longToast("Download Stargazers") }
        }
    }

  /*  override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }*/


    fun fetchJson(url: String) {

            var request = Request.Builder().url(url).build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback {

                override fun onResponse(call: Call, response: Response) {


                    val code = response.code()
                    if (code == 404) {

                        finish()


                    } else if (code == 200) {

                        val namefilelist = ProcessGson().processGson(response.body()?.string()!!)


                        runOnUiThread {
                            Recycle_view.adapter = MainAdapter(namefilelist)
                        }


                    }

                }
                override fun onFailure(call: Call, e: IOException) {
                    print("Error Failure")

                }
            })

        }
    }







