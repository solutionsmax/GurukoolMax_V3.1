package com.solutionsmax.gurukoolmax_v3.academics.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentAdministratorSubMenuBinding
import com.solutionsmax.gurukoolmax_v3.operations.data.Academics.ACADEMIC_PROJECT
import com.solutionsmax.gurukoolmax_v3.operations.data.Academics.CCE_SCHOLASTIC
import com.solutionsmax.gurukoolmax_v3.operations.data.Academics.CURRICULUM_MANAGEMENT
import com.solutionsmax.gurukoolmax_v3.operations.data.Academics.EXAMINATION_MANAGEMENT
import com.solutionsmax.gurukoolmax_v3.operations.data.Academics.KNOWLEDGE_MANAGEMENT
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationsMenuItem
import com.solutionsmax.gurukoolmax_v3.operations.ui.adapter.OperationsMenuAdapter


class AdministratorSubMenuFragment : BaseFragment() {
    private lateinit var binding: FragmentAdministratorSubMenuBinding
    private var menuType: String = ""
    private var menuItems = emptyList<OperationsMenuItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAdministratorSubMenuBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuType = requireArguments().getString("menu", "-1").toString()

        binding.toolbar.apply {
            title = when (menuType) {
                OperationMenuConstants.ACADEMICS -> getString(R.string.academics)
                else -> getString(R.string.academics)
            }
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener { currentNavController.navigate(R.id.administratorMenuFragment) }
        }
        when (menuType) {
            OperationMenuConstants.ACADEMICS -> {
                menuItems = listOf(
                    OperationsMenuItem(
                        CURRICULUM_MANAGEMENT,
                        R.drawable.curriculum_management
                    ),
                    OperationsMenuItem(CCE_SCHOLASTIC, R.drawable.cce_scholastic),
                    OperationsMenuItem(ACADEMIC_PROJECT, R.drawable.academic_project),
                    OperationsMenuItem(
                        KNOWLEDGE_MANAGEMENT,
                        R.drawable.knowledge_management
                    ),
                    OperationsMenuItem(
                        EXAMINATION_MANAGEMENT,
                        R.drawable.examination_management
                    )
                )
            }
        }

        with(binding.operationsSubMenu) {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = OperationsMenuAdapter(
                menuItems,
                OperationsMenuAdapter.OnItemClick {
                    val academicsCommonBottomDialogSheetFragment =
                        AcademicsCommonBottomDialogSheetFragment()
                    when (it) {
                        CURRICULUM_MANAGEMENT -> {
                            val bundle = bundleOf("academicItem" to CURRICULUM_MANAGEMENT)
                            academicsCommonBottomDialogSheetFragment.arguments = bundle
                            academicsCommonBottomDialogSheetFragment.show(
                                requireActivity().supportFragmentManager,
                                academicsCommonBottomDialogSheetFragment.tag
                            )
                        }
                        CCE_SCHOLASTIC -> {
                            val bundle = bundleOf("academicItem" to CCE_SCHOLASTIC)
                            academicsCommonBottomDialogSheetFragment.arguments = bundle
                            academicsCommonBottomDialogSheetFragment.show(
                                requireActivity().supportFragmentManager,
                                academicsCommonBottomDialogSheetFragment.tag
                            )
                        }
                        ACADEMIC_PROJECT -> {
                            val bundle = bundleOf("academicItem" to ACADEMIC_PROJECT)
                            academicsCommonBottomDialogSheetFragment.arguments = bundle
                            academicsCommonBottomDialogSheetFragment.show(
                                requireActivity().supportFragmentManager,
                                academicsCommonBottomDialogSheetFragment.tag
                            )
                        }
                        KNOWLEDGE_MANAGEMENT -> {
                            val bundle = bundleOf("academicItem" to KNOWLEDGE_MANAGEMENT)
                            academicsCommonBottomDialogSheetFragment.arguments = bundle
                            academicsCommonBottomDialogSheetFragment.show(
                                requireActivity().supportFragmentManager,
                                academicsCommonBottomDialogSheetFragment.tag
                            )
                        }
                        EXAMINATION_MANAGEMENT -> {
                            val bundle =
                                bundleOf("academicItem" to EXAMINATION_MANAGEMENT)
                            academicsCommonBottomDialogSheetFragment.arguments = bundle
                            academicsCommonBottomDialogSheetFragment.show(
                                requireActivity().supportFragmentManager,
                                academicsCommonBottomDialogSheetFragment.tag
                            )
                        }
                    }
                })
        }
    }
}