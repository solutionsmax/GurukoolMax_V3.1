package com.solutionsmax.gurukoolmax_v3.operations.ui.on_board_attendance

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.solutionsmax.gurukoolmax_v3.R
import com.solutionsmax.gurukoolmax_v3.core.ui.base.BaseFragment
import com.solutionsmax.gurukoolmax_v3.databinding.FragmentOnBoardAttendanceBinding


class OnBoardAttendanceFragment : BaseFragment() {

    private lateinit var binding: FragmentOnBoardAttendanceBinding
    private lateinit var codeScannerView: CodeScanner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardAttendanceBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.apply {
            title = getString(R.string.on_board_attendance)
            setTitleTextColor(resources.getColor(R.color.white, activity?.theme))
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener { currentNavController.navigate(R.id.operationsMenuFragment) }
        }

        codeScannerView = CodeScanner(requireActivity(), binding.scannerView)
        codeScannerView.decodeCallback = DecodeCallback {
            activity?.runOnUiThread {
                Toast.makeText(context, it.text, Toast.LENGTH_LONG).show()
            }
        }
        binding.scannerView.setOnClickListener {
            codeScannerView.startPreview()
        }
    }

    override fun onStart() {
        super.onStart()
        codeScannerView.startPreview()
    }

    override fun onPause() {
        codeScannerView.releaseResources()
        super.onPause()
    }
}