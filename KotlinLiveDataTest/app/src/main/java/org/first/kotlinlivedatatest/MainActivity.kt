package org.first.kotlinlivedatatest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import org.first.kotlinlivedatatest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var data = MutableLiveData<ArrayList<User>>()
    lateinit var adapter: RecyclerAdapter
    lateinit var binding : ActivityMainBinding
    lateinit var viewmodel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)



        binding.recycler.layoutManager = LinearLayoutManager(this)


        binding.funON.setOnClickListener {
            var list = ArrayList<User>()
            list.add( User("1","test1"))
            list.add( User("2","test2"))
            list.add( User("3","test3"))
            viewmodel.liveData.postValue(list)
        }

        val dataObserver: Observer<ArrayList<User>> =
            Observer { livedata ->
                data.value = livedata
                Log.e("dataValue1", data.value!![0].toString())
                Log.e("dataValue2", data.value!![1].toString())
                Log.e("dataValue3", data.value!![2].toString())
                var newAdapter = RecyclerAdapter(data)
                binding.recycler.adapter = newAdapter
            }

        viewmodel.liveData.observe(this, dataObserver)
    }
}