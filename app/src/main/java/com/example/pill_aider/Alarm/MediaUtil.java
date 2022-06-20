package com.example.pill_aider.Alarm;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;

/**
 * @description 铃声播放工具类
 */
public class MediaUtil {

    private static MediaPlayer mMediaPlayer;

    /**
     * @description 开始播放
     * @param context
     */
    public static void playRing(Context context){
        try {
            //用于获取手机默认铃声的Uri
            Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(context, alert);
            //告诉mediaPlayer播放的是铃声流
            mMediaPlayer.setAudioAttributes(new AudioAttributes
                    .Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build());
            mMediaPlayer.setLooping(false);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @description 停止播放
     */
    public static void stopRing(){
        if (mMediaPlayer!=null){
//            if (mMediaPlayer.isPlaying()){
                mMediaPlayer.stop();
                mMediaPlayer.release();
//            }
        }
    }
}