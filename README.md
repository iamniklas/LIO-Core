# LIO-Core

[![](https://jitpack.io/v/iamniklas/LIO-Core.svg)](https://jitpack.io/#iamniklas/LIO-Core)

Central library that makes the LIO features available to all sub-projects.
The advantage is that all projects are updated at once if a change is to be implemented across the entire project.

## 1. Core Features 
(Last time updated at version 0.0.8-1.2)

- Colors (RGB, RGBA, HSV, LIO Color)
- Default procedures/animations 
- Rendering interface
- JSON Communication standards
- Completely configurable network and LED Manager by config file (Host, VHost, Single-LED-Test-Device) or Shared Preferences file (LIO-Connect-Android, extended functionality in app project)

&nbsp;
The following features are still under development and some have not yet been worked on at all. See additional information for details.
- ActionTags: Scan an nfc chip with your mobile device and the set animation will play on the targeting devices. (this feature is still getting tested, will be available with version 0.0.9; LIO-Connect-Android exclusive)
- Message Broker network communication with _MQTT/Mosquitto_ (stable; becoming completely verified with version 0.0.10)
- Live Update Variables to change animation behaviour without reloading (not implemented yet; will be available with version 0.0.11)
- Time-Synched-Update: Sync animations across multiple devices without the need of them communicating (not implemented yet; will be available with version 0.0.12)
- Javascript-scriptable procedure (not available yet due to testing; becoming stable with version 0.0.13)
- Spotify Integration for mobile phones: The app detects playback changes if you are listening/controlling spotify on your mobile device. The app then fetches animation data from Firebase to sync the light to the music. (not started yet; will be available with version 0.0.14)

## 2. Installation
Via Jitpack: [https://jitpack.io/#iamniklas/LIO-Core](https://jitpack.io/#iamniklas/LIO-Core)

### a. Gradle

1. Add the __Maven Repository__ to build.gradle
 
```gradle
   repositories { 
        jcenter()
        maven { url "https://jitpack.io" }
   }
```

2. Add the __LIO-Core dependency__ (Replace [VERSION] with the version code at the top)

``` gradle
   dependencies {
         implementation 'com.github.iamniklas:LIO-Core:[VERSION]'
   }
```  

### b. Maven

1. Add the Repository to the __build file__

``` maven
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
```

2. Add __LIO-Core dependency__ (Replace 'Tag' with the version code at the top)

``` maven
    <dependency>
        <groupId>com.github.iamniklas</groupId>
        <artifactId>LIO-Core</artifactId>
        <version>Tag</version>
    </dependency>
```

## 3. Additional dependencies
This project uses the following gradle dependencies (Last time updated at version 0.0.7-1.16)

| Name | Gradle Dependency | Link |
| --- | --- | --- |
| google/guava | ``com.google.guava:guava:31.0.1-jre`` | https://mvnrepository.com/artifact/com.google.guava
| google/gson | ``com.google.code.gson:gson:2.8.9`` | https://mvnrepository.com/artifact/com.google.code.gson/gson
| iamniklas/interpolation | ``com.github.iamniklas:Interpolation:1.0.0-2`` | https://github.com/iamniklas/Interpolation
| eclipsepaho/mqttv3Client | ``org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.2.5`` | https://mvnrepository.com/artifact/org.eclipse.paho/org.eclipse.paho.client.mqttv3
| junit/junit | ``junit:junit:4.13.2`` | https://github.com/junit-team/junit4
| jupiter/junit | ``org.junit.jupiter:junit-jupiter:5.8.2`` | https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api
