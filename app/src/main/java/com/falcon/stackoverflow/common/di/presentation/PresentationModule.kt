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
    fun getResultsViewModel(fragmentActivity: AppCompatActivity,
                            application: Application,
                            fetchResultUseCase: FetchResultUseCase,
                            fetchAcceptedAnswerUseCase: FetchAcceptedAnswerUseCase
                               ): ResultViewModel{

        return ViewModelProviders.of(
                fragmentActivity,
                ResultsViewModelFactory(application, fetchResultUseCase, fetchAcceptedAnswerUseCase)
        ).get(ResultViewModel::class.java)
    }

    @Provides
    fun getResultDetailViewModel(fragmentActivity: AppCompatActivity,
                         application: Application,
                         fetchResultDetailUseCase: FetchResultDetailUseCase
                         ): ResultDetailViewModel {

        return ViewModelProviders.of(
                fragmentActivity,
            ResultDetailViewModelFactory(application, fetchResultDetailUseCase)
        ).get(ResultDetailViewModel::class.java)
    }

    @Provides
    fun getSplashViewModel (fragmentActivity: AppCompatActivity,
                            application: Application
                            ) : SplashViewModel{

        return ViewModelProviders.of(
                fragmentActivity,
                SplashViewModelFactory(application)
        ).get(SplashViewModel::class.java)
    }
}
