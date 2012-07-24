package sample.application.memopad;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MemoDBHelper extends SQLiteOpenHelper {

	//class�ϐ�
	public static String name = "memos.db";
	public static CursorFactory factory = null;
	public static Integer version = 1;
	
	//�R���X�g���N�^(�߂�l���Ȃ��A�N���X���ƃ��\�b�h��������)
	//�R���X�g���N�^�����s�����Ƃ��ɁA�C���X�^���X�����������
	//�R���X�g���N�^�̌Ăяo������ MemoDBHelper memo = new MemoDBHelper();
	public MemoDBHelper(Context context, String name, CursorFactory factory,
			Integer version) {
		//super �͐e�N���X�̂��Ƃ��w���B
		super(context, name, factory, version);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}

	public MemoDBHelper(Context context) {
		super(context, name, factory, version);
	}
	
	@Override
	//Context cont = new Content();
	//MemoDBHelper memo = new MemoDBHelper(cont);
	//SQLiteDatabase sq = new SQLiteDatabase;
	//memo.Create(sq);
	public void onCreate(SQLiteDatabase db) {
		//local�ϐ�
		String sql = "CREATE TABLE memoDB ("
				+ android.provider.BaseColumns._ID
				+" INTEGER PRIMARY KEY AUTOINCREMENT, title Text, memo TEXT);";
				
				//SQLiteDatabase�N���X�̃C���X�^���X���\�b�h
				//SQLite(�f�[�^�x�[�X)��"memoDB"�̃e�[�u�����쐬����SQL�������s����B
				db.execSQL(sql);
	}

	@Override
	//�C���X�^���X���\�b�h
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}
	
}
