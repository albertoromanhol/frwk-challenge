package com.mobile.frwk

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.mobile.frwk.contracts.Endpoint
import com.mobile.frwk.models.Albums
import com.mobile.frwk.models.Posts
import com.mobile.frwk.models.Todos
import com.mobile.frwk.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        const val POSTS_URL = "https://jsonplaceholder.typicode.com/posts"
        const val ALBUMS_URL = "https://jsonplaceholder.typicode.com/albums"
        const val TODOS_URL = "https://jsonplaceholder.typicode.com/todos"
    }

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_posts, R.id.nav_albums, R.id.nav_todos), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        populateDatabase()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun populateDatabase() {
        val retrofitClient = NetworkUtils
                .getRetrofitInstance("https://jsonplaceholder.typicode.com")

        val endpoint = retrofitClient.create(Endpoint::class.java)

        val callbackPosts = endpoint.getPosts()
        callbackPosts.enqueue(object : Callback<List<Posts>> {
            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                response.body()?.forEach {
                    Log.d("POSTTAG", it.title)
                }
            }
        })

        val callbackAlbums = endpoint.getAlbums()
        callbackAlbums.enqueue(object : Callback<List<Albums>> {
            override fun onFailure(call: Call<List<Albums>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Albums>>, response: Response<List<Albums>>) {
                response.body()?.forEach {
                    Log.d("ALBUMSTAG", it.title)
                }
            }
        })

        val callbackTodos = endpoint.getTodos()
        callbackTodos.enqueue(object : Callback<List<Todos>> {
            override fun onFailure(call: Call<List<Todos>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Todos>>, response: Response<List<Todos>>) {
                response.body()?.forEach {
                    Log.d("TODOSTAG", it.title)
                }
            }
        })
    }
}