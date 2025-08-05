package com.example.kotlin_flutter_lern

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import androidx.annotation.NonNull
import android.widget.Toast


class MainActivity : FlutterActivity()
{
    private val CHANNEL = "samples.flutter.dev/user"
    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            if(call.method=="userName"){
                val msg = call.arguments as? String
                if (msg!=null)
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                else{
                    Toast.makeText(this, "Not logged In", Toast.LENGTH_SHORT).show()
                }
            }else{
                result.notImplemented()
            }
        }
    }
}
