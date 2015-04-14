package infsi351.gustow.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseOpener extends SQLiteOpenHelper {

	public static final String TABLE_PLATS = "plats";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NOM = "nom";
	public static final String COLUMN_PRIX = "prix";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_IMAGE = "image";
	public static final String COLUMN_TYPE = "type";

	private static final String DATABASE_NAME = "commments.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_PLATS + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_NOM
			+ " text not null " 
			+ COLUMN_PRIX + " integer "
			+ COLUMN_DESCRIPTION + " text not null "
			+ COLUMN_TYPE + " text not null "
			+ COLUMN_IMAGE + " text not null "
			+");";

	public DataBaseOpener(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public DataBaseOpener(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 Log.w(DataBaseOpener.class.getName(),
			        "Upgrading database from version " + oldVersion + " to "
			            + newVersion + ", which will destroy all old data");
			    db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLATS);
			    onCreate(db);

	}

}
