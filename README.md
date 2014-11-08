VirtualSensors
==============
SdkController
==============
Picked from android source code,
see android_src/sdk/apps/SdkController

usage:
connect your phone device with PC and run:
adb forward tcp:1970 localabstract:android.sdk.controller

then start SdkController app on your phone
and start your android emulator on PC


Runs successfully on Xiaomi3 phone (android-4.4.4)
emulator's API level is 17 (android-4.2.2)



SensorsTest
==============
An app shows sensors' values
