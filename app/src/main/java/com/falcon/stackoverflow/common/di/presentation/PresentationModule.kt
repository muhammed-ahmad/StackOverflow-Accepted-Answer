package com.falcon.stackoverflow.common.di.presentation
import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.falcon.stackoverflow.screens.resultdetail.FetchResultDetailUseCase
import com.falcon.stackoverflow.screens.resultdetail.ResultDetailViewModel
import com.falcon.stackoverflow.screens.resultdetail.ResultDetailViewModelFactory
import com.falcon.stackoverflow.screens.results.FetchAcceptedAnswerUseCase
import com.falcon.stackoverflow.screens.results.FetchResultUseCase
import com.falcon.stackoverflow.screens.results.ResultViewModel
import com.falcon.stackoverflow.screens.results.ResultsViewModelFactory
import com.falcon.stackoverflow.screens.splash.SplashViewModel
import com.falcon.stackoverflow.screens.splash.SplashViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    // view models
    @Provides
    fun getResultsViewModel(appCompatActivity: AppCompatActivity,
                            application: Application,
                            fetchResultUseCase: FetchResultUseCase,
                            fetchAcceptedAnswerUseCase: FetchAcceptedAnswerUseCase
                               ): ResultViewModel{

        return ViewModelProviders.of(
                appCompatActivity,
                ResultsViewModelFactory(application, fetchResultUseCase, fetchAcceptedAnswerUseCase)
        ).get(ResultViewModel::class.java)
    }

    @Provides
    fun getResultDetailViewModel(appCompatActivity: AppCompatActivity,
                                 application: Application,
                                 fetchResultDetailUseCase: FetchResultDetailUseCase
                         ): ResultDetailViewModel {

        return ViewModelProviders.of(
                appCompatActivity,
            ResultDetailViewModelFactory(application, fetchResultDetailUseCase)
        ).get(ResultDetailViewModel::class.java)
    }

    @Provides
    fun getSplashViewModel (appCompatActivity: AppCompatActivity,
                            application: Application
                            ) : SplashViewModel{

        return ViewModelProviders.of(
                appCompatActivity,
                SplashViewModelFactory(application)
        ).get(SplashViewModel::class.java)
    }
}
