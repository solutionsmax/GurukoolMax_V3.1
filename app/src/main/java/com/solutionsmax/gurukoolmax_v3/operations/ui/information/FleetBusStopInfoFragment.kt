package com.solutionsmax.gurukoolmax_v3.operations.ui.information

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentFleetBusStopInfoBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route.PopulateBusRoutesItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_stops.PostFleetBusStopItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.PostFleetBusStopParams
import com.solutionsmax.gurukoolmax_v3.operations.ui.information.spinner_adapter.PopulateRouteAdapter
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.MastersViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import javax.inject.Inject


class FleetBusStopInfoFragment : BaseFragment() {

    private lateinit var binding: FragmentFleetBusStopInfoBinding

    companion object {
        var lblRoute: TextView? = null
        var iRouteID: Int = -1
        var dialog: Dialog? = null
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenLicenseViewModel: TokenLicenseViewModel
    private lateinit var mastersViewModel: MastersViewModel
    private lateinit var errorLogsViewModel: ErrorLogsViewModel
    private lateinit var fleetBusStopViewModel: FleetBusStopsViewModel
    private lateinit var fleetRoutesViewModel: FleetRoutesViewModel

    private var iEditID: Int = -1
    private lateinit var populateBusRoutesList: List<PopulateBusRoutesItems>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFleetBusStopInfoBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.apply {
            title = getString(R.string.bus_stops)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener { currentNavController.navigate(R.id.fleetBusStopsListFragment) }
        }

        lblRoute = view.findViewById(R.id.cboRoute)

        iEditID = requireArguments().getInt("id", -1)

        tokenLicenseViewModel =
            ViewModelProvider(this, viewModelFactory)[TokenLicenseViewModel::class.java]
        mastersViewModel =
            ViewModelProvider(this, viewModelFactory)[MastersViewModel::class.java]
        errorLogsViewModel =
            ViewModelProvider(this, viewModelFactory)[ErrorLogsViewModel::class.java]
        fleetBusStopViewModel =
            ViewModelProvider(this, viewModelFactory)[FleetBusStopsViewModel::class.java]
        fleetRoutesViewModel =
            ViewModelProvider(this, viewModelFactory)[FleetRoutesViewModel::class.java]

        tokenLicenseViewModel.retrieveTokenLicenseInfo()
        setupObservers()

        lblRoute!!.setOnClickListener {
            showRoutesDialog()
        }

        binding.btnSubmit.setOnClickListener {
            if (TextUtils.isEmpty(binding.txtSpecifyPickupPoint.text) || iRouteID == -1) {
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
        fleetBusStopViewModel.checkDuplicateBusStopInfo(
            url = sBaseURL,
            sAuthorization = sToken,
            iGroupID = iGroupID,
            iSchoolID = iBranchID,
            iRouteID = iRouteID,
            sStopName = binding.txtSpecifyPickupPoint.text.toString()
        )
    }

    private fun setupObservers() {
        tokenLicenseViewModel.tokenLicenseMutableData.observe(viewLifecycleOwner) {
            iGroupID = it.iGroupID
            iBranchID = it.iBranchID
            sToken = it.sToken
            sBaseURL = it.sBaseURL

            if (iEditID > 0) {
                binding.btnSubmit.text = getString(R.string.edit)
                retrieveDetails(iEditID)
            }

            populateBusRoutes(sBaseURL, sToken)
        }

        with(fleetBusStopViewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showError(it.peekContent())
                with(errorLogsViewModel) {
                    postErrors(it)
                    postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                }
            }
            retrieveBusStopsDetailsMutableData.observe(viewLifecycleOwner) { details ->
                for (items in details) {
                    lblRoute!!.text = items.sRouteName
                    iRouteID = items.iBusRouteID
                    binding.txtSpecifyPickupPoint.setText(items.sBusStopName)
                }
            }
            checkDuplicateBusStopMutableData.observe(viewLifecycleOwner) { duplicate ->
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

            postBusStopMutableData.observe(viewLifecycleOwner) { post ->
                if (post > 0) {
                    currentNavController.navigate(R.id.fleetBusStopsListFragment)
                } else {
                    showError(
                        title = getString(R.string.could_not_save_info),
                        message = getString(R.string.could_not_save_info_desc)
                    )
                }
            }

            amendBusStopMutableData.observe(viewLifecycleOwner) { amend ->
                if (amend > 0) {
                    currentNavController.navigate(R.id.fleetBusStopsListFragment)
                } else {
                    showError(
                        title = getString(R.string.could_not_save_info),
                        message = getString(R.string.could_not_save_info_desc)
                    )
                }
            }
        }
        with(fleetRoutesViewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showError(it.peekContent())
                with(errorLogsViewModel) {
                    postErrors(it)
                    postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                }
            }
            populateBusRoutesMutableData.observe(viewLifecycleOwner) {
                populateBusRoutesList = it
            }
        }
    }

    private fun postInfo() {
        val postInfo = PostFleetBusStopParams(
            url = sBaseURL,
            sAuthorization = sToken,
            postFleetBusStopItems = PostFleetBusStopItems(
                iGroupID = iGroupID,
                iSchoolID = iBranchID,
                sBusStopName = binding.txtSpecifyPickupPoint.text.toString(),
                iBusRouteID = iRouteID,
                iWorkflowStatusID = 1,
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
        fleetBusStopViewModel.postBusStopInfo(postInfo)
    }

    private fun amendInfo() {
        val amendInfo = PostFleetBusStopParams(
            url = sBaseURL,
            sAuthorization = sToken,
            postFleetBusStopItems = PostFleetBusStopItems(
                id = iEditID,
                iGroupID = iGroupID,
                iSchoolID = iBranchID,
                sBusStopName = binding.txtSpecifyPickupPoint.text.toString(),
                iBusRouteID = iRouteID,
                iWorkflowStatusID = 1,
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
        fleetBusStopViewModel.amendBusStopInfo(amendInfo)
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
                sErrorSource = FleetBusStopInfoFragment::class.simpleName.toString(),
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }

    private fun retrieveDetails(iEditID: Int) {
        fleetBusStopViewModel.retrieveBusStopsDetails(
            url = sBaseURL,
            sAuthorization = sToken,
            id = iEditID
        )
    }

    private fun populateBusRoutes(sBaseURL: String, sToken: String) {
        fleetRoutesViewModel.populateBusRouteName(
            url = sBaseURL,
            sAuthorization = sToken,
            iGroupID = iGroupID,
            iSchoolID = iBranchID,
            iStatusID = -1
        )
    }

    private fun showRoutesDialog() {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.spinner_dialog)

        val dialogHeader: TextView = dialog!!.findViewById(R.id.lblDialogHeading)
        dialogHeader.text = getString(R.string.vehicle_name)

        val dialogRecyclerView: RecyclerView = dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
        dialogRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = PopulateRouteAdapter(
                populateBusRoutesList,
                PopulateRouteAdapter.OnItemClick {
                    lblRoute!!.text = it.sRouteName
                    iRouteID = it.id
                })
        }
        dialog!!.show()

        val dialogClose: ImageView = dialog!!.findViewById(R.id.imgClose)
        dialogClose.setOnClickListener { dialog!!.cancel() }
    }

}