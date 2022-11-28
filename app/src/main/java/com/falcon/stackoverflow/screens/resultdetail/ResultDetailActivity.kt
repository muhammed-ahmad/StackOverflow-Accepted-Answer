package com.falcon.stackoverflow.screens.resultdetail
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.falcon.stackoverflow.databinding.ActivityResultDetailBinding
import com.falcon.stackoverflow.screens.common.BaseActivity
import com.falcon.stackoverflow.screens.common.ImageLoader
import com.falcon.stackoverflow.utils.Logger
import javax.inject.Inject

class ResultDetailActivity : BaseActivity() {

    val TAG: String = "ResultDetailActivity"
    var resultId: String = ""
    lateinit var binding: ActivityResultDetailBinding

    @Inject lateinit var resultDetailViewModel: ResultDetailViewModel
    @Inject lateinit var imageLoader: ImageLoader
    @Inject lateinit var layoutInflator: LayoutInflater

    companion object {
        const val RESULT_ID: String = "RESULT_ID"
        fun start(fromActivity: AppCompatActivity, resultId: Long)
        {
            val intent = Intent(fromActivity, ResultDetailActivity::class.java)
            intent.putExtra(RESULT_ID, resultId)
            fromActivity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        presentationComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityResultDetailBinding.inflate(layoutInflator)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        if(intent.hasExtra(RESULT_ID)) {
            resultId = intent.getStringExtra(RESULT_ID)!!
        }

        Logger.log( TAG, "onCreate: resultId: $resultId")

//        if(!resultId.isEmpty()) {
//            resultDetailViewModel.getResultDetailById(resultId, object :ResultDetailViewModel.ListenerForOne {
//
//                override fun onQuerySuccess(resultDetail: ResultDetail) {
//                    if (resultDetail !=null){
//                        imageLoader.loadImage(binding.userImg, Constants.imagesUsersUrl + resultDetail.imageUrl)
//                        binding.nameTxt.setText(resultDetail.name)
//                        binding.detailsTxt.setText(resultDetail.details)
//                    }
//                }
//                override fun onQueryFailed(e: Throwable) {
//                }
//            })
//        }
    }
}
