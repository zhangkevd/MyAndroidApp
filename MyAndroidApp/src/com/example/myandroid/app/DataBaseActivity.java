package com.example.myandroid.app;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.myandroid.R;
import com.example.myandroid.app.dao.FeedReaderContract.FeedEntry;
import com.example.myandroid.app.db.FeedReaderDbHelper;

public class DataBaseActivity extends Activity implements OnClickListener {
	private Button btn1 = null;
	private Button btn2 = null;
	private Button btn3 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (btn1.getId() == v.getId()) {
			insertdata();

		} else if (btn2.getId() == v.getId()) {

		} else if (btn3.getId() == v.getId()) {

		} else {

		}

	}

	private void insertdata() {
		FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(
				getApplicationContext());
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(FeedEntry.COLUMN_NAME_ENTRY_ID, "COLUMN_NAME_ENTRY_ID");
		values.put(FeedEntry.COLUMN_NAME_TITLE, "COLUMN_NAME_TITLE");

//		db.insert(FeedEntry.TABLE_NAME, FeedEntry.c, values)
	}
}
