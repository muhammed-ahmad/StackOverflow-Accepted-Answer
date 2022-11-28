package com.falcon.stackoverflow.screens.common
import androidx.appcompat.app.AppCompatActivity
import com.falcon.stackoverflow.common.di.activity.ActivityScope
import com.falcon.stackoverflow.screens.results.ResultsActivity
import com.falcon.stackoverflow.screens.resultdetail.ResultDetailActivity
import javax.inject.Inject

@ActivityScope
class ScreensNavigator @Inject constructor( private val fromActivity: AppCompatActivity){

    fun toResultsActivity(){
        ResultsActivity.start(fromActivity)
    }

    fun toResultDetailActivity(resultId: Long){
        ResultDetailActivity.start(fromActivity, resultId)
    }
}
