package com.solutionsmax.gurukoolmax_v3.operations.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentOperationsLoginBinding

class OperationsLoginFragment : BaseFragment() {

    private lateinit var binding: FragmentOperationsLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOperationsLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            currentNavController.navigate(R.id.operationsMenuFragment)
        }
    }

}