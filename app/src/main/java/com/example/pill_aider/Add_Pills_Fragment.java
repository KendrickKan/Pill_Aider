package com.example.pill_aider;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        //按钮事件逻辑
        Button button_5,button_4;
        button_5 = getView().findViewById(R.id.button5);
        button_4 = getView().findViewById(R.id.button4);
        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                //添加药品信息并进行数据绑定
                controller.navigate(R.id.action_add_Pills_Fragment_to_pill_List_Fragment);
            }
        });
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进行表单重置
                //test
                Toast toast = Toast.makeText(getActivity(), getContext().getString(R.string.toast_freset), Toast.LENGTH_LONG);//实例化toast对象
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