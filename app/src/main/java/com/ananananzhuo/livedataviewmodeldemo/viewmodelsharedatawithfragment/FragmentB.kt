package com.ananananzhuo.livedataviewmodeldemo.viewmodelsharedatawithfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.ananananzhuo.livedataviewmodeldemo.R
import kotlin.properties.ReadOnlyProperty

class FragmentB : Fragment() {
    private val model: ShareDataModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_b, container, false).apply {
            model.liveData.observe(viewLifecycleOwner,
                { value -> findViewById<TextView>(R.id.tv_bresult).text = value })
        }
    }
}