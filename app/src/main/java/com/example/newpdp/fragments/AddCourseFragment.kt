package com.example.newpdp.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.newpdp.R
import com.example.newpdp.adapters.CoursesAdapter
import com.example.newpdp.databinding.AddCourseDialogBinding
import com.example.newpdp.databinding.FragmentAddCourseBinding
import com.example.newpdp.models.Courses
import com.example.newpdp.room.AppDataBase

private lateinit var courseAdapter: CoursesAdapter
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var binding: FragmentAddCourseBinding
private lateinit var db: AppDataBase.AppDatabase

class AddCourseFragment : Fragment() {
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
        binding = FragmentAddCourseBinding.inflate(inflater, container, false)
        var list = ArrayList<Courses>()
        if (db.courseDao().getAll().isNotEmpty()) {
            list.addAll(db.courseDao().getAll())
        }

        courseAdapter = CoursesAdapter(list, object : CoursesAdapter.onCLick {
            override fun itemSetOnCLick(courses: Courses, position: Int) {
                val bundle = Bundle()
                list = ArrayList(db.courseDao().getAll())
                for (i in list) {
                    if (i.coure_name == courses.coure_name && i.course_desc == courses.course_desc) {
                        bundle.putString("param1", i.course_id.toString())
                        findNavController().navigate(R.id.courseInfoFragment, bundle)
                    }
                }

            }
        })

        binding.toolbarBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.addBtn.setOnClickListener {
            val builder = AlertDialog.Builder(binding.root.context)
            val dialogBinding = AddCourseDialogBinding.inflate(inflater)
            builder.setView(dialogBinding.root)
            builder.setPositiveButton(
                "Qo'shish"
            ) { dialog, which ->
                val desc = dialogBinding.kursDescEt.text.toString()
                val name = dialogBinding.kursNameEt.text.toString()
                if (desc.isNotEmpty() && name.isNotEmpty()) {
                    db.courseDao().add(Courses(name, desc))
                    list.add(Courses(name, desc))
                    courseAdapter.notifyItemInserted(list.size - 1)
                    courseAdapter.notifyItemRangeInserted(list.size - 1, list.size)
                } else {
                    Toast.makeText(requireContext(), "Fill  the data", Toast.LENGTH_SHORT)
                        .show()
                }
            }.setNegativeButton(
                "Yopish"
            ) { dialog, which -> }.create()
            builder.show()
        }
        binding.rv.adapter = courseAdapter
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddCourseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}