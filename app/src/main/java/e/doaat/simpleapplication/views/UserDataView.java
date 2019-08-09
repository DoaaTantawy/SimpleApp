package e.doaat.simpleapplication.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import e.doaat.simpleapplication.R;
import e.doaat.simpleapplication.models.User;
import e.doaat.simpleapplication.presenters.UserDataPresenter;


public class UserDataView extends AppCompatActivity implements UserDataPresenter.View{

    private TextView viewName, viewAge, viewJobTitle, viewGender;
    private UserDataPresenter userDataPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_user_data);

        userDataPresenter = new UserDataPresenter(getApplicationContext(), (UserDataPresenter.View) this);
        viewName = findViewById(R.id.viewTextName);
        viewAge = findViewById(R.id.viewTextAge);
        viewGender = findViewById(R.id.viewTextGender);
        viewJobTitle = findViewById(R.id.viewTextJobTitle);

        // getting the data through the presenter
        userDataPresenter.getUsers();

    }

    @Override
    public void presentUserData(){
        // updating UI with the upcoming data
        User user = userDataPresenter.getLastUser();
        viewName.setText(user.getUserName());
        viewAge.setText(user.getUserAge());
        viewJobTitle.setText(user.getUserJobTitle());
        viewGender.setText(user.getUserGender());
    }
}


