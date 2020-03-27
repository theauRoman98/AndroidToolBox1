package fr.isen.ROMAN.androidtoolbox

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    private val GOOD_ID = "admin"
    private val GOOD_PASSWORD = "123"
    private val KEY_ID = "id"
    private val KEY_PASSWORD = "pass"
    private val USER_PREFS = "user_prefs"
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE)

        val saveId = sharedPreferences.getString(KEY_ID, "")
        val savedPassword = sharedPreferences.getString(KEY_PASSWORD, "")


        if (saveId == GOOD_ID && savedPassword == GOOD_PASSWORD) {
            goToHome()
        }

        loginButton.setOnClickListener {
            val idUser = usernameText.text.toString()
            val passwordUser = passwordText.text.toString()

            if (idUser == GOOD_ID && passwordUser == GOOD_PASSWORD) {
                saveCredentials(idUser, passwordUser)
                goToHome()
            }
        }
    }

    private fun saveCredentials(id: String, pass: String) {
        val editor = sharedPreferences.edit()
        editor.putString(KEY_ID, id)
        editor.putString(KEY_PASSWORD, pass)
        editor.apply()
    }

    private fun goToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

}
