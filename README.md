# Invisible

## Forensics project - 2018 - Eurecom

This project is similar to the project https://github.com/LordNoteworthy/al-khaser but focused on android malware analysis.

#### **How to install it ?**

- **On your phone/on an emulator** using the .apk file

Download the .apk file in the folder app/build/outputs/apk/debug/.
Make sure you can install apps from unknown sources (in Settings > Security > Unknown sources).
Open this file on your phone. 

- **On your phone/on an emulator** using Android Studio

Download the repository to your computer.
Download Android Studio and Java jdk if you do not have them.
Connect your phone to your computer using a usb cable.
The developper mode should be activated on your phone (check this website to activate it: https://developer.android.com/studio/debug/dev-options)
Open the repository in Android Studio and run it on your phone/emulator using or not the debug mode.

#### **How to use it ?**

- Accept/deny the permissions (most tasks do not need them but the one that does won't be able to execute)
- Push the button **Start**
- Touch a task name to get more/less information on why the task succeeded/failed

#### **What are the different tasks ?**

##### 1. Checking the internet browser history
Fortunately, since Android 6.0, it is impossible to access the browser history. Therefore, a malware could not use any information relative to this.

##### 2. Checking the applications installed:
   - Look for the number of applications installed
   - Look if some of the most downloaded on Android are installed on the phone.

##### 3. Checking the storage of the device:
   - Check the free space
   - Check the used space
   - Check the total space

##### 4. Checking sim card information
   - Check the name of the network operator
   - Check the software version

##### 5. Checking build information
   - check common values of 

##### 6. Checking for debugger:
   - check for the debuggable flag
   - check for the time spent in each task and compare it to reasonable values
