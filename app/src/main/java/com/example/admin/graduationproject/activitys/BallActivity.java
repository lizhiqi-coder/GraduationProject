package com.example.admin.graduationproject.activitys;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Toast;

import com.example.admin.graduationproject.R;
import com.example.admin.graduationproject.render.BallRender;
import com.example.admin.graduationproject.utils.AppUtils;
import com.example.admin.graduationproject.widget.MenuPopWindow;

/**
 * Created by admin on 2016/5/17.
 */
public class BallActivity extends BaseActivity {

    private GLSurfaceView glSurfaceView;
    private BallRender mRender;
    private boolean hasSetRender = false;

    private FloatingActionButton mFab;


    private View mHeadLine;

    private static int[] textureList = {

            R.mipmap.overall_view01,
            R.mipmap.overall_view02,
            R.mipmap.overall_view03,

    };

    private static String[] textureNames = {
            "aaa",
            "bbb",
            "ccc"
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball);

        glSurfaceView = (GLSurfaceView) findViewById(R.id.ball_surface);

        mFab = (FloatingActionButton) findViewById(R.id.ball_fab);

//        mHeadLine = findViewById(R.id.head_line);

        mRender = new BallRender(this);

        if (AppUtils.isOpenGl2Support()) {
            glSurfaceView.setEGLContextClientVersion(2);

            glSurfaceView.setRenderer(mRender);

            hasSetRender = true;
        } else {
            Toast.makeText(this, "this device does support opengl ES 2.0", Toast.LENGTH_SHORT).show();

            return;
        }


        initTouchListener(glSurfaceView, mRender);

        final MenuPopWindow popMenu = new MenuPopWindow(this);

        for (int i = 0; i < textureList.length; i++) {
            final int TextureId = textureList[i];

            MenuPopWindow.Item item = popMenu.createItem();
            item.setItemImg(TextureId);
            item.setItemTitle(textureNames[i]);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mRender.setTextrueResId(TextureId);
                    popMenu.dismissWindow();
                }
            });
        }


        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getWindow().getDecorView() != null) {
                    popMenu.show();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (hasSetRender) {
            glSurfaceView.onResume();

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (hasSetRender) {
            glSurfaceView.onPause();
        }
    }
}
