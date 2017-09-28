package mechasolution.mpu6050test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.things.pio.I2cDevice;
import com.google.android.things.pio.PeripheralManagerService;

import java.io.IOException;

public class MainActivity extends Activity {
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
                }
            }
        }
    };

    public short appendByte(byte a, byte b) {
        return (short) (((a << 8) & 0xff00) | (b & 0x00ff));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PeripheralManagerService pioService = new PeripheralManagerService();
        try {
            mDevice = pioService.openI2cDevice("I2C1", 0x68);
            mDevice.writeRegByte(0x6B, (byte) 0x00);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mThread.start();
    }
}

//https://www.google.co.kr/search?q=%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C+%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC+%EB%B0%B0%ED%8F%AC%ED%95%98%EA%B8%B0+%EA%B7%B8%EB%9E%98%EB%93%A4&oq=%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C+%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC+%EB%B0%B0%ED%8F%AC%ED%95%98%EA%B8%B0+%EA%B7%B8%EB%9E%98%EB%93%A4&aqs=chrome..69i57.7968j0j7&sourceid=chrome&ie=UTF-8