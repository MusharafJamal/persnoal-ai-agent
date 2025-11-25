package com.example.biby
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.biby.ui.theme.BibyTheme
import android.util.Log
import android.content.Intent
import  android.widget.Toast
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            BibyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                    getInstalledApps(this, "ente jilla")
                }
            }
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BibyTheme {
        Greeting("Android")
    }
}
fun getInstalledApps(context: Context , searchName: String){
    val pm = context.packageManager

    //an Intent that looks for all "Launchable" apps
    val mainIntent = Intent(Intent.ACTION_MAIN, null)
    mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
    val appList = pm.queryIntentActivities(mainIntent, 0)
    var foundPackageName: String? = null
    for (app in appList) {
        val appLabel = app.loadLabel(pm).toString()

        // 'ignoreCase = true' means "youtube" will still find "YouTube"
        if (appLabel.equals(searchName, ignoreCase = true)) {
            foundPackageName = app.activityInfo.packageName
            break // We found it, stop looping
        }
    }
    if (foundPackageName != null) {
        val launchIntent = pm.getLaunchIntentForPackage(foundPackageName)
        context.startActivity(launchIntent)
    } else {
        Toast.makeText(context, "App '$searchName' not found!", Toast.LENGTH_SHORT).show()
    }
}
