package com.example.damazon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.damazon.databinding.FragmentProductDetailBinding
import com.example.damazon.model.Product

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_Second2Fragment)
        }
    }


    fun setupObservers() {
    viewModel.productInfo.observe(viewLifecycleOwner) { product ->
        showProductInfo(product)
    }
        viewModel.loaderState
    }



    fun showProductInfo(product: Product) {
        binding.titleTextView.text = product.title
        binding.priceTextView.text = product.price.toString()
        binding.descriptionTextView.text = product.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}