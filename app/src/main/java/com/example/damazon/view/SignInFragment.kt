package com.example.damazon.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.damazon.R
//import com.example.damazon.databinding.FragmentFirstBinding
import com.example.damazon.databinding.SignInFragmentBinding
import com.example.damazon.model.SignInViewModel
import com.example.damazon.utils.FragmentCommunicator

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SignInFragment : Fragment() {

    private var _binding: SignInFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel by viewModels<SignInViewModel>()
    var isValid: Boolean = false
    private lateinit var communicator: FragmentCommunicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = SignInFragmentBinding.inflate(inflater, container, false)
        communicator = requireActivity() as OnboardingActivity
        setupView()
        return binding.root

    }

    private fun setupView(){
        binding.loginButton.setOnClickListener{
            communicator.showLoader(true)
            findNavController().navigate(R.id.action_signInFragment_to_SecondFragment)
            //Toast.makeText(activity, "Ingreso no valido?", Toast.LENGTH_SHORT).show()
        }
        binding.emailTIET.addTextChangedListener {
            if(binding.emailTIET.text.toString().isEmpty()){
                binding.emailTIL.error = "Por favor introduce un correo"
                isValid = false
            } else {
                isValid = true
            }
        }
        binding.passwordTIET.addTextChangedListener{
            if(binding.passwordTIET.text.toString().isEmpty()){
                binding.passwordTIL.error = "Por favor introduce una contraseña"
                isValid = false
            } else {
                isValid = true
            }
        }
    }

    private fun requestLogin() {
        viewModel.requestSignIn(binding.emailTIET.text.toString(),
            binding.passwordTIET.text.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}