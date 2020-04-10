package fr.isen.ROMAN.androidtoolbox

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_save.*
import org.json.JSONObject
import java.io.File
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class SaveActivity : AppCompatActivity() {



        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_save)


            val cal = Calendar.getInstance()
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                    date.text = sdf.format(cal.time)
                }
            val current = cal.get(Calendar.YEAR)


            saveButton.setOnClickListener {
                saveDataToFile(
                    lastName.text.toString(),
                    firstName.text.toString(),
                    date.text.toString(),
                    current
                )
            }

            readButton.setOnClickListener {
                readDataFromFile()
            }

            date.setOnClickListener{
                showDatePicker(dateSetListener)
            }
        }

        private fun showDatePicker(dateSetListener: DatePickerDialog.OnDateSetListener){
            val cal = Calendar.getInstance()
            DatePickerDialog(
                this@SaveActivity, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        private fun readDataFromFile() {
            val data = File(cacheDir.absolutePath, "user_data.json").readText()
            val jsonObject = JSONObject(data)

            val lastName = jsonObject.optString(KEY_LAST_NAME)
            val firstName = jsonObject.optString(KEY_FIRST_NAME)
            val date = jsonObject.optString(KEY_BIRTH_DATE)
            val age = jsonObject.optString(AGE)



            AlertDialog.Builder(this)
                .setTitle("Lecture du fichier")
                .setMessage("Nom : $lastName \nPrénom : $firstName \nDate : $date \nAge : $age")
                .create()
                .show()
        }

        private fun saveDataToFile(lastName: String, firstName: String, birthDate: String, current:Int){
            val age = calculAge(date.text.toString())

            val jsonObject = JSONObject()
            jsonObject.put(KEY_LAST_NAME, lastName)
            jsonObject.put(KEY_FIRST_NAME, firstName)
            jsonObject.put(KEY_BIRTH_DATE, birthDate)
            jsonObject.put(AGE, age)


            val data = jsonObject.toString()
            File(cacheDir.absolutePath, "user_data.json").writeText(data)
        }

    private fun calculAge(date: String): Int {

        var age = 0

        try {
            val dates = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).parse(date)
            val today = Calendar.getInstance();
            val birth = Calendar.getInstance();

            birth.time = dates

            val thisYear = today.get(Calendar.YEAR)
            val yearBirth = birth.get(Calendar.YEAR)

            age = thisYear - yearBirth

            val thisMonth = today.get(Calendar.MONTH)
            val birthMonth = birth.get(Calendar.MONTH)

            if(birthMonth > thisMonth){
                age--
            }else if (birthMonth == thisMonth){
                val thisDay = today.get(Calendar.DAY_OF_MONTH)
                val birthDay = birth.get(Calendar.DAY_OF_MONTH)

                if(birthDay > thisDay){
                    age--
                }
            }
        }catch (e: ParseException){
            e.printStackTrace()
        }
        return age
    }

        companion object {
            private const val KEY_LAST_NAME = "lastname"
            private const val KEY_FIRST_NAME = "firstname"
            private const val KEY_BIRTH_DATE = "date"
            private const val AGE = "age"

        }
}
