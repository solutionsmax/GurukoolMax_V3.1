package com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.schedule_online_lesson_plan_sessions

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentOnlineSessionLessonsPlanListBinding
import com.solutionsmax.gurukoolmax_v3.operations.data.Academics
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants


class OnlineSessionLessonsPlanListFragment : BaseFragment() {
    private lateinit var binding: FragmentOnlineSessionLessonsPlanListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnlineSessionLessonsPlanListBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.apply {
            title = getString(R.string.online_management_configuration)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            val bundle = bundleOf("menu" to OperationMenuConstants.ACADEMICS)
            setNavigationOnClickListener {
                currentNavController.navigate(
                    R.id.administratorSubMenuFragment,
                    bundle
                )
            }
        }

        binding.fabCreateNew.setOnClickListener {
            currentNavController.navigate(R.id.onlineSessionLessonsPlanInfoFragment)
        }
    }

}