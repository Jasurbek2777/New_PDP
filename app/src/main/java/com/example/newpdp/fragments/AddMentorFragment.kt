package com.example.newpdp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.newpdp.R
import com.example.newpdp.databinding.FragmentAddMentorBinding
import com.example.newpdp.models.Mentor
import com.example.newpdp.room.AppDataBase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var db:AppDataBase.AppDatabase
private lateinit var binding:FragmentAddMentorBinding
class AddMentorFragment : Fragment() {
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
        db= AppDataBase.AppDatabase.getInstance(requireContext())
        binding= FragmentAddMentorBinding.inflate(inflater, container, false)
        binding.addMentorToolbarBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.addBtn.setOnClickListener {
            val name = binding.nameEt.text.toString()
            val second_name = binding.secondEt.text.toString()
            val middle_name = binding.middleNameEt.text.toString()
            if (name.isNotEmpty() && second_name.isNotEmpty() && middle_name.isNotEmpty()) {
                db.mentorDao().add(Mentor(name, second_name, middle_name, param1?.toInt()))
                findNavController().popBackStack()
            }
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddMentorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}