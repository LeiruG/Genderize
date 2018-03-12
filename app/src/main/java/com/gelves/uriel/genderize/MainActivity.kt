package com.gelves.uriel.genderize

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val url = "https://api.genderize.io/?name="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        genderBtn.setOnClickListener {
            progBar.visibility = View.VISIBLE
            g_lbl.visibility = View.GONE
            val name = name_edit.text.toString()

            doAsync {
                val resultJson = URL(url + name).readText()
                uiThread {
                    /*val jsonObj = JSONObject(resultJson)
                    val gender = jsonObj.getString("gender")
                    g_lbl.text = gender*/

                    val genderize: Genderize = Gson().fromJson(resultJson,Genderize::class.java)
                    val gender = genderize.gender
                    g_lbl.text = gender

                    progBar.visibility = View.GONE
                    g_lbl.visibility = View.VISIBLE

                }
            }

        }
    }
}
