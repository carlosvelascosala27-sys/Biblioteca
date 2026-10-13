package org.elcampico.biblioteca.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.elcampico.biblioteca.R
import org.elcampico.biblioteca.ui.theme.MyApplicationTheme

private val Oscurecido = Brush.verticalGradient(
    listOf(Color.Black.copy(alpha = 0.3f), Color.Black.copy(alpha = 0.75f))
)

@Composable
fun PantallaRegistro(
    onCrear: (nombre: String, correo: String, clave: String) -> Unit,
    onIrALogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Variables de estado para los campos y la casilla
    var nombre by rememberSaveable { mutableStateOf("") }
    var correo by rememberSaveable { mutableStateOf("") }
    var clave by rememberSaveable { mutableStateOf("") }
    var aceptoNormas by rememberSaveable { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {

        // 1. Imagen de fondo
        Image(
            painter = painterResource(R.drawable.fondo_pantalla),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        // 2. Capa oscura
        Box(Modifier.matchParentSize().background(Oscurecido))

        // 3. Columna principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Título
            Text(
                text = "Crear cuenta",
                color = Color.White,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Black,
                fontSize = 40.sp
            )
            // Subtítulo
            Text(
                text = "Solo te llevará un minuto",
                color = Color.White.copy(alpha = 0.85f),
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(Modifier.height(32.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.92f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    // 1. Campo Nombre
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre") },
                        leadingIcon = {
                            Icon(Icons.Default.Person, contentDescription = null)
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // 2. Campo Correo
                    OutlinedTextField(
                        value = correo,
                        onValueChange = { correo = it },
                        label = { Text("Correo") },
                        leadingIcon = {
                            Icon(Icons.Default.Email, contentDescription = null)
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // 3. Campo Contraseña
                    OutlinedTextField(
                        value = clave,
                        onValueChange = { clave = it },
                        label = { Text("Contraseña") },
                        leadingIcon = {
                            Icon(Icons.Default.Lock, contentDescription = null)
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // CASILLA DE NORMAS (Añadido)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = aceptoNormas,
                            onCheckedChange = { aceptoNormas = it }
                        )
                        Text("Acepto las normas de la biblioteca")
                    }

                    // 4. Botón Crear Cuenta (Actualizado con aceptoNormas)
                    Button(
                        onClick = { onCrear(nombre, correo, clave) },
                        enabled = nombre.isNotBlank() && correo.isNotBlank() && clave.isNotBlank() && aceptoNormas,
                        modifier = Modifier.fillMaxWidth().height(50.dp)
                    ) {
                        Text(
                            "Crear cuenta",
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }
            }

            // TEXTO INFERIOR (Añadido)
            Row(
                modifier = Modifier.padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "¿Ya tienes cuenta?",
                    color = Color.White.copy(alpha = 0.85f)
                )
                TextButton(onClick = { onIrALogin() }) {
                    Text(
                        text = "Inicia sesión",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PantallaRegistroPreview() {
    MyApplicationTheme {
        PantallaRegistro(onCrear = { _, _, _ -> }, onIrALogin = {})
    }
}