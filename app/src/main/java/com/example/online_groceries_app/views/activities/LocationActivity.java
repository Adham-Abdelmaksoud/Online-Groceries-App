package com.example.online_groceries_app.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.online_groceries_app.R;
import com.example.online_groceries_app.databinding.ActivityLocationBinding;
import com.example.online_groceries_app.util.SessionManagement;

public class LocationActivity extends AppCompatActivity{

    private ActivityLocationBinding binding;
    private String[] areas = {"Area 1", "Area 2", "Area 3", "Area 4", "Area 5"};
    private String[] zones = {"Zone 1", "Zone 2", "Zone 3", "Zone 4", "Zone 5"};
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;
    private String area;
    private String zone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_location);

        adapter1 = new ArrayAdapter<String>(LocationActivity.this, android.R.layout.simple_spinner_item, areas);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.areaSpinner.setAdapter(adapter1);

        adapter2 = new ArrayAdapter<String>(LocationActivity.this, android.R.layout.simple_spinner_item, zones);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.zoneSpinner.setAdapter(adapter2);

        binding.areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                area = areas[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.zoneSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                zone = zones[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocationActivity.this, ConfirmationCodeActivity.class);
                startActivity(intent);
            }
        });

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManagement management = new SessionManagement(getApplicationContext());
                management.setLocation(area+", "+zone);
                Intent intent = new Intent(LocationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}