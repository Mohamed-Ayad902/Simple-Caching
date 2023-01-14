package com.example.simplecaching.presentation.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.simplecaching.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

private const val TAG = "DetailsFragment mohamed"

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val postId = args.id
        viewModel.getPost(postId)

        lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest { state ->
                Log.e(TAG, "onViewCreated: $state")
                binding.apply {
                    if (state.isLoading)
                        progressBar.visibility = View.VISIBLE
                    else
                        progressBar.visibility = View.GONE
                    if (state.error != null && state.post == null) {
                        tvError.text = state.error
                        tvError.visibility = View.VISIBLE
                    } else
                        tvError.visibility = View.GONE
                    if (state.post != null) {
                        tvTitle.text = state.post.title
                        tvPostId.text = state.post.id.toString()
                        tvBody.text = state.post.body
                    }
                }
            }
        }

    }

}