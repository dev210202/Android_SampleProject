package org.first.serverconnecttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LobyActivity extends AppCompatActivity {


    EditText titleEditText;
    EditText hostNameEditText;
    EditText openLinkEditText;
    EditText latitudeEditText;
    EditText longitudeEditText;
    EditText meetingDateEditText;
    EditText updateFindDeleteEditText;


    Button insertButton;
    Button updateButton;
    Button findButton;
    Button findAllButton;
    Button deleteButton;

    TextView resultTextView;
    Button userButton;
    Retrofit retrofit;
    LobyService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loby);

        titleEditText = findViewById(R.id.titleEditText);
        hostNameEditText = findViewById(R.id.hostNameEditText);
        openLinkEditText = findViewById(R.id.openLinkEditText);
        latitudeEditText = findViewById(R.id.latitudeEditText);
        longitudeEditText = findViewById(R.id.longitudeEditText);
        meetingDateEditText = findViewById(R.id.meetingDateEditText);
        updateFindDeleteEditText = findViewById(R.id.updateFindDeleteEditText);

        insertButton = findViewById(R.id.insertButton);
        updateButton = findViewById(R.id.updateButton);
        findButton = findViewById(R.id.findButton);
        findAllButton = findViewById(R.id.findAllButton);
        deleteButton = findViewById(R.id.deleteButton);

        resultTextView = findViewById(R.id.resultTextView);
        userButton = findViewById(R.id.userButton);
        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);


        retrofit = new Retrofit.Builder().baseUrl("https://meeatnow.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();


        service = retrofit.create(LobyService.class);


        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Loby loby = new Loby(
                        titleEditText.getText().toString(),
                        hostNameEditText.getText().toString(),
                        openLinkEditText.getText().toString(),
                        Double.parseDouble(latitudeEditText.getText().toString()),
                        Double.parseDouble(longitudeEditText.getText().toString()),
                        meetingDateEditText.getText().toString()
                );
                Call<Loby> request = service.insertLobyInfo(loby);
                request.enqueue(new Callback<Loby>() {
                    @Override
                    public void onResponse(Call<Loby> call, Response<Loby> response) {
                        if (response.body() != null) {
                            resultTextView.setText("SUCCESS INSERT1\n" + response.body().toString());
                        } else {
                            resultTextView.setText("SUCCESS INSERT2\n" + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Loby> call, Throwable t) {
                        resultTextView.setText("FAIL INSERT\n" + t.toString());
                    }
                });
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Loby loby = new Loby(
                        titleEditText.getText().toString(),
                        hostNameEditText.getText().toString(),
                        openLinkEditText.getText().toString(),
                        Double.parseDouble(latitudeEditText.getText().toString()),
                        Double.parseDouble(longitudeEditText.getText().toString()),
                        meetingDateEditText.getText().toString()
                );
                Call<Loby> request = service.updateLobyInfo(updateFindDeleteEditText.getText().toString(), loby);
                request.enqueue(new Callback<Loby>() {
                    @Override
                    public void onResponse(Call<Loby> call, Response<Loby> response) {
                        if (response.body() != null) {
                            resultTextView.setText("SUCCESS UPDATE\n" + response.body().toString());
                        } else {
                            resultTextView.setText("SUCCESS UPDATE");
                        }
                    }

                    @Override
                    public void onFailure(Call<Loby> call, Throwable t) {
                        resultTextView.setText("FAIL UPDATE\n" + t.toString());
                    }
                });
            }
        });

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<Loby> request = service.findLoby(updateFindDeleteEditText.getText().toString());
                request.enqueue(new Callback<Loby>() {
                    @Override
                    public void onResponse(Call<Loby> call, Response<Loby> response) {
                        if (response.body() != null) {
                            resultTextView.setText("SUCCESS FIND1\n" + response.body().toString());
                        } else {
                            resultTextView.setText("SUCCESS FIND2");
                        }
                    }

                    @Override
                    public void onFailure(Call<Loby> call, Throwable t) {
                        resultTextView.setText("FAIL FIND\n" + t.toString());
                    }
                });
            }
        });
        findAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<List<Loby>> request = service.findAllLoby();
                request.enqueue(new Callback<List<Loby>>() {
                    @Override
                    public void onResponse(Call<List<Loby>> call, Response<List<Loby>> response) {
                        if (response.body() != null) {

                            List<Loby> data = response.body();
                            String a = "";
                            for (Loby u : data) {
                                a += u.title + " " + u.hostName + " " + u.openLink + " " + u.latitude + " " + u.longitude + " " + u.meetingDate + "\n";
                            }

                            resultTextView.setText("SUCCESS FINDALL\n" + a);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Loby>> call, Throwable t) {
                        resultTextView.setText("FAIL FINDALL\n" + t.toString());
                    }
                });
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Loby> request = service.deleteLoby(updateFindDeleteEditText.getText().toString());
                request.enqueue(new Callback<Loby>() {
                    @Override
                    public void onResponse(Call<Loby> call, Response<Loby> response) {
                        if(response.body() != null){
                            resultTextView.setText("SUCCESS DELETE1\n" + response.body().toString());
                        }
                        else{
                            resultTextView.setText("SUCCESS DELETE2");
                        }
                    }

                    @Override
                    public void onFailure(Call<Loby> call, Throwable t) {
                        resultTextView.setText("FAIL DELETE\n" + t.toString());
                    }
                });
            }
        });
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}