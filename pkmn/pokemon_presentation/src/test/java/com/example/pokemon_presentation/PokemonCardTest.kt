package com.example.pokemon_presentation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.example.pokemon_domain.models.Pokemon
import com.example.pokemon_presentation.components.PokemonCard
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog

@RunWith(RobolectricTestRunner::class)
@Config(instrumentedPackages = ["androidx.loader.content"], sdk = [32])
class PokemonCardTest {
    @get:Rule
    val rule = createComposeRule()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        // ActivityScenario.launch(MainActivity::class.java)
        ShadowLog.stream = System.out // Redirect Logcat to console
    }

    @Test
    fun basicTestCase() {
        rule.setContent {
            PokemonCard(
                position = 1,
                pokemon = Pokemon("Pikachu", ""),
                onClick = { position ->
                    println("Pokemon $position")
                }
            )
        }

        rule.onRoot().printToLog("PrintToLog")
        rule.onNodeWithText("Pikachu")
            .assertExists().performClick()
    }
}
