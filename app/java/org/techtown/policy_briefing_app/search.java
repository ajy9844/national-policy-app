package org.techtown.policy_briefing_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class search extends AppCompatActivity {
    //private SearchView searchView;
    String[] regions = {"전체","강원도","경기도","경상남도","경상북도","광주광역시","대구광역시","대전광역시",
    "부산광역시","서울특별시","세종특별자치시","울산광역시","인천광역시","전라남도","전라북도","제주특별자치도","충청남도","충청북도"};
    String[] ages = {"전체","노년기","중장년","청년","아동·청소년","영·유아"};
    String[] services = {"전체","건강·의료·사망","결혼·육아·교육","공익·봉사","금융·세금·법률","생활·병역","여가·문화·출입국",
    "자동차·교통","주택·부동산","창업·경영","취업·직장","환경·재난"};
    String[] targets = {"전체","일반","학생","구직자","근로자","외국인/재외국인","구호/구제대상자","군인/보훈대상자","소년소녀 가장","저소득층","장애인","북한이탈주민","다문화 가정",
            "한부모/조손 가정","다자녀 가정","입양가정","독거노인","중소기업/소상공인","농축수산인", "기업인","예비창업자","환자","여성","기관/시설"};

    public String s_age;
    public String s_region;
    public String s_service;
    public String s_target;
    public String s_word;
    EditText editText;
    public String search_main_table;
    public static Context context_search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        context_search = this;
        search_main_table = ((MainActivity)MainActivity.context_main).search_main_table;
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("한 눈에 쉽게 보는 정책");

        actionBar.setDisplayHomeAsUpEnabled(true);

        Spinner spinner1 = findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,regions);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adpaterView, View view, int position, long id){
                s_region = regions[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){
                s_region = "전체";
            }
        });

        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ages);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adpaterView, View view, int position, long id){
                s_age = ages[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){
                s_age = "전체";
            }
        });

        Spinner spinner3 = findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,services);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adpaterView, View view, int position, long id){
                s_service = services[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){
                s_service = "전체";
            }
        });

        Spinner spinner4 = findViewById(R.id.spinner4);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,targets);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adpaterView, View view, int position, long id){
                s_target = targets[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){
                s_target = "전체";
            }
        });

       // searchView = findViewById(R.id.search_view);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void search_Clicked(View v){
        editText = findViewById(R.id.editText_keyword);
        s_word = editText.getText().toString();

        startActivity(new Intent(search.this, result_print.class));
    }


}