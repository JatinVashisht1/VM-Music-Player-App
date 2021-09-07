package com.example.vmmusicplayer

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.vmmusicplayer.ui.theme.VMMusicPlayerTheme
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VMMusicPlayerTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Dexter.withContext(this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(object : PermissionListener {
                            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                                Log.d("HomeScreen", "Permission Granted ${p0.toString()}")


                            }

                            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                               Log.d("HomeScreen", "Permission denied ${p0.toString()}")
                            }

                            override fun onPermissionRationaleShouldBeShown(
                                p0: PermissionRequest?,
                                p1: PermissionToken?
                            ) {
                                p1?.continuePermissionRequest()
                            }
                        })
                        .check()
                }
            }
        }
    }
}


