package com.example.dell_pc.testmapsadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onComplaintClicked (View view)
    {
        Intent intent = new Intent(this,ComplaintActivity.class);
        startActivity(intent);
    }
    public void onNewBinClicked (View view)
    {
        Intent intent = new Intent(this, AddBinActivity.class);
        startActivity(intent);
    }
}
