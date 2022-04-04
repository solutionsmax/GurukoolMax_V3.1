package com.solutionsmax.gurukoolmax_v3.core.ui


import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.TokenViewModel
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentRegisterSchoolCodeBinding
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.LicenseViewModel
import javax.inject.Inject


class RegisterSchoolCodeFragment : BaseFragment() {

    private lateinit var binding: FragmentRegisterSchoolCodeBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenViewModel: TokenViewModel
    private lateinit var licenseViewModel: LicenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterSchoolCodeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tokenViewModel = ViewModelProvider(this, viewModelFactory)[TokenViewModel::class.java]
        licenseViewModel = ViewModelProvider(this, viewModelFactory)[LicenseViewModel::class.java]

        tokenViewModel.retrieveTokensFromLocal()
        tokenViewModel.retrieveTokenLiveData.observe(viewLifecycleOwner) {
            sToken = it.first().access_token
        }

        binding.btnSubmit.setOnClickListener {
            if (TextUtils.isEmpty(binding.txtSchoolCode.text)) {
                Snackbar.make(
                    binding.root,
                    getString(R.string.enter_school_empty),
                    Snackbar.LENGTH_LONG
                ).show()
            } else if (!binding.chkTerms.isChecked) {
                Snackbar.make(
                    binding.root,
                    getString(R.string.agree_to_terms_conditions),
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                licenseViewModel.retrieveSiteInfoBySideCode(
                    sToken,
                    1,
                    binding.txtSchoolCode.text.toString()
                )
                licenseViewModel.retrieveInfoBySideCodeLiveData.observe(viewLifecycleOwner) {
                    licenseViewModel.errorLiveData.observe(viewLifecycleOwner) { error ->
                        showError(error.peekContent())
                    }
                    if (it.isNullOrEmpty()) {
                        showError(
                            title = getString(R.string.site_code_error),
                            message = getString(R.string.site_code_error_desc)
                        )
                        binding.txtSchoolCode.setText("")
                    } else {
                        sBaseURL = it.first().rest_url
                        if (sBaseURL.isEmpty()) {
                            showError(
                                title = getString(R.string.site_code_error),
                                message = getString(R.string.site_code_error_desc)
                            )
                            binding.txtSchoolCode.setText("")
                        } else {
                            currentNavController.navigate(R.id.mainMenuFragment)
                        }
                    }
                }
            }
        }
    }

}