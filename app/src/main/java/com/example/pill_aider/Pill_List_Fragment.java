package com.example.pill_aider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pill_aider.Entity.Reminder;
import com.example.pill_aider.ViewModel.ReminderViewModel;
import com.example.pill_aider.ViewModel.UserViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Pill_List_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pill_List_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // 用于表单
    private ReminderViewModel reminderViewModel;
    private UserViewModel userViewModel;
    private RecyclerView recyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Pill_List_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Pill_List_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Pill_List_Fragment newInstance(String param1, String param2) {
        Pill_List_Fragment fragment = new Pill_List_Fragment();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = getView().findViewById(R.id.recyclerview);
        View imagebutton= getView().findViewById(R.id.imageButton2);//需要设透明的控件的id
        View imagebutton2 =getView().findViewById(R.id.imageButton);
        View imagebutton3 =getView().findViewById(R.id.imageButton6);
        imagebutton2.getBackground().setAlpha(0);
        imagebutton3.getBackground().setAlpha(0);
        imagebutton.getBackground().setAlpha(0);       //0~255透明度值
        imagebutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_pill_List_Fragment_to_chart_Fragment);
            }
        });
        imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_pill_List_Fragment_to_add_Pills_Fragment);
            }
        });
        imagebutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_pill_List_Fragment_to_settings_Fragment);
            }
        });
    }

    private View view;//定义view用来设置fragment的layout
    public RecyclerView mCollectRecyclerView;//定义RecyclerView
    //定义以goodsentity实体类为对象的数据集合
    private ArrayList<Reminder> goodsEntityList = new ArrayList<Reminder>();
    //自定义recyclerveiw的适配器
    private CollectRecycleAdapter mCollectRecyclerAdapter;
    Reminder reminder = new Reminder("苯巴比妥",1,2,3,1,1,"lgtswez");
//    Reminder reminder1 = new Reminder("阿莫西",1,2,3,1,1,"lgtswez");
//    Reminder reminder2 = new Reminder("APTX4869",2,1,2,2,2,"wakd");
//    Reminder reminder3 = new Reminder("雾切之回光",5,6,1,3,3,"kdalh");
//    Reminder reminder4 = new Reminder("薙草之稻光",3,4,2,1,2,"lhalgt");
//    Reminder reminder5 = new Reminder("苍古自由之誓",3,7,3,1,3,"23333");
//    Reminder reminder6 = new Reminder("松籁响起之时",1,2,3,2,3,"hjdshajkf");
//    private TextView item_name, num_day, dasage_per_time, item_type, item_time, item_rem, notice;
//    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //获取fragment的layout
        view = inflater.inflate(R.layout.fragment_pill__list_, container, false);
        //对recycleview进行配置
        initRecyclerView();
        //模拟数据
        initData();
        return view;
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_pill__list_, container, false);
    }

    /**
     * TODO 模拟数据
     */
    private void initData() {
//        if(root == null){
//            root = inflater.inflate(R.layout.item_list,container,false);
//        }
//        item_name = root.findViewById(R.id.textView);
//        num_day = root.findViewById(R.id.textView37);
//        dasage_per_time = root.findViewById(R.id.textView39);
//        item_type = root.findViewById(R.id.textView40);
//        item_time = root.findViewById(R.id.textView35);
//        item_rem = root.findViewById(R.id.textView36);
//        notice = root.findViewById(R.id.textView41);
        reminderViewModel = new ViewModelProvider(this).get(ReminderViewModel.class);
//        reminderViewModel.insertReminder(reminder1, reminder2, reminder3, reminder4, reminder5, reminder6);
        List<Reminder> reminders = reminderViewModel.getAllReminders();
        for (int i=0;i<reminders.size();i++){
            Reminder goodsEntity=new Reminder("lgt",1,2,3,1,1,"son");
            goodsEntity.setItem_name(reminders.get(i).getItem_name());
            goodsEntity.setNum_day(reminders.get(i).getNum_day());
            goodsEntity.setDasage_per_time(reminders.get(i).getDasage_per_time());
            goodsEntity.setItem_type(reminders.get(i).getItem_type());
            goodsEntity.setItem_time(reminders.get(i).getItem_time());
            goodsEntity.setItem_rem(reminders.get(i).getItem_rem());
            goodsEntity.setNotice(reminders.get(i).getNotice());
            boolean flag=true;
            for (int j=0;j<goodsEntityList.size();j++){
                if(Objects.equals(goodsEntity.getItem_name(), goodsEntityList.get(j).getItem_name())){
                    flag=false;
                    break;
                }
            }
            if(flag){
                goodsEntityList.add(goodsEntity);
            }
//            goodsEntityList.add(goodsEntity);
        }
//        for(int i=0;i<reminders.size();i++) {
//            item_name.setText(reminder.getItem_name());
//            num_day.setText(String.valueOf(reminder.getNum_day()));
//            dasage_per_time.setText(String.valueOf(reminder.getDasage_per_time()));
//            if(reminder.getItem_type()==1){
//                item_type.setText("片");
//            }
//            else if(reminder.getItem_type()==2){
//                item_type.setText("粒");
//            }
//            else{
//                item_type.setText("毫升");
//            }
//            if(reminder.getItem_time()==1){
//                item_type.setText("饭前服用");
//            }
//            else if(reminder.getItem_time()==2){
//                item_type.setText("饭中服用");
//            }
//            else{
//                item_type.setText("饭后服用");
//            }
//            if(reminder.getItem_rem()==1){
//                item_type.setText("响铃");
//            }
//            else if(reminder.getItem_rem()==2){
//                item_type.setText("振动");
//            }
//            else{
//                item_type.setText("响铃并振动");
//            }
//            notice.setText(reminder.getNotice());
//        }

//        reminderViewModel.getAllRemindersLive().observe(getViewLifecycleOwner(),new Observer<List<Reminder>>() {
//            @Override
//            public void onChanged(List<Reminder> reminders) {
//                for(int i=0;i<reminders.size();i++) {
//                reminder = reminders.get(reminders.size()-1);
//
//                item_name.setText(reminder.getItem_name());
//                num_day.setText(String.valueOf(reminder.getNum_day()));
//                dasage_per_time.setText(String.valueOf(reminder.getDasage_per_time()));
//                if(reminder.getItem_type()==1){
//                    item_type.setText("片");
//                }
//                else if(reminder.getItem_type()==2){
//                    item_type.setText("粒");
//                }
//                else{
//                    item_type.setText("毫升");
//                }
//
//                if(reminder.getItem_time()==1){
//                    item_type.setText("饭前服用");
//                }
//                else if(reminder.getItem_time()==2){
//                    item_type.setText("饭中服用");
//                }
//                else{
//                    item_type.setText("饭后服用");
//                }
//
//                if(reminder.getItem_rem()==1){
//                    item_type.setText("响铃");
//                }
//                else if(reminder.getItem_rem()==2){
//                    item_type.setText("振动");
//                }
//                else{
//                    item_type.setText("响铃并振动");
//                }
//                notice.setText(reminder.getNotice());
//                }
//            }
//        });
    }

    /**
     * TODO 对recycleview进行配置
     */

    private void initRecyclerView() {
        //获取RecyclerView
        mCollectRecyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
        //创建adapter
        mCollectRecyclerAdapter = new CollectRecycleAdapter(getActivity(), goodsEntityList);
        //给RecyclerView设置adapter
        mCollectRecyclerView.setAdapter(mCollectRecyclerAdapter);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        mCollectRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //设置item的分割线
        mCollectRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        //RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义
        mCollectRecyclerAdapter.setOnItemClickListener(new CollectRecycleAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, Reminder data) {
                //此处进行监听事件的业务处理
                Toast.makeText(getActivity(),"我是item",Toast.LENGTH_SHORT).show();
            }
        });
    }
}