package crusader.nikeshopping.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import crusader.nikeshopping.db.models.SaveItem;
import crusader.nikeshopping.db.models.UserRegistration;


public class DBHelper extends SQLiteOpenHelper {

   public static final String DATABASE_NAME = "Nike.db";
   public static final int DATABASE_VERSION = 1;


   public DBHelper(Context context)
   {
      super(context, DATABASE_NAME , null, DATABASE_VERSION);
   }

   @Override
   public void onCreate(SQLiteDatabase db) {
      //Create Registration Table
      UserRegistration userRegistration = new UserRegistration();
      userRegistration.createTable(db);
      SaveItem saveItem=new SaveItem();
      saveItem.createTable(db);
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS "+UserRegistration.REGISTRATION_TABLE_NAME);
      onCreate(db);
   }

   public SQLiteDatabase getDb(){
      return this.getWritableDatabase();
   }
}