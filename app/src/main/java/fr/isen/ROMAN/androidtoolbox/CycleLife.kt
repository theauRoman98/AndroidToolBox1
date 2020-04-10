package fr.isen.ROMAN.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cycle_life.*

class CycleLife : AppCompatActivity() {

    private var texte :String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cycle_life)
        texte += "onCreate()\n"
        CycleLifeTextView.text = texte

    }

    override fun onStart() {
        super.onStart()
        texte += "onStart\n"
        CycleLifeTextView.text = texte
    }

    override fun onResume() {
        super.onResume()
        texte += "onResume\n"
        CycleLifeTextView.text = texte
    }

    override fun onStop() {
        super.onStop()
        texte += "onStop\n"
        CycleLifeTextView.text = texte
    }

    override fun onPause() {
        super.onPause()
        texte += "onPause\n"
        CycleLifeTextView.text = texte
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show()
    }


}
