package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import android.widget.TextView

class dialogPopUp(textPass: String, context: Context) {

    lateinit var dialog: Dialog
    lateinit var txt : TextView
    lateinit var txtVariable : TextView

    var text = textPass
    private var context = context

    fun showDialog(){

         dialog = Dialog(context)
         dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
         dialog.setContentView(R.layout.pop_up)

      txt         = dialog.findViewById<View>(R.id.button_ok) as TextView
        txtVariable = dialog.findViewById<View>(R.id.textPopUp) as TextView

        txtVariable.setText(text)
       txt.isEnabled = true

       txt.setOnClickListener{

           dialog.cancel()


        }

        dialog.show()

    }

}