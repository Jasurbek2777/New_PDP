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
import com.example.newpdp.databinding.FragmentMentorsBinding
import com.example.newpdp.databinding.MentorEditDialogBinding
import com.example.newpdp.models.Mentor
import com.example.newpdp.room.AppDataBase
import com.example.pdponline.adapters.Mentor_adapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var appDataBase: AppDataBase.AppDatabase
private lateinit var binding: FragmentMentorsBinding
private lateinit var mentorAdapter: Mentor_adapter

class MentorsFragment : Fragment() {
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
        appDataBase = AppDataBase.AppDatabase.getInstance(requireContext())
        binding = FragmentMentorsBinding.inflate(inflater, container, false)
        val list = arrayListOf<Mentor>()
        for (i in appDataBase.mentorDao().getAll()) {
            if (i.courseId.toString() == param1) {
                list.add(i)
            }
        }

        mentorAdapter = Mentor_adapter(list, object : Mentor_adapter.onCLick {
            override fun itemSetOnCLick(courses: Mentor, position: Int) {
                Toast.makeText(requireContext(), "Mentor clicked", Toast.LENGTH_SHORT).show()

            }

            override fun itemDeletSetOnCLick(mentor: Mentor, position: Int) {
                appDataBase.mentorDao().delete(mentor)
                list.remove(mentor)
                mentorAdapter.notifyItemRemoved(position)
                mentorAdapter.notifyItemRangeRemoved(position, list.size)
            }

            override fun itemEditOnCLick(mentor: Mentor, position: Int) {
                val builder = AlertDialog.Builder(binding.root.context)
                val dialogBinding = MentorEditDialogBinding.inflate(inflater)
                builder.setView(dialogBinding.root)
                dialogBinding.fName.setText(mentor.mentor_name.toString())
                dialogBinding.sName.setText(mentor.second_name.toString())
                dialogBinding.tName.setText(mentor.third_name.toString())
                builder.setPositiveButton("O'zgartirish", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        val name = dialogBinding.fName.text.toString()
                        val sName = dialogBinding.sName.text.toString()
                        val tName = dialogBinding.tName.text.toString()
                        if (name.isNotEmpty() && sName.isNotEmpty() && tName.isNotEmpty()) {
                            appDataBase.mentorDao().edit(
                                Mentor(
                                    mentor.mentor_id,
                                    name,
                                    sName,
                                    tName,
                                    mentor.courseId
                                )
                            )
                            list[position] =
                                Mentor(mentor.mentor_id, name, sName, tName, mentor.mentor_id)
                            mentorAdapter.notifyItemChanged(position)

                            mentorAdapter.notifyItemRangeChanged(position, list.size)
                        } else {
                            Toast.makeText(requireContext(), "Fill  the data", Toast.LENGTH_SHORT)
                                .show()
                        }

                    }
                }).setNegativeButton("Yopish"
                ) { dialog, which -> }.create()
                builder.show()
            }

        })
        binding.addMentorBtn.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("param1", param1)
            findNavController().navigate(R.id.addMentorFragment, bundle)

        }
        val course = appDataBase.courseDao().getById(param1!!.toInt())
        binding.addMentorsToolbarTv.text = course.coure_name
        binding.addMentorToolbarBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.mentorsRv.adapter = mentorAdapter

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MentorsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}