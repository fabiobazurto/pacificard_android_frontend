package com.example.pacifico;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String textHeaderCard = String.format("Tienes una tarjeta <b>%s</b><br>aprobada de <b>%s</b>", "PLATINUM", "190000");;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.headerCardSelection);
        Button btnNext = (Button) findViewById(R.id.button);
        btnNext.setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(textHeaderCard, Html.FROM_HTML_MODE_COMPACT));
        } else {
            textView.setText(Html.fromHtml(textHeaderCard));
        }
        ActionBar mTopToolbar = this.getSupportActionBar();

        mTopToolbar = this.getSupportActionBar();

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.home_bar);

        getSupportActionBar().setElevation(0);

        View view = getSupportActionBar().getCustomView();
        ImageView name = (ImageView) findViewById(R.id.btnCloses);
        name.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                MainActivity.this.finishAndRemoveTask();
                System.exit(1);
            }
        });
    }

     public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.button: {
                Intent myIntent = new Intent(MainActivity.this, ConfirmActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
            case R.id.btnCloses:
                this.finish();
                // Do something
        }
    }
}
