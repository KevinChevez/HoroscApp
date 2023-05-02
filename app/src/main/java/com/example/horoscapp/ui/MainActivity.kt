package com.example.horoscapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.horoscapp.R
import com.example.horoscapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.mainBinding.root)

        initUI()
        initListeners()
    }

    private fun initListeners() {
        mainBinding.bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.informationFragment -> {
                    Log.i("Place","Pantalla Informacion")
                    this.navController.navigate(R.id.informationFragment)
                }
                R.id.listFragment -> {
                    Log.i("Place","Pantalla List")
                    val alertDialog = AlertDialog.Builder(this@MainActivity)
                    alertDialog.setCancelable(false)
                    alertDialog.setTitle("Confirm")
                    alertDialog.setMessage("Are you sure you want to log out?")
                    alertDialog.setPositiveButton("Log out") { dialog, _ ->
                        dialog.cancel()
                        this.navController.navigate(R.id.listFragment)
                    }
                    alertDialog.setNegativeButton("Cancel") { dialog, _ ->
                        dialog.cancel()
                    }
                    alertDialog.show()
                }
                R.id.luckyFragment -> {
                    Log.i("Place","Pantalla Lucky")
                    this.navController.navigate(R.id.luckyFragment)
                }
                else -> {
                    Log.i("Place","Pantalla else")
                }
            }
            false
        }
    }

    private fun initUI() {
        val navHostManager = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        this.navController = navHostManager.navController
        mainBinding.bottomNavView.setupWithNavController(this.navController)
    }


}