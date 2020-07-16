package com.example.waitingroom;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity{
    private EditText email;
    private EditText password;
    private Button login_button;
    private Button register_button;
    private TextInputLayout passwordTextInput;
    public static User logged_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this);

        ArrayList<Doctor> doctors = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Appointment> appointments = new ArrayList<>();

        Doctor doctor0 = new Doctor(1111,"Ostendorf", "Orthopäde","Herr","Georgstraße 32", "30160, Hannover");
        Doctor doctor1 = new Doctor(2222,"Schröder","Zahnärztin", "Frau",  "Marienstraße 72-90", "30171, Hannover");
        Doctor doctor2 = new Doctor(3333,"Raisser","Hautärztin", "Frau", "Brühlstraße 19", "30169, Hannover");
        Doctor doctor3 = new Doctor(4444,"Falke", "Unfallchirurgin", "Frau",   "Zietenstraße 2", "30163, Hannover");
        Doctor doctor4 = new Doctor(5555,"Mustermann","Gynäkologe", "Herr", "Büttnerstraße 15", "30165, Hannover");

        doctors.add(doctor0);
        doctors.add(doctor1);
        doctors.add(doctor2);
        doctors.add(doctor3);
        doctors.add(doctor4);

        for(int i = 0; i < doctors.size(); i++){
            if(!doctors.get(i).isInDatabase(databaseHelper.getAllDoctors())){
                databaseHelper.addDoctor(doctors.get(i));
            }
        }

        User user0 = new User("Nikita", "Povelkin", "17.05.1995", "Karl-Imhoff-Weg 17", "30165", "Hannover", "n@l.m", "1");
        User user1 = new User("Patrik", "Heller", "18.06.1996", "Karl-Imhoff-Weg 20", "30167", "Hannover", "patrik@gmail.com", "uno");
        User user2 = new User("Lena", "Meyer", "19.07.1997", "Karl-Imhoff-Weg 23", "30166", "Hannover", "lena@gmail.com", "medizin");
        User user3 = new User("Maik", "Hagen", "18.08.1998", "Karl-Imhoff-Weg 24", "30168", "Hannover", "maik@gmail.com", "eisladen");

        users.add(user0);
        users.add(user1);
        users.add(user2);
        users.add(user3);

        for(int i = 0; i < users.size(); i++){
            if(!users.get(i).isInDatabase(databaseHelper.getAllUsers())){
                databaseHelper.addUser(users.get(i));
            }
        }

        Appointment appointment0 = new Appointment("1234", user0.email, doctor1.id, "16.07.2020");
        Appointment appointment1 = new Appointment("2345", user0.email, doctor2.id, "13.08.2020");
        Appointment appointment2 = new Appointment("3456", user0.email, doctor3.id, "14.08.2020");
        Appointment appointment3 = new Appointment("4567", user1.email, doctor3.id, "15.08.2020");
        Appointment appointment4 = new Appointment("5678", user1.email, doctor2.id, "15.08.2020");
        Appointment appointment5 = new Appointment("6789", user2.email, doctor2.id, "17.08.2020");
        Appointment appointment6 = new Appointment("7890", user2.email, doctor0.id, "18.08.2020");
        Appointment appointment7 = new Appointment("8901", user3.email, doctor0.id, "19.08.2020");

        appointments.add(appointment0);
        appointments.add(appointment1);
        appointments.add(appointment2);
        appointments.add(appointment3);
        appointments.add(appointment4);
        appointments.add(appointment5);
        appointments.add(appointment6);
        appointments.add(appointment7);

        for(int i = 0; i < appointments.size(); i++){
            if(!appointments.get(i).isInDatabase(databaseHelper.getAllAppointments())){
                databaseHelper.addAppointment(appointments.get(i));
            }
        }

        email = findViewById(R.id.email_text);
        password = findViewById(R.id.password_edit_text);
        register_button = findViewById(R.id.register_button);
        login_button = findViewById(R.id.login_button);

        passwordTextInput = findViewById(R.id.password_text_input);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(email.getText().toString(), password.getText().toString(), databaseHelper);
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register_view_intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register_view_intent);
            }
        });
    }

    private void validate (String email, String password, DatabaseHelper databaseHelper) {
        logged_user = databaseHelper.getUser(email);
        if (email.equals(logged_user.email) && password.equals(logged_user.password)) {
            Intent main_view_intent = new Intent(LoginActivity.this, MainView.class);
            startActivity(main_view_intent);
        } else {
            passwordTextInput.setError(getString(R.string.error_password));
        }
    }
}
