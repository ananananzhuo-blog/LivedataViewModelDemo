package com.ananananzhuo.livedataviewmodeldemo.viewmodelsharedatawithfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.ananananzhuo.livedataviewmodeldemo.R


class FragmentA : Fragment() {
    private val model: ShareDataModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a, container, false).apply {
            model.liveData.observe(viewLifecycleOwner,
                { value -> findViewById<TextView>(R.id.tv_aresult).text = value })
        }
    }
}