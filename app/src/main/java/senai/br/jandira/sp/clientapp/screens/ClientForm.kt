package senai.br.jandira.sp.clientapp.screens

import android.util.Patterns
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await
import senai.br.jandira.sp.clientapp.model.Cliente
import senai.br.jandira.sp.clientapp.service.RetrofitFactory

@Composable
fun ClientForm(modifier: Modifier = Modifier){

    var nomeCliente by remember {
        mutableStateOf("")
    }

    var emailCliente by remember {
        mutableStateOf("")
    }

    var isNomeError by remember{ mutableStateOf(false) }
    var isEmailError by remember{ mutableStateOf(false) }

    fun validar(): Boolean {
        isNomeError = nomeCliente.length < 1
        isEmailError = !Patterns.EMAIL_ADDRESS.matcher(emailCliente).matches()
        return !isNomeError && !isEmailError
    }

    val clienteAPI = RetrofitFactory().getClienteService()
    
    Column (
        modifier = Modifier.fillMaxSize().padding(8.dp),

    ){
        OutlinedTextField(
            value = nomeCliente,
            onValueChange = { nome ->
                nomeCliente = nome
            },
            label = {
                Text(text = "Nome do cliente")
            },
            supportingText = {
                if(isNomeError) {
                    Text(text = "Nome do cliente é obrigatório")
                }
            },
            isError = isNomeError,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = emailCliente,
            onValueChange = { email ->
                emailCliente = email
            },
            label = {
                Text(text = "E-mail do cliente")
            },
            supportingText = {
                if(isEmailError) {
                    Text(text = "E-mail do cliente é obrigatório")
                }
            },
            isError = isEmailError,
            trailingIcon = {
                if(isEmailError) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = "erro")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if(validar()){
                    val cliente = Cliente(
                        nome = nomeCliente,
                        email = emailCliente
                    )
                    GlobalScope.launch(Dispatchers.IO){
                        val novoCliente = clienteAPI.gravar(cliente).await()
                        println(novoCliente)
                    }
                } else {
                    println("Dados incorretos")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
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