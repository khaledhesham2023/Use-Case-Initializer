package com.example.speechtotextandanswerapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.speechtotextandanswerapp.ui.model.Choice
import com.example.speechtotextandanswerapp.ui.model.Message

class AnswersUtils(private val oldData:List<Message>, private val newData:List<Message>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData.size == newData.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition] == newData[newItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}