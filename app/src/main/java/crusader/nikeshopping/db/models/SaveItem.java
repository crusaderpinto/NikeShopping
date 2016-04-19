package crusader.nikeshopping.db.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import crusader.nikeshopping.db.ColumnValuePair;

/**
 * Created by SeemaI on 3/31/2016.
 */
public class SaveItem extends BaseTable<SaveItem> {

    public static final String SELECTED_ITEMS_TABLE_NAME = "SelectedItems";
    public static final String SELECTED_ITEMS_COLUMN_ID = "id";
    public static final String SELECTED_ITEMS_COLUMN_USER_NAME = "username";
    public static final String SELECTED_ITEMS_COLUMN_ITEM_ID = "item_id";
    public static final String SELECTED_ITEMS_COLUMN_QTY = "quantity";
    public static final String SELECTED_ITEMS_COLUMN_PRICE = "price";
    public static final String SELECTED_ITEMS_COLUMN_IMG = "img_url";

    private String userName;
    private String itemId;
    private int quntity;
    private double price;
    private String imgUrl;

    public SaveItem() {
    }

    public SaveItem(String userName, String itemId, int quntity, double price, String imgUrl) {
        this.userName = userName;
        this.itemId = itemId;
        this.quntity = quntity;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    @Override
    public void createTable(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS " + SELECTED_ITEMS_TABLE_NAME + "(" + SELECTED_ITEMS_COLUMN_ID + " integer primary key AUTOINCREMENT," + SELECTED_ITEMS_COLUMN_USER_NAME + " text," + SELECTED_ITEMS_COLUMN_ITEM_ID
                + " text," + SELECTED_ITEMS_COLUMN_QTY + " integer," + SELECTED_ITEMS_COLUMN_PRICE + " numeric," + SELECTED_ITEMS_COLUMN_IMG + " text)");


        //db.execSQL("CREATE TABLE IF NOT EXISTS ");
    }

    @Override
    public String getTableName() {
        return SELECTED_ITEMS_TABLE_NAME;
    }

    @Override
    public String[] getColumnNames() {
        return new String[]{SELECTED_ITEMS_COLUMN_ID, SELECTED_ITEMS_COLUMN_USER_NAME, SELECTED_ITEMS_COLUMN_ITEM_ID, SELECTED_ITEMS_COLUMN_QTY, SELECTED_ITEMS_COLUMN_PRICE, SELECTED_ITEMS_COLUMN_IMG};
    }

    @Override
    public boolean insertInDb(SQLiteDatabase db, SaveItem tableModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SELECTED_ITEMS_COLUMN_IMG, getImgUrl());
        contentValues.put(SELECTED_ITEMS_COLUMN_ITEM_ID, getItemId());
        contentValues.put(SELECTED_ITEMS_COLUMN_PRICE, getPrice());
        contentValues.put(SELECTED_ITEMS_COLUMN_QTY, getQuntity());
        contentValues.put(SELECTED_ITEMS_COLUMN_USER_NAME, getUserName());
        long count = db.insert(getTableName(), null, contentValues);
        return count > 0;

    }

    @Override
    public ArrayList<SaveItem> getAllData(SQLiteDatabase db) {
        ArrayList<SaveItem> array_list = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " + getTableName(), null);
        try {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                SaveItem saveItem = new SaveItem();
                saveItem.setUserName(cursor.getString(cursor.getColumnIndex(SELECTED_ITEMS_COLUMN_USER_NAME)));
                saveItem.setItemId(cursor.getString(cursor.getColumnIndex(SELECTED_ITEMS_COLUMN_ITEM_ID)));
                saveItem.setQuntity(cursor.getInt(cursor.getColumnIndex(SELECTED_ITEMS_COLUMN_QTY)));
                saveItem.setPrice(cursor.getInt(cursor.getColumnIndex(SELECTED_ITEMS_COLUMN_PRICE)));
                saveItem.setImgUrl(cursor.getString(cursor.getColumnIndex(SELECTED_ITEMS_COLUMN_IMG)));
                array_list.add(saveItem);
                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return array_list;
    }

    @Override
    public ArrayList<SaveItem> getFilteredData(SQLiteDatabase db, ColumnValuePair... pair) {
        ArrayList<SaveItem> array_list = new ArrayList<>();

        String queryString = "select * from " + getTableName();

        if (pair != null) {
            StringBuilder filteredQuery = new StringBuilder();
            if (pair.length > 0) {
                filteredQuery.append(" WHERE ");
            }
            for (int i = 0; i < pair.length; i++) {
                filteredQuery.append(pair[i].getColumnName()).append(" = ").append("'").append(pair[i].getColumnValue()).append("'");
                if (i != pair.length - 1) {
                    filteredQuery.append(" AND ");
                }
            }
            if (filteredQuery.length() > 0) {
                queryString = queryString + filteredQuery.toString();
            }
        }

        Cursor cursor = db.rawQuery(queryString, null);
        try {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                SaveItem saveItem = new SaveItem();
                saveItem.setUserName(cursor.getString(cursor.getColumnIndex(SELECTED_ITEMS_COLUMN_USER_NAME)));
                saveItem.setItemId(cursor.getString(cursor.getColumnIndex(SELECTED_ITEMS_COLUMN_ITEM_ID)));
                saveItem.setQuntity(cursor.getInt(cursor.getColumnIndex(SELECTED_ITEMS_COLUMN_QTY)));
                saveItem.setPrice(cursor.getInt(cursor.getColumnIndex(SELECTED_ITEMS_COLUMN_PRICE)));
                saveItem.setImgUrl(cursor.getString(cursor.getColumnIndex(SELECTED_ITEMS_COLUMN_IMG)));
                array_list.add(saveItem);
                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return array_list;
    }

    @Override
    public int numberOfRows(SQLiteDatabase db) {
        int numRows = (int) DatabaseUtils.queryNumEntries(db, SELECTED_ITEMS_TABLE_NAME);
        return numRows;
    }

    @Override
    public boolean updateData(SQLiteDatabase db, SaveItem tableModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SELECTED_ITEMS_COLUMN_IMG, getImgUrl());
        contentValues.put(SELECTED_ITEMS_COLUMN_ITEM_ID, getItemId());
        contentValues.put(SELECTED_ITEMS_COLUMN_PRICE, getPrice());
        contentValues.put(SELECTED_ITEMS_COLUMN_QTY, getQuntity());
        contentValues.put(SELECTED_ITEMS_COLUMN_USER_NAME, getUserName());
        db.update(getTableName(), contentValues, SELECTED_ITEMS_COLUMN_ITEM_ID + " = ? ", new String[] { tableModel.getItemId() } );
        return true;
    }

    @Override
    public Integer deleteData(SQLiteDatabase db, SaveItem tableModel) {
        return db.delete(getTableName(),
                SELECTED_ITEMS_COLUMN_USER_NAME + " = ? ",
                new String[] { tableModel.getUserName() });
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuntity() {
        return quntity;
    }

    public void setQuntity(int quntity) {
        this.quntity = quntity;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
