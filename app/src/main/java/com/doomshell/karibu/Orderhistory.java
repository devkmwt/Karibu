package com.doomshell.karibu;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Orderhistory extends Fragment implements View.OnClickListener {


    View convertview;
    Context context;
    TextView order_selection;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context=getActivity().getApplicationContext();
        convertview= inflater.inflate(R.layout.order_layout, container, false);
        order_selection=(TextView)convertview.findViewById(R.id.order_selection);

        order_selection.setOnClickListener(this);
        return convertview;
    }

    @Override
    public void onClick(View v) {
        if(v==order_selection)
        {
            Ordersummery ordersummery=new Ordersummery();
            FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.home_container,ordersummery);
            transaction.addToBackStack(ordersummery.getClass().getName().toString());
            transaction.commit();
        }
    }
}
