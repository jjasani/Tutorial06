package com.rku.tutorial06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {
    TextView txtWelcomeMessage;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Button btnBackColor;
    ConstraintLayout cntBackGround;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        txtWelcomeMessage = findViewById(R.id.txtWelcomeMessage);
        btnBackColor = findViewById(R.id.btnBackColor);
        cntBackGround = findViewById(R.id.cntBackGround);

        preferences = getSharedPreferences("college", MODE_PRIVATE);
        editor = preferences.edit();

        //Check for default background color
        int backcolor = preferences.getInt("backcolor",0);
        if(backcolor!=0){
            setBackGroundColor(backcolor);
        }

        String userdatavalue = preferences.getString("username","");
        txtWelcomeMessage.setText("Welcome " + userdatavalue);
        Toast.makeText(this, userdatavalue, Toast.LENGTH_SHORT).show();

        registerForContextMenu(btnBackColor);
        registerForContextMenu(txtWelcomeMessage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.general_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnuAbout:
                break;
            case R.id.mnuLogout:
                editor.remove("username");
                editor.commit();
                Intent intent = new Intent(Welcome.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.color_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnuBlueBackGround:
                setBackGroundColor(R.color.blue);
                break;
            case R.id.mnuGreenBackGround:
                setBackGroundColor(R.color.green);
                break;
            case R.id.mnuRedBackGround:
                setBackGroundColor(R.color.red);
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void setBackGroundColor(int color){
        cntBackGround.setBackgroundColor(getResources().getColor(color));
        editor.putInt("backcolor",color);
        editor.commit();
    }
}