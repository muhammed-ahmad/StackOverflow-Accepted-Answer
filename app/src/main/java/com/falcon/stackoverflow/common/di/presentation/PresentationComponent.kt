package com.falcon.stackoverflow.common.di.presentation
import com.falcon.stackoverflow.screens.results.ResultsActivity
import com.falcon.stackoverflow.screens.resultdetail.ResultDetailActivity
import com.falcon.stackoverflow.screens.splash.SplashActivity
import dagger.Subcomponent

@PresentationScope
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {

    fun inject(activity: SplashActivity)
    fun inject(activity: ResultsActivity)
    fun inject(activity: ResultDetailActivity)

}
