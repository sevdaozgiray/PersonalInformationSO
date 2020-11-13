package com.example.personalinformationso.api;

import com.example.personalinformationso.model.PersonalModel;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Services {
    @GET("b/5e9ab216435f5604bb43cfdd")
    Call<List<PersonalModel>> getPersonalList();
}
