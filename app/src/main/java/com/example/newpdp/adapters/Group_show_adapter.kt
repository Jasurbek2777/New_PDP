package com.example.pdponline.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newpdp.databinding.MentorsItemBinding
import com.example.newpdp.models.Pupil

class Group_show_adapter(var list: ArrayList<Pupil>, var itemClick: onCLick) :
    RecyclerView.Adapter<Group_show_adapter.GroupsVh>() {
    inner class GroupsVh(var itemBind: MentorsItemBinding) :
        RecyclerView.ViewHolder(itemBind.root) {
        fun onBind(pupil: Pupil, position: Int) {
            itemBind.mentorItemTv.text=
                "${pupil.pupil_name}  ${pupil.second_name}\n${pupil.third_name}"

            itemBind.deleteMentorBtn.setOnClickListener {
                itemClick.itemDeletSetOnCLick(pupil, position)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Group_show_adapter.GroupsVh {
        return GroupsVh(
            MentorsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Group_show_adapter.GroupsVh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface onCLick {
//        fun itemSetOnCLick(courses: Group, position: Int)
        fun itemDeletSetOnCLick(pupil: Pupil, position: Int)
//        fun itemEditOnCLick(courses: Group, position: Int)
    }
}