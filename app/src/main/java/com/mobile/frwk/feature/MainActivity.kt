package com.mobile.frwk.feature

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.mobile.frwk.R
import com.mobile.frwk.data.local.database.model.AlbumEntity
import com.mobile.frwk.data.local.database.model.PostEntity
import com.mobile.frwk.data.local.database.model.TodoEntity
import com.mobile.frwk.data.source.callback.NetworkCallback
import com.mobile.frwk.data.source.repository.contract.AlbumRepository
import com.mobile.frwk.data.source.repository.contract.Endpoint
import com.mobile.frwk.data.source.repository.contract.PostRepository
import com.mobile.frwk.data.source.repository.contract.TodoRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // <editor-fold desc="[ Properties ]">

    @Inject
    lateinit var albumRepository: AlbumRepository

    @Inject
    lateinit var postRepository: PostRepository

    @Inject
    lateinit var todoRepository: TodoRepository

    private lateinit var appBarConfiguration: AppBarConfiguration

    // </editor-fold>

    // <editor-fold desc="[ Actions ]">

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViwes()
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

    // </editor-fold>

    // <editor-fold desc="[ Private Functions ]">

    private fun initViwes() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_posts,
                R.id.nav_albums,
                R.id.nav_todos
            ),
            drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun populateDatabase() {
        val retrofitClient = NetworkCallback
            .getRetrofitInstance("https://jsonplaceholder.typicode.com")

        val endpoint = retrofitClient.create(Endpoint::class.java)

        val callbackPosts = endpoint.getPosts()
        callbackPosts.enqueue(
            object : Callback<List<PostEntity>> {
                override fun onFailure(call: Call<List<PostEntity>>, t: Throwable) {
                    Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<List<PostEntity>>, response: Response<List<PostEntity>>) {
                    GlobalScope.launch(Dispatchers.Main) {
                        response.body()?.let { postRepository.insertPosts(it) }
                    }
                }
            }
        )

        val callbackAlbums = endpoint.getAlbums()
        callbackAlbums.enqueue(
            object : Callback<List<AlbumEntity>> {
                override fun onFailure(call: Call<List<AlbumEntity>>, t: Throwable) {
                    Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<List<AlbumEntity>>, response: Response<List<AlbumEntity>>) {
                    GlobalScope.launch(Dispatchers.Main) {
                        response.body()?.let { albumRepository.insertAlbums(it) }
                    }
                }
            }
        )

        val callbackTodos = endpoint.getTodos()
        callbackTodos.enqueue(
            object : Callback<List<TodoEntity>> {
                override fun onFailure(call: Call<List<TodoEntity>>, t: Throwable) {
                    Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<List<TodoEntity>>, response: Response<List<TodoEntity>>) {
                    GlobalScope.launch(Dispatchers.Main) {
                        response.body()?.let { todoRepository.insertTodos(it) }
                    }
                }
            }
        )
    }

    // </editor-fold>
}
