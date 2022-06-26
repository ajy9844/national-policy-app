package org.techtown.policy_briefing_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Arrays;

public class search2 extends AppCompatActivity {
    public static Context context_search2;

    public String s_word;
    EditText editText;
    public String s_Ministry;
    public String search_main_table;

    String[] Ministry = {"전체","행정안전부","보건복지부","과학기술정보통신부","농림축산식품부","문화체육관광부","질병관리청",
    "산림청","소방청","산업통상자원부","중소벤처기업부","해양수산부","문화재청","국토교통부","여성가족부","교육부","국가보훈처",
    "통일부","중앙선거관리위원회","환경부","식품의약품안전처","국무조정실 및 국무총리비서실","특허청","고용노동부","대법원","농촌진흥청",
    "공정거래위원회","법무부","경창철","방송통신위원회","새만금개발청","민주평화통일자문회의","기획재정부","국회","금융위원회","외교부",
    "해양경찰청","국민권익위원회","행정중심복합도시건설청","국가인권위원회","개인정보보호위원회","국방부"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context_search2 = this;

        ActionBar actionBar = getSupportActionBar();
        search_main_table = ((MainActivity)MainActivity.context_main).search_main_table;
        actionBar.setTitle("한 눈에 쉽게 보는 정책");

        actionBar.setDisplayHomeAsUpEnabled(true);

        Spinner spinner1 = findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Ministry);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adpaterView, View view, int position, long id){
                s_Ministry = Ministry[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){
                s_Ministry = "전체";
            }
        });
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

        startActivity(new Intent(search2.this, result_print.class));
    }
}