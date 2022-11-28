package com.falcon.stackoverflow.screens.common.dialogs;
import androidx.fragment.app.DialogFragment;
import com.falcon.stackoverflow.common.di.presentation.PresentationModule;
import com.falcon.stackoverflow.screens.common.BaseActivity;

open class BaseDialog : DialogFragment() {

    val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent
            .newPresentationComponent(PresentationModule())
    }

}
