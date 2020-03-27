package fr.isen.ROMAN.androidtoolbox

import Pokemon
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_web_services_cell.view.*
import java.text.DecimalFormat

class WebServicesAdapter(internal var context: Context, val pokemonList: List<Pokemon>) :
    RecyclerView.Adapter<WebServicesAdapter.WebServicesViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WebServicesViewHolder = WebServicesViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_web_services_cell, parent, false)
    )

    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: WebServicesViewHolder, position: Int) {
        holder.pokemonName.text = pokemonList[position].name

        val nf = DecimalFormat("000")
        var num = nf.format(pokemonList[position].num)
        Picasso.get().load("https://www.serebii.net/pokemongo/pokemon/$num.png")
            .into(holder.pokemonPhoto)

        var text1 =
            "<b>Id:</b> " + pokemonList[position].num.toString() + "    <b>Taille :</b> " + pokemonList[position].height.toString() + "     <b>Poids : </b>" + pokemonList[position].weight.toString()
        holder.pokemonId.text = HtmlCompat.fromHtml(text1, HtmlCompat.FROM_HTML_MODE_LEGACY)
        var text2 =
            "<b>Type:</b>       " + pokemonList[position].type.toString().replace("[\"", "   ")
                .replace("\"]", "").replace(",", "       ").replace("\"", "")
        holder.pokemonTypes.text = HtmlCompat.fromHtml(text2, HtmlCompat.FROM_HTML_MODE_LEGACY)

        holder.image.setOnClickListener {

        }

        Picasso.get().load(R.drawable.none).into(holder.pokemonType1)
        Picasso.get().load(R.drawable.none).into(holder.pokemonType2)

        for (i in 0 until pokemonList[position].type.length()) {
            val type = pokemonList[position].type.getString(i)
            if (i == 0) {
                holder.image = holder.pokemonType1
            } else {
                holder.image = holder.pokemonType2
            }
            when (type) {
                "Bug" -> Picasso.get().load(R.drawable.bug).into(holder.image)
                "Dragon" -> Picasso.get().load(R.drawable.dragon).into(holder.image)
                "Electric" -> Picasso.get().load(R.drawable.electric).into(holder.image)
                "Fighting" -> Picasso.get().load(R.drawable.fighting).into(holder.image)
                "Fire" -> Picasso.get().load(R.drawable.fire).into(holder.image)
                "Flying" -> Picasso.get().load(R.drawable.flying).into(holder.image)
                "Grass" -> Picasso.get().load(R.drawable.grass).into(holder.image)
                "Ground" -> Picasso.get().load(R.drawable.ground).into(holder.image)
                "Ice" -> Picasso.get().load(R.drawable.ice).into(holder.image)
                "Normal" -> Picasso.get().load(R.drawable.normal).into(holder.image)
                "Poison" -> Picasso.get().load(R.drawable.poison).into(holder.image)
                "Psychic" -> Picasso.get().load(R.drawable.psychic).into(holder.image)
                "Rock" -> Picasso.get().load(R.drawable.rock).into(holder.image)
                "Water" -> Picasso.get().load(R.drawable.water).into(holder.image)
            }
        }
        if (pokemonList[position].type.length() == 0) {
            Picasso.get().load(R.drawable.none).into(holder.pokemonType2)
        }
    }

    class WebServicesViewHolder(userView: View) : RecyclerView.ViewHolder(userView) {
        val pokemonName: TextView = userView.user_name
        val pokemonPhoto: ImageView = userView.user_photo
        val pokemonId: TextView = userView.user_address
        val pokemonTypes: TextView = userView.user_email
        val pokemonType1: ImageView = userView.type1
        val pokemonType2: ImageView = userView.type2
        var image: ImageView = userView.type2
    }

    /*
    private fun startActivityDetails() {
        val intent = Intent(context, lifeCycleActivity::class.java)
        startActivity(intent)
    }
     */
}
