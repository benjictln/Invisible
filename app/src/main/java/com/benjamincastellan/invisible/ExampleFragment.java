package com.benjamincastellan.invisible;

import android.app.Fragment;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * TODO: document your custom view class.
 */
public class ExampleFragment extends Fragment {

    private boolean good = true;
    private boolean unknown = false;
    private ListView detailListView;
    private ArrayList<String> detailArray = new ArrayList<String>();
    private ArrayList<Integer> isPositiveComment = new ArrayList<Integer>(); // 0:positive, 1: negative, 2:neutral
    ArrayAdapter<String> adapter;
    private FrameLayout frameLayoutFragment;

    public static ExampleFragment newInstance(String text,Boolean bool) {

        ExampleFragment f = new ExampleFragment();

        Bundle b = new Bundle();
        b.putString("text", text);
        b.putBoolean("good", bool);
        f.setArguments(b);

        return f;
    }

    public void addDetails(String details) {
        detailArray.add(details);
        this.isPositiveComment.add(2);
    }

    public void addDetails(String details, Boolean isPositiveComment) {
        detailArray.add(details);
        this.isPositiveComment.add((isPositiveComment)?0:1);
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
        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.detail_layout, detailArray){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                switch (isPositiveComment.get(position)){
                    case 0:
                        textView.setTextColor(Color.parseColor("#006400"));
                        break;
                    case 1:
                        textView.setTextColor(Color.parseColor("#B20000"));
                        break;
                    case 2:
                        textView.setTextColor(Color.GRAY);
                }
                return textView;
            }
        };
        detailListView = v.findViewById(R.id.detail_list_view);
        detailListView.setAdapter(adapter);
        frameLayoutFragment = (FrameLayout) v.findViewById(R.id.frameLayoutFragment);
        frameLayoutFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailListView.setVisibility((detailListView.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
            }
        });
        justifyListViewHeightBasedOnChildren(detailListView);

        return v;
    }


    // make sure the list view is big enough, and not scrollable
    public void justifyListViewHeightBasedOnChildren (ListView listView) {

        ListAdapter adapter = listView.getAdapter();

        if (adapter == null) {
            return;
        }
        ViewGroup vg = listView;
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, vg);
            listItem.measure(0, 0);
            Log.d("exampleFragment", "justifyListViewHeightBasedOnChildren: " + String.valueOf(listItem.getMeasuredHeight()));
            totalHeight += (i == adapter.getCount()?2:1) * listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = (this.detailArray.size()) * convertDpToPixel(44) + convertDpToPixel(20); // 44 because  2 lines (textsize 12 dp + padding up 5 dp + padding down 5 dp)  20 because padding 10 dp (up and down)
        listView.setLayoutParams(par);
        listView.requestLayout();

    }

    public static int convertDpToPixel(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

}
