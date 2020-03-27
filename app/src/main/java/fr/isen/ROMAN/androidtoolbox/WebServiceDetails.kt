package fr.isen.ROMAN.androidtoolbox

import Pokemon
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class WebServiceDetails(internal var context: Context, val pokemonList: List<Pokemon>) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_service_details)
    }
}