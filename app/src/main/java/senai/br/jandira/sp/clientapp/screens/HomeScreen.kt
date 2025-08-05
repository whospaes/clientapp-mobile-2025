package senai.br.jandira.sp.clientapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import senai.br.jandira.sp.clientapp.R
import senai.br.jandira.sp.clientapp.ui.theme.ClientAppTheme

@Composable
fun HomeScreen (modifier: Modifier = Modifier){
    Scaffold (
        topBar = {
            BarraTitulo()
        },
        bottomBar = {
            Text(text= "Barra inferior")
        },
        floatingActionButton = {

        }
    ){
        paddingValues ->
        Column (
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.LightGray)
        ){
            Text(text= "Conteudo")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraTitulo(modifier: Modifier = Modifier){
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        title = {
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Column {
                    Text(
                        text = "Jailson Souza",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "jailson@email.com",
                        fontSize = 14.sp
                    )
                }
                Card(
                    modifier = Modifier
                        .size(60.dp),
                    shape = CircleShape
                    ){
                    Image(
                    painter = painterResource(R.drawable.sigma),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                ) }

            }
        }
    )
}

@Composable
fun BarraNavegacao(modifier: Modifier = Modifier){
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = ""
                )
            }
        )
    }
}

@Composable
@Preview
private fun BarraTituloPreview(){
    ClientAppTheme {
        BarraTitulo()
    }
}

@Composable
@Preview
private fun BarraNavegacaoPreview(){
    ClientAppTheme {
        BarraNavegacao()
    }
}

@Preview
@Composable
private fun HomeScreenPreview(){
    ClientAppTheme {
        HomeScreen()
    }
}