package com.example.noteapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    private var _binding: ViewBinding? = null

    @Suppress("UNCHECKED_CAST")
    val binding: VB
        get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return bindingInflater.invoke(inflater, container, false).apply {
            _binding = this
        }.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}