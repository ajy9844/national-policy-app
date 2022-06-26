package org.techtown.policy_briefing_app;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class result_print extends AppCompatActivity {

    public String s_age;
    public String s_region;
    public String s_service;
    public String s_target;
    public String s_word;
    public String s_Ministry;
    public int Code;

    public static Context context_result_print;
    Cursor result_cursor;
    public String search_main_table;

    private ArrayList<result_data> arrayList;
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;


    private List<Local> servList;
    private List<Central> cent;
    private List<Subsidy> budget;


    private long sum;


    SQLiteDatabase db;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_print);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context_result_print =this;
        sum = 0;

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("한 눈에 쉽게 보는 정책");
        actionBar.setDisplayHomeAsUpEnabled(true);

        // 초기화면에서 중앙행정 / 지역별 / 교육청 중 무엇을 선택했는지
        search_main_table = ((MainActivity)MainActivity.context_main).search_main_table;
        Code = ((MainActivity)MainActivity.context_main).Code;





        recyclerView = (RecyclerView)findViewById(R.id.rv);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        arrayList = new ArrayList<>();
        mainAdapter = new MainAdapter(arrayList);
        recyclerView.setAdapter(mainAdapter);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        RecyclerDecoration spaceDecoration = new RecyclerDecoration(20);
        recyclerView.addItemDecoration(spaceDecoration);

        //검색
        if(Code == 0){
            if(search_main_table.equals("Local") || search_main_table.equals("Ministry")){ // 중앙행정이나 지역별
                s_age = ((search)search.context_search).s_age;
                s_region = ((search)search.context_search).s_region;
                s_service = ((search)search.context_search).s_service;
                s_target = ((search)search.context_search).s_target;
                s_word = ((search)search.context_search).s_word;
            }
            else{ //국고보조금 조회
                s_Ministry =((search2)search2.context_search2).s_Ministry;
                s_word = ((search2)search2.context_search2).s_word;
            }

            initLoadDB();

            if(search_main_table.equals("Local")){
                for(int i = 0 ; i< servList.size(); i++){
                    make_list(servList.get(i).serv_name,servList.get(i).serv_purpose,servList.get(i).age_group);
                }
            }
            else if (search_main_table.equals("Ministry")){
                for(int i = 0 ; i< cent.size(); i++){
                    make_list(cent.get(i).serv_name,cent.get(i).serv_purpose,cent.get(i).age_group);
                }
            }
            else{
                for(int i = 0 ; i< budget.size(); i++){
                    sum += budget.get(i).budget;
                    make_list(budget.get(i).program_name,Long.toString(budget.get(i).budget),budget.get(i).ministry);
                }
                TextView txt = findViewById(R.id.textView2);
                txt.setText("합계:  "+Long.toString(sum) + " (원) ");
            }
        }

        //나의 정책
        else if (Code == 1){
            DataAdapter mDbHelper= new DataAdapter(getApplicationContext());
            mDbHelper.createDatabase();
            mDbHelper.open();
            servList = mDbHelper.getTableData();

            mDbHelper.close();

            for(int i = 0 ; i< servList.size(); i++){
                make_list(servList.get(i).serv_name,servList.get(i).serv_purpose,servList.get(i).age_group);
            }
        }

        //관심목록
        else{

            String full = MainAdapter.get_rst();
            String[] temp = full.split(",");
            String str1 = temp[0].substring(4);
            String str2 = temp[1];
            String str3 = temp[2];
            result_data rd = new result_data(str1,str2,str3);

            TextView txt = findViewById(R.id.textView2);

            MyApplication ma = (MyApplication)getApplicationContext();
            ma.setState(rd);

            ArrayList<result_data> dt = ma.getState();

            for(int i = 0; i<dt.size();i++){
                make_list(dt.get(i).getService_name(),dt.get(i).getService_dsp(),dt.get(i).getAge_info());
            }
        }




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

    private void initLoadDB() {
        DataAdapter mDbHelper= new DataAdapter(getApplicationContext());


        mDbHelper.createDatabase();
        mDbHelper.open();

        // db에 있는 값들을 model을 적용해서 넣는다.

        if(search_main_table.equals("Local")){
            servList = mDbHelper.getLocalTableData("공공서비스목록_"+s_region,s_age,s_target,s_service,s_word);
        }
        else if (search_main_table.equals("Ministry")){
            cent = mDbHelper.getCentralTableData("[공공서비스목록_중앙행정기관(소속기관포함)]",s_age,s_target,s_service,s_word);
        }
        else{
            budget = mDbHelper.getSubsidyTableData("budget",s_Ministry,s_word);
        }


        // db 닫기
        mDbHelper.close();
    }

    public void make_list(String serv_name, String serv_pur,String serv_age){
        result_data rd = new result_data(serv_name,serv_pur,serv_age);
        arrayList.add(rd);
        mainAdapter.notifyDataSetChanged();
    }

}