package sample.application.memopad;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorAdapter;
import android.content.Intent;

public class MemoList extends ListActivity {

	//�v�f3��String�^�z��
	public static final String[] cols = {"title","memo",android.provider.BaseColumns._ID,};
	
	//�C���X�^���X�ϐ�
	public MemoDBHelper memos;
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {	
		super.onListItemClick(l, v, position, id);
		
		//�C���X�^���X����(�I�����ꂽ�����C���X�^���X)
		this.memos = new MemoDBHelper(this);
		
		SQLiteDatabase db = this.memos.getWritableDatabase();
		Cursor cursor = db.query("memoDB", MemoList.cols, "_ID="+String.valueOf(id),null, null, null,null);
		this.startManagingCursor(cursor);
		Integer idx = cursor.getColumnIndex("memo");
		cursor.moveToFirst();
		
		//�C���X�^���X����
		Intent i = new Intent();
		
		i.putExtra("text", cursor.getString(idx));
		this.setResult(Activity.RESULT_OK, i);
		this.memos.close();
		this.finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//�C���X�^���X���\�b�h
		this.setContentView(R.layout.memolist);
		this.showMemos(getMemos());
		
		ListView lv = (ListView)this.findViewById(android.R.id.list);
		this.registerForContextMenu(lv);
	}
	
	private void showMemos(Cursor cursor) {
		if(cursor!=null){
	
			//String�^�z��
			String[] from = {"title"};
			
			//int�^�z��
			int[] to={android.R.id.text1};
			
			//�C���X�^���X����
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(
					this, android.R.layout.simple_list_item_1,
					cursor, from, to);
			
			this.setListAdapter(adapter);
		}
		this.memos.close();
	}

	private Cursor getMemos(){
		this.memos = new MemoDBHelper(this);
		SQLiteDatabase db = memos.getReadableDatabase();
		Cursor cursor = db.query("memoDB",MemoList.cols,null,null,null,null,null);
		this.startManagingCursor(cursor);
		return cursor;
	}

}
