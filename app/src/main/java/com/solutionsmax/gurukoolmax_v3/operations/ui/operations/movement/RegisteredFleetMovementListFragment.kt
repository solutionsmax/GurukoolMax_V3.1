package com.solutionsmax.gurukoolmax_v3.operations.ui.operations.movement

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
import com.solutionsmax.gurukoolmax_v3.core.common.MethodConstants.FLEET_MOVEMENT_RETRIEVE_LIST
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.TokenViewModel
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentRegisteredFleetMovementListBinding
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.LicenseViewModel
import javax.inject.Inject


class RegisteredFleetMovementListFragment : BaseFragment() {

    private lateinit var binding: FragmentRegisteredFleetMovementListBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenViewModel: TokenViewModel
    private lateinit var licenseViewModel: LicenseViewModel
    private lateinit var fleetMovementViewModel: FleetMovementViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisteredFleetMovementListBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.apply {
            title = getString(R.string.fleet_movement)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener { currentNavController.navigate(R.id.operationsMenuFragment) }
        }

        tokenViewModel = ViewModelProvider(this, viewModelFactory)[TokenViewModel::class.java]
        licenseViewModel = ViewModelProvider(this, viewModelFactory)[LicenseViewModel::class.java]
        fleetMovementViewModel =
            ViewModelProvider(this, viewModelFactory)[FleetMovementViewModel::class.java]

        binding.fabCreateNew.setOnClickListener {
            val bundle = bundleOf("id" to -1)
            currentNavController.navigate(R.id.registeredFleetMovementInfoFragment, bundle)
        }

        tokenViewModel.retrieveTokensFromLocal()
        tokenViewModel.retrieveTokenLiveData.observe(viewLifecycleOwner) { token ->
            licenseViewModel.retrieveLicenseInfo()
            licenseViewModel.retrieveLicenseInfoUseCase.observe(viewLifecycleOwner) { license ->
                fleetMovementViewModel.retrieveFleetMovementList(
                    license.first().rest_url + FLEET_MOVEMENT_RETRIEVE_LIST,
                    token.first().access_token, license.first().group_id, license.first().branch_id,
                    1
                )
                with(fleetMovementViewModel){
                    retrieveFleetMovementListMutableLiveData.observe(
                        viewLifecycleOwner
                    ) {
                        errorLiveData.observe(viewLifecycleOwner){
                            showError(it.peekContent())
                        }
                        binding.progressBar.visibility = View.GONE
                        with(binding.registeredFleetList) {
                            layoutManager = LinearLayoutManager(requireContext())
                            adapter = FleetMovementAdapter(it, FleetMovementAdapter.OnItemClick {
                                val bundle = bundleOf("id" to it)
                                currentNavController.navigate(
                                    R.id.registeredFleetMovementInfoFragment,
                                    bundle
                                )
                            })
                        }
                    }
                }
            }
        }
    }

}