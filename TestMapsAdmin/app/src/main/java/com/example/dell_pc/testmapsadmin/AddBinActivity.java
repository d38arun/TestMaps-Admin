package com.example.dell_pc.testmapsadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddBinActivity extends AppCompatActivity {
    String urlAdd = "https://chefsondemand.000webhostapp.com/addbin.php";
    EditText latitude,longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bin);
        latitude = (EditText)findViewById(R.id.latitudeField);
        longitude = (EditText)findViewById(R.id.longitudeField);
    }
    public void onAddBinClicked (View v)
    {
        Sender s = new Sender(AddBinActivity.this,urlAdd,latitude,longitude);
        s.execute();
    }
}
