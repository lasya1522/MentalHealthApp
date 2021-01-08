import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//Class where all data will be stored(database) don't know what I'm doing anymore-
import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {


    public Database(@Nullable Context context) {
        super(context, "Save trial", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatements = "";

        db.execSQL(createTableStatements);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
