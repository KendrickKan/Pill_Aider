package com.example.pill_aider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pill_aider.ViewModel.ReminderViewModel;
import com.example.pill_aider.ViewModel.UserViewModel;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pill__list_, container, false);
    }
}