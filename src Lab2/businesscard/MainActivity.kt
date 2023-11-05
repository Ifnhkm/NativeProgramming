package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

/* Name : Muhammad Irfan Hakim
   Date : 3/11/2023
   Program : Computer Science (Mobile Computing)
*/

class MainActivity : ComponentActivity() {
    override

    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // Call your custom Composable function here
                DynamicBusinessCard()
            }
        }
    }
}

@Composable
fun DynamicBusinessCard() {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.LightGray),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = null,
                modifier = Modifier
                    .size(128.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Muhammad Irfan",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 32.sp,
                color = Color(0xFF333333)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "IT Specialist",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp,
                color = Color(0xFF666666)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
            ) {
                ContactItem(
                    text = "Phone: +603 5473642",
                    icon = R.drawable.phone_icon,
                    textColor = Color(0xFF3ddc84)
                )
                ContactItem(
                    text = "Email: agentIt@tech.gov.my",
                    icon = R.drawable.email_icon,
                    textColor = Color(0xFF3ddc84)
                )
                ContactItem(
                    text = "Instagram: @Ifnhkm",
                    icon = R.drawable.instagram_icon,
                    textColor = Color(0xFF3ddc84)
                )
            }
        }
    }
}

@Composable
fun ContactItem(text: String, icon: Int, textColor: Color = Color.Black) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp,
            color = textColor
        )
    }
}
