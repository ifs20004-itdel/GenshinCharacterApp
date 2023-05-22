package com.example.genshincharactercompose.ui.components.attribute

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.genshincharactercompose.R
import com.example.genshincharactercompose.ui.theme.GenshinCharacterComposeTheme

@Composable
fun Attribute (
    weapon: String,
    region : String,
    vision : String,
    modifier : Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 4.dp)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            when(weapon){
                stringResource(id = R.string.sword)->{
                    Weapon(weapon = R.drawable.ic_sword, description = R.string.sword)
                }
                stringResource(id = R.string.bow)->{
                    Weapon(weapon = R.drawable.ic_bow, description = R.string.bow)
                }
                stringResource(id = R.string.polearm)->{
                    Weapon(weapon = R.drawable.ic_polearm, description = R.string.polearm)
                }
                stringResource(id = R.string.claymore)->{
                    Weapon(weapon = R.drawable.ic_claymore, description = R.string.claymore)
                }
                stringResource(id = R.string.catalyst)->{
                    Weapon(weapon = R.drawable.ic_catalyst, description = R.string.catalyst)
                }
            }
            when(vision){
                stringResource(id = R.string.geo_vision) ->{
                    Vision(vision = R.drawable.element_geo, description = R.string.geo_vision)
                }
                stringResource(id = R.string.anemo_vision)  ->{
                    Vision(vision = R.drawable.element_anemo, description = R.string.anemo_vision)
                }
                stringResource(id = R.string.pyro_vision ) ->{
                    Vision(vision = R.drawable.element_pyro, description = R.string.pyro_vision)
                }
                stringResource(id = R.string.hydro_vision )->{
                    Vision(vision = R.drawable.element_hydro, description = R.string.hydro_vision)
                }
                stringResource(id = R.string.dendro_vision )->{
                    Vision(vision = R.drawable.element_dendro, description = R.string.dendro_vision)
                }
                stringResource(id = R.string.cryo_vision ) ->{
                    Vision(vision = R.drawable.element_cryo, description = R.string.cryo_vision)
                }
                stringResource(id = R.string.electro_vision) ->{
                    Vision(vision = R.drawable.element_electro, description =R.string.electro_vision )
                }
            }
        }
        Text(
            text = region,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontFamily = FontFamily.Serif
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AttributePreview(){
    GenshinCharacterComposeTheme {
        Attribute(
            weapon = stringResource(id = R.string.sword),
            region = stringResource(id = R.string.liyue), vision = stringResource(
            id = R.string.cryo_vision
        ))
    }
}