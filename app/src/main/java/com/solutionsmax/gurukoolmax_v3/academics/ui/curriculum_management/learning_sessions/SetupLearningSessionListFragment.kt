package com.solutionsmax.gurukoolmax_v3.academics.ui.curriculum_management.learning_sessions

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.RetrieveLearningSessionListParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.SetCurriculumStatusParams
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.SetLearningSessionStatusParams
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants
import com.solutionsmax.gurukoolmax_v3.core.common.PortalIdConstants
import com.solutionsmax.gurukoolmax_v3.core.data.error_logs.PostErrorLogsItems
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Event
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.ErrorLogsViewModel
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils.getMediumDateFormat
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentSetupLearningSessionListBinding
import com.solutionsmax.gurukoolmax_v3.operations.data.Academics
import com.solutionsmax.gurukoolmax_v3.operations.data.OperationMenuConstants
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import javax.inject.Inject


class SetupLearningSessionListFragment : BaseFragment() {
    private lateinit var binding: FragmentSetupLearningSessionListBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenLicenseViewModel: TokenLicenseViewModel
    private lateinit var errorLogsViewModel: ErrorLogsViewModel
    private lateinit var setupLearningSessionViewModel: SetupLearningSessionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSetupLearningSessionListBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.apply {
            title = getString(R.string.lesson_planner_management)
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

        tokenLicenseViewModel =
            ViewModelProvider(this, viewModelFactory)[TokenLicenseViewModel::class.java]
        errorLogsViewModel =
            ViewModelProvider(this, viewModelFactory)[ErrorLogsViewModel::class.java]
        setupLearningSessionViewModel =
            ViewModelProvider(this, viewModelFactory)[SetupLearningSessionViewModel::class.java]

        binding.fabCreateNew.setOnClickListener {
            val bundle = bundleOf("id" to -1)
            currentNavController.navigate(R.id.setupLearningSessionInfoFragment, bundle)
        }

        tokenLicenseViewModel.retrieveTokenLicenseInfo()
        setUpObservers()
    }

    private fun setUpObservers() {
        tokenLicenseViewModel.tokenLicenseMutableData.observe(viewLifecycleOwner) {
            sBaseURL = it.sBaseURL
            sToken = it.sToken
            iGroupID = it.iGroupID
            iBranchID = it.iBranchID

            retrieveLearningSessionList(sBaseURL, sToken, iGroupID, iBranchID)

            with(setupLearningSessionViewModel) {
                errorLiveData.observe(viewLifecycleOwner) { error ->
                    showError(error.peekContent())
                    with(errorLogsViewModel) {
                        postErrors(error)
                        postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                    }
                }
                retrieveLearningSessionListMutableData.observe(viewLifecycleOwner) {
                    with(binding.scholasticGradeList) {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = SetupLearningSessionListAdapter(
                            it,
                            SetupLearningSessionListAdapter.OnItemClick { item ->
                                val alertBuilder: AlertDialog.Builder =
                                    AlertDialog.Builder(requireContext())
                                alertBuilder.setTitle(getString(R.string.choose_an_option))
                                val options = arrayOf("Edit", "Submit for Review")
                                alertBuilder.setItems(
                                    options
                                ) { dialog, which ->
                                    when (which) {
                                        0 -> {
                                            val bundle = bundleOf("id" to item)
                                            currentNavController.navigate(
                                                R.id.setupLearningSessionInfoFragment,
                                                bundle
                                            )
                                        }
                                        1 -> {
                                            setupLearningSessionViewModel.setLearningSessionStatus(
                                                SetLearningSessionStatusParams(
                                                    sBaseURL, sToken, 3, item
                                                )
                                            )
                                            setupLearningSessionViewModel.mutableSetLearningSessionStatus.observe(
                                                viewLifecycleOwner
                                            ) { status ->
                                                if (status > 0) {
                                                    retrieveLearningSessionList(
                                                        sBaseURL,
                                                        sToken,
                                                        iGroupID,
                                                        iBranchID
                                                    )
                                                } else {
                                                    showError(
                                                        getString(R.string.could_not_update_status),
                                                        getString(R.string.could_not_update_status_desc)
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                                val dialog: AlertDialog = alertBuilder.create()
                                dialog.show()
                            })

                        addOnScrollListener(object : RecyclerView.OnScrollListener() {
                            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                                super.onScrolled(recyclerView, dx, dy)
                                if (dy > 0 || dy < 0 && binding.fabCreateNew.isShown) {
                                    binding.fabCreateNew.hide();
                                }
                            }

                            override fun onScrollStateChanged(
                                recyclerView: RecyclerView,
                                newState: Int
                            ) {
                                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                                    binding.fabCreateNew.show();
                                }
                                super.onScrollStateChanged(recyclerView, newState)
                            }
                        })
                    }
                }
            }
        }
    }

    private fun retrieveLearningSessionList(
        sBaseURL: String,
        sToken: String,
        iGroupID: Int,
        iBranchID: Int
    ) {
        setupLearningSessionViewModel.retrieveLearningSessionListInfo(
            RetrieveLearningSessionListParams(
                url = sBaseURL,
                sToken = sToken,
                iGroupID = iGroupID,
                iSchoolID = iBranchID,
                iBoardID = -1,
                iClassStandardID = -1,
                iSubjectID = -1,
                iCurriculumID = -1,
                iFacultyID = -1,
                iStatusID = -1
            )
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
                sErrorSource = SetupLearningSessionListFragment::class.simpleName.toString(),
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }

}