package com.falcon.stackoverflow.screens.resultdetail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.falcon.stackoverflow.databinding.RecyclerviewAnswerItemBinding
import com.falcon.stackoverflow.screens.models.AnswerItem
import com.falcon.stackoverflow.utils.UiUtils

class AnswersListAdapter (
    val context: Context,
    val layoutInflater: LayoutInflater
    ): RecyclerView.Adapter<AnswersListAdapter.ModelViewHolder>() {

    val TAG: String = "AnswersListAdapter"

    var main_arrlst = mutableListOf<AnswerItem>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    inner class ModelViewHolder(private val binding: RecyclerviewAnswerItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: AnswerItem){
            //UiUtils.setTypeFace(binding.answerBodyTxt, context)
            binding.answerBodyTxt.text = UiUtils.fromHtml(currentItem.body)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val binding = RecyclerviewAnswerItemBinding.inflate(layoutInflater, parent, false)
        return ModelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        if (main_arrlst != null) {
            holder.bind(main_arrlst.get(position))
        }
    }

    override fun getItemCount(): Int {
        if (main_arrlst != null)
            return main_arrlst.size
        else return 0
    }

    fun setList(items: List<AnswerItem>?) {
        main_arrlst = items as MutableList<AnswerItem>
        notifyDataSetChanged()
    }

}