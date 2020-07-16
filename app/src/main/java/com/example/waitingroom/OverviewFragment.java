package com.example.waitingroom;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import static com.example.waitingroom.LoginActivity.logged_user;

public class OverviewFragment extends Fragment {
    public static DoctorAppointmentsList list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_overview, container, false);
        final ListView appointments = view.findViewById(R.id.appointments);
        final CardView card1 = view.findViewById(R.id.card1);
        final CardView card2 = view.findViewById(R.id.card2);
        final CardView card3 = view.findViewById(R.id.card3);

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(getContext());

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.apotheken-umschau.de/Augen/Blaues-Licht-Umstrittenes-Leuchten-556263.html");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.hogrefe.de/themen/medizin-gesundheitswesen/zirkadianer-rhythmus");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.gesundleben-apotheken.de/gesundheit/infektionskrankheiten/corona-blues-mit-achtsamkeit-der-krise-trotzen.828.html");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


        final ArrayList<DoctorAppointmentsList> appointmetsList = databaseHelper.getDoctorsOf(logged_user);

        final PersonListAdapter adapter = new PersonListAdapter(view.getContext(), R.layout.adapter_view_layout, appointmetsList);
        appointments.setAdapter(adapter);
        setListViewHeightBasedOnChildren(appointments);

        appointments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                list = (DoctorAppointmentsList) parent.getItemAtPosition(position);
                Intent appointment_intent = new Intent(getActivity(), AppointmentActivity.class);
                startActivity(appointment_intent);
            }
        });

        return view;
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        PersonListAdapter listAdapter = (PersonListAdapter) listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}


