

import 'dart:developer';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:kotlin_flutter_lern/platform_service.dart';

void main(){
  runApp(MyApp());
}
class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: HomeScreen(),
    );
  }
}

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  String? batteryLevel;
  String? deviceInfo;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(onPressed: (){

              PlatformService().showToast();
            }, child: Text("Show Native Toast")),
            ElevatedButton(onPressed: ()async{
              final level = await PlatformService().getBatteryLevel();
              setState(() {
                batteryLevel = level;
              });
            }, child: Text("Show Battery")),
            if(batteryLevel!=null)Text(batteryLevel!),
            ElevatedButton(onPressed: (){
              PlatformService().onVibrator();
            }, child: Text("Vibrator")),
            ElevatedButton(onPressed: ()async{
             final info =await PlatformService().getDeviceInfo();
             setState(() {
               deviceInfo = info;
             });
            }, child: Text("Device Info")),
            if(deviceInfo!=null)Padding(
              padding: const EdgeInsets.symmetric(horizontal: 40),
              child: Text(deviceInfo.toString()),
            ),
          ],
        ),
      ),
    );
  }
}
