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
import com.example.newpdp.databinding.FragmentGroupRvBinding
import com.example.newpdp.databinding.MentorEditDialogBinding
import com.example.newpdp.models.Group
import com.example.newpdp.models.Mentor
import com.example.newpdp.room.AppDataBase
import com.example.newpdp.adapters.Group_adapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var db: AppDataBase.AppDatabase
private lateinit var binding: FragmentGroupRvBinding
private lateinit var adapter_group: Group_adapter

class GroupRvFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var list = ArrayList<Group>()
    private var list1 = ArrayList<Group>()
    private var listEnd = ArrayList<Group>()
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
        binding = FragmentGroupRvBinding.inflate(inflater, container, false)
        db = AppDataBase.AppDatabase.getInstance(requireContext())
        list = ArrayList()
        list1 = ArrayList()
        listEnd = ArrayList()
        for (i in db.groupDao().getAll()) {
            if (param2.toString() == i.group_course_id.toString()) {
                if (i.group_status == 0) {
                    list.add(i)
                } else {
                    list1.add(i)
                }
            }
        }
        if (param1 == "1") {
            listEnd = (list)
        }
        else {
            listEnd = (list1)
        }
        adapter_group = Group_adapter(listEnd, object : Group_adapter.onCLick {
            override fun itemSetOnCLick(group: Group, position: Int) {
                var bundle = Bundle()
                bundle.putString("param1", group.group_id.toString())
                bundle.putString("param2", group.group_course_id.toString())
                findNavController().navigate(R.id.oneGroupFragment, bundle)
            }

            override fun itemDeletSetOnCLick(group: Group, position: Int) {
                db.groupDao().delete(group)
                list.remove(group)
                adapter_group.notifyItemRemoved(position)
                adapter_group.notifyItemRangeChanged(position, list.size)


            }

            override fun itemEditOnCLick(group: Group, position: Int) {
                val allMentor = db.mentorDao().getAll()
                var mentor = Mentor()
                for (i in allMentor) {
                    if (i.mentor_id == group.group_mentor_id) {
                        mentor = i
                    }
                }
                val builder = AlertDialog.Builder(binding.root.context)
                val dialogBinding = MentorEditDialogBinding.inflate(inflater)
                builder.setView(dialogBinding.root)
                dialogBinding.fName.setText(group.group_name.toString())
                dialogBinding.sName.setText("${mentor.mentor_name} ${mentor.second_name}")
                dialogBinding.tName.setText(group.group_lesson_time.toString())
                builder.setPositiveButton("O'zgartirish"
                ) { dialog, which ->
                    val name = dialogBinding.fName.text.toString()
                    val sName = dialogBinding.sName.text.toString()
                    val tName = dialogBinding.tName.text.toString()
                    if (name.isNotEmpty() && sName.isNotEmpty() && tName.isNotEmpty()) {
                        db.groupDao().edit(
                            Group(
                                group.group_id,
                                name,
                                group.group_status,
                                group.group_course_id,
                                group.group_mentor_id,
                                group.group_lesson_day,
                                tName
                            )
                        )
                        adapter_group.notifyItemChanged(position)
                        adapter_group.notifyItemRangeChanged(position, list.size)
                    } else {
                        Toast.makeText(requireContext(), "Fill  the data", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                    .setNegativeButton("Yopish", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                        }
                    }).create()
                builder.show()
            }

        })
        binding.rv.adapter = adapter_group
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GroupRvFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}