package com.solutionsmax.gurukoolmax_v3.operations.ui.operations.register

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Environment
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
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.model.Image
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.common.MasterTableNames.MASTERS_CONFIGURATION_CALENDAR_YEAR
import com.solutionsmax.gurukoolmax_v3.core.common.MasterTableNames.MASTERS_FLEET_FUEL_TYPES
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_REGISTRATION_AMEND_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_REGISTRATION_CHECK_DUPLICATE
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_REGISTRATION_POST_INFO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_REGISTRATION_POST_PHOTO
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_REGISTRATION_RETRIEVE_DETAILS
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.POPULATE_MASTER_LIST
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.UPLOAD_PHOTO
import com.solutionsmax.gurukoolmax_v3.core.common.PhotoConstants.MEDIA_BUS_IMAGE
import com.solutionsmax.gurukoolmax_v3.core.common.PortalIdConstants
import com.solutionsmax.gurukoolmax_v3.core.data.error_logs.PostErrorLogsItems
import com.solutionsmax.gurukoolmax_v3.core.data.master.PopulateMasterListItem
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Event
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.ErrorLogsViewModel
import com.solutionsmax.gurukoolmax_v3.core.utils.CompressImage
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils.DATE_FORMAT
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils.getMediumDateFormat
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentRegisteredFleetInfoBinding
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.FleetPostPhotoItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.fleet_register.FleetRegisterPostInfoItem
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_register.FleetRegisterPostParams
import com.solutionsmax.gurukoolmax_v3.operations.domain.entity.params.fleet_register.PostFleetPhoto
import com.solutionsmax.gurukoolmax_v3.operations.ui.operations.register.spinner_adapter.RFFuelTypeSpinnerAdapter
import com.solutionsmax.gurukoolmax_v3.operations.ui.operations.register.spinner_adapter.RFManufactureYearSpinnerAdapter
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.MastersViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class RegisteredFleetInfoFragment : BaseFragment() {

    private lateinit var binding: FragmentRegisteredFleetInfoBinding

    companion object {
        var lblManufacturingYear: TextView? = null
        var lblFuelType: TextView? = null
        var iManufactureYearID: Int = -1
        var dialog: Dialog? = null
        var iFuelTypeID: Int = -1
    }

    private lateinit var manufacturingYearListItems: List<PopulateMasterListItem>
    private lateinit var fuelType: List<PopulateMasterListItem>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var registeredFleetViewModel: RegisteredFleetViewModel
    private lateinit var mastersViewModel: MastersViewModel
    private lateinit var errorLogsViewModel: ErrorLogsViewModel
    private var iEditID = -1

    private var images = ArrayList<Image>()
    private lateinit var file: File
    private lateinit var mediaName: String
    private lateinit var compressedImage: String

    private var date: String = ""

    private var cal: Calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisteredFleetInfoBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lblManufacturingYear = view.findViewById(R.id.cboManufactureYear)
        lblFuelType = view.findViewById(R.id.cboFuelType)

        date = SimpleDateFormat("ddMMyyyyhhmmss", Locale.getDefault()).format(Date())

        binding.toolbar.apply {
            title = getString(R.string.register_fleet)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener { currentNavController.navigate(R.id.registeredFleetListFragment) }
        }

        iEditID = requireArguments().getInt("id", -1)

        registeredFleetViewModel =
            ViewModelProvider(this, viewModelFactory)[RegisteredFleetViewModel::class.java]
        mastersViewModel = ViewModelProvider(this, viewModelFactory)[MastersViewModel::class.java]
        errorLogsViewModel =
            ViewModelProvider(this, viewModelFactory)[ErrorLogsViewModel::class.java]

        registeredFleetViewModel.retrieveTokenLicenseInfo()

        setupViewModelObservers()

        lblManufacturingYear!!.setOnClickListener {
            showManufactureYearDialog()
        }

        lblFuelType!!.setOnClickListener {
            showFuelTypeDialog()
        }

        binding.lblBusPhoto.setOnClickListener { getImagePicker().start() }

        binding.btnSubmit.setOnClickListener {
            if (TextUtils.isEmpty(binding.txtVehicleName.text) || TextUtils.isEmpty(binding.txtRegistration.text)
            ) {
                showError(
                    getString(R.string.details_required),
                    getString(R.string.details_required_desc)
                )
            } else {
                binding.progressBar.visibility = View.VISIBLE
                registeredFleetViewModel.checkRegisteredFleetDuplicateInfo(
                    sBaseURL + FLEET_REGISTRATION_CHECK_DUPLICATE,
                    sToken,
                    binding.txtRegistration.text.toString()
                )
            }
        }

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                purchaseDate()
            }

        binding.txtPurchaseDate.setOnClickListener {
            DatePickerDialog(
                requireContext(), dateSetListener, cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun purchaseDate() {
        val sdf = SimpleDateFormat(DATE_FORMAT, Locale.US)
        binding.txtPurchaseDate.text = sdf.format(cal.time)
    }

    private fun setupViewModelObservers() {
        with(registeredFleetViewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showError(error = it.peekContent())
                with(errorLogsViewModel) {
                    postErrors(it)
                    postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                }
            }
            tokenLicenseMutableData.observe(viewLifecycleOwner) {
                sBaseURL = it.sBaseURL
                sToken = it.sToken
                sAssetURL = it.sAssetURL
                iGroupID = it.iGroupID
                iBranchID = it.iBranchID

                populateManufactureYear(sBaseURL, sToken)
                populateFuelType(sBaseURL, sToken)

                if (iEditID > 0) {
                    binding.btnSubmit.text = getString(R.string.edit)
                    retrieveDetails(iEditID)
                }
            }

            checkRegisteredFleetDuplicateMutableData.observe(
                viewLifecycleOwner
            ) { duplicate ->
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

            postRegisteredFleetMutableData.observe(viewLifecycleOwner) {
                if (it > 0) {
                    currentNavController.navigate(R.id.registeredFleetListFragment)
                } else {
                    binding.progressBar.visibility = View.INVISIBLE
                    showError(
                        getString(R.string.could_not_save_info),
                        getString(R.string.could_not_save_info_desc)
                    )
                }
            }

            amendRegisteredFleetMutableData.observe(viewLifecycleOwner) {
                if (it > 0) {
                    currentNavController.navigate(R.id.registeredFleetListFragment)
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
                sErrorSource = RegisteredFleetInfoFragment::class.simpleName.toString(),
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }

    /**
     * Populate Manufacture Year
     */
    private fun populateManufactureYear(sBaseURL: String, sToken: String) {
        mastersViewModel.populateManufactureYear(
            url = sBaseURL + POPULATE_MASTER_LIST,
            sAuthorization = sToken,
            sTableName = MASTERS_CONFIGURATION_CALENDAR_YEAR
        )
        mastersViewModel.errorLiveData.observe(viewLifecycleOwner) {
            showError(it.peekContent())
        }
        mastersViewModel.populateManufactureYearMutableData.observe(viewLifecycleOwner) {
            manufacturingYearListItems = it
        }
    }

    private fun showManufactureYearDialog() {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.spinner_dialog)

        val dialogHeader: TextView = dialog!!.findViewById(R.id.lblDialogHeading)
        dialogHeader.text = getString(R.string.manufacture_year)

        val dialogRecyclerView: RecyclerView = dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
        dialogRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = RFManufactureYearSpinnerAdapter(
                manufacturingYearListItems,
                RFManufactureYearSpinnerAdapter.OnItemClick {
                    lblManufacturingYear!!.text = it.sName
                    iManufactureYearID = it.id
                })
        }
        dialog!!.show()

        val dialogClose: ImageView = dialog!!.findViewById(R.id.imgClose)
        dialogClose.setOnClickListener { dialog!!.cancel() }
    }

    /**
     * Populate Fuel Type
     */
    private fun populateFuelType(sBaseURL: String, sToken: String) {
        mastersViewModel.populateFuelType(
            url = sBaseURL + POPULATE_MASTER_LIST,
            sAuthorization = sToken,
            sTableName = MASTERS_FLEET_FUEL_TYPES
        )
        mastersViewModel.errorLiveData.observe(viewLifecycleOwner) {
            showError(it.peekContent())
        }
        mastersViewModel.populateFuelTypeMutableData.observe(viewLifecycleOwner) {
            fuelType = it
        }
    }

    private fun showFuelTypeDialog() {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(true)
        dialog!!.setContentView(R.layout.spinner_dialog)

        val dialogHeader: TextView = dialog!!.findViewById(R.id.lblDialogHeading)
        dialogHeader.text = getString(R.string.fuel_type)

        val dialogRecyclerView: RecyclerView = dialog!!.findViewById(R.id.spinnerItemsRecyclerView)
        dialogRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = RFFuelTypeSpinnerAdapter(
                fuelType,
                RFFuelTypeSpinnerAdapter.OnItemClick {
                    lblFuelType!!.text = it.sName
                    iFuelTypeID = it.id
                })
        }
        dialog!!.show()

        val dialogClose: ImageView = dialog!!.findViewById(R.id.imgClose)
        dialogClose.setOnClickListener { dialog!!.cancel() }
    }

    private fun getImagePicker(): ImagePicker {
        val imagePicker: ImagePicker = ImagePicker.create(this)
            .language("en")
            .theme(R.style.ImagePickerTheme)
            .includeVideo(false) // include video (false by default)
            .toolbarArrowColor(Color.WHITE) // set toolbar arrow up color
            .toolbarFolderTitle(getString(R.string.folder)) // folder selection title
            .toolbarImageTitle(getString(R.string.tap_to_select)) // image selection title
            .toolbarDoneButtonText(getString(R.string.done)) // done button text

        imagePicker.single() // multi mode (default mode)

        return imagePicker.limit(1) // max images can be selected (99 by default)
            .showCamera(true) // show camera or not (true by default)
            .imageDirectory("Camera") // captured image directory name ("Camera" folder by default)
            .imageFullDirectory(Environment.getExternalStorageDirectory().path) // can be full path
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            images = ImagePicker.getImages(data) as ArrayList<Image>
            printImages(images)
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun printImages(images: java.util.ArrayList<Image>) {
        binding.lblBusPhoto.text = images.first().name
    }

    /**
     * Retrieve Details
     */
    private fun retrieveDetails(iEditID: Int) {
        registeredFleetViewModel.retrieveRegisteredFleetDetails(
            url = sBaseURL + FLEET_REGISTRATION_RETRIEVE_DETAILS,
            sAuthorization = sToken,
            id = iEditID
        )
        with(registeredFleetViewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showError(error = it.peekContent())
            }
            retrieveRegisteredFleetDetailsMutableData.observe(
                viewLifecycleOwner
            ) {
                if (it.isNotEmpty()) {
                    binding.txtVehicleName.setText(it.first().sVehicleName)
                    binding.txtRegistration.setText(it.first().sRegistrationNumber)
                    binding.txtVin.setText(it.first().sVinNumber)
                    binding.txtPurchaseDate.text = it.first().sPurchaseDate.substring(0, 10)
                    binding.txtModel.setText(it.first().sModel)
                    binding.txtMake.setText(it.first().sMake)
                    binding.txtColor.setText(it.first().sColor)
                    binding.txtCubicCapacity.setText(it.first().sCubicCapacity)
                    binding.txtSeatingCapacity.setText(it.first().iSeatingCapacity.toString())
                    binding.txtRegistrationAuthority.setText(it.first().sRegistrationAuthority)
                    binding.cboManufactureYear.text = it.first().sManufactureYear
                    iManufactureYearID = it.first().iManufactureYearID
                    binding.cboFuelType.text = it.first().sFuelType
                    iFuelTypeID = it.first().iFuelTypeID
                } else {
                    showError(
                        getString(R.string.something_went_wrong),
                        getString(R.string.something_went_wrong_desc)
                    )
                }
            }
        }
    }

    /**
     * Post Info
     */
    private fun postInfo() {
        val postInfo = FleetRegisterPostInfoItem(
            iGroupID = iGroupID,
            iBranchID = iBranchID,
            sVehicleName = binding.txtVehicleName.text.toString(),
            sRegistrationNumber = binding.txtRegistration.text.toString(),
            sVinNumber = binding.txtVin.text.toString(),
            sPurchaseDate = binding.txtPurchaseDate.text.toString(),
            sModel = binding.txtModel.text.toString(),
            sMake = binding.txtMake.text.toString(),
            iManufactureYearID = iManufactureYearID,
            iFuelTypeID = iFuelTypeID,
            sColor = binding.txtColor.text.toString(),
            sCubicCapacity = binding.txtCubicCapacity.text.toString(),
            iSeatingCapacity = Integer.parseInt(binding.txtSeatingCapacity.text.toString()),
            sRegistrationAuthority = binding.txtRegistrationAuthority.text.toString(),
            sPhotoRef = "-1",
            sPhotoURL = "-1",
            iUserID = -1,
            iWorkflowStatusID = 1,
            sCreateDate = DateUtils.todayDateTime()
                .getMediumDateFormat(requireContext()),
            sUpdateDate = DateUtils.todayDateTime()
                .getMediumDateFormat(requireContext())
        )
        val postParamsInfo = FleetRegisterPostParams(
            sBaseURL + FLEET_REGISTRATION_POST_INFO,
            sToken,
            postInfo
        )
        registeredFleetViewModel.postRegisteredFleetInfo(postParamsInfo)

    }

    private fun uploadImage(images: java.util.ArrayList<Image>, it: Int) {
        val stringBuffer = StringBuilder()
        var i = 0
        val l = images.size
        while (i < l) {
            stringBuffer.append(images[i].path).append("\n")
            file = File(images[i].path)
            mediaName = file.name
            compressedImage = CompressImage.resizeAndCompressImageBeforeSend(
                requireContext(),
                images[i].path,
                mediaName
            )
            uploadFile(compressedImage, it)
            i++
        }

    }

    private fun uploadFile(compressedImage: String, result: Int) {
        registeredFleetViewModel.uploadFleetImage(
            sBaseURL + UPLOAD_PHOTO, sToken, compressedImage, requireContext()
        )
        with(registeredFleetViewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showError(it.peekContent())
            }
            uploadFleetImageMutableData.observe(viewLifecycleOwner) {
                if (it != null) {
                    postImage(result)
                } else {
                    showError(
                        getString(R.string.could_not_save_image),
                        getString(R.string.could_not_save_image_desc)
                    )
                }
            }
        }
    }

    private fun postImage(result: Int) {
        val file = File(compressedImage)
        val photoName = date + file.name
        val photoItems = FleetPostPhotoItem(
            iVehicleID = result,
            sPhotoRef = sAssetURL + MEDIA_BUS_IMAGE + photoName,
            sPhotoURL = photoName,
            1
        )
        val photo = PostFleetPhoto(
            url = sBaseURL + FLEET_REGISTRATION_POST_PHOTO,
            sAuthorization = sToken,
            photoItems
        )
        registeredFleetViewModel.postRegisteredFleetPhoto(photo)
        with(registeredFleetViewModel) {
            postRegisteredFleetPhotoMutableData.observe(viewLifecycleOwner) {
                if (it > 0) {
                    currentNavController.navigate(R.id.registeredFleetList)
                } else {
                    showError(
                        getString(R.string.could_not_save_image),
                        getString(R.string.could_not_save_image_desc)
                    )
                }
            }
        }
    }

    /**
     * Amend Info
     */
    private fun amendInfo() {
        val amendInfo = FleetRegisterPostInfoItem(
            id = iEditID,
            iGroupID = iGroupID,
            iBranchID = iBranchID,
            sVehicleName = binding.txtVehicleName.text.toString(),
            sRegistrationNumber = binding.txtRegistration.text.toString(),
            sVinNumber = binding.txtVin.text.toString(),
            sPurchaseDate = binding.txtPurchaseDate.text.toString(),
            sModel = binding.txtModel.text.toString(),
            sMake = binding.txtMake.text.toString(),
            iManufactureYearID = iManufactureYearID,
            iFuelTypeID = iFuelTypeID,
            sColor = binding.txtColor.text.toString(),
            sCubicCapacity = binding.txtCubicCapacity.text.toString(),
            iSeatingCapacity = Integer.parseInt(binding.txtSeatingCapacity.text.toString()),
            sRegistrationAuthority = binding.txtRegistrationAuthority.text.toString(),
            sPhotoRef = "-1",
            sPhotoURL = "-1",
            iUserID = -1,
            iWorkflowStatusID = 1,
            sCreateDate = DateUtils.todayDateTime()
                .getMediumDateFormat(requireContext()),
            sUpdateDate = DateUtils.todayDateTime()
                .getMediumDateFormat(requireContext())
        )
        val amendParamsInfo = FleetRegisterPostParams(
            sBaseURL + FLEET_REGISTRATION_AMEND_INFO,
            sToken,
            amendInfo
        )
        registeredFleetViewModel.amendRegisteredFleetInfo(amendParamsInfo)
    }
}