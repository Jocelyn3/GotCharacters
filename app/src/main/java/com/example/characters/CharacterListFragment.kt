package com.example.characters

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.characters.data.model.GotCharacter
import com.example.characters.data.viewmodel.CharacterViewModel
import com.example.characters.ui.theme.CharactersTheme
import com.example.characters.utils.Util

@Composable
fun CharacterListFragment(
    viewModel: CharacterViewModel
) {
    CharactersTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val listState by viewModel.characterList.collectAsState()
            val list = listState.list
            CharacterList(list)
        }
    }

}



@Composable
fun CharacterList(list: List<GotCharacter>) {
    if (list.isNotEmpty())
        LazyColumn (
            verticalArrangement = Arrangement.spacedBy(9.dp)
        ) {
            items(list) { item ->
                CharacterRow(item)
            }
        }
    else Text(text = "No data available", fontSize = 21.sp)
}

@Composable
fun CharacterRow(
    character: GotCharacter) {
    Row(
        Modifier
            .fillMaxSize(1f)
            .padding(9.dp),
        horizontalArrangement = Arrangement.spacedBy(9.dp),
    ) {
        Image(
            painter = rememberAsyncImagePainter(character.imageUrl),
            contentDescription = "Character Image",
            modifier = Modifier
                .size(58.dp)
                .clip(CircleShape)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(text = character.fullName, fontSize = 21.sp)
            Text(text = character.title)
        }
    }

}


@Preview(showBackground = true)
@Composable
fun ListPreview() {
    CharactersTheme {
        CharacterList(Util.list)
    }
}