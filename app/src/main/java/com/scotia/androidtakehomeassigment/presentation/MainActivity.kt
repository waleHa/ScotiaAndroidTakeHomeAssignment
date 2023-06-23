package com.scotia.androidtakehomeassigment.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.scotia.androidtakehomeassigment.R
import dagger.hilt.android.AndroidEntryPoint


/**
 * This is the main activity of the application.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    /**
     * The navigation controller for this activity.
     */
    private lateinit var navController: NavController

    /**
     * Called when the activity is starting. This is where most initialization
     * should go: calling setContentView(int) to inflate the activity's UI,
     * using findViewById(int) to programmatically interact with widgets in the UI.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     * previously being shut down then this Bundle contains the data it most
     * recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    /**
     * This method is called whenever the user chooses to navigate Up within your application's activity hierarchy from the action bar.
     *
     * @return boolean Return true if Up navigation completed successfully and this Activity was finished, false otherwise.
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
