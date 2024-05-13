package com.msid.navigationcomposeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.msid.navigationcomposeexample.ui.theme.NavigationComposeExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationComposeExampleTheme {
                App()
            }
        }
    }
}

@Composable
fun App(){
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "registration" ) {

        composable(route = "registration"){
            //RegistrationScreen(navController)
            RegistrationScreen{
                navController.navigate("login/${it}")
            }

        }

        composable(route = "login/{email}", arguments = listOf(
            navArgument("email"){
                type = NavType.StringType
            }
        )){
           val email = it.arguments!!.getString("email")
            LoginScreen(email!!)
        }

        composable(route = "main"){
            MainScreen()
        }
    }
        
}

@Composable
fun RegistrationScreen(onClick: (email: String)->Unit){
    Text(text = "Registration",
        style = MaterialTheme.typography.headlineLarge,
        fontSize = 38.sp,
        modifier = Modifier.clickable {
            onClick("sidmundece17@gmail.com")
        })
}


@Composable
fun LoginScreen(email: String){
    Text(text = "Login- $email",
        fontSize = 38.sp,
        style = MaterialTheme.typography.headlineLarge)
}


@Composable
fun MainScreen(){
    Text(text = "Main",
        fontSize = 38.sp,
        style = MaterialTheme.typography.headlineLarge)
}
