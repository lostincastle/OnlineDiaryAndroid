package com.anup.myapplication;

import com.anup.myapplication.api.ApiClient;
import com.anup.myapplication.models.Result;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Mockito.when;

public class SignupTest {
    @Test
    public void SignupApiTest() {
        ApiClient apiClient = Mockito.mock(ApiClient.class);
        final Call<Result> mockedSignup = Mockito.mock(Call.class);
        when(apiClient.Signup("testName","9800000889","test@mail.com","testUsername","testPassword"))
                .thenReturn(mockedSignup);

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Callback<Result> callback = invocation.getArgument(0,Callback.class);
                callback.onResponse(mockedSignup, Response.success(new Result()));
                return null;
            }
        });
    }
}
