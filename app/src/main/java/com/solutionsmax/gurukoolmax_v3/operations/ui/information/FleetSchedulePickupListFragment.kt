package com.solutionsmax.gurukoolmax_v3.operations.ui.information

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.RETRIEVE_FLEET_PICKUP_SCHEDULE
import com.solutionsmax.gurukoolmax_v3.core.common.PortalIdConstants
import com.solutionsmax.gurukoolmax_v3.core.data.error_logs.PostErrorLogsItems
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Event
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.ErrorLogsViewModel
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils.getMediumDateFormat
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentFleetSchedulePickupListBinding
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants
import com.solutionsmax.gurukoolmax_v3.operations.ui.information.adapter.FleetSchedulePickupAdapter
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import javax.inject.Inject


class FleetSchedulePickupListFragment : BaseFragment() {

    private lateinit var binding: FragmentFleetSchedulePickupListBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var fleetViewModel: FleetRoutesViewModel
    private lateinit var tokenLicenseViewModel: TokenLicenseViewModel
    private lateinit var errorLogsViewModel: ErrorLogsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFleetSchedulePickupListBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.apply {
            title = getString(R.string.pickup_schedule)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                val bundle = bundleOf("menu" to OperationMenuConstants.FLEET_INFORMATION)
                currentNavController.navigate(
                    R.id.operationsSubMenuFragment,
                    bundle
                )
            }
        }

        binding.progressBar.visibility = View.VISIBLE
        tokenLicenseViewModel =
            ViewModelProvider(this, viewModelFactory)[TokenLicenseViewModel::class.java]
        errorLogsViewModel =
            ViewModelProvider(this, viewModelFactory)[ErrorLogsViewModel::class.java]
        fleetViewModel = ViewModelProvider(this, viewModelFactory)[FleetRoutesViewModel::class.java]

        tokenLicenseViewModel.retrieveTokenLicenseInfo()
        setupObservers()

        binding.fabCreateNew.setOnClickListener {
            val bundle = bundleOf("id" to -1)
            currentNavController.navigate(
                R.id.fleetScheduleInfoFragment,
                bundle
            )
        }
    }

    private fun setupObservers() {
        tokenLicenseViewModel.tokenLicenseMutableData.observe(viewLifecycleOwner) {
            fleetViewModel.retrieveFleetPickupSchedule(
                it.sBaseURL + RETRIEVE_FLEET_PICKUP_SCHEDULE, it.sToken, -1, -1
            )
            with(fleetViewModel) {
                errorLiveData.observe(viewLifecycleOwner) { error ->
                    showError(error.peekContent())
                    with(errorLogsViewModel) {
                        postErrors(error)
                        postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                    }
                }
                fleetPickupScheduleMutableData.observe(viewLifecycleOwner) {
                    binding.progressBar.visibility = View.GONE
                    with(binding.fleetRouteList) {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter =
                            FleetSchedulePickupAdapter(
                                it,
                                FleetSchedulePickupAdapter.OnItemClick { item ->
                                    val bundle = bundleOf("id" to item)
                                    currentNavController.navigate(
                                        R.id.fleetScheduleInfoFragment,
                                        bundle
                                    )
                                })
                    }
                }
            }
        }
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    private fun postErrors(event: Event<Failure>) {
        errorLogsViewModel.postErrorLogs(
            url = sBaseURL + MethodConstants.POST_ERROR_LOGS,
            sAuthorization = sToken,
            postErrorLogsItems = PostErrorLogsItems(
                iGroupID = iGroupID,
                iPlantID = iBranchID,
                iUserRegistrationID = 1,
                iPortalID = PortalIdConstants.MANAGEMENT_PORTAL,
                sErrorException = event.peekContent().stackTraceToString(),
                sErrorMessage = event.peekContent().localizedMessage,
                sErrorTrace = event.peekContent().message.toString(),
                iReviewStatusID = -1,
                sErrorSource = FleetSchedulePickupListFragment::class.simpleName.toString(),
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }

}