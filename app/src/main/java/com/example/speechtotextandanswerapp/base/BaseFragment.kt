package com.example.speechtotextandanswerapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.speechtotextandanswerapp.ui.loading.LoadingDialog
import com.example.speechtotextandanswerapp.ui.main.MainViewModel

abstract class BaseFragment<VB : ViewDataBinding, VM: ViewModel> : Fragment() {
    protected lateinit var viewBinding: VB
    protected lateinit var viewModel: VM
    abstract val layout: Int
    abstract val viewModelClass:Class<VM>

    protected lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[viewModelClass]
        loadingDialog = LoadingDialog(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layout, container, false)
        return viewBinding.root
    }
}