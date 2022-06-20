package com.example.pill_aider;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.example.pill_aider.Alarm.AlarmBuilder;
import com.example.pill_aider.Entity.Reminder;
import com.example.pill_aider.Entity.User;
import com.example.pill_aider.ViewModel.ReminderViewModel;
import com.example.pill_aider.ViewModel.UserViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Add_Pills_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Add_Pills_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Add_Pills_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Add_Pills_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Add_Pills_Fragment newInstance(String param1, String param2) {
        Add_Pills_Fragment fragment = new Add_Pills_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    //判提交的reminder中是否有空
    public boolean if_empty (String name,int a,int b,int c,int d,int e,String notice){
        boolean res=false;
        if(a*b*c*d*e==0) res=true;
        if(name.isEmpty()) res=true;
//        if(notice.isEmpty()) res=false;
        return res;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private View add_view;
    private EditText name, num_of_day, num_per_time, notice;
    private MultiAutoCompleteTextView item_type,item_time,item_rem;
    //提交数据准备
    private String name_s="",notice_s="";
    private int num_of_day_i=0,num_per_time_i=0,item_type_i=0,item_time_i=0,item_rem_i=0;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //viewmodel配置
        ReminderViewModel database_viewModel = new ViewModelProvider(this).get(ReminderViewModel.class);
        //绑定输入文本框
        name = getView().findViewById(R.id.editTextTextPersonName);
        num_of_day = getView().findViewById(R.id.editTextNumber);
        num_per_time = getView().findViewById(R.id.editTextNumber2);
        notice = getView().findViewById(R.id.editTextTextPersonName2);
        String[] res_for_item_type={getString(R.string.pill_type_piece),getString(R.string.pill_type_grain),getString(R.string.pill_type_ml)};
        String[] res_for_item_rem={getString(R.string.remind_ring),getString(R.string.remind_vibrate),getString(R.string.remind_ring_and_vibrate)};
        String[] res_for_item_time={getString(R.string.eat_before_meal),getString(R.string.eat_with_meal),getString(R.string.eat_after_meal)};
        //自动补全 -同样中英文适配
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,res_for_item_type);
        item_type = (MultiAutoCompleteTextView) getView().findViewById(R.id.multiAutoCompleteTextView2);
        item_type.setAdapter(arrayAdapter);
        item_type.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,res_for_item_time);
        item_time = (MultiAutoCompleteTextView) getView().findViewById(R.id.multiAutoCompleteTextView3);
        item_time.setAdapter(arrayAdapter2);
        item_time.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,res_for_item_rem);
        item_rem = (MultiAutoCompleteTextView) getView().findViewById(R.id.multiAutoCompleteTextView);
        item_rem.setAdapter(arrayAdapter3);
        item_rem.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        //所有输入框更改时进行reminder前期配置
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                name_s=name.getText().toString();
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                name_s=name.getText().toString();
            }
            @Override
            public void afterTextChanged(Editable s) {
                name_s=name.getText().toString();
            }
        });
        notice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                notice_s=notice.getText().toString();
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                notice_s=notice.getText().toString();
            }
            @Override
            public void afterTextChanged(Editable s) {
                notice_s=notice.getText().toString();
            }
        });
        num_of_day.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(!num_of_day.getText().toString().isEmpty())
                num_of_day_i=Integer.valueOf(num_of_day.getText().toString());
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!num_of_day.getText().toString().isEmpty())
                num_of_day_i=Integer.valueOf(num_of_day.getText().toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(!num_of_day.getText().toString().isEmpty())
                num_of_day_i=Integer.valueOf(num_of_day.getText().toString());
            }
        });
        num_per_time.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(!num_per_time.getText().toString().isEmpty())
                num_per_time_i=Integer.valueOf(num_per_time.getText().toString());
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!num_per_time.getText().toString().isEmpty())
                num_per_time_i=Integer.valueOf(num_per_time.getText().toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(!num_per_time.getText().toString().isEmpty())
                num_per_time_i=Integer.valueOf(num_per_time.getText().toString());
            }
        });
        item_type.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(item_type.getText().toString().equals(getString(R.string.pill_type_piece)))item_type_i=1;
                if(item_type.getText().toString().equals(getString(R.string.pill_type_grain)))item_type_i=2;
                if(item_type.getText().toString().equals(getString(R.string.pill_type_ml)))item_type_i=3;
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(item_type.getText().toString().equals(getString(R.string.pill_type_piece)))item_type_i=1;
                if(item_type.getText().toString().equals(getString(R.string.pill_type_grain)))item_type_i=2;
                if(item_type.getText().toString().equals(getString(R.string.pill_type_ml)))item_type_i=3;
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(item_type.getText().toString().equals(getString(R.string.pill_type_piece)))item_type_i=1;
                if(item_type.getText().toString().equals(getString(R.string.pill_type_grain)))item_type_i=2;
                if(item_type.getText().toString().equals(getString(R.string.pill_type_ml)))item_type_i=3;
            }
        });
        item_time.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(item_time.getText().toString().equals(getString(R.string.eat_before_meal)))item_time_i=1;
                if(item_time.getText().toString().equals(getString(R.string.eat_with_meal)))item_time_i=2;
                if(item_time.getText().toString().equals(getString(R.string.eat_after_meal)))item_time_i=3;
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(item_time.getText().toString().equals(getString(R.string.eat_before_meal)))item_time_i=1;
                if(item_time.getText().toString().equals(getString(R.string.eat_with_meal)))item_time_i=2;
                if(item_time.getText().toString().equals(getString(R.string.eat_after_meal)))item_time_i=3;
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(item_time.getText().toString().equals(getString(R.string.eat_before_meal)))item_time_i=1;
                if(item_time.getText().toString().equals(getString(R.string.eat_with_meal)))item_time_i=2;
                if(item_time.getText().toString().equals(getString(R.string.eat_after_meal)))item_time_i=3;
            }
        });
        item_rem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(item_rem.getText().toString().equals(getString(R.string.remind_ring)))item_rem_i=1;
                if(item_rem.getText().toString().equals(getString(R.string.remind_vibrate)))item_rem_i=2;
                if(item_rem.getText().toString().equals(getString(R.string.remind_ring_and_vibrate)))item_rem_i=3;
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(item_rem.getText().toString().equals(getString(R.string.remind_ring)))item_rem_i=1;
                if(item_rem.getText().toString().equals(getString(R.string.remind_vibrate)))item_rem_i=2;
                if(item_rem.getText().toString().equals(getString(R.string.remind_ring_and_vibrate)))item_rem_i=3;
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(item_rem.getText().toString().equals(getString(R.string.remind_ring)))item_rem_i=1;
                if(item_rem.getText().toString().equals(getString(R.string.remind_vibrate)))item_rem_i=2;
                if(item_rem.getText().toString().equals(getString(R.string.remind_ring_and_vibrate)))item_rem_i=3;
            }
        });
        //按钮事件逻辑
        Button button_5,button_4;
        button_5 = getView().findViewById(R.id.button5);
        button_4 = getView().findViewById(R.id.button4);
        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 如果没有输入完整，弹出表项不完整提示且不做跳转
                if(if_empty(name_s,num_of_day_i,num_per_time_i,item_type_i,item_time_i,item_rem_i,notice_s)){
                    Toast toast = Toast.makeText(getActivity(), getContext().getString(R.string.toast_input_fullfill), Toast.LENGTH_SHORT);//实例化toast对象
                    toast.show();
                }
                //插入成功
                else{
                    Reminder reminder_toinput=new Reminder(name_s,num_of_day_i,num_per_time_i,item_type_i,item_time_i,item_rem_i,notice_s);
                    database_viewModel.insertReminder(reminder_toinput);
                    Toast toast = Toast.makeText(getActivity(),"\""+name_s+"\""+getContext().getString(R.string.toast_insert_succeed), Toast.LENGTH_SHORT);//实例化toast对象
                    toast.show();

                    //根据新加list添加notification（假设user唯一）
                    UserViewModel userViewModel = new ViewModelProvider(getActivity()).get(UserViewModel.class);
                    User user = userViewModel.getAllUsers().get(0);
                    AlarmBuilder alarm = new AlarmBuilder(user);
                    List<Reminder> res = database_viewModel.getAllReminders();

                    reminder_toinput = res.get(res.size()-1);
                    alarm.createAlarm(getContext() ,reminder_toinput);
                    NavController controller = Navigation.findNavController(v);
                    //添加药品信息并进行数据绑定
                    controller.navigate(R.id.action_add_Pills_Fragment_to_pill_List_Fragment);
                }
            }
        });
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进行表单重置
                name.setText("");
                num_of_day.setText("");
                num_per_time.setText("");
                notice.setText("");
                item_type.setText("");
                item_time.setText("");
                item_rem.setText("");
                Toast toast = Toast.makeText(getActivity(), getContext().getString(R.string.toast_freset), Toast.LENGTH_SHORT);//实例化toast对象
                //位置设定
//                toast.setGravity(Gravity.CENTER, -20, 0);
//                LinearLayout toast_layout = (LinearLayout) toast.getView();
//                ImageView imageView = new ImageView(getActivity());
//                imageView.setBackgroundResource(R.drawable.pill_list_add_button);
//                AnimationDrawable background = (AnimationDrawable) imageView.getBackground();
//                background.start();
//                toast_layout.addView(imageView, 0);
                toast.show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add__pills_, container, false);
    }
}