package com.example.quizmaster.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.quizmaster.R
import com.example.quizmaster.data.model.OpentdbAPI.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.io.Serializable

class ResultPageActivity : AppCompatActivity() {
    var score = 1
    var numOfQuestion =0
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var result:List<Result>
    var username = ""
    lateinit var textView10:TextView
    lateinit var textView11:TextView
    lateinit var  database:FirebaseDatabase
    lateinit var myRef : DatabaseReference
    lateinit var viewResult:Button
    lateinit var home:Button
    lateinit var iv_trophy:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_page)

        result = (intent.extras?.get("results") as? List<Result>)!!
        numOfQuestion = result.size
        firebaseAuth = FirebaseAuth.getInstance()

//        score = intent.extras?.get("score") as Int
//        println("ResultPageActivity  $score")
//        println("ResultPageActivity  $result")
        println("ResultPageActivity $result")
        textView10 = findViewById(R.id.textView10)
        textView11 = findViewById(R.id.textView11)
        viewResult = findViewById(R.id.button2)
        home = findViewById(R.id.button3)
        iv_trophy = findViewById(R.id.iv_trophy)

        database = Firebase.database
        var uid = firebaseAuth.uid
        myRef = database.getReference("/Users/$uid")

        home.setOnClickListener {
            var mainActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(mainActivityIntent)
        }
        viewResult.setOnClickListener {
            var mainActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(mainActivityIntent)

        }


        message()

    }


    fun message() {
        myRef.get().addOnSuccessListener {
            username = it.child("username").value.toString()
            println("username  $username")
            textView11.setText("You have scored $score out of $numOfQuestion ")

            if (score >= numOfQuestion * 0.9) {
            textView10.setText("Great job! $username")
                iv_trophy.setImageResource(R.drawable.ic_trophy)
            } else if (score >= numOfQuestion * 0.8 && score < numOfQuestion * 0.9 ) {
            textView10.setText("Almost perfect! $username")
                iv_trophy.setImageResource(R.drawable.silver_cup)
            } else if (score >= numOfQuestion * 0.7 && score < numOfQuestion * 0.8) {
            textView10.setText("You’re on the right track $username")
           } else if (score >= numOfQuestion * 0.6 && score < numOfQuestion * 0.7) {
            textView10.setText("That's a great start  $username")
           }else if (score >= numOfQuestion * 0.5 && score < numOfQuestion * 0.6) {
            textView10.setText("You can do better $username")
           }else if (score <= numOfQuestion * 0.5 ) {
            textView10.setText("Rome wasn't built in a day $username")
               }
        }
    }
}