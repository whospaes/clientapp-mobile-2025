package senai.br.jandira.sp.clientapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ClientForm(modifier: Modifier = Modifier){

    var
    
    Column (
        modifier = Modifier.fillMaxSize()
    ){
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {
                Text(text = "Nome do cliente")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            Text(text = "Gravar cliente")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ClientFormPreview(){
    ClientForm()
}