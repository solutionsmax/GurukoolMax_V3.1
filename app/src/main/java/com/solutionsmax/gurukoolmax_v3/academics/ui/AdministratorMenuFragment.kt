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
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentAdministratorMenuBinding
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants.ACADEMICS
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants.ACCOUNTING
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants.ADMISSIONS
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants.CRM
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants.LIBRARY
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants.MISCELLANEOUS
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants.PRO_DESK
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants.SERVICES
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants.STAFF
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants.STUDENT
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationsMenuItem
import com.solutionsmax.gurukoolmax_v3.operations.ui.adapter.OperationsMenuAdapter


class AdministratorMenuFragment : BaseFragment() {

    private lateinit var binding: FragmentAdministratorMenuBinding
    var menuItems = emptyList<OperationsMenuItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAdministratorMenuBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.apply {
            title = getString(R.string.home)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            inflateMenu(R.menu.main_toolbar_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.logout -> currentNavController.navigate(R.id.administratorLoginFragment)
                }
                true
            }
        }

        menuItems = listOf(
            OperationsMenuItem(ACADEMICS, R.drawable.academics),
            OperationsMenuItem(ADMISSIONS, R.drawable.admission),
            OperationsMenuItem(SERVICES, R.drawable.services),
            OperationsMenuItem(STUDENT, R.drawable.student),
            OperationsMenuItem(STAFF, R.drawable.staff),
            OperationsMenuItem(LIBRARY, R.drawable.library),
            OperationsMenuItem(ACCOUNTING, R.drawable.accounting),
            OperationsMenuItem(PRO_DESK, R.drawable.pro_desk),
            OperationsMenuItem(MISCELLANEOUS, R.drawable.miscellaneous),
            OperationsMenuItem(CRM, R.drawable.crm)
        )

        with(binding.administratorMenu) {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = OperationsMenuAdapter(menuItems, OperationsMenuAdapter.OnItemClick {
                when (it) {
                    ACADEMICS -> currentNavController.navigate(
                        R.id.administratorSubMenuFragment,
                        bundleOf("menu" to it)
                    )
                }
            })
        }
    }
}