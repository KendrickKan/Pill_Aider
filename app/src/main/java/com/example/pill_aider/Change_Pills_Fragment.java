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
import android.util.Log;
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

import com.example.pill_aider.Entity.Reminder;
import com.example.pill_aider.ViewModel.ReminderViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Change_Pills_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Change_Pills_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Change_Pills_Fragment() {
        // Required empty public constructor
    }
    //判提交的reminder中是否有空
    public boolean if_empty (String name,int a,int b,int c,int d,int e,String notice){
        boolean res=false;
        if(a*b*c*d*e==0) res=true;
        if(name.isEmpty()) res=true;
//        if(notice.isEmpty()) res=false;
        return res;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Change_Pills_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Change_Pills_Fragment newInstance(String param1, String param2) {
        Change_Pills_Fragment fragment = new Change_Pills_Fragment();
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

    private View add_view;
    private EditText name, num_of_day, num_per_time, notice;
    private MultiAutoCompleteTextView item_type,item_time,item_rem;
    private boolean if_has_been_changed=false;
    //提交数据准备
    private String name_s="",notice_s="";
    private int num_of_day_i=0,num_per_time_i=0,item_type_i=0,item_time_i=0,item_rem_i=0;    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //viewmodel配置
        ReminderViewModel database_viewModel = new ViewModelProvider(this).get(ReminderViewModel.class);
        int id_for_change=getArguments().getInt("id_for_change");
        name_s=getArguments().getString("name1");
        String name_forever = name_s;
        notice_s=getArguments().getString("notice1");
        num_of_day_i=getArguments().getInt("num_of_day1");
        num_per_time_i=getArguments().getInt("num_per_time1");
        item_type_i=getArguments().getInt("item_type1");
        item_time_i=getArguments().getInt("item_time1");
        item_rem_i=getArguments().getInt("item_rem1");
        System.out.println(num_of_day_i);
        System.out.println(num_per_time_i);
        System.out.println(item_time_i);
        System.out.println(item_rem_i);
        //绑定输入文本框
        name = getView().findViewById(R.id.editTextTextPersonName3);
        num_of_day = getView().findViewById(R.id.editTextNumber3);
        num_per_time = getView().findViewById(R.id.editTextNumber4);
        notice = getView().findViewById(R.id.editTextTextPersonName4);
        String[] res_for_item_type={getString(R.string.pill_type_piece),getString(R.string.pill_type_grain),getString(R.string.pill_type_ml)};
        String[] res_for_item_rem={getString(R.string.remind_ring),getString(R.string.remind_vibrate),getString(R.string.remind_ring_and_vibrate)};
        String[] res_for_item_time={getString(R.string.eat_before_meal),getString(R.string.eat_with_meal),getString(R.string.eat_after_meal)};
        //自动补全
        ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,res_for_item_type);
        item_type = (MultiAutoCompleteTextView) getView().findViewById(R.id.multiAutoCompleteTextView4);
        item_type.setAdapter(arrayAdapter4);
        item_type.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        ArrayAdapter<String> arrayAdapter5 = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,res_for_item_time);
        item_time = (MultiAutoCompleteTextView) getView().findViewById(R.id.multiAutoCompleteTextView6);
        item_time.setAdapter(arrayAdapter5);
        item_time.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        ArrayAdapter<String> arrayAdapter6 = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,res_for_item_rem);
        item_rem = (MultiAutoCompleteTextView) getView().findViewById(R.id.multiAutoCompleteTextView7);
        item_rem.setAdapter(arrayAdapter6);
        item_rem.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        //设定初始值
        name.setText(name_s);
        notice.setText(notice_s);
        num_of_day.setText(String.valueOf(num_of_day_i));
        num_per_time.setText(String.valueOf(num_per_time_i));
        System.out.println(item_type_i);
        item_type.setText(res_for_item_type[item_type_i-1]);
        item_time.setText(res_for_item_time[item_time_i-1]);
        item_rem.setText(res_for_item_rem[item_rem_i-1]);
        //
        Button button_8,button_9; // 8放弃更改并退回到药单 9保存更改并返回药单
        button_8 = getView().findViewById(R.id.button8);
        button_9 = getView().findViewById(R.id.button9);
        //所有输入框更改时进行reminder前期配置
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if_has_been_changed=true;
                name_s=name.getText().toString();
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if_has_been_changed=true;
                name_s=name.getText().toString();
            }
            @Override
            public void afterTextChanged(Editable s) {
                if_has_been_changed=true;
                name_s=name.getText().toString();
            }
        });
        notice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if_has_been_changed=true;
                notice_s=notice.getText().toString();
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if_has_been_changed=true;
                notice_s=notice.getText().toString();
            }
            @Override
            public void afterTextChanged(Editable s) {
                if_has_been_changed=true;
                notice_s=notice.getText().toString();
            }
        });
        num_of_day.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if_has_been_changed=true;
                if(!num_of_day.getText().toString().isEmpty())
                    num_of_day_i=Integer.valueOf(num_of_day.getText().toString());
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if_has_been_changed=true;
                if(!num_of_day.getText().toString().isEmpty())
                    num_of_day_i=Integer.valueOf(num_of_day.getText().toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                if_has_been_changed=true;
                if(!num_of_day.getText().toString().isEmpty())
                    num_of_day_i=Integer.valueOf(num_of_day.getText().toString());
            }
        });
        num_per_time.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if_has_been_changed=true;
                if(!num_per_time.getText().toString().isEmpty())
                    num_per_time_i=Integer.valueOf(num_per_time.getText().toString());
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if_has_been_changed=true;
                if(!num_per_time.getText().toString().isEmpty())
                    num_per_time_i=Integer.valueOf(num_per_time.getText().toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                if_has_been_changed=true;
                if(!num_per_time.getText().toString().isEmpty())
                    num_per_time_i=Integer.valueOf(num_per_time.getText().toString());
            }
        });
        item_type.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if_has_been_changed=true;
                if(item_type.getText().toString().equals(getString(R.string.pill_type_piece)))item_type_i=1;
                if(item_type.getText().toString().equals(getString(R.string.pill_type_grain)))item_type_i=2;
                if(item_type.getText().toString().equals(getString(R.string.pill_type_ml)))item_type_i=3;
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if_has_been_changed=true;
                if(item_type.getText().toString().equals(getString(R.string.pill_type_piece)))item_type_i=1;
                if(item_type.getText().toString().equals(getString(R.string.pill_type_grain)))item_type_i=2;
                if(item_type.getText().toString().equals(getString(R.string.pill_type_ml)))item_type_i=3;
            }
            @Override
            public void afterTextChanged(Editable s) {
                if_has_been_changed=true;
                if(item_type.getText().toString().equals(getString(R.string.pill_type_piece)))item_type_i=1;
                if(item_type.getText().toString().equals(getString(R.string.pill_type_grain)))item_type_i=2;
                if(item_type.getText().toString().equals(getString(R.string.pill_type_ml)))item_type_i=3;
            }
        });
        item_time.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if_has_been_changed=true;
                if(item_time.getText().toString().equals(getString(R.string.eat_before_meal)))item_time_i=1;
                if(item_time.getText().toString().equals(getString(R.string.eat_with_meal)))item_time_i=2;
                if(item_time.getText().toString().equals(getString(R.string.eat_after_meal)))item_time_i=3;
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if_has_been_changed=true;
                if(item_time.getText().toString().equals(getString(R.string.eat_before_meal)))item_time_i=1;
                if(item_time.getText().toString().equals(getString(R.string.eat_with_meal)))item_time_i=2;
                if(item_time.getText().toString().equals(getString(R.string.eat_after_meal)))item_time_i=3;
            }
            @Override
            public void afterTextChanged(Editable s) {
                if_has_been_changed=true;
                if(item_time.getText().toString().equals(getString(R.string.eat_before_meal)))item_time_i=1;
                if(item_time.getText().toString().equals(getString(R.string.eat_with_meal)))item_time_i=2;
                if(item_time.getText().toString().equals(getString(R.string.eat_after_meal)))item_time_i=3;
            }
        });
        item_rem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if_has_been_changed=true;
                if(item_rem.getText().toString().equals(getString(R.string.remind_ring)))item_rem_i=1;
                if(item_rem.getText().toString().equals(getString(R.string.remind_vibrate)))item_rem_i=2;
                if(item_rem.getText().toString().equals(getString(R.string.remind_ring_and_vibrate)))item_rem_i=3;
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if_has_been_changed=true;
                if(item_rem.getText().toString().equals(getString(R.string.remind_ring)))item_rem_i=1;
                if(item_rem.getText().toString().equals(getString(R.string.remind_vibrate)))item_rem_i=2;
                if(item_rem.getText().toString().equals(getString(R.string.remind_ring_and_vibrate)))item_rem_i=3;
            }
            @Override
            public void afterTextChanged(Editable s) {
                if_has_been_changed=true;
                if(item_rem.getText().toString().equals(getString(R.string.remind_ring)))item_rem_i=1;
                if(item_rem.getText().toString().equals(getString(R.string.remind_vibrate)))item_rem_i=2;
                if(item_rem.getText().toString().equals(getString(R.string.remind_ring_and_vibrate)))item_rem_i=3;
            }
        });
        //放弃更改
        button_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                //不做任何数据更新
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), name_forever+getContext().getString(R.string.quit_change), Toast.LENGTH_SHORT);//实例化toast对象
//                LinearLayout toast_layout = (LinearLayout) toast.getView();
//                ImageView imageView = new ImageView(getActivity().getApplicationContext());
//                imageView.setBackgroundResource(R.drawable.beginer);
//                AnimationDrawable background = (AnimationDrawable) imageView.getBackground();
//                background.start();
//                toast_layout.addView(imageView, 0);
                toast.show();
                controller.navigate(R.id.action_change_Pills_Fragment_to_pill_List_Fragment);
            }
        });
        //加入更改
        button_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //为空/未修改都不能提交
                if(if_empty(name_s,num_of_day_i,num_per_time_i,item_type_i,item_time_i,item_rem_i,notice_s)||if_has_been_changed==false){
                    if(if_has_been_changed==false){  // 未修改提醒
                        Toast toast = Toast.makeText(getActivity(), getContext().getString(R.string.toast_have_not_changed), Toast.LENGTH_SHORT);//实例化toast对象
                        toast.show();
                    }
                    else{   // 为空提醒
                        Toast toast = Toast.makeText(getActivity(), getContext().getString(R.string.toast_input_fullfill), Toast.LENGTH_SHORT);//实例化toast对象
                        toast.show();
                    }
                }
                else{  //有效更改
                    System.out.println("正在更改");
                    Log.d("zhengzaigenggai1","huhu");
                    Reminder reminder_to_update=new Reminder(name_s,num_of_day_i,num_per_time_i,item_type_i,item_time_i,item_rem_i,notice_s);
                    reminder_to_update.setItem_id(id_for_change);
//                    Reminder reminder_to_update=database_viewModel.getReminderByID(id_for_change);
//                    reminder_to_update.setItem_name("大傻逼");
                    database_viewModel.updateReminder(reminder_to_update);
                    Toast toast = Toast.makeText(getActivity(),"\""+name_forever+"\""+getContext().getString(R.string.toast_change_succeed), Toast.LENGTH_SHORT);//实例化toast对象
                    toast.show();
                    NavController controller = Navigation.findNavController(v);
                    //添加药品信息并进行数据绑定
                    controller.navigate(R.id.action_change_Pills_Fragment_to_pill_List_Fragment);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change__pills_, container, false);
    }
}