package com.solutionsmax.gurukoolmax_v3.operations.ui.on_board_attendance

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_POPULATE_BUS_ROUTES
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentOnBoardAttendanceBinding
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentOnBoardAttendanceSelectRouteBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.PopulateFleetBusRoutesItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.PopulateRegisteredFleetList
import com.solutionsmax.gurukoolmax_v3.operations.ui.operations.register.RegisteredFleetViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import javax.inject.Inject


class OnBoardAttendanceSelectRouteFragment : BaseFragment() {

    private lateinit var binding: FragmentOnBoardAttendanceSelectRouteBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var registeredFleetViewModel: RegisteredFleetViewModel
    private lateinit var tokenLicenseViewModel: TokenLicenseViewModel

    private var iBusRouteID: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardAttendanceSelectRouteBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.apply {
            title = getString(R.string.fleet_movement)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener { currentNavController.navigate(R.id.registeredFleetMovementListFragment) }
        }

        registeredFleetViewModel =
            ViewModelProvider(this, viewModelFactory)[RegisteredFleetViewModel::class.java]
        tokenLicenseViewModel =
            ViewModelProvider(this, viewModelFactory)[TokenLicenseViewModel::class.java]

        tokenLicenseViewModel.retrieveTokenLicenseInfo()
        setupObservers()

        binding.btnSubmit.setOnClickListener {
            if (iBusRouteID > 0) {
                val bundle = bundleOf("id" to iBusRouteID)
                currentNavController.navigate(R.id.onBoardAttendanceFragment, bundle)
            } else {
                showError(
                    getString(R.string.select_bus_route),
                    getString(R.string.select_bus_route_desc)
                )
            }
        }
    }

    private fun setupObservers() {
        tokenLicenseViewModel.tokenLicenseMutableData.observe(viewLifecycleOwner) {
            sBaseURL = it.sBaseURL
            sToken = it.sToken
            sAssetURL = it.sAssetURL
            iGroupID = it.iGroupID
            iBranchID = it.iBranchID
            populateFleetBusRoute(sBaseURL, sToken)
        }
        with(registeredFleetViewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showError(it.peekContent())
            }
        }
    }

    private fun populateFleetBusRoute(sBaseURL: String, sToken: String) {
        with(registeredFleetViewModel) {
            populateFleetRoutesList(
                url = sBaseURL + FLEET_POPULATE_BUS_ROUTES,
                sAuthorization = sToken,
                iGroupID = iGroupID,
                iSchoolID = iBranchID,
                iStatusID = -1
            )
            populateFleetRoutesMutableData.observe(viewLifecycleOwner) {
                binding.cboRoute.apply {
                    it.add(0, PopulateFleetBusRoutesItems(-1, getString(R.string.choose_an_option)))
                    adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, it)
                    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            p0: AdapterView<*>?,
                            p1: View?,
                            p2: Int,
                            p3: Long
                        ) {
                            if (p2 > 0) {
                                val busRoute =
                                    binding.cboRoute.selectedItem as PopulateFleetBusRoutesItems
                                iBusRouteID = busRoute.id
                            }
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }

                    }
                }
            }
        }
    }

}