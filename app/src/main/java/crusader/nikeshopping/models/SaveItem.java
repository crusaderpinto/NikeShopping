package crusader.nikeshopping.models;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import crusader.retrofittest.db.ColumnValuePair;
import crusader.retrofittest.db.DBHelper;

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
        return new String[0];
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
        return null;
    }

    @Override
    public ArrayList<SaveItem> getFilteredData(SQLiteDatabase db, ColumnValuePair... pair) {
        return null;
    }

    @Override
    public int numberOfRows(SQLiteDatabase db) {
        return 0;
    }

    @Override
    public boolean updateData(SQLiteDatabase db, SaveItem tableModel) {
        return false;
    }

    @Override
    public Integer deleteData(SQLiteDatabase db, SaveItem tableModel) {
        return null;
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
