package com.denise.niso_planner.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.denise.niso_planner.app.ui.auth.RegisterScreen
import com.denise.niso_planner.app.ui.auth.LoginScreen
import com.denise.niso_planner.app.ui.home.HomeScreen
import com.denise.niso_planner.app.ui.auth.WelcomeScreen
import com.denise.niso_planner.app.ui.tasks.CreateTaskScreen
import com.denise.niso_planner.app.ui.notes.CreateNoteScreen
import com.denise.niso_planner.app.viewmodel.AuthState

@Composable
fun AppNavGraph(
    navController: NavHostController,
    authState: AuthState,
    onGoogleClick: () -> Unit
) {

    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {

        // ⭐ WELCOME
        composable("welcome") {
            WelcomeScreen(
                onCreateAccountClick = { navController.navigate("register") },
                onLoginClick = { navController.navigate("login") }
            )
        }

        // ⭐ LOGIN
        composable("login") {
            LoginScreen(
                state = authState,
                onGoogleClick = onGoogleClick,
                onSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate("register") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        // ⭐ REGISTER
        composable("register") {
            RegisterScreen(
                state = authState,
                onGoogleClick = onGoogleClick,
                onSuccess = {
                    navController.navigate("home") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }

        // ⭐ HOME
        composable("home") {
            HomeScreen(
                onAddTask = { navController.navigate("create_task") },
                onAddNote = { navController.navigate("create_note") },
                onCalendarClick = {},
                onMoodClick = {},
                onTasksClick = {},
                onConfigClick={}
            )
        }

        // ⭐ CREATE TASK (sin parámetros)
        composable("create_task") {
            CreateTaskScreen(
                onBack = { navController.popBackStack() }
            )
        }

        // ⭐ CREATE NOTE (sin parámetros)
        composable("create_note") {
            CreateNoteScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
