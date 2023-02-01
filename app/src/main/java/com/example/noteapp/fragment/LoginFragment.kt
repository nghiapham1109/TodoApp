package com.example.noteapp.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.noteapp.BaseFragment
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentAddBinding
import com.example.noteapp.databinding.FragmentLoginBinding
import com.example.noteapp.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
//class LoginFragment : Fragment()
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private val viewModel by viewModels<UserViewModel>()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
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
            val sharedPreferences =
                requireContext().getSharedPreferences("idUser", Context.MODE_PRIVATE)
//
            if (it) {
                val idUser = viewModel.getAllUser()
                    .observe(viewLifecycleOwner, Observer { users ->
                        if (users != null && users.isNotEmpty()) {
                            val firstIdUser = users[0].idUser
                            Log.d("TAG", firstIdUser.toString())
                            val editor = sharedPreferences.edit()
                            editor.putString("idUser", firstIdUser.toString())
                            editor.apply()
                        }
                    })

//                editor.putString("idUser", idUser.toString())
//                editor.apply()
////                Log.d("First user editor", editor.toString())
//                Log.d("First user", idUser.toString())
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                Toast.makeText(context, "Invalid username/ password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}