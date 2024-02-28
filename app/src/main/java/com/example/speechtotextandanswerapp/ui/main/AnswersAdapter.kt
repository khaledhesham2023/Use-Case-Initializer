package com.example.speechtotextandanswerapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.DiffResult
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.speechtotextandanswerapp.ui.model.Choice
import com.example.speechtotextandanswerapp.R
import com.example.speechtotextandanswerapp.databinding.ItemAnswerBinding
import com.example.speechtotextandanswerapp.ui.model.Message
import com.example.speechtotextandanswerapp.utils.AnswersUtils


class AnswersAdapter(val oldData: ArrayList<Message>, private val callback: AnswersCallback) :
    Adapter<AnswersAdapter.AnswersViewHolder>() {
    inner class AnswersViewHolder(val item: ItemAnswerBinding) : RecyclerView.ViewHolder(item.root) {
        init {
            item.root.setOnClickListener {
                callback.onAnswerSelected(oldData[layoutPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswersViewHolder {
        return AnswersViewHolder(
            getItemViewBinding(parent, R.layout.item_answer)
        )
    }

    override fun getItemCount(): Int = oldData.size

    override fun onBindViewHolder(holder: AnswersViewHolder, position: Int) {
        holder.item.choice
    }

    private fun getItemViewBinding(parent: ViewGroup, layout: Int): ItemAnswerBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
    }

    fun updateDataSet(newData: ArrayList<Message>) {
        val diffResult = DiffUtil.calculateDiff(AnswersUtils(oldData, newData))
        oldData.clear()
        oldData.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }
}