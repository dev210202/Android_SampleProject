package org.first.serverconnecttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import okhttp3.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText userIDEditText;
    EditText userMailEditText;
    EditText userUpdateEditText;
    Button userInfoInsertButton;
    Button userInfoUpdateButton;
    Button userInfoFindButton;
    Button userInfoFindAllButton;
    Button deleteButton;
    TextView resultTextView;
    Button lobyButton;
    Retrofit retrofit;
    User user;
    UserService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userIDEditText = findViewById(R.id.userIDEditText);
        userMailEditText = findViewById(R.id.userMailEditText);
        userUpdateEditText = findViewById(R.id.userUpdateEditText);
        userInfoInsertButton = findViewById(R.id.insertButton);
        userInfoUpdateButton = findViewById(R.id.updateButton);
        userInfoFindButton = findViewById(R.id.findButton);
        userInfoFindAllButton = findViewById(R.id.findAllButton);
        resultTextView = findViewById(R.id.resultTextView);
        deleteButton = findViewById(R.id.deleteButton);
        lobyButton = findViewById(R.id.lobyButton);
        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);


        retrofit = new Retrofit.Builder().baseUrl("https://meeatnow.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();


        service = retrofit.create(UserService.class);

        userInfoInsertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                user = new User(userMailEditText.getText().toString(), userIDEditText.getText().toString(), "123");
                Log.e("USER INFO", user.toString());
                final Call<User> request = service.insertUserInfo(user);
                request.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.code() == 400) {
                            Log.e("ERROR CODE ", response.errorBody().toString());
                        }
                        if (response.body() != null) {
                            resultTextView.setText("SUCCESS INSERT1\n" + response.body().toString());
                        } else {
                            resultTextView.setText("SUCCESS INSERT2\n" + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        resultTextView.setText("FAIL INSERT\n" + t.toString());
                    }
                });
            }
        });
        userInfoUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = new User(userMailEditText.getText().toString(), userIDEditText.getText().toString(), "123");
                Call<User> request = service.updateUserInfo(userUpdateEditText.getText().toString(), user);
                request.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.body() != null) {
                            resultTextView.setText("SUCCESS UPDATE\n" + response.body().toString());
                        } else {
                            resultTextView.setText("SUCCESS UPDATE");
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        resultTextView.setText("FAIL UPDATE\n" + t.toString());
                    }
                });
            }
        });
        userInfoFindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<User> request = service.findUser(userUpdateEditText.getText().toString());
                request.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.body() != null) {
                            resultTextView.setText("SUCCESS FIND1\n" + response.body().toString());
                        } else {
                            resultTextView.setText("SUCCESS FIND2");
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        resultTextView.setText("FAIL FIND\n" + t.toString());
                    }
                });
            }
        });
        userInfoFindAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<List<User>> request = service.findAllUser();

                request.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        if (response.body() != null) {

                            List<User> data = response.body();
                            String a = "";
                            for (User u : data) {
                                a += u.name + " " + u.email + "\n";
                            }

                            resultTextView.setText("SUCCESS FINDALL\n" + a);
                        } else {
                            resultTextView.setText("SUCCESS FINDALL\n");
                        }

                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        resultTextView.setText("FAIL FINDALL\n" + t.toString());
                    }

                });
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<User> request = service.deleteUserInfo(userUpdateEditText.getText().toString());
                request.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.body() != null) {
                            resultTextView.setText("SUCCESS DELETE1\n" + response.body().toString());
                        }
                        else{
                            resultTextView.setText("SUCCESS DELETE2");
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        resultTextView.setText("FAIL DELETE\n" + t.toString());
                    }
                });
            }
        });
        lobyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), LobyActivity.class);
                startActivity(intent);
            }
        });
    }


}