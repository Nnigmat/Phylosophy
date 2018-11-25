package com.example.ngm.philosophy;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowAnswersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_answers3);

        Bundle b = this.getIntent().getExtras();
        ArrayList<String> data = b.getStringArrayList("data");
        ArrayList<Integer> indexes = b.getIntegerArrayList("indexes");
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        linearLayout.setBackgroundColor(Color.TRANSPARENT);
        for (Integer i : indexes) {
            FrameLayout fl1= new FrameLayout(this);
            fl1.setHorizontalFadingEdgeEnabled(Boolean.TRUE);
            fl1.setFadingEdgeLength(10);
            TextView txt = new TextView(this);
            txt.setText(data.get(i));
            // Color.argb(85, 51, 153, 102)
            fl1.addView(txt);
//            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
//            layoutParams.setMargins(0, 10, 0, 10);
            Space sp = new Space(this);
            sp.setMinimumHeight(30);
            sp.setBackgroundColor(0);
            linearLayout.addView(fl1);
            linearLayout.addView(sp);
            txt.setTextSize(20);
        }
    }
}
