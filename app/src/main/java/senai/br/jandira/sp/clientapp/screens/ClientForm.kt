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
fun ClientForm(modifier: Modifier = Modifier) {

    // Variáveis de estado para utilizar o outlined
    var nomeCliente by remember {
        mutableStateOf("")
    }

    var emailCliente by remember {
        mutableStateOf("")
    }

    // Variáveis de estado para validar a entrada do usuário
    var isNomeError by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }

    fun validar(): Boolean {
        isNomeError = nomeCliente.length < 1
        isEmailError = !Patterns.EMAIL_ADDRESS.matcher(emailCliente).matches()
        return !isNomeError && !isEmailError
    }

    // Criar uma instância do RetrofitFactory
    val clienteApi = RetrofitFactory().getClienteService()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = nomeCliente,
            onValueChange = { nome ->
                nomeCliente = nome
            },
            label = {
                Text(text = "Digite seu nome")
            },
            supportingText = {
                if (isNomeError) {
                    Text(text = "Nome do cliente é obrigatório")
                }
            },
            trailingIcon = {
                if (isNomeError){
                    Icon(imageVector = Icons.Default.Info, contentDescription = "erro")
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
                Text(text = "Digite seu e-mail")
            },
            supportingText = {
                if (isEmailError) {
                    Text(text = "E-mail inválido")
                }
            },
            trailingIcon = {
                if (isEmailError){
                    Icon(imageVector = Icons.Default.Info, contentDescription = "erro")
                }
            },
            isError = isEmailError,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                // Criar um cliente com os dados que o usuário digitou
                if (validar()) {
                    val cliente = Cliente(
                        nome = nomeCliente,
                        email = emailCliente
                    )
                    // Requisição POST para a API
                    GlobalScope.launch(Dispatchers.IO) {
                        val novoCliente = clienteApi.gravar(cliente).await()
                        println(novoCliente)
                    }
                } else {
                    println("****** Os dados estão incorretos")
                }
            },
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Gravar Cliente")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ClientFormPreview(){
    ClientForm()
}