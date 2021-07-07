package com.example.permission

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.permission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnCamera.setOnClickListener {
            checkPermission()
        }
    }


    fun checkPermission(){

        //카메라 권한의 승인 상태 가져오기
        val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

        if(cameraPermission == PackageManager.PERMISSION_GRANTED){
            //승인
            startProcess()
        }
        else{
            //미승인
            requestPermission()
        }
    }

    fun startProcess(){
        //프로그램 실행 메서드
        Toast.makeText(this, "카메라를 실행합니다.", Toast.LENGTH_LONG).show()
    }

    fun requestPermission() {
        //권한 요청 메서드
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 99)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            99 -> {
                //권한 결괏값을 확인 후 실행 내용 결정
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) startProcess()
                else finish()
            }
        }
    }
}