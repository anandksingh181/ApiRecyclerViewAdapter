package com.example.apirecyclerviewadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apirecyclerviewadapter.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getProductData()

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                var responseBody = response.body()
                val productList = responseBody?.products!!

//                val collectDataInSB = StringBuilder()
//                for (myData in productList){
//                     collectDataInSB.append(myData.title + " ")
//                 }

                 val myAdapter = MyAdapter(productList)
                binding.rcv.adapter = myAdapter

                binding.rcv.layoutManager = LinearLayoutManager(this@MainActivity)


            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                Log.i("Main Activity","onFailure: " + t.message     )
            }
        })
    }
}
