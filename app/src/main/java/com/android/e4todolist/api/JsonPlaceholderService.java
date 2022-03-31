package com.android.e4todolist.api;



import com.android.e4todolist.Model.Task;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceholderService {
    @GET("todos/{todoId}")
    Call<Task> getTodo(@Path("todoId") Integer id);

}
