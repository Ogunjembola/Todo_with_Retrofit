package com.example.todowithretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todowithretrofit.Adapter.TodoRecyclerViewAdapter
import com.example.todowithretrofit.RetrofitApi.RetrofitInstance
import com.example.todowithretrofit.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpRetryException
const  val TAG = " MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoRecyclerViewAdapter: TodoRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible= true
            val response= try {
                RetrofitInstance.api.getTodos()
            }catch (e:IOException){
                Log.e(TAG,"IOException,You might not have internet connection ")
                binding.progressBar.isVisible=false
                return@launchWhenCreated

            }catch (e : HttpException){
                Log.e(TAG,"HttpException,unexpected response ")
                binding.progressBar.isVisible=false
                return@launchWhenCreated

            }
            if (response.isSuccessful && response.body() != null){
                todoRecyclerViewAdapter.allTodos= response.body()!!
            }else{
                Log.e(TAG,"Response not successful")

            }
            binding.progressBar.isVisible=false
        }
    }
 // setting the recycler view
    private fun setUpRecyclerView()= binding.rvTodos.apply {
        todoRecyclerViewAdapter= TodoRecyclerViewAdapter()
        adapter =todoRecyclerViewAdapter
        layoutManager=LinearLayoutManager(this@MainActivity)
    }
}