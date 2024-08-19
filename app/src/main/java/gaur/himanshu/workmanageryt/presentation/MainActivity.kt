package gaur.himanshu.workmanageryt.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import gaur.himanshu.workmanageryt.common.formatTimestampToDMY
import gaur.himanshu.workmanageryt.presentation.ui.theme.WorkManagerYTTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkManagerYTTheme {
                Surface(modifier = Modifier.safeContentPadding()) {
                    val viewModel = hiltViewModel<MainViewModel>()
                    MainScreen(viewModel) {
                        viewModel.getQuote()
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel, fetchQuote: () -> Unit) {

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Quote's") }, actions = {
            IconButton(onClick = fetchQuote) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
            }
        })
    }) {

        if (uiState.data.isEmpty()) {
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(text = "Nothing found")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                items(uiState.data) {
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(text = it.quote)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Author: ${it.author}")
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = formatTimestampToDMY(it.time).toString(),
                                    color = Color.Red
                                )
                                Text(text = it.workType, color = Color.Red)
                            }
                        }
                    }
                }
            }
        }

    }


}

