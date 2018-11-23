package me.xeno.frescoimagedemo;

import android.os.Handler;
import android.util.Log;
import android.view.Choreographer;

import java.util.concurrent.TimeUnit;

public class FpsCallback implements Choreographer.FrameCallback {

    private static final int INTERVAL = 10;//統計一次的間隔

    private boolean stopFlag = false;

    private long lastFrameTime;
    private long recordStartFrameTime;

    private int frameCount;

    private StHandlerThread mHandlerThread = new StHandlerThread("統計");
    private Handler mHandler;

    @Override
    public void doFrame(long frameTimeNanos) {
        if(recordStartFrameTime == 0) {
            recordStartFrameTime = frameTimeNanos;
        }

        if(frameTimeNanos < recordStartFrameTime + TimeUnit.SECONDS.toNanos(INTERVAL)) {
//            Log.i("xenofps", "當前幀數：" + frameCount + "," + frameTimeNanos + ",INTER" + recordStartFrameTime + TimeUnit.SECONDS.toNanos(INTERVAL));
            frameCount++;
        } else {
            Log.i("xenofps", "幀數：" + frameCount);
            frameCount = 0;
            recordStartFrameTime = 0;
        }
        Choreographer.getInstance().postFrameCallback(this);
    }

    public void startMonitor() {
//        mHandlerThread.start();
//        mHandler = new Handler(mHandlerThread.getLooper());
    }
}
