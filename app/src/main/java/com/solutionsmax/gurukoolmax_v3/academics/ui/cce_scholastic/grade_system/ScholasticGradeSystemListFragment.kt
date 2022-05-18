package com.solutionsmax.gurukoolmax_v3.academics.ui.cce_scholastic.grade_system

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
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.RetrieveGradeByListScholasticGrading
import com.solutionsmax.gurukoolmax_v3.academics.domain.entity.params.RetrieveListScholasticGrading
import com.solutionsmax.gurukoolmax_v3.academics.ui.cce_scholastic.CceScholasticViewModel
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants
import com.solutionsmax.gurukoolmax_v3.core.common.PortalIdConstants
import com.solutionsmax.gurukoolmax_v3.core.data.error_logs.PostErrorLogsItems
import com.solutionsmax.gurukoolmax_v3.core.exception.Failure
import com.solutionsmax.gurukoolmax_v3.core.functional.Event
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.ErrorLogsViewModel
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils
import com.solutionsmax.gurukoolmax_v3.core.utils.DateUtils.getMediumDateFormat
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentScholasticGradeSystemListBinding
import com.solutionsmax.gurukoolmax_v3.operations.data.Academics
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.TokenLicenseViewModel
import javax.inject.Inject


class ScholasticGradeSystemListFragment : BaseFragment() {

    private lateinit var binding: FragmentScholasticGradeSystemListBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenLicenseViewModel: TokenLicenseViewModel
    private lateinit var errorLogsViewModel: ErrorLogsViewModel
    private lateinit var cceScholasticViewModel: CceScholasticViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentScholasticGradeSystemListBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabCreateNew.setOnClickListener {
            val bundle = bundleOf("menu" to "-1")
            currentNavController.navigate(R.id.scholasticGradeSystemInfoFragment, bundle)
        }

        binding.progress.visibility = View.VISIBLE

        binding.toolbar.apply {
            title = getString(R.string.cce_scholastic_grade_system_list)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            val bundle = bundleOf("menu" to Academics.CCE_SCHOLASTIC)
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
        cceScholasticViewModel =
            ViewModelProvider(this, viewModelFactory)[CceScholasticViewModel::class.java]

        tokenLicenseViewModel.retrieveTokenLicenseInfo()
        setUpObservers()
    }

    private fun setUpObservers() {
        tokenLicenseViewModel.tokenLicenseMutableData.observe(viewLifecycleOwner) {
            sToken = it.sToken
            sBaseURL = it.sBaseURL

            cceScholasticViewModel.retrieveListScholasticGrading(
                RetrieveListScholasticGrading(
                    url = sBaseURL,
                    sAuthorization = sToken,
                    iGroupID = 1,
                    iSchoolID = 1,
                    iBoardID = -1,
                    iClassID = -1,
                    iStatusID = -1
                )
            )

            with(cceScholasticViewModel) {
                errorLiveData.observe(viewLifecycleOwner) { error ->
                    showError(error.peekContent())
                    with(errorLogsViewModel) {
                        postErrors(error)
                        postErrorLogsMutableData.observe(viewLifecycleOwner) {}
                    }
                }
                mutableRetrieveListScholasticGrading.observe(viewLifecycleOwner) {
                    binding.progress.visibility = View.GONE
                    with(binding.scholasticGradeList) {
                        layoutManager = LinearLayoutManager(context)
                        adapter = ScholasticGradeSystemAdapter(
                            it,
                            ScholasticGradeSystemAdapter.OnItemClick {
                                val bundle = bundleOf("id" to it)
                                currentNavController.navigate(
                                    R.id.scholasticGradeSystemInfoFragment,
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
                sErrorSource = ScholasticGradeSystemListFragment::class.simpleName.toString(),
                sCreateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext()),
                sUpdateDate = DateUtils.todayDateTime()
                    .getMediumDateFormat(requireContext())
            )
        )
    }

}