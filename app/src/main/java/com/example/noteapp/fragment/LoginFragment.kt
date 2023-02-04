package com.example.noteapp.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.noteapp.BaseFragment
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentLoginBinding
import com.example.noteapp.datastore.local.TempData
import com.example.noteapp.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
//class LoginFragment : Fragment()
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    private val viewModel by viewModels<UserViewModel>()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = { inflater, container, attachToParent ->
            FragmentLoginBinding.inflate(inflater, container, attachToParent)
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            viewModel.login(
                binding.etUsername.text.toString(),
                binding.etPassword.text.toString()
            )
        }

        viewModel.loginSuccess.observe(viewLifecycleOwner) {
//            val sharedPreferences =
//                requireContext().getSharedPreferences("idUser", Context.MODE_PRIVATE)

            if (it) {
                viewModel.loginUser.value?.let {
                    TempData.idUser = it.idUser
//                    val editor = sharedPreferences.edit()
//                    editor.putString("idUser", it.idUser.toString())
//                    editor.apply()
                }
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                Toast.makeText(context, "Invalid username/ password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}