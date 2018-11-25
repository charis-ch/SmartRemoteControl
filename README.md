# SmartRemoteControl

The SmartRemoteControl is an application which is actually replace the remote control of a device (TV , Radio)
The application sends the infrared code (according to user decision when pressing a corresponding button on the smartphone) to a
microcontroller in this case an Arduino (equipped with ethernet shield) via UDP protocol.
The Command Design Pattern is used for sending the right command(infrared code) to Arduino via UDP.
This repository contains ONLY the src files of an Android Studio Project (.java files) and not the infrared codes files.
