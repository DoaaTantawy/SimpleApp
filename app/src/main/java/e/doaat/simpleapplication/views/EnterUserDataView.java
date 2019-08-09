package e.doaat.simpleapplication.views;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import e.doaat.simpleapplication.R;
import e.doaat.simpleapplication.presenters.EnterUserDataPresenter;


public class EnterUserDataView extends AppCompatActivity implements EnterUserDataPresenter.View {

    private EditText editName, editAge, editJobTitle;
    private RadioGroup radioGenderGroup;
    private RadioButton radioGenderButton;
    private FloatingActionButton addUser;
    private EnterUserDataPresenter userPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_user_data);

        userPresenter = new EnterUserDataPresenter((EnterUserDataPresenter.View) this);
        editName = findViewById(R.id.editTextName);
        editAge = findViewById(R.id.editTextAge);
        editJobTitle = findViewById(R.id.editTextJobTitle);
        addUser = findViewById(R.id.addUserBtn);
        radioGenderGroup = findViewById(R.id.radioGender);

        //inserting the data through the presenter
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendInputs();
            }
        });

    }

    public void sendInputs (){
        // first checking if any field is empty
        final String name = editName.getText().toString().trim();
        if(name.isEmpty()) {
            editName.setError("Name required");
            editName.requestFocus();
            return;
        }
        final String age = editAge.getText().toString().trim();
        if(age.isEmpty()) {
            editAge.setError("Age required");
            editAge.requestFocus();
            return;
        }
        final String jobTitle = editJobTitle.getText().toString().trim();
        if(jobTitle.isEmpty()) {
            editJobTitle.setError("Job Title required");
            editJobTitle.requestFocus();
            return;
        }

        int selectedId = radioGenderGroup.getCheckedRadioButtonId();
        radioGenderButton = (RadioButton) findViewById(selectedId);

        final String gender = radioGenderButton.getText().toString();

        // then sending the data to database
        userPresenter.insertUserInfo(name, age, gender, jobTitle, getApplicationContext());

    }

    @Override
    public void navigateToViewingData() {
        // updating UI by navigating to the second screen
        Intent i = new Intent(this, UserDataView.class);
        startActivity(i);
        // emptying the fields so when the user hit the back button doesn't find the
        // previous data.
        editName.setText("");
        editAge.setText("");
        editJobTitle.setText("");

    }
}
