package com.denise.niso_planner.app.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.denise.niso_planner.app.R
import com.denise.niso_planner.app.ui.components.AppBackground
import com.denise.niso_planner.app.ui.components.AuthCard
import com.denise.niso_planner.app.ui.components.Button1
import com.denise.niso_planner.app.ui.theme.AppThemeType
import com.denise.niso_planner.app.ui.theme.getThemeConfig
import com.denise.niso_planner.app.viewmodel.AuthState

@Composable
fun RegisterScreen(
    state: AuthState,
    onGoogleClick: () -> Unit,
    onSuccess: () -> Unit = {}
) {
    if (state is AuthState.Success) onSuccess()

    val theme = getThemeConfig(AppThemeType.PINK)

    AppBackground(theme = theme) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.weight(1f))

            AuthCard(
                modifier = Modifier.padding(bottom = 16.dp)
            ) {

                // ——— TITULO ———
                Text(
                    text = "Crear Cuenta",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // ——— SUBTÍTULO ROSITA ———
                Text(
                    text = "Organización inteligente para mentes creativas.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary,   // 💗 ROSITA
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(28.dp))

                // ——— TEXTO MÁS PEQUEÑO ———
                Text(
                    text = "¿Listo para continuar?",
                    style = MaterialTheme.typography.titleMedium, // 🔽 más pequeño
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "Registrarse con.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(58.dp))

                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )

                Spacer(modifier = Modifier.height(28.dp))

                // ——— BOTÓN ———
                Button1(
                    text = "Registrarse con Google",
                    onClick = onGoogleClick,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                // ——— LINEA SEPARADORA ———
                Divider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth()
                )

                // ——— PREGUNTA FINAL ———
                Text(
                    text = "¿Ya cuentas con una cuenta registrada? Iniciar Sesión",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = MaterialTheme.colorScheme.primary
                )

                if (state is AuthState.Loading)
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                if (state is AuthState.Error)
                    Text(
                        text = "Error: ${state.message}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
            }
        }
    }
}
