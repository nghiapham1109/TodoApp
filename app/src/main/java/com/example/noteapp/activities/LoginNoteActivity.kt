package com.example.noteapp.activities
//
//import android.content.Intent
//import android.os.Bundle
//import androidx.activity.viewModels
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import com.example.noteapp.adapter.NoteAdapter
//import com.example.noteapp.databinding.ActivityLoginBinding
//import com.example.noteapp.model.User
//import com.example.noteapp.viewmodel.NoteViewModel
//import com.example.noteapp.viewmodel.UserViewModel
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class LoginNoteActivity : AppCompatActivity() {
//    lateinit var userViewModel: UserViewModel
//    private lateinit var binding: ActivityLoginBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityLoginBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        val userName = binding.etUsername.text.toString()
//        val passWord = binding.etPassword.text.toString()
//
//        binding.btnLogin.setOnClickListener {
//            if (userName == "gage" && passWord == "123") {
//                userViewModel.getUserLoginDataStatus(userName, passWord)
//                startActivity(Intent(applicationContext, MainActivity::class.java))
//                this.finish()
//            }
//            if (userName == "ace" && passWord == "123") {
//                userViewModel.getUserLoginDataStatus(userName, passWord)
//                startActivity(Intent(applicationContext, MainActivity::class.java))
//                this.finish()
//            }
//            else {
//                startActivity(Intent(applicationContext, LoginNoteActivity::class.java))
//                this.finish()
//            }
//        }
//    }
//}