package com.example.characters

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.characters.data.viewmodel.CharacterViewModel
import com.example.characters.ui.theme.CharactersTheme
import com.example.characters.utils.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CharactersTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    if (Util.isNetworkAvailable(this)) {
                        val viewModel = hiltViewModel<CharacterViewModel>()
                        CharacterListFragment(viewModel)
                    } else NoNetwork()
                }
            }
        }

    }


}

@Composable
fun NoNetwork() {
    Text(text = "Network Service not available", fontSize = 21.sp)
}

@Preview(showBackground = true)
@Composable
fun FragmentPreview() {
    CharactersTheme {
        CharacterList(list = Util.list)
    }
}