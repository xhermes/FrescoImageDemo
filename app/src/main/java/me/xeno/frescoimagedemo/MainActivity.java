package me.xeno.frescoimagedemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Choreographer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    private SimpleDraweeView ivStatic;
//    private SimpleDraweeView ivMove;

    private LinearLayout mContainer;
    private Button mStartButton;
    private Button mSwitchButton;
    private TextView mCurrentView;

    private View mSingleLayout;

    private LinearLayout mLottieLayout;
    private LottieAnimationView mLottie1View;
    private LottieAnimationView mLottie2View;

//    private CpuSampler mCpuSampler;

//    private DraweeController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mStartButton = (Button) findViewById(R.id.start_btn);
        mSwitchButton = (Button) findViewById(R.id.switch_btn);
        mCurrentView = (TextView) findViewById(R.id.current);
        mContainer = (LinearLayout) findViewById(R.id.container);
        mSingleLayout = findViewById(R.id.single_layout);
        init();
//        initSingleImage();
        initLottie();

        mStartButton.setOnClickListener(this);
        mSwitchButton.setOnClickListener(this);

//        Choreographer.getInstance().postFrameCallback(new FpsCallback());
//        mCpuSampler = new CpuSampler(100);
    }

    private void init() {
        //豎排
        for (int i = 0; i < 1; i++) {
            createHorizontalLayout();
        }
        findViewById(R.id.single).setVisibility(View.GONE);
    }

    private void initSingleImage() {
        mContainer.setVisibility(View.GONE);

        SimpleDraweeView singleView = findViewById(R.id.single);
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse("res://" + getApplication().getPackageName() + "/" + R.drawable.angelwebp))
                .setControllerListener(controllerListener)
                .build();
        singleView.setController(controller);

        SimpleDraweeView secView = findViewById(R.id.second);
        DraweeController controller2 = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse("res://" + getApplication().getPackageName() + "/" + R.drawable.angelwebp))
                .setControllerListener(controllerListener)
                .build();
        secView.setController(controller2);
//
//        SimpleDraweeView thrView = findViewById(R.id.third);
//        DraweeController controller3 = Fresco.newDraweeControllerBuilder()
//                .setUri(Uri.parse("res://" + getApplication().getPackageName() + "/" + R.drawable.angelwebp))
//                .setControllerListener(controllerListener)
//                .build();
//        thrView.setController(controller3);
    }

    private void initLottie() {
        mSingleLayout.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);

        mLottie1View = findViewById(R.id.lottie1);
        mLottie2View = findViewById(R.id.lottie2);

        mLottie1View.playAnimation();
        mLottie2View.setVisibility(View.GONE);
//        mLottie2View.playAnimation();
    }

    private void createHorizontalLayout() {
        LinearLayout ll = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.layout_horizontal, mContainer, false);
        //橫排
        for (int i = 0; i < 1; i++) {
            createSimpleDrawee(ll);
        }
        mContainer.addView(ll);
    }

    @SuppressLint("NewApi")
    private void createSimpleDrawee(ViewGroup parent) {
        SimpleDraweeView sdv = new SimpleDraweeView(this);
        sdv.setBackgroundColor(getColor(R.color.colorPrimary));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dip2px(this, 200), dip2px(this, 100));
        sdv.setLayoutParams(lp);
        parent.addView(sdv);
    }

    private void testGif() {
        mCurrentView.setText("gif");

        for (int i = 0; i < mContainer.getChildCount(); i++) {

            View child = mContainer.getChildAt(i);
            if (child instanceof LinearLayout) {
                LinearLayout ll = (LinearLayout) child;
                for (int j = 0; j < ll.getChildCount(); j++) {
                    SimpleDraweeView sdv = (SimpleDraweeView) ll.getChildAt(j);
                    DraweeController controller = Fresco.newDraweeControllerBuilder()
                            .setUri(Uri.parse("res://" + getApplication().getPackageName() + "/" + R.drawable.angelsmall))
                            .setControllerListener(controllerListener)
                            .build();
                    sdv.setController(controller);
                }
            }
        }
    }

    private void testWebp() {
        mCurrentView.setText("webp");

        for (int i = 0; i < mContainer.getChildCount(); i++) {

            View child = mContainer.getChildAt(i);
            if (child instanceof LinearLayout) {
                LinearLayout ll = (LinearLayout) child;
                for (int j = 0; j < ll.getChildCount(); j++) {
                    SimpleDraweeView sdv = (SimpleDraweeView) ll.getChildAt(j);
                    DraweeController controller = Fresco.newDraweeControllerBuilder()
                            .setUri(Uri.parse("res://" + getApplication().getPackageName() + "/" + R.drawable.angelwebp))
                            .setControllerListener(controllerListener)
                            .build();
                    sdv.setController(controller);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.start_btn:
                testGif();
                //开跑cpuSampler
//                mCpuSampler.start();
                break;
            case R.id.switch_btn:
                if(mCurrentView.getText().equals("webp"))
                    testGif();
                else
                    testWebp();
                break;
//            case R.id.btn_load_static:
//                ivStatic.setImageResource(R.drawable.bg_tool_image);
//                break;
//            case R.id.btn_load_move:
//                AbstractDraweeController build = Fresco.newDraweeControllerBuilder()
//                        .setUri(Uri.parse("https://isparta.github.io/compare-webp/image/gif_webp/webp/1.webp"))
//                        .setControllerListener(controllerListener)
//                        .build();
//                ivMove.setController(build);
//                break;
//            case R.id.btn_load_move1:
//                AbstractDraweeController build1 = Fresco.newDraweeControllerBuilder()
//                        .setUri(Uri.parse("res://" + getApplication().getPackageName() + "/" + R.drawable.youlun))
//                        .setControllerListener(controllerListener1)
//                        .build();
//                ivMove.setController(build1);
//                break;
//            default:
//                break;
        }
    }


    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
        @Override
        public void onFinalImageSet(
                String id,
                @Nullable ImageInfo imageInfo,
                @Nullable Animatable anim) {
            if (anim != null) {
                // app-specific logic to enable animation starting
                anim.start();
            }
        }
    };

    ControllerListener controllerListener1 = new BaseControllerListener<ImageInfo>() {
        @Override
        public void onFinalImageSet(
                String id,
                @Nullable ImageInfo imageInfo,
                @Nullable Animatable anim) {
            if (anim != null) {
                // app-specific logic to enable animation starting
                anim.start();
            }
        }
    };

//    @Override
//    public void onTrimMemory(int level) {
//        super.onTrimMemory(level);
//        Log.i("xeno", "onTrimMem, level:" + level);
//    }
}
