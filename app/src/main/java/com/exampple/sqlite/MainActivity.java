package com.exampple.sqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.exampple.sqlite.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        dbHelper = new DBHelper(this, "emp", null, 1);

        mainBinding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.addEmp(mainBinding.name.getText().toString(), Integer.valueOf(mainBinding.age.getText().toString()));
            }
        });

        mainBinding.showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Emp> empArrayList = dbHelper.readEmployees();
                Toast.makeText(MainActivity.this, empArrayList.toString(), Toast.LENGTH_SHORT).show();

                EmployeeAdaptor employeeAdaptor = new EmployeeAdaptor(empArrayList, MainActivity.this);
                mainBinding.recycle.setHasFixedSize(true);
                mainBinding.recycle.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                mainBinding.recycle.setAdapter(employeeAdaptor);

            }
        });
    }
}