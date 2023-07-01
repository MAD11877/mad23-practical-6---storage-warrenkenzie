package sg.edu.np.mad.madpractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {
    String title = "MyDBHandler";

    public static String DATABASE_NAME = "accountDB.db";
    public static int DATABASE_VERSION = 1;
    public static String ACCOUNTS = "accounts";
    public static String COLUMN_USERNAME = "Username";
    public static String COLUMN_DESCRIPTION = "Description";
    public static String COLUMN_ID = "id";
    public static String COLUMN_FOLLOWED = "Followed";

    public MyDBHandler( Context context, String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        Log.d(title,"DB constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE_COMMAND = "CREATE TABLE " +ACCOUNTS + "("+
                COLUMN_USERNAME +" TEXT," +
                COLUMN_DESCRIPTION +" TEXT," +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_FOLLOWED + " INTEGER DEFAULT 0)";
        Log.d(title,CREATE_TABLE_COMMAND);
        db.execSQL(CREATE_TABLE_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNTS);
        Log.d(title,"Drop and Create new DB");
        onCreate(db);
    }

    public void addUser(UserModelClass userData){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, userData.getName().toString());
        values.put(COLUMN_DESCRIPTION,userData.getDescription().toString());
        if(userData.Followed){
            values.put(COLUMN_FOLLOWED,1);
        }else {
            values.put(COLUMN_FOLLOWED,0);
        }

        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(title,"adding user" +values);
        db.insert(ACCOUNTS,null,values);
        db.close();
    }

    public ArrayList<UserModelClass> getUsers(){

        ArrayList<UserModelClass> userList = new ArrayList<>();
        String Query = "SELECT * FROM " + ACCOUNTS;
        Log.d(title,"find command with " +Query);
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor =db.rawQuery(Query,null);

        if(cursor.moveToFirst()){
            /*queryResult.setName(Integer.valueOf(cursor.getString(0)));
            queryResult.setDescription(Integer.valueOf(cursor.getString(1)));
            if (cursor.getInt(2) == 1) {
                queryResult.setFollowed(true);
            }
            else {
                queryResult.setFollowed(false);
            }
            cursor.close();*/
            do{
                UserModelClass user = new UserModelClass();
                user.setName(Integer.valueOf(cursor.getString(0)));
                user.setDescription(Integer.valueOf(cursor.getString(1)));
                if(cursor.getInt(3) == 1){
                    user.setFollowed(true);
                }else {
                    user.setFollowed(false);
                }
                userList.add(user);
            }while(cursor.moveToNext());

        }else {
            userList = null;
        }
        cursor.close();
        db.close();
        for (int i =0;i<userList.size();i++){
            Log.d("title",userList.get(i).toString());
        }
        return userList;
    }

    public void updateUser(String username, boolean followed){
        /*String Query = "SELECT * FROM " + ACCOUNTS + " WHERE " + COLUMN_USERNAME + "=\"" + username + "\"";
        Log.d(title,"find command with " +Query);
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor =db.rawQuery(Query,null);

        UserModelClass queryResult = new UserModelClass();

        if(cursor.moveToFirst()){
            queryResult.setName(Integer.valueOf(cursor.getString(0)));

        }*/

        SQLiteDatabase db = this.getWritableDatabase();

        // Prepare the new values to be updated
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOLLOWED, followed ? 1 : 0);

        // Define the WHERE clause to find the user by username
        String whereClause = COLUMN_USERNAME + " = ?";
        String[] whereArgs = {username};

        // Perform the update operation
        int rowsAffected = db.update(ACCOUNTS, values, whereClause, whereArgs);
        Log.d(title, "Rows affected: " + rowsAffected);

        // Close the database connection
        db.close();
    }

    public Boolean findUserFollowValue(String username){
        String Query = "SELECT * FROM " + ACCOUNTS + " WHERE " + COLUMN_USERNAME + "=\"" + username + "\"";
        Log.d(title,"find command with " +Query);
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor =db.rawQuery(Query,null);

        Integer queryResult;

        if(cursor.moveToFirst()){
            queryResult = cursor.getInt(3);
        }else {
            queryResult = null;
        }
        db.close();
        if(queryResult == 1){
            return true;
        }else {
            return false;
        }
    }
}
