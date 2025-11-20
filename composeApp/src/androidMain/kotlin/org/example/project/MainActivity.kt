package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        initKoin()
        initAndroidKoin()
        setContent {
            val context = LocalContext.current
            App(
                openUrl = { url ->
                    openUrl(
                        url = url,
                        context = context,
                    )
                },
            )
        }
    }

    private fun initAndroidKoin() {
        val appModule = module { single<android.content.Context> { applicationContext } }
        loadKoinModules(appModule)
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App(
        openUrl = { },
    )
}