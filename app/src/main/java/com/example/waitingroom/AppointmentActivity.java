package com.example.waitingroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.example.waitingroom.OverviewFragment.list;

public class AppointmentActivity extends AppCompatActivity {
    private RelativeLayout check_in_container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        getSupportFragmentManager().beginTransaction().replace(R.id.check_in_container, new Checked_out()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.map_container, new MapsFragment()).commit();

        Toolbar toolbar = findViewById(R.id.toolbar);
        String subtitle = list.sex + " " + list.name;
        toolbar.setSubtitle(subtitle);
        toolbar.setTitle(list.doctor_type);

        TextView text = findViewById(R.id.appointment_text);
        String message;
        if (list.sex.equals("Frau")){
            message = "Sie haben um 14:15 Uhr am " + list.apointment_date + " bei der "
                    + list.doctor_type+ " " + list.sex + " " + list.name + ", ein Termin in der "
                    + list.street + " " + list.postcode;
        } else {
            message = "Sie haben um 14:15 Uhr am " + list.apointment_date + " beim"
                    + list.doctor_type + "n " + list.sex + "n " + list.name + ",  " +
                    "ein Termin in der " + list.street + " " + list.postcode;
        }
        text.setText(message);

        check_in_container = findViewById(R.id.check_in_container);
    }

    public void replaceFragments(int id, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    public RelativeLayout getCheck_in_container() {
        return check_in_container;
    }
}
