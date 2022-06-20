package com.example.pill_aider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.pill_aider.Entity.PillAiderFunction;
import com.example.pill_aider.Entity.User;
import com.example.pill_aider.ViewModel.UserViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Settings_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Settings_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Settings_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Settings_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Settings_Fragment newInstance(String param1, String param2) {
        Settings_Fragment fragment = new Settings_Fragment();
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

//        EditText bhour, bmin, lhour, lmin, dhour, dmin, rtimes, rinter;

    }

    private View root;
    private EditText bhour, bmin, lhour, lmin, dhour, dmin, rtimes, rinter;
//    UserDao users;
    User user = new User("0:0","0:0","0:0",0,0);
    List<User> users;
    UserViewModel userViewModel;
    Button saveButton;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button_l3 ,button_l4;
        button_l3 =getView().findViewById(R.id.button3);
        button_l4 =getView().findViewById(R.id.button4);
        button_l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_settings_Fragment_to_developer_Fragment);
            }
        });
        button_l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_settings_Fragment_to_protocolFragment);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(root == null){
            root = inflater.inflate(R.layout.fragment_settings_,container,false);
        }
        bhour = root.findViewById(R.id.bhour);
        bmin = root.findViewById(R.id.bmin);
        lhour = root.findViewById(R.id.lhour);
        lmin = root.findViewById(R.id.lmin);
        dhour = root.findViewById(R.id.dhour);
        dmin = root.findViewById(R.id.dmin);
        rtimes = root.findViewById(R.id.dhour2);
        rinter = root.findViewById(R.id.dhour4);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        users = userViewModel.getAllUsers();
        if (users.size() >= 1) {
            for(int i=0;i<users.size();i++) {
                user = users.get(users.size() - 1);
                bhour.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getBre_time()).get(0)));
                bmin.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getBre_time()).get(1)));
                lhour.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getLun_time()).get(0)));
                lmin.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getLun_time()).get(1)));
                dhour.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getDin_time()).get(0)));
                dmin.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getDin_time()).get(1)));
                rtimes.setText(String.valueOf(user.getRem_num()));
                rinter.setText(String.valueOf(user.getInterval()));
            }
        }
        else{
            user = new User("0:0","0:0","0:0",0,0);
            bhour.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getBre_time()).get(0)));
            bmin.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getBre_time()).get(1)));
            lhour.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getLun_time()).get(0)));
            lmin.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getLun_time()).get(1)));
            dhour.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getDin_time()).get(0)));
            dmin.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getDin_time()).get(1)));
            rtimes.setText(String.valueOf(user.getRem_num()));
            rinter.setText(String.valueOf(user.getInterval()));
            userViewModel.insertUser(user);
        }
//        for(int i=0;i<users.size();i++) {
//            if (((users.size() - 1)) >= 1) {
//                user = users.get(users.size() - 1);
//                bhour.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getBre_time()).get(0)));
//                bmin.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getBre_time()).get(1)));
//                lhour.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getLun_time()).get(0)));
//                lmin.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getLun_time()).get(1)));
//                dhour.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getDin_time()).get(0)));
//                dmin.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getDin_time()).get(1)));
//                rtimes.setText(String.valueOf(user.getRem_num()));
//                rinter.setText(String.valueOf(user.getInterval()));
//            }
//            else{
//                user = new User("0:0","0:0","0:0",0,0);
//                bhour.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getBre_time()).get(0)));
//                bmin.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getBre_time()).get(1)));
//                lhour.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getLun_time()).get(0)));
//                lmin.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getLun_time()).get(1)));
//                dhour.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getDin_time()).get(0)));
//                dmin.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getDin_time()).get(1)));
//                rtimes.setText(String.valueOf(user.getRem_num()));
//                rinter.setText(String.valueOf(user.getInterval()));
//                userViewModel.insertUser(user);
//            }
//        }
//        userViewModel.insertUser(user);
//        userViewModel.getAllUsersLive().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
//            @Override
//            public void onChanged(List<User> users) {
////                for(int i=0;i<users.size();i++) {
//                if((users.get(users.size()-1))!=null){
//                    user = users.get(users.size()-1);
//                    bhour.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getBre_time()).get(0)));
//                    bmin.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getBre_time()).get(1)));
//                    lhour.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getLun_time()).get(0)));
//                    lmin.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getLun_time()).get(1)));
//                    dhour.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getDin_time()).get(0)));
//                    dmin.setText(String.valueOf(PillAiderFunction.stringToTwoTime(user.getDin_time()).get(1)));
//                    rtimes.setText(String.valueOf(user.getRem_num()));
//                    rinter.setText(String.valueOf(user.getInterval()));
//                }
////                }
////                userViewModel.updateUser(user);
//            }
//        });

        saveButton = root.findViewById(R.id.set_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setBre_time(PillAiderFunction.twoTimeToString(Integer.parseInt(bhour.getText().toString()),Integer.parseInt(bmin.getText().toString())));
                user.setLun_time(PillAiderFunction.twoTimeToString(Integer.parseInt(lhour.getText().toString()),Integer.parseInt(lmin.getText().toString())));
                user.setDin_time(PillAiderFunction.twoTimeToString(Integer.parseInt(dhour.getText().toString()),Integer.parseInt(dmin.getText().toString())));
                user.setRem_num(Integer.parseInt(rtimes.getText().toString()));
                user.setInterval(Integer.parseInt(rinter.getText().toString()));
                userViewModel.updateUser(user);
            }
        });

//        Log.d("KD", String.valueOf(user.getId()));
        return root;

//        UserViewModel userviewmodel;
//        userviewmodel = new ViewModelProvider(this).get(UserViewModel.class);
//        FragmentSettingsBinding binding;
//        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_settings_,container,false);
//        binding.setData(userviewmodel);
//        binding.setLifecycleOwner(this);

//        return binding.getRoot();
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_settings_, container, false);
    }
}