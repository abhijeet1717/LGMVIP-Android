package com.codewithabhijeet.lgmcovidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class MainActivity : AppCompatActivity() {
    lateinit var countryCasesTV: TextView
    lateinit var countryrecvTV: TextView

    lateinit var countrydeathTV: TextView
    lateinit var stateRV: TextView
    lateinit var stateRVAdapter: StateRVAdapter
    lateinit var stateList: List<StateModal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countryCasesTV =findViewById(R.id.idcases)

        countrydeathTV = findViewById(R.id.iddeath)
        countryrecvTV = findViewById(R.id.idrecv)
        stateRV =findViewById(R.id.idcases)
        stateList = ArrayList<StateModal>()
        getStateInfo()
    }
    private  fun getStateInfo(){
        val url= "https://api.rootnet.in/covid19-in/stats/latest"
        val queue = Volley.newRequestQueue(this@MainActivity)
        val request =
            JsonObjectRequest(Request.Method.GET , url,null, { response ->
                try {
                    val dataObj = response.getJSONObject("data")
                    val summaryObj = dataObj.getJSONObject("summary")
                    val cases:Int = summaryObj.getInt("total")
                    val recovered:Int = summaryObj.getInt("discharged")
                    val death:Int = summaryObj.getInt("total")

                    countryCasesTV.text = cases.toString()
                    countryrecvTV.text = recovered.toString()
                    countrydeathTV.text = death.toString()
                    val regionalArray = dataObj.getJSONArray("regional")
                    for(i in 0 until regionalArray.length()) {
                        val regionalObj = regionalArray.getJSONObject(i)
                        val stateName:String = regionalObj.getString("loc")
                        val cases:Int = regionalObj.getInt("totalconfirmed")
                        val deaths:Int = regionalObj.getInt("deaths")
                        val recovered:Int = regionalObj.getInt("discharged")

                        val stateModal =StateModal(stateName,recovered,deaths,cases)
                        stateList = stateList+stateModal

                    }
                    stateRVAdapter = StateRVAdapter(stateList)



                }catch (e:JSONException){
                    e.printStackTrace()
                }

            }, { error ->
                {
                    Toast.makeText(this,"fail to fetch data", Toast.LENGTH_SHORT).show()
                }

            }
            )
        queue.add(request)


    }
}