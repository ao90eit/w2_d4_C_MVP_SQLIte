package com.aoinc.w2_d4_c_mvp_sqlite.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.aoinc.w2_d4_c_mvp_sqlite.R;
import com.aoinc.w2_d4_c_mvp_sqlite.model.Shoe;

import java.util.ArrayList;
import java.util.List;

public class ShoeDatabaseHelper extends SQLiteOpenHelper {

    public static int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "shoe_db";

    public static final String SHOE_TABLE_NAME = "shoe_table";
    public static final String COLUMN_SHOE_ID = "shoe_id";
    public static final String COLUMN_SHOE_MODEL = "shoe_model";
    public static final String COLUMN_SHOE_SIZE = "shoe_size";
    public static final String COLUMN_SHOE_PRICE = "shoe_price";

    private Resources resources;

    public ShoeDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        resources = context.getResources();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + SHOE_TABLE_NAME + " ("
                + COLUMN_SHOE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_SHOE_MODEL + " TEXT, "
                + COLUMN_SHOE_SIZE + " INTEGER, "
                + COLUMN_SHOE_PRICE + " TEXT)";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String update = "DROP TABLE IF EXISTS " + SHOE_TABLE_NAME;
        db.execSQL(update);
        DATABASE_VERSION = newVersion;
    }

    public List<Shoe> getAllShoesFromDatabase() {
        Cursor shoeCursor = getReadableDatabase().rawQuery("SELECT * FROM " + SHOE_TABLE_NAME,
                null, null);
        List<Shoe> shoes = new ArrayList<>();
        shoeCursor.move(-1);

        while (shoeCursor.moveToNext()) {
            int shoeID = shoeCursor.getInt(shoeCursor.getColumnIndex(COLUMN_SHOE_ID));
            String shoeModel = shoeCursor.getString(shoeCursor.getColumnIndex(COLUMN_SHOE_MODEL));
            int shoeSize = shoeCursor.getInt(shoeCursor.getColumnIndex(COLUMN_SHOE_SIZE));
            String shoePrice = shoeCursor.getString(shoeCursor.getColumnIndex(COLUMN_SHOE_PRICE));

            Shoe shoe = new Shoe(shoeID, shoeModel, shoeSize, Double.parseDouble(shoePrice));
            shoes.add(shoe);
        }

        return shoes;
    }
    
    public void insertNewShoeIntoDatabase(Shoe shoe) {
        ContentValues shoeValue = new ContentValues();

        shoeValue.put(COLUMN_SHOE_MODEL, shoe.getShoeModel());
        shoeValue.put(COLUMN_SHOE_SIZE, Double.toString(shoe.getShoeSize()));
        shoeValue.put(COLUMN_SHOE_PRICE, shoe.getShoePrice());

        getWritableDatabase().insert(SHOE_TABLE_NAME, null, shoeValue);
    }

    public void deleteShoeFromDatabase(Shoe shoe) {
        String deleteSql = resources.getString(R.string.delete_command, SHOE_TABLE_NAME, COLUMN_SHOE_ID , shoe.getShoeID());
        getWritableDatabase().execSQL(deleteSql);
    }
}
