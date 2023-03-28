package com.sanuja.dicegamew1869873

// ====================================================================================

// Full Name : E.M.G.S.S.B. Dehideniya
// IIT ID    : 20200731
// UoW ID    : w1869873

// I confirm that I understand what plagiarism is and have read and understood the section on
// Assessment Offences in the Essential Information for Students.
// The work that I have submitted is entirely my own.
// Any work from other authors is duly referenced and acknowledged.

// ====================================================================================

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    // lateinit var is used to initialise only before use not when class is declared
    // MaterialAlertDialogBuilder is used to display about information
    private lateinit var materialAlertDialogBuilder: MaterialAlertDialogBuilder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val newGameButton = findViewById<Button>(R.id.newGameButton)

        // click listener for proceed to the new game when the button is pressed
        newGameButton.setOnClickListener(){
            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)
        }

        aboutMe()
        aboutButton.setOnClickListener(){
            materialAlertDialogBuilder.show()
        }

    }
    // defining function to show the about me info as a material alert dialog
    private fun aboutMe() {

        materialAlertDialogBuilder = MaterialAlertDialogBuilder((this))
            .setTitle(("Name : Sanuja Dehideniya\n"+
                       "ID : 20200731 / w1869873"))
            .setMessage("I confirm that I understand what plagiarism is and have read and understood " +
                    " the section on Assessment Offences in the Essential Information for Students." +
                    " The work that I have submitted is entirely my own. Any work from other authors" +
                    " is duly referenced and acknowledged." )
                // to exit the dialog when clicked outside the dialog and when clicked the exit button
            .setCancelable(true)
            .setPositiveButton("Ok") { dialog, _ -> // '_' is given as a parameter since its never used
                dialog.dismiss()
            }
    } // REFERENCES : https://developer.android.com/reference/com/google/android/material/dialog/MaterialAlertDialogBuilder
}