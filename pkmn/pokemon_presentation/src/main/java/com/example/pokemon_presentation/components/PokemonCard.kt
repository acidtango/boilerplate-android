package com.example.pokemon_presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.core_ui.Dimensions
import com.example.core_ui.theme.BoilerPlateComposeTheme
import com.example.pokemon_domain.models.Pokemon

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PokemonCard(
    pokemon: Pokemon,
    position: Int,
    onClick: (position: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RectangleShape,
        onClick = { onClick(position) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = Dimensions.spacing.spacing_lg)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$position.png"),
                contentDescription = pokemon.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(64.dp)
                    .width(64.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = pokemon.name.capitalize(Locale.current))
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    BoilerPlateComposeTheme {
        PokemonCard(
            pokemon = Pokemon(name = "Charizard", url = ""),
            position = 6,
            onClick = { }
        )
    }
}
