package com.example.admin.graduationproject.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.graduationproject.R;

import java.util.ArrayList;

/**
 * Created by lizhiqi on 2016/3/3.
 */
public class MenuPopWindow {
    private Dialog mPopWindow;
    private Context mContext;
    private View mView;
    private LinearLayout mItemContanier;

    private LayoutInflater mInflater;

    private ArrayList<Item> mMenuItemList;

    public MenuPopWindow(Context context) {
        mContext = context;

        mInflater = LayoutInflater.from(mContext);

        mView = mInflater.inflate(R.layout.menu_pop_window_layout, null);
        mItemContanier = (LinearLayout) mView.findViewById(R.id.menu_pop_item_container);

        mMenuItemList = new ArrayList<>();

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

    public void addItem(Item item) {
        mItemContanier.addView(item);
        mMenuItemList.add(item);
    }

    public Item createItem() {

        Item mItemView = new Item(mContext);

        addItem(mItemView);
        return mItemView;
    }


    public class Item extends LinearLayout {
        private ImageView itemImg;
        private TextView itemTitle;
        private View itemBottomLine;


        public Item(Context context) {
            super(context);
            mInflater.inflate(R.layout.menu_pop_window_item, this);
            itemImg = (ImageView) findViewById(R.id.item_img);
            itemTitle = (TextView) findViewById(R.id.item_title);
            itemBottomLine = findViewById(R.id.item_bottom_line);
        }

        public void setItemImg(int id) {
            this.itemImg.setImageResource(id);
        }

        public void setItemTitle(String text) {
            this.itemTitle.setText(text);
        }

        public void hideBottomLine() {
            itemBottomLine.setVisibility(GONE);
        }
    }

}
