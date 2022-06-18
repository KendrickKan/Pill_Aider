package com.example.pill_aider.widget;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.pill_aider.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

public class ReportInfoMarkerView extends MarkerView {


    private final TextView tvContent;
    private final String unit;

    public ReportInfoMarkerView(Context context, String unit) {
        super(context, R.layout.weight_custom_marker_view);
        tvContent = (TextView) findViewById(R.id.tvContent);
        this.unit = unit;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tvContent.setText("" + e.getY() + (TextUtils.isEmpty(unit)? "" : (" " + unit)));
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}