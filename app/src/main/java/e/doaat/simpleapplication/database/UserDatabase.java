package e.doaat.simpleapplication.database;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import e.doaat.simpleapplication.models.User;

@Database(entities = {User.class}, version = 1)

public abstract class UserDatabase extends RoomDatabase{

    public abstract UserDao userDao();

}

