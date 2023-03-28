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

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.second_activity.*
//import kotlinx.coroutines.NonCancellable.message


// defining win count before the class is defined to allow the application to save the wins till restarting the application
var humanWinCount : Int = 0
var computerWinCount : Int = 0

class SecondActivity : AppCompatActivity() {
    // defining all the variables
    var randomNo : Int = 0
    var TAG : String = "SecondActivity" // this tag is used to filter out the randomNo in logcat
    var scoreOfPlayer : Int = 0
    var scoreOfComputer : Int = 0
    var playerScoreCount : Int = 0
    var computerScoreCount : Int = 0
    var playerScore : Int =0
    var computerScore : Int = 0
    var gamePointDefault : Int = 101
    var gameWon : String = "YOU WON"
    var gameLost : String = "YOU LOST"
    var clickedForRerollP1 : Boolean = false
    var clickedForRerollP2 : Boolean = false
    var clickedForRerollP3 : Boolean = false
    var clickedForRerollP4 : Boolean = false
    var clickedForRerollP5 : Boolean = false
    private var rerollCount : Int = 0
    var dice1P : Int = 0
    var dice2P : Int = 0
    var dice3P : Int = 0
    var dice4P : Int = 0
    var dice5P : Int = 0

    var dice1C : Int = 0
    var dice2C : Int = 0
    var dice3C : Int = 0
    var dice4C : Int = 0
    var dice5C : Int = 0

// ===============================================================================================

    @SuppressLint("SetTextI18n") // auto generated
    override fun onCreate(savedInstanceState: Bundle?) { // onCreate method is called whenever the application run
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
        val throwButton = findViewById<Button>(R.id.throwButton)
        val scoreButton = findViewById<Button>(R.id.scoreButton)
        // setting the game point value and updating the text view
        tvGamePoint.text = gamePointDefault.toString()
        // updating the previous win counts (0 for first time)
        humanWins.text = humanWinCount.toString()
        computerWins.text = computerWinCount.toString()
// ===============================================================================================
        // click listener for THROW button
        throwButton.setOnClickListener {

            // to allow user to reroll 2 times for a total of three rolls per round
            rerollCount++
            if (rerollCount == 1){
                throwButton.text = "REROLL"
            }
            if (rerollCount==3){
                throwButton.text = "SCORE"
            }
            // if reroll count is 4, which means user has rolled the dice 3 times
            if (rerollCount == 4){
                // auto click the score button
                scoreButton.performClick()
                // stop executing the throwButton.setOnClickListener()
                return@setOnClickListener
            }
            // throw buttons body is called
            throwButtonBody()
        }
// ===============================================================================================
        // on click listener for SCORE button

        scoreButton.setOnClickListener() {
            // resetting the reroll count
            rerollCount = 0
            // resetting the throw button
            throwButton.text = "THROW"
            // calling the body of score button
            scoreButtonBody()

        }
// ===============================================================================================






    }
    //=============================== USER DEFINED FUNCTIONS ====================================================

    private fun throwButtonBody(){
        // after 1st throw enable re-rolling
        controlClickForReroll(true)

        // PLAYER : calling the methods to roll the dices and change image accordingly
        // if else statements : it will check whether the dices are clicked before re-roll
        // CLICKED_DICES are LOCKED and other dices will be re-rolled when user clicks throw
        if (!clickedForRerollP1){
            playerDice1()
            playerScoreCount+=randomNo

        }else {
            clickedForRerollP1 = false
            playerScoreCount+=dice1P
            // AFTER LOCKED RE-ROLL : should reset the image from clicked to normal after a throw
            assignDiceImage(dice1P,player_dice_1)
            Log.e(TAG, "$dice1P LOCKED")}

        if (!clickedForRerollP2){
            playerDice2()
            playerScoreCount+=randomNo
        }else {
            clickedForRerollP2 = false
            playerScoreCount+=dice2P
            // AFTER LOCKED RE-ROLL : should reset the image from clicked to normal after a throw
            assignDiceImage(dice2P,player_dice_2)
            Log.e(TAG, "$dice2P LOCKED")}

        if (!clickedForRerollP3){
            playerDice3()
            playerScoreCount+=randomNo
        }else {
            clickedForRerollP3 = false
            playerScoreCount+=dice3P
            // AFTER LOCKED RE-ROLL : should reset the image from clicked to normal after a throw
            assignDiceImage(dice3P,player_dice_3)
            Log.e(TAG, "$dice3P LOCKED")}

        if (!clickedForRerollP4){
            playerDice4()
            playerScoreCount+=randomNo
        }else {
            clickedForRerollP4 = false
            playerScoreCount+=dice4P
            // AFTER LOCKED RE-ROLL : should reset the image from clicked to normal after a throw
            assignDiceImage(dice4P,player_dice_4)
            Log.e(TAG, "$dice4P LOCKED")}

        if (!clickedForRerollP5){
            playerDice5()
            playerScoreCount+=randomNo
        }else {
            clickedForRerollP5 = false
            playerScoreCount+=dice5P
            // AFTER LOCKED RE-ROLL : should reset the image from clicked to normal after a throw
            assignDiceImage(dice5P,player_dice_5)
            Log.e(TAG, "$dice5P LOCKED")}


        playerScore = playerScoreCount

        Log.e(TAG, "_______PLAYER_______")
        Log.e(TAG, playerScoreCount.toString())
        Log.e(TAG, playerScore.toString())
        Log.e(TAG, "______________________")
        // resetting the playerScoreCount to 0 to use it when THROW button is clicked again
        playerScoreCount = 0

        // COMPUTER : calling the methods to roll the dices and change image accordingly
        computerStrategy()
        computerScoreCount = 0

    }

    // ---------------------------------------------------------------------------------------------------------

    private fun scoreButtonBody(){
        rerollCount = 0
        // add scores to player and computer
        // add the score to player
        scoreOfComputer += computerScore
        scoreOfPlayer += playerScore
        // set the text of the text view
        tvGamePointPlayer.text = scoreOfPlayer.toString()
        tvGamePointComputer.text = scoreOfComputer.toString()

        // reset both previous scores and images
        playerScore = 0
        computerScore = 0
        resetDiceImages()

        // disable the click fro re-roll
        controlClickForReroll(false)
        // check for results
        checkForResults()


    }
    // ---------------------------------------------------------------------------------------------------------
    // FUNCTION : to control clickable dices. after each SCORE button pressed dices gets locked for first roll. (Applied in score button onClick)
    private fun controlClickForReroll(boolean: Boolean) {
        fun updateClickedForReroll(dice: Int, clicked: Boolean, diceImage: ImageView) {
            if (clicked) {
                Log.e(TAG, "dice $dice : Locked")
                clickedDiceImageChange(dice, diceImage)
            }
        }

        player_dice_1.setOnClickListener {
            clickedForRerollP1 = boolean
            updateClickedForReroll(dice1P, clickedForRerollP1, player_dice_1)
        }
        player_dice_2.setOnClickListener {
            clickedForRerollP2 = boolean
            updateClickedForReroll(dice2P, clickedForRerollP2, player_dice_2)
        }
        player_dice_3.setOnClickListener {
            clickedForRerollP3 = boolean
            updateClickedForReroll(dice3P, clickedForRerollP3, player_dice_3)
        }
        player_dice_4.setOnClickListener {
            clickedForRerollP4 = boolean
            updateClickedForReroll(dice4P, clickedForRerollP4, player_dice_4)
        }
        player_dice_5.setOnClickListener {
            clickedForRerollP5 = boolean
            updateClickedForReroll(dice5P, clickedForRerollP5, player_dice_5)
        }

    }
// ---------------------------------------------------------------------------------------------------------




    private fun playerDice1(){
        // generating a random number from 1 to 6
        randomNo = (1..6).random()
        dice1P = randomNo

        // to change the dice image according to the random number
        assignDiceImage(dice1P,player_dice_1)

        // showing the random number in Logcat
        Log.e(TAG, "$randomNo->player_dice_1") // $ - interpolates the value to the string
    }
    // ---------------------------------------------------------------------------------------------------------
    private fun playerDice2(){
        randomNo = (1..6).random()
        dice2P = randomNo

        // to change the dice image according to the random number
        assignDiceImage(dice2P,player_dice_2)
        // showing the random number in Logcat
        Log.e(TAG, "$randomNo->player_dice_2") // $ - interpolates the value to the string
    }
    // ---------------------------------------------------------------------------------------------------------
    private fun playerDice3(){
        randomNo = (1..6).random()
        dice3P = randomNo
        // to change the dice image according to the random number
        assignDiceImage(dice3P,player_dice_3)
        // showing the random number in Logcat
        Log.e(TAG, "$randomNo->player_dice_3") // $ - interpolates the value to the string
    }
    // ---------------------------------------------------------------------------------------------------------
    private fun playerDice4(){
        randomNo = (1..6).random()
        dice4P=randomNo
        // to change the dice image according to the random number
        assignDiceImage(dice4P,player_dice_4)
        // showing the random number in Logcat
        Log.e(TAG, "$randomNo->player_dice_4") // $ - interpolates the value to the string
    }
    // ---------------------------------------------------------------------------------------------------------
    private fun playerDice5(){
        randomNo = (1..6).random()
        dice5P = randomNo
        // to change the dice image according to the random number
        assignDiceImage(dice5P,player_dice_5)

        // showing the random number in Logcat
        Log.e(TAG, "$randomNo->player_dice_5") // $ - interpolates the value to the string
    }

    // ======================== Defining Functions for Computer's Dices ===================================

    private fun computerDice1(){

        randomNo = (1..6).random()
        dice1C = randomNo

        // to change the dice image according to the random number
        assignDiceImage(dice1C,computer_dice_1)

        // showing the random number in Logcat
        Log.e(TAG, "$dice1C->computer_dice_1") // $ - interpolates the value to the string
    }
    // ---------------------------------------------------------------------------------------------------------

    private fun computerDice2(){
        randomNo = (1..6).random()
        dice2C = randomNo
        // to change the dice image according to the random number
        assignDiceImage(dice2C,computer_dice_2)

        // showing the random number in Logcat
        Log.e(TAG, "$dice2C->computer_dice_2") // $ - interpolates the value to the string
    }
    // ---------------------------------------------------------------------------------------------------------

    private fun computerDice3(){
        randomNo = (1..6).random()
        dice3C = randomNo
        // to change the dice image according to the random number
        assignDiceImage(dice3C,computer_dice_3)

        // showing the random number in Logcat
        Log.e(TAG, "$dice3C->computer_dice_3") // $ - interpolates the value to the string
    }
    // ---------------------------------------------------------------------------------------------------------

    private fun computerDice4(){
        randomNo = (1..6).random()
        dice4C = randomNo
        // to change the dice image according to the random number
        assignDiceImage(dice4C,computer_dice_4)

        // showing the random number in Logcat
        Log.e(TAG, "$dice4C->computer_dice_4") // $ - interpolates the value to the string
    }
    // ---------------------------------------------------------------------------------------------------------
    private fun computerDice5(){
        randomNo = (1..6).random()
        dice5C = randomNo
        // to change the dice image according to the random number
        assignDiceImage(dice5C,computer_dice_5)

        // showing the random number in Logcat
        Log.e(TAG, "$dice5C->computer_dice_5") // $ - interpolates the value to the string
    }
    // ---------------------------------------------------------------------------------------------------------

    private fun resetDiceImages(){
        player_dice_1.setImageResource(R.drawable.dice1)
        player_dice_2.setImageResource(R.drawable.dice1)
        player_dice_3.setImageResource(R.drawable.dice1)
        player_dice_4.setImageResource(R.drawable.dice1)
        player_dice_5.setImageResource(R.drawable.dice1)

        computer_dice_1.setImageResource(R.drawable.dice1)
        computer_dice_2.setImageResource(R.drawable.dice1)
        computer_dice_3.setImageResource(R.drawable.dice1)
        computer_dice_4.setImageResource(R.drawable.dice1)
        computer_dice_5.setImageResource(R.drawable.dice1)
    }

    //----------------- to change the image color when dice is clicked to LOCK FOR RE_ROLL -----------------
    private fun clickedDiceImageChange(numberGenerated : Int,diceNumber : ImageView  ){
        when (numberGenerated) {
            1 -> { diceNumber.setImageResource(R.drawable.d1_clicked) }
            2 -> { diceNumber.setImageResource(R.drawable.d2_clicked) }
            3 -> { diceNumber.setImageResource(R.drawable.d3_clicked) }
            4 -> { diceNumber.setImageResource(R.drawable.d4_clicked) }
            5 -> { diceNumber.setImageResource(R.drawable.d5_clicked) }
            6 -> { diceNumber.setImageResource(R.drawable.d6_clicked) }
        }
    }
    // ----------------- to change dice image according to the generated number -----------------
    private fun assignDiceImage(numberGenerated : Int, diceNumber : ImageView  ){
        when (numberGenerated) {
            1 -> { diceNumber.setImageResource(R.drawable.dice1) }
            2 -> { diceNumber.setImageResource(R.drawable.dice2) }
            3 -> { diceNumber.setImageResource(R.drawable.dice3) }
            4 -> { diceNumber.setImageResource(R.drawable.dice4) }
            5 -> { diceNumber.setImageResource(R.drawable.dice5) }
            6 -> { diceNumber.setImageResource(R.drawable.dice6) }
        }
    }

    // ----------------- to display the pop up message when winner is decided ---------------------------------
    @SuppressLint("ResourceAsColor")
    private fun gameOverPopup(message : String){
        // adding the message and background + updating win count
        val popupView = TextView(this).apply {
            text = message
            textSize = 50f
            // if game is won display the game won message and update the humanWinCount
            if (message == gameWon){
                setTextColor(Color.GREEN)
                tvGameOver.text = gameWon
                humanWinCount++
                humanWins.text = humanWinCount.toString()
                Log.e(TAG, message)
            }
            // if the game is lost display the game lost message and update the computerWinCount
            if (message == gameLost){
                setTextColor(Color.RED)
                tvGameOver.text = gameLost
                computerWinCount++
                computerWins.text = computerWinCount.toString()
                Log.e(TAG, message)
            }

            gravity - Gravity.CENTER
            setBackgroundResource(R.drawable.winner_popup) // background for popup is set
        }

        val popupWindow = PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT, true)

        // pop up window is centered
        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.CENTER, 0, 0)
    }
    // ---------------------------------------------------------------------------------------------------------
    private fun checkForResults(){
        // check for default game point
        var isGameStillGoing = false // this is to check whether the game is still going
        // it can avoid multiple if conditions being executed when a situation like ->
        // -> " scoreOfPlayer > scoreOfComputer > gamePointDefault " happens.
        if (scoreOfPlayer > gamePointDefault){
            if (!isGameStillGoing && scoreOfPlayer>scoreOfComputer){
                // display the results
                gameOverPopup(gameWon)

                Log.e(TAG, "$scoreOfPlayer $scoreOfComputer")

                // disable the score button and Throw button
                scoreButton.isEnabled = false
                throwButton.isEnabled = false
                isGameStillGoing = true
            }
            else if (!isGameStillGoing && scoreOfPlayer<scoreOfComputer){
                // display the results
                gameOverPopup(gameLost)
                Log.e(TAG, "$scoreOfPlayer $scoreOfComputer")

                // disable the score button and Throw button
                scoreButton.isEnabled = false
                throwButton.isEnabled = false
                isGameStillGoing = true
            }
        }
        else if (scoreOfComputer>gamePointDefault){
            if (!isGameStillGoing && scoreOfPlayer>scoreOfComputer){
                // display the results
                gameOverPopup(gameWon)
                Log.e(TAG, "$scoreOfPlayer $scoreOfComputer")
                // disable the score button and Throw button
                scoreButton.isEnabled = false
                throwButton.isEnabled = false
                isGameStillGoing = true
            }
            else if (!isGameStillGoing && scoreOfPlayer<scoreOfComputer){
                // display the results
                gameOverPopup(gameLost)
                Log.e(TAG, "$scoreOfPlayer $scoreOfComputer")

                // disable the score button and Throw button
                scoreButton.isEnabled = false
                throwButton.isEnabled = false
                isGameStillGoing = true
            }
        }
    }
    // ---------------------------------------------------------------------------------------------------------
    private fun computerStrategy(){
    // no special strategy for computer is designed, computer re-rolls when user press re-rolls without keeping any dices for the next round


        computerDice1()
        computerScoreCount+=randomNo
        computerDice2()
        computerScoreCount+=randomNo
        computerDice3()
        computerScoreCount+=randomNo
        computerDice4()
        computerScoreCount+=randomNo
        computerDice5()
        computerScoreCount+=randomNo

        computerScore = computerScoreCount

        // to Log the end of a round (five dice throws)
        Log.e(TAG, "_______COMPUTER_______")
        Log.e(TAG, "$computerScoreCount and Cscore- $computerScore")
        Log.e(TAG, "______________________")
    }

}