package com.teamx.pregads;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TabOne extends Fragment {
    RecyclerView recyclerView;
    CustomLLAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        recyclerView = view.findViewById(R.id.rv_leaders);
        getLearningHours();


        return view;
    }
    public void getList(List<LearningLeaders> leadersList){
        adapter = new CustomLLAdapter(getContext(), leadersList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    public void getLearningHours(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gadsapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceholderApi jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);

        Log.i("About to make call","Hopes  it works");
        Call<List<LearningLeaders>> call = jsonPlaceholderApi.getHours();
        call.enqueue(new Callback<List<LearningLeaders>>() {
            @Override
            public void onResponse(Call<List<LearningLeaders>> call, Response<List<LearningLeaders>> response) {
                if (!response.isSuccessful()){
                    Log.i("Call unsuccessful","Code : " + response.code());
                    Toast.makeText(getActivity(),"Code : " + response.code(), Toast.LENGTH_LONG).show();
                }


                    getList(response.body());
                    Log.i("Call successful","Code : " + response.code());

                /*List<Post> posts = response.body();
                for (Post post : posts){
                    String content = "";
                    content += "Name :" + post.getName() + "\n";
                    content += "Country : " + post.getCountry() + "\n";
                    content += "Hours : " + post.getHours() + "\n";
                    content += "Badge Url : " + post.getBadgeUrl() + "\n\n";

                    textView.append(content);*/
            }

            @Override
            public void onFailure(Call<List<LearningLeaders>> call, Throwable t) {
                Log.i("Call failed","Code : " + t.getMessage());
                Toast.makeText(getActivity(),"Code : " + t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });

    }
}
