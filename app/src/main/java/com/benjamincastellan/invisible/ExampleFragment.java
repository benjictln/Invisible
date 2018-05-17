package com.benjamincastellan.invisible;

import android.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * TODO: document your custom view class.
 */
public class ExampleFragment extends Fragment {

    public static ExampleFragment newInstance(String text,Boolean bool) {

        ExampleFragment f = new ExampleFragment();

        Bundle b = new Bundle();
        b.putString("text", text);
        b.putBoolean("good", bool);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.sample_element_test, container, false);

        ((TextView) v.findViewById(R.id.text_element)).setText(getArguments().getString("text"));
        if (getArguments().getBoolean("good") ) {
            ((ImageView) v.findViewById(R.id.image_element)).setImageDrawable(getResources().getDrawable(R.drawable.good));
        }else{
            ((ImageView) v.findViewById(R.id.image_element)).setImageDrawable(getResources().getDrawable(R.drawable.bad));
        }
        return v;
    }
}