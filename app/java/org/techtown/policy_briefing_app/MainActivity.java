package org.techtown.policy_briefing_app;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
   // public PostgreSqlJDBC db;
    private AppBarConfiguration mAppBarConfiguration;
    public static Context context_main;
    public String search_main_table;
    public int Code;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context_main = this;


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Snackbar.make(view, "관심 정책", Snackbar.LENGTH_LONG)
                        .setAction("GO", new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                Code = 2;
                                Intent intent = new Intent (MainActivity.this,result_print.class);
                                startActivity(intent);
                            }

                        } ).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void home_Clicked(View v){
        Code = 0;
        if (v.getId() == R.id.region){
            search_main_table = "Local";
            startActivity(new Intent(MainActivity.this, search.class));
        }
        else if (v.getId() == R.id.central){
            //sql을 중앙부처 db에서 확인
            search_main_table = "Ministry";
           startActivity(new Intent(MainActivity.this, search.class));
        }
        else{
            // sql을 교육청 db에서 확인
            search_main_table = "Subsi";
            startActivity(new Intent(MainActivity.this, search2.class));
        }


    }

    public void onClicked(View v){
        Code = 1;
        startActivity(new Intent(MainActivity.this, result_print.class));}


}