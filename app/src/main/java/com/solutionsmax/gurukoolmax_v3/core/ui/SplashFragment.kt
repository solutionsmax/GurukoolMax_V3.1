package com.solutionsmax.gurukoolmax_v3.core.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.slider.SliderFourFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.slider.SliderOneFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.slider.SliderThreeFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.slider.SliderTwoFragment
import com.solutionsmax.gurukoolmax_v3.core.ui.viewmodel.TokenViewModel
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentSplashBinding
import com.solutionsmax.gurukoolmax_v3.operations.ui.viewmodel.LicenseViewModel
import javax.inject.Inject

class SplashFragment : BaseFragment() {

    private lateinit var binding: FragmentSplashBinding
    private val fragmentList = ArrayList<Fragment>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tokenViewModel: TokenViewModel
    private lateinit var licenseViewModel: LicenseViewModel
    private var siteCodeExist: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requireActivity().window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tokenViewModel = ViewModelProvider(this, viewModelFactory)[TokenViewModel::class.java]
        licenseViewModel = ViewModelProvider(this, viewModelFactory)[LicenseViewModel::class.java]

        tokenViewModel.getRemoteToken()
        licenseViewModel.retrieveLicenseInfo()

        licenseViewModel.retrieveLicenseInfoUseCase.observe(viewLifecycleOwner) {
            siteCodeExist = it.isNotEmpty()
        }

        val adapter = SplashViewPager(requireActivity())
        binding.splashViewPager.adapter = adapter
        fragmentList.addAll(
            listOf(
                SliderOneFragment(),
                SliderTwoFragment(),
                SliderThreeFragment(),
                SliderFourFragment()
            )
        )
        adapter.setFragmentList(fragmentList)
        binding.indicatorLayout.setIndicatorCount(adapter.itemCount)
        binding.indicatorLayout.selectCurrentPosition(0)
        registerListeners()
    }

    private fun registerListeners() {
        binding.splashViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position < fragmentList.lastIndex) {
                    binding.lblSkip.visibility = View.VISIBLE
                    //tvNext.text = "Next"
                } else {
                    binding.lblSkip.visibility = View.GONE
                    //tvNext.text = "Get Started"
                }
            }
        })
        binding.lblSkip.setOnClickListener {
            if (checkPermission()){
                currentNavController.navigate(R.id.mainMenuFragment)
            }
        }

        binding.imgNext.setOnClickListener {
            val position = binding.splashViewPager.currentItem
            if (position < fragmentList.lastIndex) {
                binding.splashViewPager.currentItem = position + 1
            } else {
                currentNavController.navigate(R.id.mainMenuFragment)
            }
        }
    }

    class SplashViewPager(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        private val fragmentList = ArrayList<Fragment>()
        override fun getItemCount(): Int = fragmentList.size

        override fun createFragment(position: Int): Fragment = fragmentList[position]

        fun setFragmentList(list: List<Fragment>) {
            fragmentList.clear()
            fragmentList.addAll(list)
            notifyDataSetChanged()
        }
    }

}