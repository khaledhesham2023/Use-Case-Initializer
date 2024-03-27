package com.example.speechtotextandanswerapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.speechtotextandanswerapp.databinding.ItemQuestionBinding
import com.example.speechtotextandanswerapp.ui.model.Question
import com.example.speechtotextandanswerapp.R
import com.example.speechtotextandanswerapp.utils.QuestionsUtils

class QuestionsAdapter(private var oldData: ArrayList<Question>) : Adapter<QuestionsAdapter.QuestionsViewHolder>() {
    inner class QuestionsViewHolder(val binding: ItemQuestionBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
        return QuestionsViewHolder(getItemViewBinding(parent,R.layout.item_question))
    }

    override fun getItemCount(): Int = oldData.size

    override fun onBindViewHolder(holder: QuestionsViewHolder, position: Int) {
        holder.binding.question = oldData[position]
    }

    private fun getItemViewBinding(parent: ViewGroup, layout: Int): ItemQuestionBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
    }

    fun updateDataSet(newData: ArrayList<Question>) {
        val diffResult = DiffUtil.calculateDiff(QuestionsUtils(oldData, newData))
        oldData.clear()
        oldData.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

}