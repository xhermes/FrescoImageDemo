package me.xeno.frescoimagedemo;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Choreographer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    private SimpleDraweeView ivStatic;
//    private SimpleDraweeView ivMove;

    private LinearLayout mContainer;
    private Button mStartButton;
    private Button mSwitchButton;
    private TextView mCurrentView;

//    private DraweeController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mStartButton = (Button) findViewById(R.id.start_btn);
        mSwitchButton = (Button) findViewById(R.id.switch_btn);
        mCurrentView = (TextView) findViewById(R.id.current);

        init();

        mStartButton.setOnClickListener(this);
        mSwitchButton.setOnClickListener(this);

        Choreographer.getInstance().postFrameCallback(new FpsCallback());
    }

    private void init() {
        mContainer = (LinearLayout) findViewById(R.id.container);
        //豎排
        for (int i = 0; i < 8; i++) {
            createHorizontalLayout();
        }
    }

    private void createHorizontalLayout() {
        LinearLayout ll = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.layout_horizontal, mContainer, false);
        //橫排
        for (int i = 0; i < 6; i++) {
            createSimpleDrawee(ll);
        }
        mContainer.addView(ll);
    }

    private void createSimpleDrawee(ViewGroup parent) {
        SimpleDraweeView sdv = new SimpleDraweeView(this);
        sdv.setBackgroundColor(getColor(R.color.colorPrimary));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dip2px(this, 50), dip2px(this, 50));
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
                            .setUri(Uri.parse("res://" + getApplication().getPackageName() + "/" + R.drawable.testgif))
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
                            .setUri(Uri.parse("res://" + getApplication().getPackageName() + "/" + R.drawable.test))
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
}
