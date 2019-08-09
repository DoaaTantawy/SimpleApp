package e.doaat.simpleapplication.database;
import android.arch.persistence.room.Room;
import android.content.Context;

public class UserDatabaseClient {
    private Context mCtx;
    private static UserDatabaseClient mUserInstance;

    //our app database object
    private UserDatabase userDatabase;

    private UserDatabaseClient(Context mCtx) {
        this.mCtx = mCtx;
        userDatabase = Room.databaseBuilder(mCtx, UserDatabase.class, "AppUsers").build();
    }

    public static synchronized UserDatabaseClient getInstance(Context mCtx) {
        if (mUserInstance == null) {
            mUserInstance = new UserDatabaseClient(mCtx);
        }
        return mUserInstance;
    }

    public UserDatabase getAppDatabase() {
        return userDatabase;
    }
}
