package com.example.newpdp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newpdp.databinding.GroupItemBinding
import com.example.newpdp.models.Group

class Group_adapter(var list: ArrayList<Group>, var itemClick: onCLick) :
    RecyclerView.Adapter<Group_adapter.GroupsVh>() {
    inner class GroupsVh(var itemBind: GroupItemBinding) :
        RecyclerView.ViewHolder(itemBind.root) {
        fun onBind(group: Group, position: Int) {
            itemBind.groupItemTv.text =
                "PDP ${group.group_name}"
            itemBind.show.setOnClickListener {
                itemClick.itemSetOnCLick(group, position)
            }
            itemBind.deleteGroupBtn.setOnClickListener {
                itemClick.itemDeletSetOnCLick(group, position)
            }
            itemBind.edit.setOnClickListener {
                itemClick.itemEditOnCLick(group, position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupsVh {
        return GroupsVh(
            GroupItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GroupsVh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface onCLick {
        fun itemSetOnCLick(group: Group, position: Int)
        fun itemDeletSetOnCLick(group: Group, position: Int)
        fun itemEditOnCLick(group: Group, position: Int)
    }
}