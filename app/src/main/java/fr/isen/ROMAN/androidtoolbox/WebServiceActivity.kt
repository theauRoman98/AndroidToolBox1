package fr.isen.ROMAN.androidtoolbox


import Next_evolution
import Pokemon
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_web_services.*
import org.json.JSONArray
import org.json.JSONObject


class WebServiceActivity : AppCompatActivity() {

    private val url =
        "https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/pokedex.json"
    private val listPokemon = mutableListOf<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_services)

        Volley.newRequestQueue(this).add(getJsonObjectRequest())
    }

    private fun getJsonObjectRequest(): JsonObjectRequest {

        return JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                val pokemonListGson = Gson().fromJson(response.toString(), Pokemon::class.java)
                parseObject(response)
                println(pokemonListGson)
            },
            Response.ErrorListener { })
    }

    private fun parseObject(response: JSONObject) {
        val jsonArrayResults: JSONArray = response.getJSONArray("pokemon")
        val size: Int = jsonArrayResults.length()
        for (i in 0 until size) {
            val pokemonObject = jsonArrayResults.getJSONObject(i)

            val pokemonTypeObject = pokemonObject.getJSONArray("type")
            val pokemonWeaknessesObject = pokemonObject.getJSONArray("weaknesses")


            val id = pokemonObject.getInt("id")
            val num = pokemonObject.getInt("num")
            val name = pokemonObject.getString("name")
            val img = pokemonObject.getString("img")
            val height = pokemonObject.getString("height")
            val weight = pokemonObject.getString("weight")
            val candy = pokemonObject.getString("candy")
            val egg = pokemonObject.getString("egg")
            val multipliers = listOf<Double>()
            val next_evolution = listOf<Next_evolution>()

            listPokemon += Pokemon(
                id,
                num,
                name,
                img,
                pokemonTypeObject,
                height,
                weight,
                candy,
                egg,
                multipliers,
                pokemonWeaknessesObject,
                next_evolution
            )
        }
        recyclerView.adapter = WebServiceAdapter(this, listPokemon, ::onDeviceClicked)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun onDeviceClicked(pokemon: Pokemon) {
        val intent = Intent(this, WebServiceDetails::class.java)
        intent.putExtra("name", pokemon.name)
        intent.putExtra("id", pokemon.id)
        intent.putExtra("num",pokemon.num)
        intent.putExtra("img", pokemon.img)
        intent.putExtra("type1", pokemon.type.getString(0))
        if (pokemon.type.length() == 2) {
            intent.putExtra("type2", pokemon.type.getString(1))
        }
        intent.putExtra("height", pokemon.height)
        intent.putExtra("weight", pokemon.weight)
        intent.putExtra("candy", pokemon.candy)
        intent.putExtra("egg", pokemon.egg)
        intent.putExtra("weaknesses", pokemon.weaknesses.getString(0))
        startActivity(intent)
    }
}