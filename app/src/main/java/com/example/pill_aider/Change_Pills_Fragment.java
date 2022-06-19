package com.example.pill_aider;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        Button button_8,button_9; // 8放弃更改并退回到药单 9保存更改并返回药单
        button_8 = getView().findViewById(R.id.button8);
        button_9 = getView().findViewById(R.id.button9);
        button_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                //不做任何数据更新
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "带图片显示", Toast.LENGTH_LONG);//实例化toast对象
                LinearLayout toast_layout = (LinearLayout) toast.getView();
                ImageView imageView = new ImageView(getActivity().getApplicationContext());
                imageView.setBackgroundResource(R.drawable.beginer);
                AnimationDrawable background = (AnimationDrawable) imageView.getBackground();
                background.start();
                toast_layout.addView(imageView, 0);
                toast.show();
                controller.navigate(R.id.action_change_Pills_Fragment_to_pill_List_Fragment);
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