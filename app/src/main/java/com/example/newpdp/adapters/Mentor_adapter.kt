package com.example.pdponline.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newpdp.databinding.MentorsItemBinding
import com.example.newpdp.models.Mentor

class Mentor_adapter(var list: ArrayList<Mentor>, var itemClick: onCLick) :
    RecyclerView.Adapter<Mentor_adapter.MentorsVh>() {
    inner class MentorsVh(var itemBind: MentorsItemBinding) :
        RecyclerView.ViewHolder(itemBind.root) {
        fun onBind(mentor: Mentor, position: Int) {
            itemBind.mentorItemTv.text =
                "${mentor.second_name}  ${mentor.mentor_name}\n${mentor.third_name}"
            itemBind.mentorItemTv.setOnClickListener {
                itemClick.itemSetOnCLick(mentor, position)
            }
            itemBind.deleteMentorBtn.setOnClickListener {
                itemClick.itemDeletSetOnCLick(mentor, position)
            }
            itemBind.editMentorBtn.setOnClickListener {
                itemClick.itemEditOnCLick(mentor, position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Mentor_adapter.MentorsVh {
        return MentorsVh(
            MentorsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Mentor_adapter.MentorsVh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface onCLick {
        fun itemSetOnCLick(courses: Mentor, position: Int)
        fun itemDeletSetOnCLick(courses: Mentor, position: Int)
        fun itemEditOnCLick(courses: Mentor, position: Int)
    }
}