package infsi351.gustow.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class PlatDataSource {
	
	private SQLiteDatabase database;
	  private DataBaseOpener dbHelper;
	  private String[] allColumns = { DataBaseOpener.COLUMN_ID,
	      DataBaseOpener.COLUMN_NOM , DataBaseOpener.COLUMN_PRIX, DataBaseOpener.COLUMN_DESCRIPTION  };

	  public PlatDataSource(Context context) {
	    dbHelper = new DataBaseOpener(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public Plat createPlat(String plat) {
	    ContentValues values = new ContentValues();
	    values.put(DataBaseOpener.COLUMN_NOM, plat);
	    long insertId = database.insert(DataBaseOpener.TABLE_PLATS, null,
	        values);
	    Cursor cursor = database.query(DataBaseOpener.TABLE_PLATS,
	        allColumns, DataBaseOpener.COLUMN_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Plat newPlat = cursorToPlat(cursor);
	    cursor.close();
	    return newPlat;
	  }

	  public void deletePlat(Plat plat) {
	    long id = plat.getId();
	    System.out.println("Plat deleted with id: " + id);
	    database.delete(DataBaseOpener.TABLE_PLATS, DataBaseOpener.COLUMN_ID
	        + " = " + id, null);
	  }

	  public List<Plat> getAllPlats() {
	    List<Plat> plats = new ArrayList<Plat>();

	    Cursor cursor = database.query(DataBaseOpener.TABLE_PLATS,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Plat plat = cursorToPlat(cursor);
	      plats.add(plat);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return plats;
	  }

	  private Plat cursorToPlat(Cursor cursor) {
	    Plat plat = new Plat();
	    plat.setId(cursor.getLong(0));
//	    plat.setPlat(cursor.getString(1));
	    return plat;
	  }

}
