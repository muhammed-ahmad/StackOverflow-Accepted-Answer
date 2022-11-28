package com.falcon.stackoverflow.common.di.activity
import com.falcon.stackoverflow.common.di.presentation.PresentationComponent
import com.falcon.stackoverflow.common.di.presentation.PresentationModule
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
   fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent
}
