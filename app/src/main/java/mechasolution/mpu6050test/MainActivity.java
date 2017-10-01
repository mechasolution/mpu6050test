package mechasolution.mpu6050test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import mechasolution.mpu6050.mpu6050;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();
    /*
        I2cDevice mDevice;
        Thread mThread = new Thread() {
            public void run() {
                byte[] buffer = new byte[14];
                while (true) {
                    try {
                        mDevice.readRegBuffer(0x3B, buffer, 14);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    double acX = (double) appendByte(buffer[0], buffer[1]) / 32768. / 2.;
                    double acY = (double) appendByte(buffer[2], buffer[3]) / 32768. / 2.;
                    double acZ = (double) appendByte(buffer[4], buffer[5]) / 32768. / 2.;
                    double Tmp = (double) appendByte(buffer[6], buffer[7]) / 340. + 36.53;
                    double gyX = (double) appendByte(buffer[8], buffer[9]) * 250. / 32768.;
                    double gyY = (double) appendByte(buffer[10], buffer[11]) * 250. / 32768.;
                    double gyZ = (double) appendByte(buffer[12], buffer[13]) * 250. / 32768.;

                    Log.i("Acel", String.format("%f \t %f \t %f", acX, acY, acZ));
                    Log.i("Temp", String.format("%f", Tmp));
                    Log.i("Gyro", String.format("%f \t %f \t %f", gyX, gyY, gyZ));

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }z
                }
            }
        };

        public short appendByte(byte a, byte b) {
            return (short) (((a << 8) & 0xff00) | (b & 0x00ff));
        }
    */

    mpu6050 mMpu = new mpu6050();
    Thread mThread = new Thread() {
        public void run() {
            while (true) {
                try {
                    Log.i("Acel", String.format("%f \t %f \t %f", mMpu.getAccelX(), mMpu.getAccelY(), mMpu.getAccelZ()));
                    Log.i("Temp", String.format("%f", mMpu.getTemp()));
                    Log.i("Gyro", String.format("%f \t %f \t %f", mMpu.getGyroX(), mMpu.getGyroY(), mMpu.getGyroZ()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        PeripheralManagerService pioService = new PeripheralManagerService();
        try {
            mDevice = pioService.openI2cDevice("I2C1", 0x68);
            mDevice.writeRegByte(0x6B, (byte) 0x00);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        try {
            mMpu.open();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        mThread.start();
    }
}