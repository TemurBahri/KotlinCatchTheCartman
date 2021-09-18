package com.timurbahri.kotlincatchthecartman

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var score = 0
    var imageArray = ArrayList<ImageView>()
    var handler = Handler()
    var runnable = Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        imageArray.add(imageView)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)

        hideImages()
        //Count Down

        object : CountDownTimer(15500,1000) {
            override fun onTick(millisUntilFinished: Long) {
                  timeText.text = "Time: " + millisUntilFinished/1000
            }

            override fun onFinish() {

                timeText.text = "Time: 0"
                handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }

                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Restart the Game")
                alert.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                    val intent = getIntent()
                    finish()
                    startActivity(intent)
                }

                alert.setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->
                    Toast.makeText(this@MainActivity,"Game Over",Toast.LENGTH_LONG).show()
                }

                alert.show()
            }

        }.start()


    }

    fun hideImages(){

        runnable = object : Runnable {
            override fun run() {
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                val random = Random()
                val randomIndex = random.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable,600)
            }
        }

        handler.post(runnable)


    }

    fun increaseScore (view:View) {
        score = score + 1
        scoreText.text = "Score: $score"
    }
}