package com.example.newpdp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.newpdp.R
import com.example.newpdp.databinding.FragmentAllGroupsBinding
import com.example.newpdp.adapters.PagerAdapter
import com.google.android.material.tabs.TabLayout

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var binding: FragmentAllGroupsBinding
private lateinit var pagerAdapter: PagerAdapter
class AllGroupsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllGroupsBinding.inflate(inflater, container, false)

        pagerAdapter = PagerAdapter(
            childFragmentManager,
            binding.tabLayout.tabCount,
            param1.toString()
        )

        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager.currentItem = tab?.position!!
                if (tab.position == 0) {
                    binding.toolbarAddBtn.visibility = View.INVISIBLE
                } else {
                    binding.toolbarAddBtn.visibility = View.VISIBLE
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        binding.toolbarBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.toolbarAddBtn.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("param1", param1)
            findNavController().navigate(R.id.addGroupFragment, bundle)
        }

        binding.viewPager.adapter = pagerAdapter
        return binding.root
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            AllGroupsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}