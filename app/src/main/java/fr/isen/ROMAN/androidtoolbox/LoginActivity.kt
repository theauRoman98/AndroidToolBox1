package fr.isen.ROMAN.androidtoolbox

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)






        loginButton.setOnClickListener {

            var username = usernameText.text.toString()
            var password = passwordText.text.toString()

            doLogin(username, password)


        }
    }

    private fun doLogin(username: String , password: String){

        //val prefs = applicationContext.getSharedPreferences("credentials", Context.MODE_PRIVATE)
        /*if (rememberMe.isChecked) {
            prefs.edit()
                .putString("username", username)
                .putString("password", password)
                .apply()
        } else {
            prefs.edit().clear().apply()
        }*/

        if (username != "admin" && password != "123") {

            Toast.makeText(applicationContext,"ERROR", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(applicationContext,username, Toast.LENGTH_LONG).show()

            val intent = Intent(this ,  HomeActivity::class.java)
            startActivity(intent)
        }


    }
}
