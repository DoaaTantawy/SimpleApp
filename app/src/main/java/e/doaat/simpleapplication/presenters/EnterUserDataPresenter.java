package e.doaat.simpleapplication.presenters;

import android.content.Context;
import android.os.AsyncTask;

import e.doaat.simpleapplication.database.UserDatabaseClient;
import e.doaat.simpleapplication.models.User;

public class EnterUserDataPresenter {

    private User user;
    private View view;


    public EnterUserDataPresenter(View view) {
        this.user = new User();
        this.view = view;
    }

    public void insertUserInfo(final String uName, final String uAge,
                               final String uGender, final String uJobTitle, final Context conx){

        class SaveUsers extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                user.setUserName(uName);
                user.setUserAge(uAge);
                user.setUserJobTitle(uJobTitle);
                user.setUserGender(uGender);

                //adding to database
                UserDatabaseClient.getInstance(conx).getAppDatabase()
                        .userDao()
                        .insert(user);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
               // Toast.makeText(conx, "Saved", Toast.LENGTH_LONG).show();
                view.navigateToViewingData();
            }
        }

        SaveUsers st = new SaveUsers();
        st.execute();

    }

    //interface for the View functionality
    public interface View{
        void navigateToViewingData();
    }
}
