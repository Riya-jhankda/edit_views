
package com.example.permissions

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.btn_requestPermissions


abstract class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_requestPermissions.setOnClickListener{
            requestPermissions()
        }

    }

    private fun hasWriteExternalStoragePermission() =
        ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED

    private fun hasLocationForegroundPermission() =
        ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED

    private fun hasLocationBackgroundPermission() =
        ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_BACKGROUND_LOCATION)== PackageManager.PERMISSION_GRANTED

    private fun requestPermissions(){
        var permissiontoRequest = mutableListOf<String>()

        if(!hasWriteExternalStoragePermission()){
            permissiontoRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        if(!hasLocationForegroundPermission()){
            permissiontoRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }

        if(!hasLocationBackgroundPermission()){
            permissiontoRequest.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }

        if(permissiontoRequest.isNotEmpty()){
            ActivityCompat.requestPermissions(this,permissiontoRequest.toTypedArray(),0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==0 && grantResults.isNotEmpty())
            for(i in grantResults.indices){
                if(grantResults[i]== PackageManager.PERMISSION_GRANTED){
                    Log.d("PermissionsRequest","${permissions[i]} granted ")
                }
            }
    }

}