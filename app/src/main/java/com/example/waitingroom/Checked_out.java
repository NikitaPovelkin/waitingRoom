package com.example.waitingroom;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.waitingroom.OverviewFragment.list;

public class Checked_out extends Fragment {

    public static Checked_out newInstance() {
        return new Checked_out();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checked_out, container, false);

        Button check_in_button = view.findViewById(R.id.check_in);
        check_in_button.setEnabled(false);

        Date date = java.util.Calendar.getInstance().getTime();
        String date_str = new SimpleDateFormat("dd.MM.yyyy").format(date);

        if (list.apointment_date.equals(date_str)) {
            check_in_button.setEnabled(true);
        }

        check_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppointmentActivity) getActivity()).replaceFragments(R.id.check_in_container, new Checked_in());
            }
        });
        return view;
    }

    private void toastMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
