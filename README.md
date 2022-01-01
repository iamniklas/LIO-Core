# LIO-Core

[![](https://jitpack.io/v/iamniklas/LIO-Core.svg)](https://jitpack.io/#iamniklas/LIO-Core)

Central library that makes the LIO features available to all sub-projects.
The advantage is that all projects are updated at once if a change is implemented across the entire project.

## 1. Core Features 
(Last time updated at version 0.0.6-9.8)

- Colors (RGB, RGBA, HSV, LIO Color)
- Rendering interface
- JSON Communication standards
- Default procedures/animations and javascript-scriptable procedure (experimental)
- Server/Client communication with _custom network implementation_ and _HTTP_ via _LIO-API_
- Server/Client communication with _MQTT/Mosquitto_ (experimental)

## 2. Installation
via Jitpack: [https://jitpack.io/#iamniklas/LIO-Core](https://jitpack.io/#iamniklas/LIO-Core)

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
This project uses the following gradle dependencies (Last time updated at version 0.0.6-9.8)

| Name | Gradle Dependency | Link |
| --- | --- | --- |
| google/gson | ``com.google.code.gson:gson:2.8.9`` | https://mvnrepository.com/artifact/com.google.code.gson/gson
| iamniklas/interpolation | ``com.github.iamniklas:Interpolation:1.0.0-1`` | https://github.com/iamniklas/Interpolation
| eclipsepaho/mqttv3Client | ``org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.2.5`` | https://mvnrepository.com/artifact/org.eclipse.paho/org.eclipse.paho.client.mqttv3
| junit/junit | ``junit:junit:4.13.2`` | https://github.com/junit-team/junit4
| jupiter/junit | ``org.junit.jupiter:junit-jupiter:5.8.2`` | https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api