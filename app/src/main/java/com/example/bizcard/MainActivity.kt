package com.example.bizcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bizcard.ui.theme.BizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BizCardTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard(){

    val buttonClickedState = remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateImageProfile()
                HorizontalDivider()
                CreateInfo()
                Button(onClick = {
                    buttonClickedState.value = !buttonClickedState.value
//                    Log.d("Clicked", "CreateBizCard: Clicked!!")
                }) {
                    Text(
                        text = "Portfolio",
                    )
                }
                if (buttonClickedState.value ){
                    Content()
                }
                else{
                    Box{}
                }
            }
        }

    }
}

@Composable
fun Content(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ){
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp),
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(2.dp,Color.LightGray)
        ) {
            Portfolio(data = listOf("Project 1","Project 2","Project 3","Project 4","Project 5","Project 6"))
        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn(
        modifier = Modifier
            .background(Color.White)
    ) {
        items(data){ item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(13.dp),
                shape = RectangleShape,
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(Color.White)
            ) { 
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(7.dp)
                ) {
                    CreateImageProfile(
                        modifier = Modifier
                            .size(100.dp)
                    )
                    Column(
                        modifier = Modifier
                            .padding(7.dp)
                            .align(Alignment.CenterVertically)
                    ) {
                        Text(
                            text = item,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "A great Project",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                }
            }
        }
    }
}

@Composable
private fun CreateInfo() {
    Column(
        modifier = Modifier
            .padding(5.dp)
    ) {
        Text(
            text = "Ansh Raj",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Android Compose Programmer",
            modifier = Modifier
                .padding(3.dp)
        )
        Text(
            text = "@anshCompose",
            modifier = Modifier
                .padding(3.dp),
            style = MaterialTheme.typography.titleSmall
        )

    }
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        shadowElevation = 4.dp,
        tonalElevation = 4.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(R.drawable.profile_image),
            contentDescription = "profile image",
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BizCardTheme {
        CreateBizCard()
    }
}