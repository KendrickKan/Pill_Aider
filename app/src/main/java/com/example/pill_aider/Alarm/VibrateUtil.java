package com.example.pill_aider.Alarm;

import android.app.Service;
import android.content.Context;
import android.os.Build;
import android.os.VibrationAttributes;
import android.os.VibrationEffect;
import android.os.Vibrator;

import androidx.annotation.RequiresApi;

/**
 * @description 振动工具类
 */
public class VibrateUtil {
    /**
     * 让手机振动
     */
    @RequiresApi(api = Build.VERSION_CODES.R)
    public static void vibrate(Context context) {
        Vibrator vib = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        if(vib.hasVibrator()){  //判断手机硬件是否有振动器
            VibrationAttributes.Builder b = new VibrationAttributes.Builder();
            b.setUsage(VibrationAttributes.USAGE_CLASS_ALARM);
            VibrationAttributes v = b.build();
//            VibrationEffect effect = new VibrationEffect();
//
//            vib.vibrate(VibrationEffect.EFFECT_HEAVY_CLICK, v);
        }
    }

    /**
     * 取消震动
     */
    public static void virateCancle(Context context){
        //关闭震动
        Vibrator vib = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        vib.cancel();
    }

}
