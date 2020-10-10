package com.teamx.pregads;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Submit extends AppCompatActivity {
    Button done;
    TextView firstname, lastname, emailaddress, projectlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        done = findViewById(R.id.button);
        firstname = findViewById(R.id.textView3);
        lastname = findViewById(R.id.textView4);
        emailaddress = findViewById(R.id.textView5);
        projectlink = findViewById(R.id.textView6);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitProject();
            }
        });



    }
    private void submitProject() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceholderApi jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);


        Call<Post> call =jsonPlaceholderApi.createPost(firstname.getText().toString(), lastname.getText().toString(), emailaddress.getText().toString(), projectlink.getText().toString());
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                alertSuccess();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

                alertFailure();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        alertexit();
        //AlertSuccess cdd = new AlertSuccess(Submit.this);
       // cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
       // cdd.show();
    }
    public void alertSuccess(){
        DialogFragment alertSuccess= AlertSuccess.newInstance("Data", "Data");
        alertSuccess.show(getSupportFragmentManager(), "dialog");
       // alertSuccess.show(getFragmentManager(), "dialog");


    }
    public void alertFailure(){

        DialogFragment alertSuccess= AlertFailure.newInstance("Data", "Data");
        alertSuccess.show(getSupportFragmentManager(), "dialog");
    }

    private void alertexit() {
        DialogFragment alertSuccess= AlertCancel.newInstance("Data", "Data");
        alertSuccess.show(getSupportFragmentManager(), "dialog");

    }
}