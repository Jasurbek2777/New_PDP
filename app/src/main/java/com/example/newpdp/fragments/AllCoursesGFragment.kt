package com.example.newpdp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.newpdp.R
import com.example.newpdp.adapters.CoursesAdapter
import com.example.newpdp.databinding.FragmentAllCoursesGBinding
import com.example.newpdp.models.Courses
import com.example.newpdp.room.AppDataBase

private lateinit var binding: FragmentAllCoursesGBinding
private const val ARG_PARAM1 = "param1"
private lateinit var db: AppDataBase.AppDatabase
private const val ARG_PARAM2 = "param2"
private lateinit var courseAdapter: CoursesAdapter

class AllCoursesGFragment : Fragment() {
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
    ): View? {
        db = AppDataBase.AppDatabase.getInstance(requireContext())
        binding = FragmentAllCoursesGBinding.inflate(inflater, container, false)

        val list = ArrayList<Courses>(db.courseDao().getAll())
        courseAdapter = CoursesAdapter(list, object : CoursesAdapter.onCLick {
            override fun itemSetOnCLick(courses: Courses, position: Int) {
                val bundle = Bundle()
                bundle.putString("param1", courses.course_id.toString())
                findNavController().navigate(
                    R.id.allGroupsFragment, bundle
                )
            }
        })
        binding.rv.adapter = courseAdapter
        binding.toolbarBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }


        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AllCoursesGFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}