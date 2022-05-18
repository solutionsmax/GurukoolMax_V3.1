package com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.schedule_online_lesson_plan_sessions

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentOnlineSessionLessonsPlanInfoBinding


class OnlineSessionLessonsPlanInfoFragment : BaseFragment() {
    private lateinit var binding: FragmentOnlineSessionLessonsPlanInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnlineSessionLessonsPlanInfoBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.apply {
            title = getString(R.string.online_management_configuration)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

            setNavigationOnClickListener {
                currentNavController.navigate(
                    R.id.onlineSessionLessonsPlanListFragment
                )
            }
        }
    }

}