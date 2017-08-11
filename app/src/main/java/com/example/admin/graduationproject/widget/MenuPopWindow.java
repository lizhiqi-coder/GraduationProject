package com.example.admin.graduationproject.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.graduationproject.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhiqi on 2016/3/3.
 */
public class MenuPopWindow {
    private Dialog mPopWindow;
    private Context mContext;
    private View mView;
    private ListView mListView;

    private LinearLayout mItemContanier;

    private LayoutInflater mInflater;


    private SelectImageListener mListener;
    private static final String IMAGE_DIR = "/storage/emulated/0/Android/images/";

    public MenuPopWindow(Context context) {
        mContext = context;

        mInflater = LayoutInflater.from(mContext);

        mView = mInflater.inflate(R.layout.menu_pop_window_layout, null);

        mItemContanier = (LinearLayout) mView.findViewById(R.id.menu_pop_item_container);

        mListView = (ListView) mView.findViewById(R.id.ls_images);


        ImageAdapter adapter = new ImageAdapter(mContext);
        adapter.setImagePath(searchImage(IMAGE_DIR));

        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String imagePath = (String) parent.getItemAtPosition(position);
                if (mListener != null) {
                    mListener.onSelectImage(imagePath);
                }
                dismissWindow();
            }
        });


        if (mPopWindow == null) {

            mPopWindow = new Dialog(mContext, R.style.dialog);
            mPopWindow.setContentView(mView);
            mPopWindow.setCanceledOnTouchOutside(true);

            mView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    dismissWindow();
                    return false;
                }
            });
        }

    }

    public void show() {
        mPopWindow.show();
    }


    public void dismissWindow() {
        if (mPopWindow != null && mPopWindow.isShowing()) {

            mPopWindow.dismiss();
        }

    }

    public void setSelectImageListener(SelectImageListener listener) {
        mListener = listener;
    }


    private List<String> searchImage(String dirPath) {

        ArrayList<String> imagePath = new ArrayList<>();
        File dir = new File(dirPath);

        File[] files = dir.listFiles();

        if (files == null) {
            return imagePath;
        }

        for (int i = 0; i < files.length; i++) {
            File f = files[i];
            if (f.isFile()) {
                if (f.getName().endsWith("jpg")) {
                    imagePath.add(f.getPath());
                }
            }
        }

        return imagePath;

    }

    public class ImageAdapter extends BaseAdapter {


        private Context context;
        List<String> images = new ArrayList<>();

        public ImageAdapter(Context context) {
            this.context = context;
        }

        public void setImagePath(List<String> imagePath) {

            images = imagePath;
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public Object getItem(int position) {

            return images.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;

            if (convertView == null) {

                convertView = View.inflate(context, R.layout.image_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.setImage(images.get(position));

            return convertView;
        }
    }

    public class ViewHolder {
        private ImageView imageView;
        private TextView textView;

        private Bitmap bitmap;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.iv_image);
            textView = (TextView) view.findViewById(R.id.tv_name);
        }

        public void setImage(String imagePath) {

            try {
                File file = new File(imagePath);
                FileInputStream fis = new FileInputStream(imagePath);

                bitmap = BitmapFactory.decodeStream(fis);
                imageView.setImageBitmap(bitmap);
                textView.setText(file.getName());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public interface SelectImageListener {
        void onSelectImage(String path);
    }
}
