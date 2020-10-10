package com.teamx.pregads;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceholderApi {

    @GET("/api/hours")
    Call<List<LearningLeaders>> getHours();

    @GET("/api/skilliq")
    Call<List<SkillIQ>> getSkill();


    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<Post> createPost(
            @Field("entry.1877115667") String firstname,
            @Field("entry.2006916086") String lastname,
            @Field("entry.1824927963") String emailaddress,
            @Field("entry.284483984") String projectlink
    );
}
