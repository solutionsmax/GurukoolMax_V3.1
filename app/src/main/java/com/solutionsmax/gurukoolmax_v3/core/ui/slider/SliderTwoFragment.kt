package com.solutionsmax.gurukoolmax_v3.core.ui.slider

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment


class SliderTwoFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_slider_two, container, false)
    }

}