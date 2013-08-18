package com.example.myandroid.app;

import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.WindowManager;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.example.myandroid.R;
import com.example.myandroid.app.util.Cheeses;
import com.example.myandroid.app.view.MyActionProvider;

public class MainActivity extends Activity implements OnClickListener,
		OnNavigationListener {
	private Button btnFragment = null;
	private Button btnLoader = null;
	private Button btnPop = null;
	private ListView lv1 = null;
	private static boolean isChecked = false;
	private static final String TAG = "MainActivity";
	private ActionMode mActionMode = null;
	private View mMainView = null;
	private ShareActionProvider mShareActionProvider = null;
	private MyActionProvider map = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mMainView = getLayoutInflater().inflate(R.layout.activity_main, null);
		setContentView(mMainView);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				   WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		btnFragment = (Button) findViewById(R.id.btnFragment);
		btnLoader = (Button) findViewById(R.id.btnLoader);
		lv1 = (ListView) findViewById(R.id.lv1);
		btnPop = (Button) findViewById(R.id.btnPop);
		btnFragment.setOnClickListener(this);
		btnFragment.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				// Called when the user long-clicks on someView

				// TODO Auto-generated method stub
				// 判断下ActionMode是否为空，不为空则表示已经有该对象
				if (mActionMode != null)
					return false;
				/*
				 * 当你调用startActionMode()方法时，系统返回被创建的ActionMode对象。
				 * 通过把这个对象保存在一个成员变量中，你能够在对其他事件的响应中对上下文菜单进行改变。
				 */
				mActionMode = startActionMode(mActionModeCallback);
				// 高亮
				v.setSelected(true);
				return false;
			}
		});
		// 给按钮添加流动的上下文菜单
		registerForContextMenu(btnLoader);
		btnLoader.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

		// 在ListView或GridView对象中的批处理上下文操作
		lv1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		lv1.setMultiChoiceModeListener(new MultiChoiceModeListener() {

			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void onDestroyActionMode(ActionMode mode) {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				// TODO Auto-generated method stub
				mode.getMenuInflater().inflate(R.menu.main, menu);

				return true;
			}

			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				// TODO Auto-generated method stub
				Log.d(TAG, "MultiChoiceModeListener-->onActionItemClicked<--");
				if (item.getItemId() == R.id.file) {
					Log.d(TAG, "-->onItemClicked<--");
					mode.finish();
				}
				return false;
			}

			@Override
			public void onItemCheckedStateChanged(ActionMode mode,
					int position, long id, boolean checked) {
				// TODO Auto-generated method stub

			}
		});
		lv1.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, Cheeses.sCheeseStrings));

		// PopupMenu
		btnPop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PopupMenu popup = new PopupMenu(getApplicationContext(), v);
				popup.getMenuInflater().inflate(R.menu.main, popup.getMenu());
				popup.show();
				popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						// TODO Auto-generated method stub
						if (item.getItemId() == R.id.file) {
							Log.d(TAG, "popup#onMenuItemClick#");
							return true;
						}
						return false;
					}
				});
			}
		});

	}

	@Override
	public void onAttachedToWindow() {
		// TODO Auto-generated method stub
		//getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);

		super.onAttachedToWindow();
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		Log.d(TAG, "-->onCreateContextMenu<--");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		Log.d(TAG, "-->onCreateOptionsMenu<--");
		mShareActionProvider = (ShareActionProvider) menu.findItem(
				R.id.menu_share).getActionProvider();
		// MyActionProvider map = new MyActionProvider(this);

		map = (MyActionProvider) menu.findItem(R.id.menu_share2)
				.getActionProvider();
		// mShareActionProvider
		// .setShareHistoryFileName("custom_share_history.xml");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Log.d(TAG, "-->onOptionsItemSelected<--");
		if (item.getItemId() == R.id.file) {
			Log.d(TAG, "--> item is onClick <--");

		} else if (item.getItemId() == android.R.id.home) {
			Toast.makeText(this, "Home !", Toast.LENGTH_LONG).show();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == btnFragment.getId()) {
			// Intent i = new Intent(this, FragmentExampleActivity.class);
			// startActivity(i);

			// this.isChecked = !this.isChecked;
			// invalidateOptionsMenu();

		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Log.d(TAG, "-->onContextItemSelected<--");
		return super.onContextItemSelected(item);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		// if (this.isChecked) {
		// menu.removeItem(R.id.file);
		// } else {
		// menu.add("file");
		// }
		Log.d(TAG, "-->onPrepareOptionsMenu<--");
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * 针对个别View对象的上下文操作模式。<br>
	 * 1. 实现ActionMode.Callback接口。在它的回调方法中，你能够针对上下文操作栏指定动作，
	 * 在操作项目上响应点击事件和处理针对这个操作模式的其他生命周期事件。<br>
	 * 2. 在显示这个操作栏时，调用startActionMode()方法（如用户长按（long-click）对应的view对象时）。
	 */
	private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

		// 当调用ActionMode的invalidate方法时会调用
		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			// TODO Auto-generated method stub

			Log.d(TAG, "ActionMode.Callback-->onPrepareActionMode<--");
			return false;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
			// TODO Auto-generated method stub
			Log.d(TAG, "ActionMode.Callback-->onDestroyActionMode<--");
			mActionMode = null;

		}

		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			// TODO Auto-generated method stub
			Log.d(TAG, "ActionMode.Callback-->onCreateActionMode<--");
			mode.getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			// TODO Auto-generated method stub
			Log.d(TAG, "ActionMode.Callback-->onActionItemClicked<--");
			if (item.getItemId() == R.id.file) {
				Log.d(TAG, "ActionMode.Callback#ItemOnClick#");
				mode.finish();
				return true;
			}
			return false;
		}
	};

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		return false;
	}

}
