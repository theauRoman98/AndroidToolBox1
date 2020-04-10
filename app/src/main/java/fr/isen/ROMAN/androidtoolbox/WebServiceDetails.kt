package fr.isen.ROMAN.androidtoolbox

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_web_service_details.*
import java.text.DecimalFormat


class WebServiceDetails() :
    AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_service_details)

        val pokeNum = intent.getIntExtra("num", 0)
        val pokeName = intent.getStringExtra("name")
        val pokeType1 = intent.getStringExtra("type1")
        val pokeType2 = intent.getStringExtra("type2")

        val nf = DecimalFormat("000")
        val num = nf.format(pokeNum)
        Picasso.get().load("https://www.serebii.net/pokemongo/pokemon/${num}.png").into(pokemon_image)

        pokemon_name.text = pokeName

        when (pokeType1) {
            "Bug" -> Picasso.get().load(R.drawable.bug).into(type1)
            "Dragon" -> Picasso.get().load(R.drawable.dragon).into(type1)
            "Electric" -> Picasso.get().load(R.drawable.electric).into(type1)
            "Fighting" -> Picasso.get().load(R.drawable.fighting).into(type1)
            "Fire" -> Picasso.get().load(R.drawable.fire).into(type1)
            "Flying" -> Picasso.get().load(R.drawable.flying).into(type1)
            "Grass" -> Picasso.get().load(R.drawable.grass).into(type1)
            "Ground" -> Picasso.get().load(R.drawable.ground).into(type1)
            "Ice" -> Picasso.get().load(R.drawable.ice).into(type1)
            "Normal" -> Picasso.get().load(R.drawable.normal).into(type1)
            "Poison" -> Picasso.get().load(R.drawable.poison).into(type1)
            "Psychic" -> Picasso.get().load(R.drawable.psychic).into(type1)
            "Rock" -> Picasso.get().load(R.drawable.rock).into(type1)
            "Water" -> Picasso.get().load(R.drawable.water).into(type1)
            else -> Picasso.get().load(R.drawable.none).into(type1)
        }

        when (pokeType2) {
            "Bug" -> Picasso.get().load(R.drawable.bug).into(type2)
            "Dragon" -> Picasso.get().load(R.drawable.dragon).into(type2)
            "Electric" -> Picasso.get().load(R.drawable.electric).into(type2)
            "Fighting" -> Picasso.get().load(R.drawable.fighting).into(type2)
            "Fire" -> Picasso.get().load(R.drawable.fire).into(type2)
            "Flying" -> Picasso.get().load(R.drawable.flying).into(type2)
            "Grass" -> Picasso.get().load(R.drawable.grass).into(type2)
            "Ground" -> Picasso.get().load(R.drawable.ground).into(type2)
            "Ice" -> Picasso.get().load(R.drawable.ice).into(type2)
            "Normal" -> Picasso.get().load(R.drawable.normal).into(type2)
            "Poison" -> Picasso.get().load(R.drawable.poison).into(type2)
            "Psychic" -> Picasso.get().load(R.drawable.psychic).into(type2)
            "Rock" -> Picasso.get().load(R.drawable.rock).into(type2)
            "Water" -> Picasso.get().load(R.drawable.water).into(type2)
            else -> Picasso.get().load(R.drawable.none).into(type2)
        }

        val pokeId = intent.getIntExtra("id", 0)
        pokemon_id_.text = pokeId.toString()

        pokemonType.text = "$pokeType1, $pokeType2"

        val pokeTaille = intent.getStringExtra("height")
        pokemon_taille.text = pokeTaille

        val pokePoids = intent.getStringExtra("weight")
        pokemon_poids.text = pokePoids
    }
}