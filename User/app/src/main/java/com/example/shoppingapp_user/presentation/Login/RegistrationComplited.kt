package com.example.shoppingapp_user.presentation.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shoppingapp_user.Navigation.Routes

@Composable
fun RegistrationComplited(navController: NavController
)
{

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF).copy(alpha = 0.7f)), // Semi-transparent background
        contentAlignment = Alignment.Center
    ) {
        // Card for the dialog
        Card(
            modifier = Modifier
                .padding(32.dp)
                .wrapContentSize(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Success Icon
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color(0xFFFFCDD2), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "✔",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Success TitleF
                Text(
                    text = "Success",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFF5252)
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Success Message
                Text(
                    text = "Congratulations, you have completed your registration!",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Gray
                    ),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Done Button
                Button(
                    onClick = {
                        navController.navigate(Routes.HomeScreen)
                    },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(
                        text = "Done",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

}