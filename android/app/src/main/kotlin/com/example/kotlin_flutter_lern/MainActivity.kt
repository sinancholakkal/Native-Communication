package com.example.kotlin_flutter_lern
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import androidx.annotation.NonNull
import android.widget.Toast
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES

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
            }else if(call.method=="getBattery"){
                var battery = getBattery()
                if(battery!=1){
                    result.success(battery)
                }else{
                    result.error("UNAVAILABLE", "Battery level not available.", null)
                }
            }
            else{
                result.notImplemented()
            }
        }
    }
    private fun getBattery():Int{
        val batteryManager = getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        return batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    }
}
