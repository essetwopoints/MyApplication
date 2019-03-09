package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SearchButton.setOnClickListener {

            when {
                getUsername() == "" -> AlertDialog.Builder(this).setTitle("No Owner")
                    .setMessage("Please Insert Owner")
                    .setPositiveButton(android.R.string.ok) { _, _ -> }
                    .setIcon(android.R.drawable.ic_dialog_alert).show()
                getRepositoryName() == "" -> AlertDialog.Builder(this).setTitle("No Repository Name")
                    .setMessage("Please Insert Repository Name")
                    .setPositiveButton(android.R.string.ok) { _, _ -> }
                    .setIcon(android.R.drawable.ic_dialog_alert).show()
                else -> verifyNetwork()
            }
        }
    }

    private fun getUsername(): String {

        val username = Username_editText.text.toString()
        Log.d("MainActivity", "UserName is + $username")
        return username

    }

    private fun getRepositoryName(): String {
        val nameRepository = RepositoryName_editText.text.toString()
        Log.d("MainActivity", "UserName is + $nameRepository")
        return nameRepository

    }

    private fun verifyNetwork()
    {
        if(isConnected(this)){

            val url = CreateUrl().createUrl(getUsername().toLowerCase(), getRepositoryName().toLowerCase())
            var intent = Intent(this, GitActivity::class.java)
            intent.putExtra("URL", url)
            startActivity(intent)
        }

        else {
            AlertDialog.Builder(this).setTitle("No Internet Connection")
                .setMessage("Please check your internet connection and try again")
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }
    }

    private fun isConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}








