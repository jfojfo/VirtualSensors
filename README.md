VirtualSensors
==============
emulator will get virtual sensor's data from your phone device

SdkController
==============
Picked from android source code,
see android_src/sdk/apps/SdkController

usage:
connect your phone device with PC and run:
adb forward tcp:1970 localabstract:android.sdk.controller

then start SdkController app on your phone
and start your android emulator on PC

emulator -avd your_avd -debug sensors_port,mtport,sdkctlsocket -screen multi-touch


Runs successfully on Xiaomi3 phone (android-4.4.4)
emulator's API level is 17 (android-4.2.2)



SensorsTest
==============
An app shows sensors' values
just install the app on emulator to see its virtual sensors' data



virtual_sensors
==============
virtual sensors in HAL of Android.

Compiled with cubieboard2 Android source code,
path: android_src/development/tools/emulator/system/virtual_sensors


virtual_sensors_client
==============
send random data to virtual_sensors

It's android jni project

