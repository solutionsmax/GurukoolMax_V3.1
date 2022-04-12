package com.solutionsmax.gurukoolmax_v3.operations.ui.information

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants
import com.solutionsmax.gurukoolmax_v3.core.common.PortalIdConstants
import com.solutionsmax.gurukoolmax_v3.core.data.error_logs.PostErrorLogsItems
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Event
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.ErrorLogsViewModel
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils.getMediumDateFormat
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentFleetRoutesInfoBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route.PostBusRouteItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.PostBusRoutesParams
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import javax.inject.Inject


class FleetRoutesInfoFragment : BaseFragment() {

    private lateinit var binding: FragmentFleetRoutesInfoBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var fleetViewModel: FleetRoutesViewModel
    private lateinit var tokenLicenseViewModel: TokenLicenseViewModel
    private lateinit var errorLogsViewModel: ErrorLogsViewModel

    private var iEditID = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFleetRoutesInfoBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.apply {
            title = getString(R.string.bus_routes)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                currentNavController.navigate(
                    R.id.fleetRoutesListFragment
                )
            }
        }

        iEditID = requireArguments().getInt("id", -1)

        tokenLicenseViewModel =
            ViewModelProvider(this, viewModelFactory)[TokenLicenseViewModel::class.java]
        errorLogsViewModel =
            ViewModelProvider(this, viewModelFactory)[ErrorLogsViewModel::class.java]

        fleetViewModel = ViewModelProvider(this, viewModelFactory)[FleetRoutesViewModel::class.java]

        tokenLicenseViewModel.retrieveTokenLicenseInfo()
        setupObservers()

        binding.btnSubmit.setOnClickListener {
            if (TextUtils.isEmpty(binding.txtVehicleName.text)) {
                showError(
                    title = getString(R.string.enter_all_info),
                    message = getString(R.string.enter_all_info_desc)
                )
            } else {
                checkDuplicateInfo()
            }
        }
    }

    private fun checkDuplicateInfo() {
        fleetViewModel.checkDuplicateBusRoutes(
            url = sBaseURL,
            sAuthorization = sToken,
            iGroupID = iGroupID,
            iSchoolID = iBranchID,
            sRouteName = binding.txtVehicleName.text.toString()
        )
    }

    private fun setupObservers() {
        tokenLicenseViewModel.tokenLicenseMutableData.observe(viewLifecycleOwner) {
            sBaseURL = it.sBaseURL
            sToken = it.sToken
            sAssetURL = it.sAssetURL
            iGroupID = it.iGroupID
            iBranchID = it.iBranchID
            with(fleetViewModel) {
                errorLiveData.observe(viewLifecycleOwner) { error ->
                    showError(error = error.peekContent())
                    with(errorLogsViewModel) {
                        postErrors(error)
                        postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                    }
                }
                if (iEditID > 0) {
                    retrieveDetails(iEditID)
                }

                retrieveBusRoutesDetailsMutableData.observe(viewLifecycleOwner) { details ->
                    for (busRoutes in details) {
                        binding.txtVehicleName.setText(busRoutes.sRouteName)
                    }
                }

                checkDuplicateBusRoutesMutableData.observe(viewLifecycleOwner) { duplicate ->
                    if (iEditID > 0) {
                        if (duplicate > 0) {
                            if (duplicate == iEditID) {
                                amendInfo()
                            } else {
                                binding.progressBar.visibility = View.INVISIBLE
                                showError(
                                    getString(R.string.duplicate_info),
                                    getString(R.string.duplicate_info_desc)
                                )
                            }
                        } else {
                            amendInfo()
                        }
                    } else {
                        if (duplicate > 0) {
                            binding.progressBar.visibility = View.INVISIBLE
                            showError(
                                getString(R.string.duplicate_info),
                                getString(R.string.duplicate_info_desc)
                            )
                        } else {
                            postInfo()
                        }
                    }
                }

                postBusRoutesMutableData.observe(viewLifecycleOwner) { post ->
                    if (post > 0) {
                        currentNavController.navigate(R.id.fleetRoutesListFragment)
                    } else {
                        showError(
                            title = getString(R.string.could_not_save_info),
                            message = getString(R.string.could_not_save_info_desc)
                        )
                    }
                }

                amendBusRoutesMutableData.observe(viewLifecycleOwner) { amend ->
                    if (amend > 0) {
                        currentNavController.navigate(R.id.fleetRoutesListFragment)
                    } else {
                        showError(
                            title = getString(R.string.could_not_save_info),
                            message = getString(R.string.could_not_save_info_desc)
                        )
                    }
                }
            }
        }
    }

    private fun postInfo() {
        val postItems = PostBusRouteItem(
            iGroupID = iGroupID,
            iSchoolID = iBranchID,
            sRouteName = binding.txtVehicleName.text.toString(),
            iWorkflowStatusID = 1,
            sCreateDate = DateUtils.todayDateTime()
                .getMediumDateFormat(requireContext()),
            sUpdateDate = DateUtils.todayDateTime()
                .getMediumDateFormat(requireContext())
        )
        val postParams = PostBusRoutesParams(
            url = sBaseURL,
            sAuthorization = sToken,
            postItems
        )
        fleetViewModel.postBusRoute(postParams)
    }

    private fun amendInfo() {
        val amendItems = PostBusRouteItem(
            id = iEditID,
            iGroupID = iGroupID,
            iSchoolID = iBranchID,
            sRouteName = binding.txtVehicleName.text.toString(),
            iWorkflowStatusID = 1,
            sCreateDate = DateUtils.todayDateTime()
                .getMediumDateFormat(requireContext()),
            sUpdateDate = DateUtils.todayDateTime()
                .getMediumDateFormat(requireContext())
        )
        val amendParams = PostBusRoutesParams(
            url = sBaseURL,
            sAuthorization = sToken,
            postBusRouteItem = amendItems
        )
        fleetViewModel.amendBusRoute(amendParams)
    }

    private fun retrieveDetails(iEditID: Int) {
        fleetViewModel.retrieveBusRoutesDetails(
            url = sBaseURL, sAuthorization = sToken, id = iEditID
        )
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
                sErrorSource = FleetRoutesInfoFragment::class.simpleName.toString(),
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }
}