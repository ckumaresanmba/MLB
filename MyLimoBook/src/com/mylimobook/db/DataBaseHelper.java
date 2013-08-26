package com.mylimobook.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {
	private static String TAG = "DataBaseHelper"; // Tag just for the LogCat
													// window
	// private static String DB_PATH = "/data/data/com.dbassets/databases/";//
	// path
	// of

	private static String DB_PATH = "/data/data/com.mylimobook/databases/";// path
	public static final String FIELD_SuburbId = "SuburbId";
	public static final String FIELD_Postalcode = "Postalcode";
	public static final String FIELD_SuburbDesc = "SuburbDesc";
	public static final String FIELD_State = "State";
	public static final String FIELD_Lat = "Lat";
	public static final String FIELD_Long = "Long";
	public static final String FIELD_CountryCode = "CountryCode";

	public static final String TABLE_NAME = "Suburbs";

	// our
	// database
	private static String DB_NAME = "mylimo.db";// Database name
	private SQLiteDatabase mDataBase;
	private final Context mContext;

	public DataBaseHelper(Context context) {
		super(context, DB_NAME, null, 1);
		DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
		this.mContext = context;
	}

	public void createDataBase() throws IOException {
		// If database not exists copy it from the assets

		boolean mDataBaseExist = checkDataBase();
		if (!mDataBaseExist) {
			this.getReadableDatabase();
			this.close();
			try {
				// Copy the database from assests
				copyDataBase();
				Log.e(TAG, "createDatabase database created");
			} catch (IOException mIOException) {
				throw new Error("ErrorCopyingDataBase");
			}
		}
	}

	// Check that the database exists here: /data/data/your package/databases/Da
	// Name
	private boolean checkDataBase() {
		File dbFile = new File(DB_PATH + DB_NAME);
		// Log.v("dbFile", dbFile + "   "+ dbFile.exists());
		return dbFile.exists();
	}

	// Copy the database from assets
	private void copyDataBase() throws IOException {
		InputStream mInput = mContext.getAssets().open(DB_NAME);
		String outFileName = DB_PATH + DB_NAME;
		OutputStream mOutput = new FileOutputStream(outFileName);
		byte[] mBuffer = new byte[1024];
		int mLength;
		while ((mLength = mInput.read(mBuffer)) > 0) {
			mOutput.write(mBuffer, 0, mLength);
		}
		mOutput.flush();
		mOutput.close();
		mInput.close();
	}

	// Open the database, so we can query it
	public boolean openDataBase() throws SQLException {
		String mPath = DB_PATH + DB_NAME;
		// Log.v("mPath", mPath);
		mDataBase = SQLiteDatabase.openDatabase(mPath, null,
				SQLiteDatabase.CREATE_IF_NECESSARY);
		// mDataBase = SQLiteDatabase.openDatabase(mPath, null,
		// SQLiteDatabase.NO_LOCALIZED_COLLATORS);
		return mDataBase != null;
	}

	@Override
	public synchronized void close() {
		if (mDataBase != null)
			mDataBase.close();
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}

	public void deleteAllRows() {
		// TODO Auto-generated method stub
		System.out.println("DataBaseHelper.deleteAllRows()");
		try {

			String delete = "DELETE FROM  " + TABLE_NAME;

			mDataBase.rawQuery(delete, null);
			// db.execSQL(delete);

		} catch (SQLException mSQLException) {
			Log.e(TAG, "getOptions >>" + mSQLException.toString());
			mSQLException.printStackTrace();
			throw mSQLException;
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public void inserRow(int SuburbIdm, int Postalcode, String SuburbDesc,
			String State, String Lat, String Long, String Countrycode) {
		try {
			ContentValues cv = new ContentValues();
			cv.put(FIELD_SuburbId, SuburbIdm);
			cv.put(FIELD_Postalcode, Postalcode);
			cv.put(FIELD_SuburbDesc, SuburbDesc);
			cv.put(FIELD_State, State);
			cv.put(FIELD_Lat, Lat);
			cv.put(FIELD_Long, Long);
			cv.put(FIELD_CountryCode, Countrycode);

			/*
			 * ...... ..... put more code here for other columns ......
			 */
			System.out.println(SuburbIdm + "  SuburbDesc : " + SuburbDesc);
			mDataBase.insertOrThrow(TABLE_NAME, null, cv);
			System.out.println("done");
		} catch (SQLException mSQLException) {
			Log.e(TAG, "getOptions >>" + mSQLException.toString());
			throw mSQLException;
		}

	}

	public Cursor getAlldataFromDb1() {
		// TODO Auto-generated method stub
		System.out.println("DataBaseHelper.getQuoteofTheDayFromDB()");

		try {
			// String sql ="SELECT description FROM location";
			String sql = "SELECT * FROM " + TABLE_NAME;
			// String sql
			// ="SELECT insertdate FROM location ORDER BY insertdate DESC";

			Cursor mCur = mDataBase.rawQuery(sql, null);
			if (mCur != null) {
				mCur.moveToFirst();
			}
			return mCur;
		} catch (SQLException mSQLException) {
			Log.e(TAG, "getOptions >>" + mSQLException.toString());
			mSQLException.printStackTrace();
			throw mSQLException;
		}
	}

	public Cursor getAlldataFromDb(String state) {
		// TODO Auto-generated method stub
		
		System.out.println("DataBaseHelper.getQuoteofTheDayFromDB()");

		try {
			// String sql ="SELECT description FROM location";
		//	String sql = "SELECT * FROM " + TABLE_NAME;
			String sql =	"SELECT * FROM "+ TABLE_NAME+" where " + FIELD_State+"  = "+"'"+state+"'"+" group by "+FIELD_State+","+ FIELD_SuburbDesc;
			// String sql
			// ="SELECT insertdate FROM location ORDER BY insertdate DESC";

			Cursor mCur = mDataBase.rawQuery(sql, null);
			if (mCur != null) {
				mCur.moveToFirst();
			}
			return mCur;
		} catch (SQLException mSQLException) {
			Log.e(TAG, "getOptions >>" + mSQLException.toString());
			mSQLException.printStackTrace();
			throw mSQLException;
		}
	}
}
