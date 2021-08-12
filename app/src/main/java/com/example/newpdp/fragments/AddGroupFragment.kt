package com.example.newpdp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.newpdp.R
import com.example.newpdp.databinding.FragmentAddGroupBinding
import com.example.newpdp.models.Group
import com.example.newpdp.room.AppDataBase

private lateinit var binding: FragmentAddGroupBinding
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var db: AppDataBase.AppDatabase

class AddGroupFragment : Fragment() {
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
        binding = FragmentAddGroupBinding.inflate(inflater, container, false)
        binding.addBtn.setOnClickListener {
            val name = binding.groupName.text.toString()
            val mentor = binding.guruhMentor.text.toString()
            val date = binding.groupTime.text.toString()

            if (name.isNotEmpty() && mentor.isNotEmpty() && date.isNotEmpty()) {
                db.groupDao().add(Group(name, 0, param1?.toInt(), 0, date, "Mon-Third-fri"))
                findNavController().popBackStack()
            }
        }
        binding.addMentorToolbarBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddGroupFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}