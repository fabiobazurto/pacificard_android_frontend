package com.example.pacifico;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pacifico.model.foto;
import com.example.pacifico.network.GetDataService;
import com.example.pacifico.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CongratulationActivity extends AppCompatActivity {
    ProgressDialog progressDoalog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation);
        ActionBar mTopToolbar = this.getSupportActionBar();

        mTopToolbar = this.getSupportActionBar();

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.congratulation_bar);

        getSupportActionBar().setElevation(0);

        View view = getSupportActionBar().getCustomView();
        ImageView name = (ImageView) findViewById(R.id.btnCloses);
        name.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });

        /*Retrofit*/
        ImageView btnFotos = (ImageView) findViewById(R.id.openRest);
        btnFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDoalog = new ProgressDialog(CongratulationActivity.this);
                progressDoalog.setMessage("Loading....");
                progressDoalog.show();
                GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<List<foto>> call = service.getAllPhotos();
                call.enqueue(new Callback<List<foto>>() {
                    @Override
                    public void onResponse(Call<List<foto>> call, Response<List<foto>> response) {
                        progressDoalog.dismiss();
                        generateDataList(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<foto>> call, Throwable t) {
                        progressDoalog.dismiss();
                        Toast.makeText(CongratulationActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<foto> photoList) {
        AlertDialog alertDialog = new AlertDialog.Builder(CongratulationActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(photoList.get(photoList.size()-1).toString() + "\n \n" + photoList.get(photoList.size()-2).toString());
        alertDialog.show();
    }
    }

