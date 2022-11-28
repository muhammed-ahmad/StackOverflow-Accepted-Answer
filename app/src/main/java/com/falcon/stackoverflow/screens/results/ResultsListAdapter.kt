package com.falcon.stackoverflow.screens.results

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.falcon.stackoverflow.databinding.RecyclerviewResultItemBinding
import com.falcon.stackoverflow.screens.common.ImageLoader
import com.falcon.stackoverflow.screens.models.Item
import com.falcon.stackoverflow.screens.models.RenderedItem
import com.falcon.stackoverflow.utils.UiUtils

class ResultsListAdapter (
    val context: Context,
    val layoutInflater: LayoutInflater,
    val imageLoader: ImageLoader
    ): RecyclerView.Adapter<ResultsListAdapter.ModelViewHolder>() {

    val TAG: String = "ResultsListAdapter"

    var main_arrlst = mutableListOf<RenderedItem>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    lateinit var onItemClicked: (question_id: Long) -> Unit

    inner class ModelViewHolder(private val binding: RecyclerviewResultItemBinding) : RecyclerView.ViewHolder(binding.root) {

        // TODO: i don't want to this initialization
        var question_id: Long = 0

        fun bind(currentItem: RenderedItem){
            binding.titleTxt.text = currentItem.title
            binding.answerTxt.text = UiUtils.fromHtml(currentItem.answerBody)
            question_id = currentItem.question_id
        }

        init {
            itemView.setOnClickListener { view -> onItemClicked(question_id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val binding = RecyclerviewResultItemBinding.inflate(layoutInflater, parent, false)
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

    fun setList(items: List<RenderedItem>?) {
        main_arrlst = items as MutableList<RenderedItem>
        notifyDataSetChanged()
    }

}