package com.example.admin.graduationproject.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.admin.graduationproject.R;


public class MyDialog {

	private Dialog mDialog;
	private TextView dialog_title;
	private TextView dialog_message;
	public TextView positive;
	public TextView negative;
	private View view_line;
	private Context mContext;

	public MyDialog(Context context, String title, String message) {
		mContext = context;
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View view = inflater.inflate(R.layout.dialog_layout, null);

		mDialog = new Dialog(mContext, R.style.dialog);
		mDialog.setContentView(view);
		mDialog.setCanceledOnTouchOutside(false);

		dialog_title = (TextView) view.findViewById(R.id.dialog_title);
		dialog_message = (TextView) view.findViewById(R.id.dialog_message);
		view_line = view.findViewById(R.id.view_line);
		dialog_title.setText(title);
		dialog_message.setText(message);

		positive = (TextView) view.findViewById(R.id.yes);
		negative = (TextView) view.findViewById(R.id.no);

	}
	
	public void setNoTitle(){
		dialog_title.setVisibility(View.GONE);
		view_line.setVisibility(View.GONE);
	}

	public void show() {
		if (mContext == null)
			return;
		Activity activity = (Activity) mContext;
		if (activity.isFinishing())
			return;
		mDialog.show();
	}

	public void dismiss() {
		mDialog.dismiss();
	}

}
