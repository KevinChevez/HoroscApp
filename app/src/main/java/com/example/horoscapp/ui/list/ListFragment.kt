package com.example.horoscapp.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.horoscapp.R
import com.example.horoscapp.databinding.FragmentListBinding
import com.example.horoscapp.databinding.FragmentLuckyBinding
import com.example.horoscapp.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {
    private val viewModel by viewModels<ListViewModel>()
    private lateinit var listBinding: FragmentListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listBinding.btnAries.setOnClickListener {
            openDetail()
        }
    }

    private fun openDetail(){
        startActivity(DetailActivity.create(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.listBinding = FragmentListBinding.inflate(layoutInflater)
        return this.listBinding.root
    }

}