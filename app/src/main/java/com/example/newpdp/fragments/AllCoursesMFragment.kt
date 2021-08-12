package com.example.newpdp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.newpdp.R
import com.example.newpdp.adapters.CoursesAdapter
import com.example.newpdp.databinding.FragmentAllCoursesMBinding
import com.example.newpdp.models.Courses
import com.example.newpdp.room.AppDataBase

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var binding: FragmentAllCoursesMBinding
private lateinit var appDatabase: AppDataBase.AppDatabase

class AllCoursesMFragment : Fragment() {
    // TODO: Rename and change types of parameters
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
        binding = FragmentAllCoursesMBinding.inflate(inflater, container, false)
        appDatabase = AppDataBase.AppDatabase.getInstance(requireContext())
        lateinit var courseAdapter: CoursesAdapter
        val list = ArrayList<Courses>(appDatabase.courseDao().getAll())
        courseAdapter = CoursesAdapter(list, object : CoursesAdapter.onCLick {
            override fun itemSetOnCLick(courses: Courses, position: Int) {
                val bundle = Bundle()
                bundle.putString("param1", courses.course_id.toString())
                findNavController().navigate(R.id.mentorsFragment, bundle)
            }
        })
        binding.rv.adapter = courseAdapter
        binding.toolbarBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }
      return  binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AllCoursesMFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}