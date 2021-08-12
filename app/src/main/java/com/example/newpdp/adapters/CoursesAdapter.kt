package com.example.newpdp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newpdp.databinding.CoursesItemBinding
import com.example.newpdp.models.Courses

class CoursesAdapter(var list: ArrayList<Courses>, var itemClick: onCLick) : RecyclerView.Adapter<CoursesAdapter.CoursesVh>() {
    inner class CoursesVh(var itemBind: CoursesItemBinding) : RecyclerView.ViewHolder(itemBind.root) {
        fun onBind(courses: Courses, position: Int) {
            itemBind.coursesItemTv.text = courses.coure_name + "\nDevelopment"
            itemBind.cardItemId.setOnClickListener{
                itemClick.itemSetOnCLick(courses, position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesVh {
        return CoursesVh(CoursesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CoursesVh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface onCLick {
        fun itemSetOnCLick(courses: Courses, position: Int)
    }
}