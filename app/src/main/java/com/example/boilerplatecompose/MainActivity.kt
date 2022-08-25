package com.example.boilerplatecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.boilerplatecompose.navigation.Route
import com.example.core_ui.Dimensions
import com.example.core_ui.theme.BoilerPlateComposeTheme
import com.example.pokemon_presentation.views.PokemonDetail
import com.example.pokemon_presentation.views.PokemonsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BoilerPlateComposeTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = rememberScaffoldState()
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.POKEMON_LIST
                    ) {
                        composable(Route.POKEMON_LIST) {
                            PokemonsScreen(onCardClick = { position ->
                                navController.navigate(Route.DETAIL + "/$position")
                            })
                        }

                        composable(
                            Route.DETAIL + "/{position}",
                            arguments = listOf(
                                navArgument("position") {
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            val position = it.arguments?.getInt("position")!!
                            PokemonDetail(position = position)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        style = androidx.compose.ui.text.TextStyle(
            fontSize = Dimensions.text.headline
        )
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BoilerPlateComposeTheme {
        Greeting("Android")
    }
}
