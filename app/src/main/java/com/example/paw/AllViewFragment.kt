package com.example.paws

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paw.R
import com.example.paw.adapter.AdapterClass
import com.example.paw.databinding.FragmentAllViewBinding
import com.example.paw.databinding.FragmentLoginBinding
import com.example.paw.model.AllViewDataClass


class AllViewFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<AllViewDataClass>
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>
    private lateinit var binding: FragmentAllViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllViewBinding.inflate(inflater, container, false)

        imageList = arrayOf(
            R.drawable.ic_history
        )

        titleList = arrayOf(
            "History"
        )

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf<AllViewDataClass>()
        getData()

        return binding.root
    }
    private fun getData() {
        for (i in imageList.indices) {
            val dataClass = AllViewDataClass(imageList[i], titleList[i])
            dataList.add(dataClass)
        }
        recyclerView.adapter = AdapterClass(dataList)
    }
}



