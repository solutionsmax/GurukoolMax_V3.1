package com.solutionsmax.gurukoolmax_v3.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.common.PortalNameConstants.ADMINISTRATORS
import com.solutionsmax.gurukoolmax_v3.core.common.PortalNameConstants.ALUMNI
import com.solutionsmax.gurukoolmax_v3.core.common.PortalNameConstants.FACULTY
import com.solutionsmax.gurukoolmax_v3.core.common.PortalNameConstants.MANAGEMENT
import com.solutionsmax.gurukoolmax_v3.core.common.PortalNameConstants.OPERATIONS
import com.solutionsmax.gurukoolmax_v3.core.common.PortalNameConstants.PARENT
import com.solutionsmax.gurukoolmax_v3.core.common.PortalNameConstants.STUDENT
import com.solutionsmax.gurukoolmax_v3.core.data.main_menu.MainMenuItem
import com.solutionsmax.gurukoolmax_v3.core.ui.adapter.MainMenuAdapter
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentMainMenuBinding


class MainMenuFragment : BaseFragment() {

    private lateinit var binding: FragmentMainMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainMenuBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val menuListItems = listOf(
            MainMenuItem(ADMINISTRATORS, R.drawable.administrator_1),
            MainMenuItem(MANAGEMENT, R.drawable.management_main),
            MainMenuItem(FACULTY, R.drawable.faculty_main),
            MainMenuItem(STUDENT, R.drawable.student_main),
            MainMenuItem(PARENT, R.drawable.parent_1),
            MainMenuItem(ALUMNI, R.drawable.alumni1),
            MainMenuItem(OPERATIONS, R.drawable.industry_main)
        )

        with(binding.menuItems) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = MainMenuAdapter(menuListItems, MainMenuAdapter.OnItemClick {
                when (it) {
                    MANAGEMENT -> currentNavController.navigate(R.id.managementLoginFragment)
                    OPERATIONS -> currentNavController.navigate(R.id.operationsLoginFragment)
                }
            })
        }
    }
}