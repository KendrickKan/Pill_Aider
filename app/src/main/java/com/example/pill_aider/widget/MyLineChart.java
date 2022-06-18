package com.example.pill_aider.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import com.example.pill_aider.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;

import java.lang.ref.WeakReference;

public class MyLineChart extends LineChart {
    //弱引用覆盖物对象,防止内存泄漏,不被回收
    private WeakReference<ReportInfoMarkerView> reportInfoMarkerViewWeakReference;
    private WeakReference<ReportPointMarkerView> reportPointMarkerViewWeakReference;

    public MyLineChart(Context context) {
        super(context);
    }

    public MyLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLineChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * @description 检查相关覆盖物是否为空
     */
    public boolean isMarkerAllNull() {
        return reportInfoMarkerViewWeakReference == null || reportInfoMarkerViewWeakReference.get() == null
                || reportPointMarkerViewWeakReference == null || reportPointMarkerViewWeakReference.get() == null;
    }

    public void setReportInfoMarkerView(ReportInfoMarkerView reportInfoMarkerView) {
        reportInfoMarkerViewWeakReference = new WeakReference<>(reportInfoMarkerView);
    }

    public void setReportPointMarkerView(ReportPointMarkerView roundMarker) {
        reportPointMarkerViewWeakReference = new WeakReference<>(roundMarker);
    }
    /**
     * @description
     * 复制父类的 drawMarkers方法，并且更换上自己的markerView
     * draws all MarkerViews on the highlighted positions
     */
    protected void drawMarkers(Canvas canvas) {
        if (reportInfoMarkerViewWeakReference == null || reportInfoMarkerViewWeakReference.get() == null
                || reportPointMarkerViewWeakReference == null || reportPointMarkerViewWeakReference.get() == null
        ){
            return;
        }
        ReportInfoMarkerView reportInfoMarkerView = reportInfoMarkerViewWeakReference.get();
        ReportPointMarkerView reportPointMarkerView = reportPointMarkerViewWeakReference.get();


        // if there is no marker view or drawing marker is disabled
        if (!isDrawMarkersEnabled() || !valuesToHighlight())
            return;

        for (int i = 0; i < mIndicesToHighlight.length; i++) {

            Highlight highlight = mIndicesToHighlight[i];

            IDataSet set = mData.getDataSetByIndex(highlight.getDataSetIndex());

            Entry e = mData.getEntryForHighlight(mIndicesToHighlight[i]);

            int entryIndex = set.getEntryIndex(e);

            // make sure entry not null
            if (e == null || entryIndex > set.getEntryCount() * mAnimator.getPhaseX())
                continue;

            float[] pos = getMarkerPosition(highlight);

            // check bounds
            if (!mViewPortHandler.isInBounds(pos[0], pos[1])) {
                continue;
            }

            //在对应点上添加选择信息覆盖物
            reportInfoMarkerView.refreshContent(e, highlight);
            reportInfoMarkerView.draw(canvas, pos[0], pos[1] - reportInfoMarkerView.getHeight() / 2);

            //在对挺点位置条件选择点覆盖物
            reportPointMarkerView.refreshContent(e, highlight);
            reportPointMarkerView.draw(canvas, pos[0], pos[1] + reportPointMarkerView.getHeight() / 2);

            //判断是否可以绘制虚线
            if (this.getHeight() - 50 > pos[1] + reportPointMarkerView.getHeight() / 2) {

                //通过canvas绘制虚线
                Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                mPaint.setColor(this.getContext().getColor(R.color.color_orange));
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setStrokeWidth(3);
                mPaint.setPathEffect(new DashPathEffect(new float[]{15, 10}, 0));

                Path mPath = new Path();
                mPath.reset();
                mPath.moveTo(pos[0], pos[1] + reportPointMarkerView.getHeight() / 2);
                mPath.lineTo(pos[0], this.getHeight() - 50);
                canvas.drawPath(mPath, mPaint);
            }
        }
    }
}