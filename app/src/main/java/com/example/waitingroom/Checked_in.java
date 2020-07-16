package com.example.waitingroom;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import static com.example.waitingroom.OverviewFragment.list;

public class Checked_in extends Fragment {
    public static Checked_in newInstance() {
        return new Checked_in();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checked_in, container, false);
        Button check_out_button = view.findViewById(R.id.check_out);

        check_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppointmentActivity) getActivity()).replaceFragments(R.id.check_in_container, new Checked_out());
            }
        });


        final int people_in_wainting_room = generatePeopleInQueue();
        final TextView waiting_text = view.findViewById(R.id.checked_in_text);

        new CountDownTimer(30000, 1000) {
            String message;
            public void onTick(long millisUntilFinished) {
                message = "Vor ihnen sind " + people_in_wainting_room +  " Menschen in der Warteschlange. Es verbleiben "
                        + millisUntilFinished / 1000 + " Minuten bis zu Ihrem Termin. (Mögliche Verspätung: " + 5 + " Minuten)";
                waiting_text.setText(message);
            }

            public void onFinish() {
                message = "Sie werden gleich aufgerufen!";
                waiting_text.setText(message);
            }
        }.start();

        return view;
    }

    public int generatePeopleInQueue(){
        Random random_gen = new Random();
        return random_gen.nextInt(10) + 1;
    }
}
