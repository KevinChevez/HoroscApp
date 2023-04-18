package com.example.horoscapp.ui.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.horoscapp.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object{
        fun create(context: Context) = Intent(context, DetailActivity::class.java)
    }

    private val viewModel by viewModels<DetailViewModel>()

    private lateinit var detailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        initUI()
        viewModel.getHoroscope()
    }

    private fun initUI() {
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.collect{
                    when(it){
                        is DetailUIState.Error -> {
                            // Show Dialog
                            detailBinding.loading.isVisible = false
                            Toast.makeText(this@DetailActivity, it.msg, Toast.LENGTH_SHORT).show()
                        }
                        is DetailUIState.Loading -> {
                            // Show Loading
                            detailBinding.loading.isVisible = true
                        }
                        is DetailUIState.Success -> {
                            // Show info
                            detailBinding.loading.isVisible = false
//                            runOnUiThread {
//                                Toast.makeText(this, "Esto ha funcionado", Toast.LENGTH_SHORT).show()
//                            }
                            Toast.makeText(this@DetailActivity, "Esto ha funcionado", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}