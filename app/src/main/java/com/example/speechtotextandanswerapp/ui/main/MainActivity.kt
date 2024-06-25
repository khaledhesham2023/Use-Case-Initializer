package com.example.speechtotextandanswerapp.ui.main

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.speechtotextandanswerapp.R
import com.example.speechtotextandanswerapp.base.BaseActivity
import com.example.speechtotextandanswerapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layout: Int
        get() = R.layout.activity_main

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment).findNavController()
        appBarConfiguration = AppBarConfiguration.Builder(R.id.mainFragment).build()
        setSupportActionBar(viewBinding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.url -> createDialogToEnterBaseUrl()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun createDialogToEnterBaseUrl() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_baseurl,null)
        val alertDialogBuilder = AlertDialog.Builder(this)
        val editText = dialogView.findViewById<EditText>(R.id.baseurl_edittext)
//        editText.setText(sharedPreferencesManager.getBaseUrl() ?: "")
        alertDialogBuilder.setTitle("Info")
        alertDialogBuilder.setView(dialogView)
        alertDialogBuilder.setPositiveButton("Confirm"
        ) { _, _ ->
            if(!TextUtils.isEmpty(editText.text.toString())){
//                sharedPreferencesManager.setBaseUrl("http://${editText.text}:8080/V1/rest/")
//                viewModel.getQuestions()
//                Toast.makeText(requireContext(),sharedPreferencesManager.getBaseUrl(),Toast.LENGTH_SHORT).show()
            } else {
//                Toast.makeText(requireContext(),"Please enter a baseUrl", Toast.LENGTH_SHORT).show()
            }
        }
        alertDialogBuilder.setNegativeButton("Cancel"
        ) { dialog, _ -> dialog!!.cancel() }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

}