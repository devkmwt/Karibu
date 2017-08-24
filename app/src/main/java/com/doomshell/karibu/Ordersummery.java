package com.doomshell.karibu;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Ordersummery extends Fragment {

    TextView textView;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity().getApplicationContext();
        View convertview = inflater.inflate(R.layout.fragment_ordersummery, container, false);

        textView = (TextView) convertview.findViewById(R.id.total_amount);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, Orderhistory.class);
                startActivity(intent);

            }
        });
        return convertview;
    }

}
