package e.doaat.simpleapplication.presenters;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import e.doaat.simpleapplication.database.UserDatabaseClient;
import e.doaat.simpleapplication.models.User;

public class UserDataPresenter {

    private Context conx;
    private View view;
    private List<User> usersList;
    private User user;

    public UserDataPresenter(Context c, View view){
        this.conx=c;
        this.view=view;
    }

    public User getLastUser(){
        return user;
    }

    // Fetching the data from database
    public void getUsers() {

        class GetUsers extends AsyncTask<Void, Void, List<User>> {

            @Override
            protected List<User> doInBackground(Void... voids) {
                usersList = UserDatabaseClient
                        .getInstance(conx)
                        .getAppDatabase()
                        .userDao()
                        .getAll();
                return usersList;
            }

            @Override
            protected void onPostExecute(List<User> users) {
                super.onPostExecute(users);
                // getting the last element of the list which the user just inserted.
                user = usersList.get(usersList.size()-1);
                view.presentUserData();
            }
        }

        GetUsers gt = new GetUsers();
        gt.execute();

    }

    //interface for the View functionality
    public interface View{
        void presentUserData();
    }
}
