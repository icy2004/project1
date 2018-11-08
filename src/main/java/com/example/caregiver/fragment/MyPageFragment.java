package com.example.caregiver.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.caregiver.App;
import com.example.caregiver.R;
import com.example.caregiver.SharedUtil;
import com.example.caregiver.model.ZipCode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyPageFragment extends Fragment {
    private static final int STATUS_FINISH = 1;
    private static final int STATUS_READAY = 2;
    private static final int STATUS_PROCESS= 3;

    private Spinner sp_1;
    private Spinner sp_2;
    private Spinner sp_3;
    private Button btn_1;
    private Button btn_2;
    private RadioGroup gender_group;
    private RadioButton check_m;
    private RadioButton check_f;
    private List<ZipCode> zipCodeArray;

    public static Fragment newInstance() {
        return new MyPageFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(getContext(), R.layout.fragment_mypage, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        zipCodeArray = new ArrayList<>();

        final App app = (App) getActivity().getApplication();

        sp_1 = getView().findViewById(R.id.sp_1);
        sp_2 = getView().findViewById(R.id.sp_2);
        sp_3 = getView().findViewById(R.id.sp_3);
        btn_1 = getView().findViewById(R.id.btn_1);
        btn_2 = getView().findViewById(R.id.btn_2);
        gender_group = getView().findViewById(R.id.gender_group);
        check_m = getView().findViewById(R.id.check_m);
        check_f = getView().findViewById(R.id.check_f);

        String[] sidoArr = new String[((App)getActivity().getApplication()).getSidoArray().size()];
        ((App)getActivity().getApplication()).getSidoArray().toArray(sidoArr);

        String[] diseases = getResources().getStringArray(R.array.arr_4);

        sp_1.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.arr_4)));
        sp_2.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, sidoArr));


        if(TextUtils.equals(app.getGender(), "남")) {
            check_m.setChecked(true);
        } else {
            check_f.setChecked(true);
        }

        for(int i = 0; i < diseases.length; i++) {
            if(TextUtils.equals(diseases[i], app.getDisease())) {
                sp_1.setSelection(i);
                break;
            }
        }

        String sido = app.getSido();
        for(int i = 0; i < sidoArr.length; i++) {
            if(TextUtils.equals(sidoArr[i], app.getSido())) {
                sp_2.setSelection(i);
                sido = sidoArr[i];
                break;
            }
        }

        List<ZipCode> zipCodes = ((App)getActivity().getApplication()).getZipCodeArray();
        zipCodeArray.clear();
        int index = 0;
        int sel = 0;
        for(int i = 0; i < zipCodes.size(); i++) {
            ZipCode zipCode = zipCodes.get(i);
            if(TextUtils.equals(zipCode.getSido(), sido)) {
                zipCodeArray.add(zipCode);
                if(TextUtils.equals(zipCode.getGugun(), app.getGugun())) {
                    sel = index;
                }
                index++;
            }
        }
        sp_3.setAdapter(new ArrayAdapter<ZipCode>(getContext(), android.R.layout.simple_dropdown_item_1line, zipCodeArray));
        sp_3.setSelection(sel);

        sp_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                app.setDisease(adapterView.getSelectedItem().toString());
                SharedUtil.put(getActivity(), getString(R.string.app_name), "disease", (String)adapterView.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String sido = (String) adapterView.getSelectedItem();
                app.setSido(sido);
                SharedUtil.put(getActivity(), getString(R.string.app_name), "sido", sido);
                List<ZipCode> zipCodes = ((App)getActivity().getApplication()).getZipCodeArray();
                zipCodeArray.clear();
                for(ZipCode zipCode : zipCodes) {
                    if(TextUtils.equals(zipCode.getSido(), sido)) {
                        zipCodeArray.add(zipCode);
                    }
                }
                if(zipCodeArray.size() > 0) {
                    ((App) getActivity().getApplication()).setGugun(zipCodeArray.get(0).getGugun());
                }
                ((ArrayAdapter)sp_3.getAdapter()).notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String gugun = ((ZipCode) adapterView.getSelectedItem()).getGugun();
                app.setGugun(gugun);
                SharedUtil.put(getActivity(), getString(R.string.app_name), "gugun", gugun);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        gender_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                SharedUtil.put(getActivity(), getString(R.string.app_name), "gender", i == R.id.check_m ? "남" : "여");
            }
        });

        buttonRefresh();
    }

    public void buttonRefresh() {
        Button[] btn = {btn_1, btn_2};
        for(int i = 0; i < btn.length; i++) {
            int rnd = new Random().nextInt(3) + 1;
            if(rnd == STATUS_FINISH) {
                long startDate = System.currentTimeMillis();
                long endDate = startDate + (3600 * 24 * 1000);
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                btn[i].setText("후기를 작성해 주세요.\n(" + format.format(startDate) + " ~ " + format.format(endDate) + ")");
                btn[i].setBackgroundColor(getResources().getColor(R.color.holo_orange_dark));
            } else if(rnd == STATUS_PROCESS) {
                btn[i].setText("서비스 이용중");
                btn[i].setBackgroundResource(R.drawable.bg_small_button_gray);
                btn[i].setBackgroundColor(getResources().getColor(R.color.material_deep_teal_500));
            } else {
                btn[i].setText("신청대기");
                btn[i].setBackgroundResource(R.drawable.bg_small_button_gray);
                btn[i].setBackgroundColor(getResources().getColor(R.color.holo_red_light));
            }
        }
    }
}
