package com.benjamincastellan.invisible;

import android.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * TODO: document your custom view class.
 */
public class ExampleFragment extends Fragment {

    public static ExampleFragment newInstance(String text) {

        ExampleFragment f = new ExampleFragment();

        Bundle b = new Bundle();
        b.putString("text", text);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.sample_element_test, container, false);

        ((TextView) v.findViewById(R.id.test)).setText(getArguments().getString("text"));
        return v;
    }
}