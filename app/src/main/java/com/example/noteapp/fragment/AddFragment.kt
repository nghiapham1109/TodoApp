package com.example.noteapp.fragment

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.work.*
import com.example.noteapp.AlarmReceiver
import com.example.noteapp.MyWork
import com.example.noteapp.R
import com.example.noteapp.activities.MainActivity
import com.example.noteapp.databinding.FragmentAddBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent
    private val viewModel by activityViewModels<NoteViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createNotification()


        binding.btnAdd.setOnClickListener {
//                view ->
//            AddNote(view)

            val IDnote = binding.etIDNote.text.toString()
            val IDUser = binding.etIDUser.text.toString()

            if (IDnote.isNotEmpty() && IDUser.isNotEmpty()) {
                val Title = binding.etTitle.text.toString()
                val Description = binding.etDescription.text.toString()
                val Priority = binding.etPriority.text.toString()
                val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                val currentDateAndTime: String = sdf.format(Date())
                val insertNote =
                    Note(IDnote, IDUser, Title, Description, Priority, currentDateAndTime)
                viewModel.insertNote(insertNote)
                myWork()
                setAlarm()
            }

            findNavController().navigate(R.id.action_addFragment_to_homeFragment)


        }
        binding.btBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

//    fun AddNote(view: View?) {
//        Log.d(tag, "my Message")
//    }

    private fun myWork() {
//        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.NOT_REQUIRED)
//            .setRequiresCharging(true).build()
//        val myWorkRequest: WorkRequest =
//            OneTimeWorkRequest.Builder(MyWork::class.java).setConstraints(constraints).build()
//
//        activity?.let { WorkManager.getInstance(it.applicationContext).enqueue(myWorkRequest) }
    }

    private fun createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "Reminder Channel"
            val description = " Alarm Manager"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("Warning", name, importance)
            channel.description = description
            val notificationManager = context?.let {
                ContextCompat.getSystemService(
                    it,
                    NotificationManager::class.java
                )
            }
            notificationManager?.createNotificationChannel(channel)
        }
    }

    private fun setAlarm() {
        alarmManager = (context?.getSystemService(Context.ALARM_SERVICE) as? AlarmManager)!!
        pendingIntent = Intent(context, AlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(context, 0, intent, 0)
        }
        alarmManager.set(
            AlarmManager.RTC_WAKEUP, 1000, pendingIntent
        )
    }

}