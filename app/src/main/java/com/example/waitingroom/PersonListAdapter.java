package com.example.waitingroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PersonListAdapter extends ArrayAdapter<DoctorAppointmentsList> {
    private Context context;
    int resource;

    public PersonListAdapter(@NonNull Context context, int resource, @NonNull List<DoctorAppointmentsList> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String sex = getItem(position).sex;
        String name = getItem(position).name;
        String anrede = sex + " " + name;

        String type = getItem(position).doctor_type;
        String date = getItem(position).apointment_date;

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(this.resource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.doctor_name_lw);
        TextView tvType = (TextView) convertView.findViewById(R.id.doctor_type_lw);
        TextView tvDate = (TextView) convertView.findViewById(R.id.appointment_date);

        tvName.setText(anrede);
        tvType.setText(type);
        tvDate.setText(date);

        return convertView;
    }
}
