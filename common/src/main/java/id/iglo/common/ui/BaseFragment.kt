package id.iglo.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import id.iglo.common.BR
import id.iglo.common.base_response.AppResponse

abstract class BaseFragment<VM : BaseViewModel, Binding : ViewDataBinding> : Fragment() {
    abstract val vm: VM
    lateinit var binding: Binding
    abstract val layoutResourceId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<Binding>(inflater, layoutResourceId, container, false)
        initBinding(binding)
        binding.setVariable(BR.vm, vm)
        binding.lifecycleOwner = this
        return binding.root
    }

    open fun initBinding(binding: Binding) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        parentFragment?.let {
            if (it is BaseFragment<*, *>) {
                vm.parent = it.vm
            }
        }

        vm.navigationEvent.observe(this) {
            findNavController().navigate(it)
        }

        vm.popBackStackEvent.observe(this) {
            findNavController().popBackStack()
        }
    }

    fun <T> observeResponseData(
        data: MutableLiveData<AppResponse<T>>,
        success: ((T) -> Unit),
        error: ((Throwable) -> Unit)?,
        loading: (() -> Unit) = {}
    ) {
        data.observe(this) { response ->
            when (response) {
                is AppResponse.AppResponseSuccess -> {
                    response.data?.let { success?.invoke(it) }
                }

                is AppResponse.AppResponseError -> {
                    response.error?.let { error?.invoke(it) }
                }

                is AppResponse.AppResponseLoading -> {
                    loading?.invoke()
                }
            }
        }
    }
}