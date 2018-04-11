package BDHELPER;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by JOSE LUIS on 4/04/2018.
 */

public class SQLite extends SQLiteOpenHelper{
    public String alumno="CREATE TABLE alumno(idalumno INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT,escuela TEXT,"+"codigo INTEGER)";
    public SQLite(Context context,String name,SQLiteDatabase.CursorFactory factory,int version)
    {
        super(context,name,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(alumno);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
