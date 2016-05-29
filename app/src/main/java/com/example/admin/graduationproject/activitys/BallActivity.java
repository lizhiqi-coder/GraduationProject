package com.example.admin.graduationproject.activitys;

import android.app.ProgressDialog;
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

    private ProgressDialog mProgressBar;


    private static int[] textureList = {

            R.mipmap.overall_view02,
            R.mipmap.overall_view03,
            R.mipmap.world,
            R.mipmap.all_view,
            R.mipmap.all_view02,
            R.mipmap.all_view03,
            R.mipmap.all_view04,
            R.mipmap.all_view05,
            R.mipmap.all_view06,
    };


    private static String[] textureNames = {
            "overall_view02",
            "overall_view03",
            "world",
            "all_view",
            "all_view02",
            "all_view03",
            "all_view04",
            "all_view05",
            "all_view06"
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball);
        initView();

        glSurfaceView = (GLSurfaceView) findViewById(R.id.ball_surface);

        mFab = (FloatingActionButton) findViewById(R.id.ball_fab);


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
            final int textureResId = textureList[i];

            MenuPopWindow.Item item = popMenu.createItem();
            item.setItemImg(textureResId);
            item.setItemTitle(textureNames[i]);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    popMenu.dismissWindow();
                    mProgressBar.show();

                    mRender.setTextrueResId(textureResId, new BallRender.CallBack() {
                        @Override
                        public void onLoadComplete() {
                            mProgressBar.dismiss();
                            glSurfaceView.onPause();
                            glSurfaceView.onResume();
                        }
                    });

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

    private void initView() {
        mProgressBar = new ProgressDialog(this);
        mProgressBar.setMessage("全景图片文件较大，请耐性等候加载。。。");
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressBar.setCancelable(false);
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
