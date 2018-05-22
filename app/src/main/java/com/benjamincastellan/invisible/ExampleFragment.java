package com.benjamincastellan.invisible;

import android.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * TODO: document your custom view class.
 */
public class ExampleFragment extends Fragment {

    private boolean good = false;
    private boolean unknown = false;
    private ListView detailListView;
    private ArrayList<String> detailArray = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    public static ExampleFragment newInstance(String text,Boolean bool) {

        ExampleFragment f = new ExampleFragment();

        Bundle b = new Bundle();
        b.putString("text", text);
        b.putBoolean("good", bool);
        f.setArguments(b);

        return f;
    }


    public void addDetails(String details) {
        // TODO: 18/05/2018 add details to the view
        detailArray.add(details);
    }

    public void setGood(boolean good) {
        this.good = good;
    }

    public void setUnknown() {this.unknown = true;}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.sample_element_test, container, false);
        // set the name of the test
        ((TextView) v.findViewById(R.id.text_element)).setText(getArguments().getString("text"));

        // set the image according to if the test was passed or failed or unknown
        if (unknown) {
            ((ImageView) v.findViewById(R.id.image_element)).setImageDrawable(getResources().getDrawable(R.drawable.interrogation_mark));
        }else if (good) {
            ((ImageView) v.findViewById(R.id.image_element)).setImageDrawable(getResources().getDrawable(R.drawable.good));
        }else{
            ((ImageView) v.findViewById(R.id.image_element)).setImageDrawable(getResources().getDrawable(R.drawable.bad));
        }

        // add the details of how the test happened
        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.detail_layout, detailArray);
        detailListView = v.findViewById(R.id.detail_list_view);
        detailListView.setAdapter(adapter);
        justifyListViewHeightBasedOnChildren(detailListView);
        return v;
    }

    // make sure the list view is big enough, and not scrollable
    public static void justifyListViewHeightBasedOnChildren (ListView listView) {

        ListAdapter adapter = listView.getAdapter();

        if (adapter == null) {
            return;
        }
        ViewGroup vg = listView;
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, vg);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(par);
        listView.requestLayout();
    }
}