package com.falcon.stackoverflow.screens.results

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.falcon.stackoverflow.R
import com.falcon.stackoverflow.databinding.ActivityResultBinding
import com.falcon.stackoverflow.screens.common.BaseActivity
import com.falcon.stackoverflow.screens.common.ImageLoader
import com.falcon.stackoverflow.screens.common.ScreensNavigator
import com.falcon.stackoverflow.utils.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import javax.inject.Inject

class ResultsActivity : BaseActivity() {

    val TAG: String = "ResultsActivity"
    lateinit var binding: ActivityResultBinding
    lateinit var adapter: ResultsListAdapter

    @Inject lateinit var resultViewModel: ResultViewModel
    @Inject lateinit var screensNavigator: ScreensNavigator 
    //@Inject lateinit var dialogsNavigator: DialogsNavigator
    @Inject lateinit var layoutInflator: LayoutInflater
    @Inject lateinit var imageLoader: ImageLoader
    @Inject lateinit var fetchResultUseCase: FetchResultUseCase

    companion object {
        fun start(fromActivity: AppCompatActivity)
        {
            val intent = Intent(fromActivity, ResultsActivity::class.java)
            fromActivity.startActivity(intent)
            fromActivity.finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        presentationComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflator)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        RxJavaPlugins.setErrorHandler{ e -> { }}
        checkConnectivity()

        adapter = ResultsListAdapter(
                this,
                layoutInflator,
                imageLoader)

        adapter.onItemClicked = { question_id -> screensNavigator.toResultDetailActivity(question_id) }

        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

    }

    @SuppressLint("CheckResult")
    private fun checkConnectivity() {
        resultViewModel.checkConnectivity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { isConnected ->
                    Logger.log(TAG, "onSuccess: ")
                    //binding.progressBar.visibility = View.INVISIBLE
                },
                { error ->
                    //binding.progressBar.visibility = View.INVISIBLE
                    val error: String? = error.localizedMessage
                    Logger.log( TAG,"onFailed: " + error)
                    binding.errorTxt.visibility = View.INVISIBLE
                    binding.errorTxt.setText(error)
                }
            )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val myActionMenuItem: MenuItem = menu.findItem( R.id.mnuSearch)
        val searchView: SearchView = myActionMenuItem.actionView as SearchView
        val searchIcon: ImageView = searchView.findViewById(R.id.search_button)
        searchIcon.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.baseline_search_white_24))
        searchView.maxWidth =Integer.MAX_VALUE

        searchView.isIconified = false
        searchIcon.visibility = View.VISIBLE

        val closeImgView: ImageView = searchView.findViewById(R.id.search_close_btn)
        closeImgView.visibility = View.INVISIBLE

        val searchSrcTextView: SearchView.SearchAutoComplete = searchView.findViewById(R.id.search_src_text);
        searchSrcTextView.setHint("")

        searchIcon.setOnClickListener{
            Logger.log(TAG , "searchIcon: " + searchView.query.toString())
            this.currentFocus?.let { view ->
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(view.windowToken, 0)
            }
            search(searchView.query.toString())
        }

        return true
    }

    fun search(query: String){
        binding.progressBar.setVisibility(View.VISIBLE)
        resultViewModel.fetch(query).observe(this, { renderedItems ->
            Logger.log(TAG, "items: $renderedItems")
            binding.progressBar.setVisibility(View.INVISIBLE)
            adapter.setList(renderedItems)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        return super.onOptionsItemSelected(item)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return true 
    }
}