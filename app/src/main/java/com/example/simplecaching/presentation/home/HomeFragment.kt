package com.example.simplecaching.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.simplecaching.databinding.FragmentHomeBinding
import com.example.simplecaching.domain.models.Post
import com.example.simplecaching.presentation.adapters.PostsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

private const val TAG = "HomeFragment mohamed"

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var postsAdapter: PostsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            postsAdapter = PostsAdapter(object : PostsAdapter.OnClickListener {
                override fun onClick(post: Post) {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                            post.id
                        )
                    )
                }
            })
            adapter = postsAdapter
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest { state ->
                binding.apply {
                    Log.w(TAG, "onViewCreated: $state")

                    if (state.error != null && state.posts.isEmpty()) {
                        tvError.visibility = View.VISIBLE
                        tvError.text = state.error
                    } else {
                        tvError.visibility = View.GONE
                    }
                    if (state.isLoading)
                        progressBar.visibility = View.VISIBLE
                    else
                        progressBar.visibility = View.GONE
                    if (state.posts.isNotEmpty())
                        postsAdapter.differ.submitList(state.posts)

                }
            }
        }

    }

}