package com.example.pill_aider;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pill_aider.Entity.Report;
import com.example.pill_aider.ViewModel.ReportViewModel;
import com.example.pill_aider.databinding.FragmentChartBinding;
import com.example.pill_aider.widget.ReportInfoMarkerView;
import com.example.pill_aider.widget.ReportPointMarkerView;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Chart_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Chart_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentChartBinding binding;
    private OnChartGestureListener onChartGestureListener;
    private ReportViewModel reportViewModel;

    private List<Report> list;//最后一百份报告

    private LineDataSet totalLine;//总数折线
    private LineDataSet tokenLine;//已服用折线
    private LineData lineData;//两线集合，表示一张表中的所有折线


    public Chart_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Chart_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Chart_Fragment newInstance(String param1, String param2) {
        Chart_Fragment fragment = new Chart_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChartBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        reportViewModel = new ViewModelProvider(this).get(ReportViewModel.class);
        //设置刷新按钮响应
        Button refreshButton = view.findViewById(R.id.button_fresh);
        refreshButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onButtonRefreshClicked();
            }
        });

        initListener();
        initiateChart();
    }

    /**
     * @description 响应刷新按钮click
     */
    private void onButtonRefreshClicked(){
        RefreshData();
        lineData.clearValues();
        lineData.addDataSet(totalLine);
        lineData.addDataSet(tokenLine);

        binding.linechart.setData(lineData);
        binding.linechart.invalidate();
    }

    /**
     * @description 初始化图表
     */
    private void initiateChart(){
        totalLine = new LineDataSet(new ArrayList<>(), getContext().getString(R.string.chart_line_label_total));
        tokenLine = new LineDataSet(new ArrayList<>(), getContext().getString(R.string.chart_line_label_token));
        lineData = new LineData();

        RefreshData();
        setTotalLineStyle();
        setTokenLineStyle();
        setXAxisStyle();
        setYAxisStyle();
        setChartStyle();

        binding.linechart.invalidate();
    }

    /**
     * @description 更新数据
     */
    private void RefreshData() {
        List<Report> originalList = reportViewModel.getAllReportsLive().getValue();
        List<Entry> total = new ArrayList<>();//总次数
        List<Entry> token = new ArrayList<>();//已服用次数

        list.clear();
        for (int i = Math.max(originalList.size() - 100, 0); i < originalList.size(); i++) {
            list.add(originalList.get(i));
        }
        originalList = null;

        for (int i = 0; i < list.size(); i++) {
            Report tmp = list.get(i);
            token.add(new Entry(i, tmp.getOk_num()));
            total.add(new Entry(i, tmp.getNo_num()+tmp.getOk_num()));
        }

        totalLine.setValues(total);
        tokenLine.setValues(token);
    }

    /**
     * @description 设置总数折线样式
     */
    private void setTotalLineStyle(){
        //设置折线下部阴影是否填充
        totalLine.setDrawFilled(true);
        //设置折现填充的颜色(透明度为0x85/0x255 在33左右)
        //详情原因可以查看{@link LineRadarDataSet#mFillAlpha 参数相关定义}
        totalLine.setFillColor(Color.parseColor("#c04851"));
        //设置折现的颜色
        totalLine.setColor(Color.parseColor("#c04851"));
        //折线的宽度
        totalLine.setLineWidth(2);
        //设置折线模式为圆滑的曲线
        totalLine.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        //是否绘制
        totalLine.setDrawValues(false);
        //是否绘制折线的点(下面会手动添加)
        totalLine.setDrawCircles(true);
        //是否绘制高亮效果
        totalLine.setDrawHighlightIndicators(false);
    }

    /**
     * @description 设置已服用折线样式
    */
    private void setTokenLineStyle() {

        tokenLine.setDrawFilled(true);
        tokenLine.setFillColor(Color.GRAY);
        //设置折现的颜色
        tokenLine.setColor(Color.RED);
        //折线的宽度
        tokenLine.setLineWidth(2);
        //设置折线模式为圆滑的曲线
        tokenLine.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        //是否绘制
        tokenLine.setDrawValues(false);
        //是否绘制折线的点(下面会手动添加)
        tokenLine.setDrawCircles(true);
        //是否绘制高亮效果
        tokenLine.setDrawHighlightIndicators(false);




        binding.linechart.invalidate(); // refresh
        //-------------图表通用设置-------------

    }

    /**
     * @description 设置x轴样式
     */
    private void setXAxisStyle(){
        XAxis xAxis = binding.linechart.getXAxis();
        //将x轴置于底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);

        //两侧设置留空效果, 具体数值可以根据实际调整
        xAxis.setAxisMinimum(-0.3f);
        xAxis.setAxisMaximum(list.size() - 0.7f);


        //x轴文件显示颜色
        xAxis.setTextColor(Color.WHITE);
        //关闭网络线
        xAxis.setDrawGridLines(false);
        //关闭延x轴线
        xAxis.setDrawAxisLine(false);

        //可选, 是否控制x轴间隔数量
        xAxis.setGranularityEnabled(true);
        xAxis.setGranularity(1);

        List<String> xAxisValues = new ArrayList<>();
        //自定义x轴显示样式
        for (Report r : list) {
            xAxisValues.add(r.getReport_date());
        }

//        for (Entry entry: entries){
//            xAxisValues.add(Math.round(entry.getX()) + "时");
//        }

        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
    }

    /**
     * @description 设置y轴样式
     */
    private void setYAxisStyle(){
        YAxis yAxis = binding.linechart.getAxisLeft();
        //控制y轴最大最小范围, 避免因为使用了曲线而导致的折线截断
        yAxis.setAxisMinimum(-100);
        yAxis.setAxisMaximum(2000);

        //关闭两侧y轴显示
        binding.linechart.getAxisRight().setEnabled(false);
        yAxis.setEnabled(true);
    }

    /**
     * @description 设置图表整体样式
     * 如缩放、边界、动画、滑动监听等
     */
    private void setChartStyle(){
        //设置折线图显示的数据
        lineData.clearValues();
        lineData.addDataSet(totalLine);
        lineData.addDataSet(tokenLine);
        binding.linechart.setData(lineData);
        //不可手动缩放
        binding.linechart.setDragDecelerationEnabled(true);

        Matrix matrix = new Matrix();
        //图标缩放显示, 使得一次最多显示6个数据
        matrix.postScale(list.size() / 6f, 1f);
        binding.linechart.getViewPortHandler().refresh(matrix, binding.linechart, false);

        //设置四周边界位移
        binding.linechart.setMinOffset(0);

        //设置空描述
        binding.linechart.setDescription(null);
        //关闭缩放
        binding.linechart.setScaleEnabled(false);


        //设置图例不显示
        Legend legend = binding.linechart.getLegend();
        legend.setTextColor(Color.BLACK);
        legend.setEnabled(true);

//        //创建覆盖物并选择中心位置数据高亮
        if (list.size() != 0) {
            createMakerView();
            //此操作需要晚于setData(), 否则会导致空指针异常
            binding.linechart.highlightValue(getChartMiddle(), 0);
            updateBottomInfo(tokenLine.getEntryForIndex(getChartMiddle()).getY());
        }

        //设置滑动监听
        binding.linechart.setOnChartGestureListener(onChartGestureListener);


        //监听选中
        binding.linechart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                //查看覆盖物是否被回收
                if (binding.linechart.isMarkerAllNull()) {
                    //重新绑定覆盖物
                    createMakerView();
                    //并且手动高亮覆盖物
                    binding.linechart.highlightValue(h);
                }
                updateBottomInfo(e.getY());
            }

            @Override
            public void onNothingSelected() {

            }
        });

        //设置出现动画
        binding.linechart.animateY(1500);
    }

    /**
     * @description 获取当前页面中心坐标
     */
    private int getChartMiddle(){
        ViewPortHandler handler = binding.linechart.getViewPortHandler();
        MPPointD topLeft = binding.linechart.getValuesByTouchPoint(handler.contentLeft(), handler.contentTop(), YAxis.AxisDependency.LEFT);
        MPPointD bottomRight = binding.linechart.getValuesByTouchPoint(handler.contentRight(), handler.contentBottom(), YAxis.AxisDependency.LEFT);

        return (int) ((bottomRight.x - topLeft.x) / 2 + topLeft.x);
    }

    /**
     * @description 设置滑动监听
     */
    private void initListener() {
        onChartGestureListener = new OnChartGestureListener() {
            @Override
            public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
            }

            @Override
            public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
            }

            @Override
            public void onChartLongPressed(MotionEvent me) {
            }

            @Override
            public void onChartDoubleTapped(MotionEvent me) {
            }

            @Override
            public void onChartSingleTapped(MotionEvent me) {
            }

            @Override
            public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
            }

            @Override
            public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
            }

            @Override
            public void onChartTranslate(MotionEvent me, float dX, float dY) {
                //高亮屏幕中心位置的数据
                binding.linechart.highlightValue(getChartMiddle(), 0);
            }
        };
    }

    /**
     * @description 创建图表高亮覆盖物
     */
    public void createMakerView() {
        ReportInfoMarkerView reportInfoMarkerView = new ReportInfoMarkerView(getContext(), "次");
        //避免覆盖物超出屏幕显示范围
        reportInfoMarkerView.setChartView(binding.linechart);
        binding.linechart.setReportInfoMarkerView(reportInfoMarkerView);
        binding.linechart.setReportPointMarkerView(new ReportPointMarkerView(getContext()));
    }

    /**
     * @description 更新底部显示
     */
    @SuppressLint("SetTextI18n")
    private void updateBottomInfo(float index){
        binding.textView.setText("" + index);
    }

}