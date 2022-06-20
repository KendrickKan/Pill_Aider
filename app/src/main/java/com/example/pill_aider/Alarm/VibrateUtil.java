package com.example.pill_aider.Alarm;

import android.app.Service;
import android.content.Context;
import android.os.Build;
import android.os.VibrationAttributes;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;

import androidx.annotation.RequiresApi;

/**
 * @description 振动工具类
 */
public class VibrateUtil {
    static private final long[] pattern = {1000, 800, 800, 800};

    /**
     * 让手机振动
     */
    @RequiresApi(api = Build.VERSION_CODES.R)
    public static void vibrate(Context context) {
        Vibrator vib = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        if(vib.hasVibrator()){  //判断手机硬件是否有振动器
            VibrationEffect effect = VibrationEffect.createWaveform(pattern, 0);
            vib.vibrate(effect);
        }
    }

    /**
     * 取消震动
     */
    public static void cancel(Context context){
        //关闭震动
        Vibrator vib = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        vib.cancel();
    }

}
