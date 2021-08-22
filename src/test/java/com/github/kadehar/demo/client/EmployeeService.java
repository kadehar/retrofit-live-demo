package com.github.kadehar.demo.client;

import com.github.kadehar.demo.model.Employee;
import com.github.kadehar.demo.model.EmployeeData;
import com.github.kadehar.demo.model.EmployeeResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface EmployeeService {
    @POST("api/v1/create")
    Call<EmployeeData> create(@Body Employee employee);

    @PUT("api/v1/update/{id}")
    Call<EmployeeData> update(@Path("id") Long id, @Body Employee employee);

    @DELETE("api/v1/delete/{id}")
    Call<EmployeeResponse> delete(@Path("id") Long id);
}
