package com.teamx.pregads;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TabTwo extends Fragment {

    RecyclerView recyclerView;
    List<SkillIQ> skillIQList;
    CustomSIQAdapter customSIQAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        recyclerView = view.findViewById(R.id.rv_skill);
        getSkillIQ();

        return view;
    }


    public void getSkillIQ(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gadsapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceholderApi jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);

        Call<List<SkillIQ>> call = jsonPlaceholderApi.getSkill();
        call.enqueue(new Callback<List<SkillIQ>>() {
            @Override
            public void onResponse(Call<List<SkillIQ>> call, Response<List<SkillIQ>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getActivity(),"Code : " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                    getList(response.body());
            }

            @Override
            public void onFailure(Call<List<SkillIQ>> call, Throwable t) {

                Toast.makeText(getActivity(),"Code : " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void getList(List<SkillIQ> leadersList){
        customSIQAdapter = new CustomSIQAdapter(leadersList, getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(customSIQAdapter);
    }
}
