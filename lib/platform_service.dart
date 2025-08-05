import 'dart:developer';

import 'package:flutter/services.dart';

class PlatformService{
  MethodChannel channel = MethodChannel("samples.flutter.dev/user");

  Future<String> getBatteryLevel() async {
    String batteryLevel;
    try {
      final result = await channel.invokeMethod<int>('getBattery');
      log(result.toString());
      batteryLevel = 'Battery level at $result % .';
    } on PlatformException catch (e) {
      batteryLevel = "Failed to get battery level: '${e.message}'.";
    }

    return batteryLevel;
  }
  void showToast(){
    channel.invokeMethod("userName","Success fully logged");
  }
  void onVibrator(){
    channel.invokeMethod("vibrateDevice");
  }
}