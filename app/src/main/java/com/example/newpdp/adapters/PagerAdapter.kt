package com.example.newpdp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.newpdp.fragments.GroupRvFragment

class PagerAdapter(manager: FragmentManager, var tabsCount: Int, var coursId: String) :
    FragmentPagerAdapter(manager) {
    override fun getCount(): Int = tabsCount

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                GroupRvFragment.newInstance("0", coursId)
            }
            1 -> {
                GroupRvFragment.newInstance("1", coursId)
            }
            else -> GroupRvFragment()
        }
    }
}