package org.techtown.policy_briefing_app;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DataAdapter
{
    protected static final String TAG = "DataAdapter";

    // TODO : TABLE 이름을 명시해야함
    protected static final String TABLE_NAME = "공공서비스목록_서울특별시";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DatabaseHelper mDbHelper;

    public DataAdapter(Context context)
    {
        this.mContext = context;
        mDbHelper = new DatabaseHelper(mContext);
    }

    public DataAdapter createDatabase() throws SQLException
    {
        try
        {
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException)
        {
            Log.e(TAG, mIOException.toString() + "  unable to create database!");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public DataAdapter open() throws SQLException
    {
        try
        {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close()
    {
        mDbHelper.close();
    }

    public List<Local> getTableData()
    {
        try
        {
            String sql = "SELECT * FROM " + TABLE_NAME +" WHERE 생애주기 LIKE '%청년%' AND 서비스대상지역 LIKE '%은평구%'";

            // 모델 넣을 리스트 생성
            List<Local> servList = new ArrayList();

            // TODO : 모델 선언
            Local local = null;

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                // 칼럼의 마지막까지
                while(mCur.moveToNext()) {

                    // TODO : 커스텀 모델 생성
                    local = new Local();

                    // TODO : Record 기술

                    local.setServ_id(mCur.getString(0));
                    local.setServ_name(mCur.getString(3));
                    local.setServ_purpose(mCur.getString(4));
                    local.setServ_target(mCur.getString(6));
                    local.setSelect_criteria(mCur.getString(7));
                    local.setServ_contents(mCur.getString(8));
                    local.setApplication_url(mCur.getString(9));
                    local.setApplication_procedure(mCur.getString(10));
                    local.setRequired_documents(mCur.getString(12));
                    local.setInstitution(mCur.getString(14));
                    local.setInquiries(mCur.getString(16));
                    local.setInquiries_contact(mCur.getString(17));

                    local.setServ_class(mCur.getString(19));
                    local.setAge_group(mCur.getString(20));
                    local.setServ_area(mCur.getString(21));
                    local.setTarget_characteristic(mCur.getString(22));

                    // 리스트에 넣기
                    servList.add(local);
                }
            }
            return servList;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public List<Local> getLocalTableData(String table,String s_age,String s_target,String s_service,String s_word)
    {
        try {
            String sql;
            if(table.equals("공공서비스목록_전체")){
                sql = "SELECT * FROM 공공서비스목록_서울특별시" + " UNION ALL "+"SELECT * FROM 공공서비스목록_부산광역시";
            }
            else{sql = "SELECT * FROM " + table;}

            int count = 0;

            if (!s_age.equals("전체")){
                if (count == 0){
                    sql = sql + " WHERE 생애주기 LIKE '%"+s_age+"%'";
                }
                else if (count >= 1){
                    sql = sql+" AND 생애주기 LIKE '%"+s_age+"%'";
                }
                count += 1;
            }

            if(!s_target.equals("전체")){
                if (count == 0){
                    sql = sql + " WHERE 대상특성및가구유형 LIKE '%"+s_target+"%'";;
                }

                else if (count >= 1){
                    sql = sql+" AND 대상특성및가구유형 LIKE '%"+s_target+"%'";
                }
                count +=1;
            }

            if(!s_service.equals("전체")){
                if (count == 0){
                    sql = sql + " WHERE 서비스분류 LIKE '%"+s_service+"%'";;
                }

                else if (count >= 1){
                    sql = sql+" AND 서비스분류 LIKE '%"+s_service+"%'";
                }
                count +=1;
            }

            if(!s_word.isEmpty()){
                if (count == 0){
                    sql = sql + " WHERE 서비스목적 LIKE '%"+s_word+"%'";;
                }

                else if (count >= 1){
                    sql = sql+" AND 서비스목적 LIKE '%"+s_word+"%'";
                }
                count +=1;
            }

            // 모델 넣을 리스트 생성
            List<Local> servList = new ArrayList();

            // TODO : 모델 선언
            Local local_serv = null;

            Cursor mCur = mDb.rawQuery(sql, null);

            if (mCur!=null)
            {
                // 칼럼의 마지막까지
                while(mCur.moveToNext()) {

                    // TODO : 커스텀 모델 생성
                    local_serv = new Local();

                    // TODO : Record 기술
                    local_serv.setServ_id(mCur.getString(0));
                    local_serv.setServ_name(mCur.getString(3));
                    local_serv.setServ_purpose(mCur.getString(4));
                    local_serv.setServ_target(mCur.getString(6));
                    local_serv.setSelect_criteria(mCur.getString(7));
                    local_serv.setServ_contents(mCur.getString(8));
                    local_serv.setApplication_url(mCur.getString(9));
                    local_serv.setApplication_procedure(mCur.getString(10));
                    local_serv.setRequired_documents(mCur.getString(12));
                    local_serv.setInstitution(mCur.getString(14));
                    local_serv.setInquiries(mCur.getString(16));
                    local_serv.setInquiries_contact(mCur.getString(17));

                    local_serv.setServ_class(mCur.getString(19));
                    local_serv.setAge_group(mCur.getString(20));
                    local_serv.setServ_area(mCur.getString(21));
                    local_serv.setTarget_characteristic(mCur.getString(22));

                    // 리스트에 넣기
                    servList.add(local_serv);
                }
            }
            return servList;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public List<Central> getCentralTableData(String table,String s_age,String s_target,String s_service,String s_word)
    {
        try {
            String sql = "SELECT * FROM " + table;

            int count = 0;

            if (!s_age.equals("전체")){
                if (count == 0){
                    sql = sql + " WHERE 생애주기 LIKE '%"+s_age+"%'";
                }
                else if (count >= 1){
                    sql = sql+" AND 생애주기 LIKE '%"+s_age+"%'";
                }
                count += 1;
            }

            if(!s_target.equals("전체")){
                if (count == 0){
                    sql = sql + " WHERE 대상특성및가구유형 LIKE '%"+s_target+"%'";;
                }

                else if (count >= 1){
                    sql = sql+" AND 대상특성및가구유형 LIKE '%"+s_target+"%'";
                }
                count +=1;
            }

            if(!s_service.equals("전체")){
                if (count == 0){
                    sql = sql + " WHERE 서비스분류 LIKE '%"+s_service+"%'";;
                }

                else if (count >= 1){
                    sql = sql+" AND 서비스분류 LIKE '%"+s_service+"%'";
                }
                count +=1;
            }

            if(!s_word.isEmpty()){
                if (count == 0){
                    sql = sql + " WHERE 서비스목적 LIKE '%"+s_word+"%'";;
                }

                else if (count >= 1){
                    sql = sql+" AND 서비스목적 LIKE '%"+s_word+"%'";
                }
                count +=1;
            }


            // 모델 넣을 리스트 생성
            List<Central> servList = new ArrayList();

            // TODO : 모델 선언
            Central central_serv = null;

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                // 칼럼의 마지막까지
                while(mCur.moveToNext()) {

                    // TODO : 커스텀 모델 생성
                    central_serv = new Central();

                    // TODO : Record 기술
                    central_serv.setServ_id(mCur.getString(0));
                    central_serv.setSubsidies_program(mCur.getString(2));
                    central_serv.setServ_name(mCur.getString(3));
                    central_serv.setServ_purpose(mCur.getString(4));
                    central_serv.setServ_target(mCur.getString(6));
                    central_serv.setSelect_criteria(mCur.getString(7));
                    central_serv.setServ_contents(mCur.getString(8));
                    central_serv.setApplication_url(mCur.getString(9));
                    central_serv.setApplication_procedure(mCur.getString(10));
                    central_serv.setRequired_documents(mCur.getString(12));
                    central_serv.setMinistry(mCur.getString(14));
                    central_serv.setInquiries(mCur.getString(16));
                    central_serv.setInquiries_contact(mCur.getString(17));

                    central_serv.setServ_class(mCur.getString(19));
                    central_serv.setAge_group(mCur.getString(20));
                    central_serv.setServ_area(mCur.getString(21));
                    central_serv.setTarget_characteristic(mCur.getString(22));

                    // 리스트에 넣기
                    servList.add(central_serv);
                }
            }
            return servList;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public List<Subsidy> getSubsidyTableData(String table,String min, String s_word)
    {
        try {
            String sql = "SELECT * FROM " + table;
            int count = 0;
            if (!min.equals("전체")){
                if (count == 0){
                    sql = sql + " WHERE 중앙부처 LIKE '%"+min+"%'";
                }
                else if (count >= 1){
                    sql = sql+" AND 중앙부처 LIKE '%"+min+"%'";
                }
                count += 1;
            }

            if(!s_word.isEmpty()){
                if (count == 0){
                    sql = sql + " WHERE 내역사업 LIKE '%"+s_word+"%'";;
                }

                else if (count >= 1){
                    sql = sql+" AND 내역사업 LIKE '%"+s_word+"%'";
                }
                count +=1;
            }

            // 모델 넣을 리스트 생성
            List<Subsidy> servList = new ArrayList();

            // TODO : 모델 선언
            Subsidy subsidy = null;

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                // 칼럼의 마지막까지
                while(mCur.moveToNext()) {

                    // TODO : 커스텀 모델 생성
                    subsidy = new Subsidy();

                    // TODO : Record 기술
                    subsidy.setProgram_name(mCur.getString(1));
                    subsidy.setExpenditure(mCur.getString(2));
                    subsidy.setBudget(mCur.getLong(3));
                    subsidy.setMinistry(mCur.getString(4));

                    // 리스트에 넣기
                    servList.add(subsidy);
                }
            }
            return servList;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

}
