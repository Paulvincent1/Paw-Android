package com.example.paw

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController



class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Add any initialization logic here, such as checking for authentication, loading data, etc.

        // Navigate to the next screen after a delay (e.g., 2 seconds)
        Handler(Looper.getMainLooper()).postDelayed({
            // Navigate to the next screen
            // For example, navigate to your main activity
            navigateToNextScreen()
        }, 2000) // 2000 milliseconds = 2 seconds delay
    }

    private fun navigateToNextScreen() {
        // Get the NavController
        val navController = view?.let { findNavController(it) }

        // Navigate to the next fragment
        navController?.navigate(R.id.action_splashFragment_to_loginFragment2)
    }

}