package com.example.waitingroom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    private Button save_button;
    private EditText surname;
    private EditText name;
    private EditText birthday;
    private EditText street;
    private EditText postcode;
    private EditText city;
    private EditText email;
    private EditText password;
    private EditText password_repeat;
    public static User user;

    private DatabaseHelper databaseHelper;
    private ArrayList arrayList;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this);

        surname = findViewById(R.id.surname_edit_text);
        name = findViewById(R.id.last_name_edit_text);
        birthday = findViewById(R.id.birthday_edit_text);
        street = findViewById(R.id.street_edit_text);
        postcode = findViewById(R.id.postcode_edit_text);
        city = findViewById(R.id.city_edit_text);
        email = findViewById(R.id.email_edit_text);
        password = findViewById(R.id.password_edit_text);
        password_repeat = findViewById(R.id.password_repeat_edit_text);

        save_button = findViewById(R.id.save_button);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new User(surname.getText().toString(), name.getText().toString(),
                                birthday.getText().toString(), street.getText().toString(),
                                postcode.getText().toString(),city.getText().toString(),
                                email.getText().toString(), password.getText().toString());

                String password_repeat_text = password_repeat.getText().toString();
                final boolean password_is_right = password_repeat_text.equals(password.getText().toString());

                if (password_is_right) {

                    databaseHelper.addUser(user);

                    surname.setText("");
                    name.setText("");
                    birthday.setText("");
                    street.setText("");
                    postcode.setText("");
                    city.setText("");
                    email.setText("");
                    password.setText("");
                    password_repeat.setText("");

                    Intent login_intent = new Intent (RegisterActivity.this, LoginActivity.class);
                    startActivity(login_intent);
                } else {
                    toastMessage("Sie müssen alle Felder füllen");
                }
            }
        });
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
