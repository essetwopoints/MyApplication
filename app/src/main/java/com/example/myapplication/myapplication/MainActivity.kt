package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import android.R.id.edit
import android.content.SharedPreferences




class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        retriveData()

        SearchButton.setOnClickListener {

            when {
                getUsername() == "" -> AlertDialog.Builder(this,  R.style.AlertDialogCustom).setTitle("Invalid Owner")
                    .setMessage("Please Insert Owner")
                    .setPositiveButton(android.R.string.ok) { _, _ -> }
                    .setIcon(android.R.drawable.ic_dialog_alert).show()
                getRepositoryName() == "" -> AlertDialog.Builder(this,  R.style.AlertDialogCustom).setTitle("Invalid Repository Name")
                    .setMessage("Please Insert Repository Name")
                    .setPositiveButton(android.R.string.ok) { _, _ -> }
                    .setIcon(android.R.drawable.ic_dialog_alert).show()
                else -> verifyNetwork()
            }
        }


        }

    public override fun onResume() {
        super.onResume()

        val myPreference = getSharedPreferences("preferenceRepository", Context.MODE_PRIVATE)

        if (myPreference.getBoolean("firstrun", true)) {

            clearPrefs()

            myPreference.edit().putBoolean("firstrun", false).commit()
        }
    }




    private fun getUsername(): String {

        val username = Username_editText.text.toString()
        Log.d("MainActivity", "UserName is + $username")
        return username

    }

    fun retriveData(){

        val myPreference = getSharedPreferences("preferenceRepository", Context.MODE_PRIVATE)
        val username = myPreference.getString("username", "")
        val repository = myPreference.getString("repository", "")

        RepositoryName_editText.setText(repository)
        Username_editText.setText(username)

    }

    private fun clearPrefs() {

        val myPreference = getSharedPreferences("preferenceRepository", Context.MODE_PRIVATE)
        val editor = myPreference.edit()

        editor.putString("username", "" )
        editor.putString("repository", "" )
        editor.clear().commit()

        RepositoryName_editText.setText("")
        Username_editText.setText("")
    }


    private fun getRepositoryName(): String {
        val nameRepository = RepositoryName_editText.text.toString()
        Log.d("MainActivity", "Repository Name is + $nameRepository")
        return nameRepository

    }

    private fun verifyNetwork()
    {
        if(isConnected(this)){

            val myPreference = getSharedPreferences("preferenceRepository", Context.MODE_PRIVATE)
            val editor = myPreference.edit()

            editor.putString("username", getUsername().toLowerCase() )
            editor.putString("repository", getRepositoryName().toLowerCase() )
            editor.apply()


            val url = CreateUrl().createUrl(getUsername().toLowerCase(), getRepositoryName().toLowerCase())
            var intent = Intent(this, GitActivity::class.java)
            intent.putExtra("URL", url)
            startActivity(intent)
        }

        else {
            AlertDialog.Builder(this, R.style.AlertDialogCustom).setTitle("No Internet Connection")
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








