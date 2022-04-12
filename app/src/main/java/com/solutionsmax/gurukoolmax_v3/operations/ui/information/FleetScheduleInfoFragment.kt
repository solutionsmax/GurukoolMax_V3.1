package com.solutionsmax.gurukoolmax_v3.operations.ui.information

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.common.MasterTableNames.MASTERS_TIMETABLE_TIME_CYCLE
import com.solutionsmax.gurukoolmax_v3.core.common.MasterTableNames.MASTERS_TIMETABLE_TIME_RANGE_HR
import com.solutionsmax.gurukoolmax_v3.core.common.MasterTableNames.MASTERS_TIMETABLE_TIME_RANGE_MM
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.POPULATE_MASTER_LIST
import com.solutionsmax.gurukoolmax_v3.core.common.PortalIdConstants
import com.solutionsmax.gurukoolmax_v3.core.data.error_logs.PostErrorLogsItems
import com.solutionsmax.gurukoolmax_v3.core.data.master.PopulateMasterListItem
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Event
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.ErrorLogsViewModel
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils.getMediumDateFormat
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentFleetScheduleInfoBinding
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_route.PopulateBusRoutesItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_schedule.PostBusScheduleItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.bus_stops.PopulateFleetBusStopItems
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.PostFleetBusScheduleParams
import com.solutionsmax.gurukoolmax_v3.operations.ui.information.spinner_adapter.schedule.*
import com.solutionsmax.gurukoolmax_v3.operations.ui.operations.register.RegisteredFleetInfoFragment
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.MastersViewModel
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class FleetScheduleInfoFragment : BaseFragment() {

    private lateinit var binding: FragmentFleetScheduleInfoBinding

    companion object {
        var lblRoute: TextView? = null
        var iRouteID: Int = -1
        var lblPickupPoint: TextView? = null
        var iPickupPoint: Int = -1
        var lblHH: TextView? = null
        var iHr: Int = -1
        var lblMM: TextView? = null
        var iMm: Int = -1
        var lblAMPM: TextView? = null
        var iAmPm: Int = -1
        var dialog: Dialog? = null
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenLicenseViewModel: TokenLicenseViewModel
    private lateinit var mastersViewModel: MastersViewModel
    private lateinit var errorLogsViewModel: ErrorLogsViewModel
    private lateinit var fleetBusStopViewModel: FleetBusStopsViewModel
    private lateinit var fleetRoutesViewModel: FleetRoutesViewModel
    private lateinit var fleetScheduleViewModel: FleetScheduleViewModel

    private lateinit var populateBusRoutesList: List<PopulateBusRoutesItems>
    private lateinit var populatePickupPointsList: List<PopulateFleetBusStopItems>
    private lateinit var populateHoursList: List<PopulateMasterListItem>
    private lateinit var populateMinutesList: List<PopulateMasterListItem>
    private lateinit var populateTimeCycleList: List<PopulateMasterListItem>

    private var date: String = ""
    private var iEditID: Int = -1

    private var cal: Calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFleetScheduleInfoBinding.inflate(inflater)
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

        iEditID = requireArguments().getInt("id", -1)

        date = SimpleDateFormat("ddMMyyyyhhmmss", Locale.getDefault()).format(Date())

        lblRoute = view.findViewById(R.id.cboRoute)
        lblPickupPoint = view.findViewById(R.id.cboPickupPoint)
        lblHH = view.findViewById(R.id.lblHH)
        lblMM = view.findViewById(R.id.lblMM)
        lblAMPM = view.findViewById(R.id.lblAMPM)

        tokenLicenseViewModel =
            ViewModelProvider(this, viewModelFactory)[TokenLicenseViewModel::class.java]
        mastersViewModel = ViewModelProvider(this, viewModelFactory)[MastersViewModel::class.java]
        errorLogsViewModel =
            ViewModelProvider(this, viewModelFactory)[ErrorLogsViewModel::class.java]
        fleetBusStopViewModel =
            ViewModelProvider(this, viewModelFactory)[FleetBusStopsViewModel::class.java]
        fleetRoutesViewModel =
            ViewModelProvider(this, viewModelFactory)[FleetRoutesViewModel::class.java]
        fleetScheduleViewModel =
            ViewModelProvider(this, viewModelFactory)[FleetScheduleViewModel::class.java]

        tokenLicenseViewModel.retrieveTokenLicenseInfo()
        setupObservers()

        lblRoute!!.setOnClickListener {
            showRouteDialog()
        }

        lblPickupPoint!!.setOnClickListener {
            if (populatePickupPointsList.isNotEmpty()) {
                showPickupPointDialog()
            }
        }

        lblHH!!.setOnClickListener {
            showHoursDialog()
        }

        lblMM!!.setOnClickListener {
            showMinutesDialog()
        }

        lblAMPM!!.setOnClickListener {
            showAMPMDialog()
        }

        val startDateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                startDate()
            }

        val endDateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                endDate()
            }

        binding.lblStartDate.setOnClickListener {
            DatePickerDialog(
                requireContext(), startDateSetListener, cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.lblEndDate.setOnClickListener {
            DatePickerDialog(
                requireContext(), endDateSetListener, cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.btnSubmit.setOnClickListener {
            if (iRouteID == -1 || iPickupPoint == -1 || iHr == -1 || iMm == -1 || iAmPm == -1 ||
                binding.lblStartDate.text.isNullOrEmpty() || binding.lblEndDate.text.isNullOrEmpty()
            ) {
                showError(
                    getString(R.string.details_required),
                    getString(R.string.details_required_desc)
                )
            } else {
                binding.progressBar.visibility = View.VISIBLE
                fleetScheduleViewModel.checkDuplicateBusScheduleInfo(
                    url = sBaseURL,
                    sAuthorization = sToken,
                    iGroupID = iGroupID,
                    iSchoolID = iBranchID,
                    iRouteID = iRouteID,
                    iStopID = iPickupPoint
                )
            }
        }
    }

    private fun endDate() {
        val sdf = SimpleDateFormat(DateUtils.DATE_FORMAT, Locale.US)
        binding.lblEndDate.text = sdf.format(cal.time)
    }

    private fun startDate() {
        val sdf = SimpleDateFormat(DateUtils.DATE_FORMAT, Locale.US)
        binding.lblStartDate.text = sdf.format(cal.time)
    }

    private fun setupObservers() {
        tokenLicenseViewModel.tokenLicenseMutableData.observe(viewLifecycleOwner) {
            sBaseURL = it.sBaseURL
            sToken = it.sToken
            sAssetURL = it.sAssetURL
            iGroupID = it.iGroupID
            iBranchID = it.iBranchID

            populateRoute(sBaseURL, sToken)
            populateHH(sBaseURL, sToken)
            populateMM(sBaseURL, sToken)
            populateAMPM(sBaseURL, sToken)

            if (iEditID > 0) {
                retrieveDetails(iEditID)
            }
        }

        with(fleetRoutesViewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showError(error = it.peekContent())
                with(errorLogsViewModel) {
                    postErrors(it)
                    postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                }
            }
            populateBusRoutesMutableData.observe(viewLifecycleOwner) {
                populateBusRoutesList = it
            }
        }

        with(fleetBusStopViewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showError(error = it.peekContent())
                with(errorLogsViewModel) {
                    postErrors(it)
                    postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                }
            }
        }

        with(fleetScheduleViewModel) {
            retrieveBusScheduleDetailsMutableData.observe(viewLifecycleOwner) { details ->
                for (items in details) {
                    lblRoute!!.text = items.sRouteName
                    iRouteID = items.sRouteID
                    lblPickupPoint!!.text = items.sBusStopName
                    iPickupPoint = items.iBusStopID
                    lblHH!!.text = items.sArrivalTimeHRValue
                    iHr = items.sArrivalTimeHR.toInt()
                    lblMM!!.text = items.sArrivalTimeMMValue
                    iMm = items.sArrivalTimeMM.toInt()
                    lblAMPM!!.text = items.sTimeCycle
                    iAmPm = items.iTimeCycleID
                    binding.lblStartDate.text = items.sStartDateRange.substring(0,10)
                    binding.lblEndDate.text = items.sEndDateRange.substring(0,10)
                }
            }

            checkDuplicateBusScheduleMutableData.observe(viewLifecycleOwner) { duplicate ->
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

            postBusScheduleMutableData.observe(viewLifecycleOwner) { post ->
                if (post > 0) {
                    currentNavController.navigate(R.id.fleetSchedulePickupListFragment)
                } else {
                    binding.progressBar.visibility = View.INVISIBLE
                    showError(
                        getString(R.string.could_not_save_info),
                        getString(R.string.could_not_save_info_desc)
                    )
                }
            }

            amendBusScheduleMutableData.observe(viewLifecycleOwner) { amend ->
                if (amend > 0) {
                    currentNavController.navigate(R.id.fleetSchedulePickupListFragment)
                } else {
                    binding.progressBar.visibility = View.INVISIBLE
                    showError(
                        getString(R.string.could_not_save_info),
                        getString(R.string.could_not_save_info_desc)
                    )
                }
            }
        }
    }

    private fun postInfo() {
        val postInfo = PostFleetBusScheduleParams(
            url = sBaseURL,
            sAuthorization = sToken,
            postBusScheduleItem = PostBusScheduleItem(
                iGroupID = iGroupID,
                iSchoolID = iBranchID,
                iBusStopID = iPickupPoint,
                sArrivalTimeHR = iHr.toString(),
                sArrivalTimeMM = iMm.toString(),
                iTimeCycleID = iAmPm.toString(),
                sStartDateRange = binding.lblStartDate.text.toString(),
                sEndDateRange = binding.lblEndDate.text.toString(),
                iRouteID = iRouteID,
                iWorkflowStatusID = 1,
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
        fleetScheduleViewModel.postBusScheduleInfo(postInfo)
    }

    private fun amendInfo() {
        val amendInfo = PostFleetBusScheduleParams(
            url = sBaseURL,
            sAuthorization = sToken,
            postBusScheduleItem = PostBusScheduleItem(
                id = iEditID,
                iGroupID = iGroupID,
                iSchoolID = iBranchID,
                iBusStopID = iPickupPoint,
                sArrivalTimeHR = iHr.toString(),
                sArrivalTimeMM = iMm.toString(),
                iTimeCycleID = iAmPm.toString(),
                sStartDateRange = binding.lblStartDate.text.toString(),
                sEndDateRange = binding.lblEndDate.text.toString(),
                iRouteID = iRouteID,
                iWorkflowStatusID = 1,
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
        fleetScheduleViewModel.amendBusScheduleInfo(amendInfo)

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
                sErrorSource = FleetScheduleInfoFragment::class.simpleName.toString(),
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }

    /**
     * Populate Route
     */
    private fun populateRoute(sBaseURL: String, sToken: String) {
        fleetRoutesViewModel.populateBusRouteName(
            url = sBaseURL,
            sAuthorization = sToken,
            iGroupID = iGroupID,
            iSchoolID = iBranchID,
            iStatusID = -1
        )

    }

    private fun showRouteDialog() {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.spinner_dialog)

        val dialogHeader: TextView = dialog!!.findViewById(R.id.lblDialogHeading)
        dialogHeader.text = getString(R.string.bus_routes)

        val dialogRecyclerView: RecyclerView = dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
        dialogRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = FBS_RoutesAdapter(
                populateBusRoutesList,
                FBS_RoutesAdapter.OnItemClick {
                    lblRoute!!.text = it.sRouteName
                    iRouteID = it.id
                    if (iRouteID > 0) {
                        populatePickupPoints(iRouteID)
                    }
                })
        }
        dialog!!.show()

        val dialogClose: ImageView = dialog!!.findViewById(R.id.imgClose)
        dialogClose.setOnClickListener { dialog!!.cancel() }
    }

    private fun populatePickupPoints(iRouteID: Int) {
        fleetBusStopViewModel.populateFleetBuStops(
            url = sBaseURL,
            sAuthorization = sToken,
            iGroupID = iGroupID,
            iSchoolID = iBranchID,
            iRouteID = iRouteID,
            iEditMode = -1,
            iStatusID = -1
        )

        fleetBusStopViewModel.populateFleetBuStopsMutableData.observe(viewLifecycleOwner) {
            populatePickupPointsList = it
        }
    }

    private fun showPickupPointDialog() {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.spinner_dialog)

        val dialogHeader: TextView = dialog!!.findViewById(R.id.lblDialogHeading)
        dialogHeader.text = getString(R.string.pickup_point)

        val dialogRecyclerView: RecyclerView = dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
        dialogRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = FBS_PickupPointsAdapter(
                populatePickupPointsList,
                FBS_PickupPointsAdapter.OnItemClick {
                    lblPickupPoint!!.text = it.sBusStopName
                    iPickupPoint = it.iBusRouteID
                })
        }
        dialog!!.show()

        val dialogClose: ImageView = dialog!!.findViewById(R.id.imgClose)
        dialogClose.setOnClickListener { dialog!!.cancel() }
    }

    private fun populateHH(sBaseURL: String, sToken: String) {
        mastersViewModel.populateHours(
            url = sBaseURL + POPULATE_MASTER_LIST,
            sAuthorization = sToken,
            sTableName = MASTERS_TIMETABLE_TIME_RANGE_HR
        )

        mastersViewModel.errorLiveData.observe(viewLifecycleOwner) {
            showError(it.peekContent())
        }
        mastersViewModel.populateHoursMutableData.observe(viewLifecycleOwner) {
            populateHoursList = it
        }

    }

    private fun showHoursDialog() {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.spinner_dialog)

        val dialogHeader: TextView = dialog!!.findViewById(R.id.lblDialogHeading)
        dialogHeader.text = getString(R.string.hh)

        val dialogRecyclerView: RecyclerView = dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
        dialogRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = FBS_HoursAdapter(
                populateHoursList,
                FBS_HoursAdapter.OnItemClick {
                    lblHH!!.text = it.sName
                    iHr = it.id
                })
        }
        dialog!!.show()

        val dialogClose: ImageView = dialog!!.findViewById(R.id.imgClose)
        dialogClose.setOnClickListener { dialog!!.cancel() }
    }

    private fun populateMM(sBaseURL: String, sToken: String) {
        mastersViewModel.populateMinutes(
            url = sBaseURL + POPULATE_MASTER_LIST,
            sAuthorization = sToken,
            sTableName = MASTERS_TIMETABLE_TIME_RANGE_MM
        )

        mastersViewModel.errorLiveData.observe(viewLifecycleOwner) {
            showError(it.peekContent())
        }

        mastersViewModel.populateMinutesMutableData.observe(viewLifecycleOwner) {
            populateMinutesList = it
        }
    }

    private fun showMinutesDialog() {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.spinner_dialog)

        val dialogHeader: TextView = dialog!!.findViewById(R.id.lblDialogHeading)
        dialogHeader.text = getString(R.string.mm)

        val dialogRecyclerView: RecyclerView = dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
        dialogRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = FBS_MinutesAdapter(
                populateMinutesList,
                FBS_MinutesAdapter.OnItemClick {
                    lblMM!!.text = it.sName
                    iMm = it.id
                })
        }
        dialog!!.show()

        val dialogClose: ImageView = dialog!!.findViewById(R.id.imgClose)
        dialogClose.setOnClickListener { dialog!!.cancel() }
    }

    private fun populateAMPM(sBaseURL: String, sToken: String) {
        mastersViewModel.populateTimeCycle(
            url = sBaseURL + POPULATE_MASTER_LIST,
            sAuthorization = sToken,
            sTableName = MASTERS_TIMETABLE_TIME_CYCLE
        )

        mastersViewModel.errorLiveData.observe(viewLifecycleOwner) {
            showError(it.peekContent())
        }

        mastersViewModel.populateTimeCycleMutableData.observe(viewLifecycleOwner) {
            populateTimeCycleList = it
        }
    }

    private fun showAMPMDialog() {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.spinner_dialog)

        val dialogHeader: TextView = dialog!!.findViewById(R.id.lblDialogHeading)
        dialogHeader.text = getString(R.string.am_pm)

        val dialogRecyclerView: RecyclerView = dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
        dialogRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = FBS_TimeCycleAdapter(
                populateTimeCycleList,
                FBS_TimeCycleAdapter.OnItemClick {
                    lblAMPM!!.text = it.sName
                    iAmPm = it.id
                })
        }
        dialog!!.show()

        val dialogClose: ImageView = dialog!!.findViewById(R.id.imgClose)
        dialogClose.setOnClickListener { dialog!!.cancel() }
    }

    private fun retrieveDetails(iEditID: Int) {
        fleetScheduleViewModel.retrieveBusScheduleDetails(
            url = sBaseURL,
            sAuthorization = sToken,
            id = iEditID
        )
    }

}