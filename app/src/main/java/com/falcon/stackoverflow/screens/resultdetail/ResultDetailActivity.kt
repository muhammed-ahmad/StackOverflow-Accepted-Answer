package com.falcon.stackoverflow.screens.resultdetail
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.falcon.stackoverflow.databinding.ActivityResultDetailBinding
import com.falcon.stackoverflow.screens.common.BaseActivity
import com.falcon.stackoverflow.screens.common.ImageLoader
import com.falcon.stackoverflow.screens.results.ResultsListAdapter
import com.falcon.stackoverflow.utils.Logger
import com.falcon.stackoverflow.utils.UiUtils
import javax.inject.Inject

class ResultDetailActivity : BaseActivity() {

    val TAG: String = "ResultDetailActivity"
    var questionId: Long = 0
    lateinit var binding: ActivityResultDetailBinding
    lateinit var adapter: AnswersListAdapter

    @Inject lateinit var resultDetailViewModel: ResultDetailViewModel
    @Inject lateinit var imageLoader: ImageLoader
    @Inject lateinit var layoutInflator: LayoutInflater

    companion object {
        const val QUESTION_ID: String = "QUESTION_ID"
        fun start(fromActivity: AppCompatActivity, questionId: Long)
        {
            val intent = Intent(fromActivity, ResultDetailActivity::class.java)
            intent.putExtra(QUESTION_ID, questionId)
            fromActivity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        presentationComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityResultDetailBinding.inflate(layoutInflator)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        if(intent.hasExtra(QUESTION_ID)) {
            questionId = intent.getLongExtra(QUESTION_ID, 0)
        }

        Logger.log( TAG, "onCreate: questionId: $questionId")

        adapter = AnswersListAdapter(
            this,
            layoutInflator
            )

        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)


        resultDetailViewModel.getQuestionData(questionId).observe(this, { resultDetail ->

            binding.questionHeaderTxt.visibility = View.VISIBLE
            binding.answerHeaderTxt.visibility = View.VISIBLE
            binding.progressBar.visibility = View.INVISIBLE

            binding.questionTitleTxt.text = UiUtils.fromHtml(resultDetail.questionItem.title)
            binding.questionBodyTxt.text = UiUtils.fromHtml(resultDetail.questionItem.body)
            adapter.setList(resultDetail.answerItems)
        })

    }
}
