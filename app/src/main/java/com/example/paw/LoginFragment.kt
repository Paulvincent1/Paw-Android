package com.example.paw

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.paw.R
import com.example.paw.builder.RetrofitBuilder
import com.example.paw.databinding.FragmentLoginBinding
import com.example.paw.model.User
import com.example.paw.service.PawService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var navController: NavController
    private var isExitRequested = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        binding.tvSignup.setOnClickListener {
            navController.navigate(R.id.action_loginFragment2_to_signupFragment)
        }

        login("nathan.example@gmail.com","manzano123")


        requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (isExitRequested) {
                // Exit immediately if the exit request is already in progress
                requireActivity().onBackPressed()
            } else {
                isExitRequested = true
                showExitConfirmationDialog()
            }
        }
    }

    private fun showExitConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Exit Confirmation")
        builder.setMessage("Are you sure you want to exit the app?")
        builder.setPositiveButton("Yes") { _, _ ->
            // User confirmed exit, finish the current activity to exit the app
            requireActivity().finish()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            // User canceled exit, dismiss the dialog
            dialog.dismiss()
            isExitRequested = false
        }
        builder.setOnCancelListener {
            // Handle the case when the user cancels the dialog with the back button
            isExitRequested = false
        }

        val dialog = builder.create()
        dialog.show()
    }
    private fun login(email: String, password: String) {
        val user = RetrofitBuilder.buildService(PawService::class.java)
        val call = user.login(email, password)
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "login", Toast.LENGTH_SHORT).show()
                    val token = response.body()?.token
                    binding.textView4.text = token
                    token?.let { RetrofitBuilder.setAuthToken(it) }
                    findNavController().navigate(R.id.action_loginFragment2_to_bottomNavigationFragment)

                } else {
                    Toast.makeText(context, "bad credentials", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(context, "cant connect", Toast.LENGTH_SHORT).show()
            }
        })
    }
}