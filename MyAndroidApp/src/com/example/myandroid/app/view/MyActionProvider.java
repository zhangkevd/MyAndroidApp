package com.example.myandroid.app.view;

import android.content.Context;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myandroid.R;

/**
 * 自定义action provider<br>
 * <h1>在Menu的XML文件配置一个item标签，使用android:actionProviderClass=
 * "com.example.myandroidapp.MyActionProvider"</h1>
 * 
 * @author zhangwei2
 * 
 */
public class MyActionProvider extends ActionProvider {
	private Context mContext = null;

	// public MyActionProvider() {
	// // TODO Auto-generated constructor stub
	// }

	public MyActionProvider(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	@Override
	public View onCreateActionView() {
		// Inflate the action view to be shown on the action bar.

		LayoutInflater layoutInflater = LayoutInflater.from(mContext);
		View view = layoutInflater.inflate(R.layout.action_provider, null);
		ImageButton button = (ImageButton) view.findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Do something...
				Toast.makeText(mContext, "ImageButton", Toast.LENGTH_LONG)
						.show();
			}
		});
		return view;

	}
}
