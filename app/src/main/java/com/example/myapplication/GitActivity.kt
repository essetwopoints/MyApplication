@file:Suppress("DEPRECATION")

package com.example.myapplication


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.git_activity.*
import okhttp3.*
import org.jetbrains.anko.doAsync
import java.io.IOException
import android.view.View


class GitActivity: AppCompatActivity() {

    lateinit var mContext: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.git_activity)
        val recycleView = findViewById<RecyclerView>(R.id.Recycle_view)

        recycleView.layoutManager = LinearLayoutManager(this)
        val url = intent.getStringExtra("URL")
        mContext = this@GitActivity

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "List of Stargazers"
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)

        Recycle_view.adapter = MainAdapter(ArrayList())

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        doAsync {
            fetchJson(url)
        }

        @Override
        fun onSupportNavigateUp(): Boolean {
            onBackPressed()
            return true
        }
    }


    fun fetchJson(url: String) {


        val progressBar =  findViewById<ProgressBar>(R.id.progressbar)


        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        progressBar.visibility = View.VISIBLE
        client.newCall(request).enqueue(object : Callback {



            override fun onResponse(call: Call, response: Response) {

                runOnUiThread { progressBar.visibility = View.INVISIBLE}

                val code = response.code()
                if (code == 404) {

                    runOnUiThread {
                        dialogPopUp(
                            "Please check if username and repository are correct and try again",
                            mContext
                        ).showDialog()
                    }

                } else if (code == 200) {

                    val namefilelist = ProcessGson().processGson(response.body()?.string()!!)

                    if (namefilelist.size == 0) {

                    } else {

                        runOnUiThread {
                            Recycle_view.adapter = MainAdapter(namefilelist)
                        }
                    }
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                print("Error Failure")

            }
        })
    }


}






