package com.example.genshincharactercompose.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.genshincharactercompose.R
import com.example.genshincharactercompose.ui.theme.GenshinCharacterComposeTheme

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        placeholder = {
            Text(stringResource(R.string.search_hero))
        },
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .heightIn(min = 28.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview(){
    GenshinCharacterComposeTheme {
        SearchBar(query = "", onQueryChange = {})
    }
}