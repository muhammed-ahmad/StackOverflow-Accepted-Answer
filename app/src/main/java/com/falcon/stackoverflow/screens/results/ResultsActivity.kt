package com.falcon.stackoverflow.screens.results

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
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.falcon.stackoverflow.R
import com.falcon.stackoverflow.databinding.ActivityResultBinding
import com.falcon.stackoverflow.screens.common.BaseActivity
import com.falcon.stackoverflow.screens.common.ImageLoader
import com.falcon.stackoverflow.screens.common.ScreensNavigator
import com.falcon.stackoverflow.screens.models.Item
import com.falcon.stackoverflow.screens.models.RenderedItem
import com.falcon.stackoverflow.utils.Logger
import javax.inject.Inject

class ResultsActivity : BaseActivity() {

    val TAG: String = "ResultsActivity"
    lateinit var adapter: ResultsListAdapter
    lateinit var binding: ActivityResultBinding

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

        adapter = ResultsListAdapter(
                this,
                layoutInflator,
                imageLoader)

        adapter.onItemClicked = { question_id -> screensNavigator.toResultDetailActivity(question_id) }

        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

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
        resultViewModel.fetch(query, object : ResultViewModel.ResultListener {
            override fun onSuccess(items: List<RenderedItem>) {
                Logger.log(TAG, "items: $items")
                adapter.setList(items)
            }
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