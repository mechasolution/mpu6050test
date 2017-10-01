# mpu6050test
This driver supports Invensense <a href='https://www.invensense.com/products/motion-tracking/6-axis/mpu-6050/'>MPU6050</a> 6 axis IMU sensor.

<a href='https://store.invensense.com/datasheets/invensense/MPU-6050_DataSheet_V3%204.pdf'>Datasheet</a>\
<a href='https://www.invensense.com/wp-content/uploads/2015/02/MPU-6000-Register-Map1.pdf'>I2C register map</a>

If you're Korean please visit <a href='www.mechasolution'>our website</a> check our <a href='http://mechasolution.com/shop/goods/goods_view.php?goodsno=543077&category=048'>AndroidThings Kit</a>. we provide <a href='https://github.com/mechasolution/AndroidThingsTextBook'>textbook</a> and project code in github.

NOTE: these drivers are not production-ready. They are offered as sample implementations of Android Things user space drivers for common peripherals as part of the Developer Preview release. There is no guarantee of correctness, completeness or robustness.

## How to use the driver
### Gradle dependency
To use the mpu6050 driver, simply add the line below to your project's build.gradle. The package hasn't linked to Jcenter yet, so you need to add them until the package is linked to Jcenter.
```
allprojects {
    repositories {
        jcenter()
        maven { url "https://dl.bintray.com/mechasolution/androidthings/" }
    }
}
```
alos to your app's build.gradle, where <version> matches the last version of the driver available on <a href='https://bintray.com/mechasolution/androidthings/mpu6050/_latestVersion'>Jcenter</a>.

```
dependencies {
    compile 'com.mechasolution:mpu6050:<version>'
}
```

### Sample usage

```
import mechasolution.mpu6050.mpu6050;
  
mpu6050 mMpu = new mpu6050();
  
try {
    mMpu.open();
} catch ( IOException e ) { e.printStackTrace(); }
  
try {
    Log.i("Acel", String.format("%f \t %f \t %f", mMpu.getAccelX(), mMpu.getAccelY(), mMpu.getAccelZ()));
    Log.i("Temp", String.format("%f", mMpu.getTemp()));
    Log.i("Gyro", String.format("%f \t %f \t %f", mMpu.getGyroX(), mMpu.getGyroY(), mMpu.getGyroZ()));
} catch ( IOException e ) { e.printStackTrace(); }
```

## License

Copyright 2017 Mechasolution

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

<a href='http://www.apache.org/licenses/LICENSE-2.0'>http://www.apache.org/licenses/LICENSE-2.0</a>

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
