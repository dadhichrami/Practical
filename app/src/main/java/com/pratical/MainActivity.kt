package com.pratical

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.pratical.adapter.RepoAdapter
import com.pratical.databinding.ActivityMainBinding
import com.pratical.model.RepoModel
import com.pratical.utility.ActivityViewBinding.viewBinding
import com.pratical.utility.Globals
import com.pratical.utility.URLClass
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MainActivity : AppCompatActivity(), TextWatcher {

    private val mBinding by viewBinding(ActivityMainBinding::inflate)
    private var client: AsyncHttpClient? = null
    private lateinit var adapter: RepoAdapter
    private var model: RepoModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        initDataAndView()
        getRepoListFromApi()
    }

    private fun initDataAndView() {

        mBinding.apply {
            rvRepo.layoutManager = GridLayoutManager(this@MainActivity, 1)
            etSearch?.addTextChangedListener(this@MainActivity)
        }
    }

    private fun getRepoListFromApi() {
        client = AsyncHttpClient()
        client!!.get(URLClass.Repo_URL, object : JsonHttpResponseHandler() {

            override fun onStart() {
                Globals.showProgress(this@MainActivity)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseString: String?,
                throwable: Throwable?
            ) {
                Globals.hideProgress()
            }

            override fun onSuccess(
                statusCode: Int, headers: Array<out Header>?, response: JSONObject?
            ) {
                Globals.hideProgress()
                model = Gson().fromJson(response.toString(), RepoModel::class.java)
                setAdapter()
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                throwable: Throwable?,
                errorResponse: JSONObject?
            ) {
                Globals.hideProgress()
            }
        })
    }

    private fun setAdapter() {

        mBinding.apply {

            if (rvRepo.adapter == null) {
                adapter = RepoAdapter(this@MainActivity)
                rvRepo.adapter = adapter
            }

            adapter.doRefresh(model!!.items)
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        if (s!!.isNotEmpty()) {
//            adapter.filter.filter(s.toString())
        }
    }
}