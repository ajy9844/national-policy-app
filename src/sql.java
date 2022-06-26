package o;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;	
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.JSONParser; 
import org.json.simple.parser.ParseException;

public class sql {
	
	static sql instance;
	
    String url = "jdbc:postgresql://localhost:5432/postgres";
    String user = "postgres";
    String password = "qorwhdtjd215";
    Connection connection = null;
    
    Statement st = null;
	ResultSet rs = null; //결과 받아서 처리할때
	
	public void createT_centeral(String T_name)throws SQLException {
		try {
			connection =DriverManager.getConnection(url,user,password);
			st = connection.createStatement();
			String query_create = "create table "+T_name+" (id varchar(5000), name1 varchar(5000), name2 varchar(5000), name3 varchar(5000), name4 varchar(5000), name5 varchar(5000), name6 varchar(5000), name7 varchar(5000), name8 varchar(5000), name9 varchar(5000), name10 varchar(5000), name11 varchar(5000), name12 varchar(5000), name13 varchar(5000), name14 varchar(5000), name15 varchar(5000), name16 varchar(5000), name17 varchar(5000), name18 varchar(5000), name19 varchar(5000), name20 varchar(5000), name21 varchar(5000), name22 varchar(5000), name23 varchar(5000), name24 varchar(5000), name25 varchar(5000));";
			st.executeUpdate(query_create);	
			}
        
        catch (SQLException ex) {
            System.out.println("DB 연결 실패!");
            throw ex;}
        
	}
	public void createT_money(String T_name)throws SQLException {
		try {
			connection =DriverManager.getConnection(url,user,password);
			st = connection.createStatement();
			String query_create = "create table "+T_name+" (id varchar(5000), name1 varchar(5000), name2 varchar(5000), name3 varchar(5000), name4 varchar(5000), name5 varchar(5000));";
			st.executeUpdate(query_create);	
			}
        
        catch (SQLException ex) {
            System.out.println("DB 연결 실패!");
            throw ex;}
	}
	public void copy(String T_name, String file_name) throws SQLException {
		try {
			connection =DriverManager.getConnection(url,user,password);
			st = connection.createStatement();
			String query_create = "COPY PUBLIC."+T_name +" FROM 'C:\\Users\\jsbae\\downloads\\"+file_name+".csv' DELIMITER ',' CSV HEADER ;";
			st.executeUpdate(query_create);	
			}
        
        catch (SQLException ex) {
            System.out.println("DB 연결 실패!");
            throw ex;}
	}
	
	public void trigger1(String T_name , String T_name_dst) throws SQLException {
		try {
			connection =DriverManager.getConnection(url,user,password);
			st = connection.createStatement();
			String trg_funct1 = "create function test2() returns trigger as $$\r\n"
					+ "begin\r\n"
					+ "update "+T_name+" set name19 = split_part(name19,'>',1) ;\r\n"
					+ "return New;\r\n"
					+ "end;\r\n"
					+ "$$\r\n"
					+ "language 'plpgsql';";
			String trg_query1 ="create trigger R3\r\n"
					+ "after update on "+T_name_dst+"\r\n"
					+ "for each row\r\n"
					+ "execute procedure test2();";
			st.executeUpdate(trg_funct1);
			st.executeUpdate(trg_query1);
			}
        catch (SQLException ex) {
            System.out.println("DB 연결 실패!");
            throw ex;}
	}
	public void trigger2(String T_name , String T_name_dst) throws SQLException{
		try {
			connection =DriverManager.getConnection(url,user,password);
			st = connection.createStatement();
			String trg_funct1 = "create function test3() returns trigger as $$\r\n"
					+ "begin\r\n"
					+ "IF exists (select * from "+ T_name +" where name20 like '%{%') THEN\r\n"
					+ "return null;\r\n"
					+ "ELSE\r\n"
					+ "update "+ T_name +" set name20 = string_to_array(name20 , '||');\r\n"
					+ "return New;\r\n"
					+ "END IF;\r\n"
					+ "end;\r\n"
					+ "$$\r\n"
					+ "language 'plpgsql';";
			String trg_query1 ="create trigger R2\r\n"
					+ "after update on "+T_name_dst+"\r\n"
					+ "for each row\r\n"
					+ "execute procedure test3();";
			st.executeUpdate(trg_funct1);
			st.executeUpdate(trg_query1);
			}
        catch (SQLException ex) {
            System.out.println("DB 연결 실패!");
            throw ex;}
	}
	public void trigger3(String T_name , String T_name_dst) throws SQLException{
		try {
			connection =DriverManager.getConnection(url,user,password);
			st = connection.createStatement();
			String trg_funct1 = "create function test4() returns trigger as $$\r\n"
					+ "begin\r\n"
					+ "IF exists (select * from "+T_name+" where name22 like '%{%') THEN\r\n"
					+ "return null;\r\n"
					+ "ELSE\r\n"
					+ "update "+T_name+" set name22 = string_to_array(name22 , '||');\r\n"
					+ "return New;\r\n"
					+ "END IF;\r\n"
					+ "end;\r\n"
					+ "$$\r\n"
					+ "language 'plpgsql';";
			String trg_query1 ="create trigger R1\r\n"
					+ "after update on "+T_name_dst+"\r\n"
					+ "for each row\r\n"
					+ "execute procedure test4();";
			st.executeUpdate(trg_funct1);
			st.executeUpdate(trg_query1);
			}
        catch (SQLException ex) {
            System.out.println("DB 연결 실패!");
            throw ex;}
	}
	public void do_trigger(String T_name) throws SQLException{
		try {
			connection =DriverManager.getConnection(url,user,password);
			st = connection.createStatement();
			String trg1= "delete from "+T_name+" where name5 in (select name5 from PUBLIC."+T_name+" where name5 is not null);";
			String trg2= "update "+T_name+" set name3 = name3 || '천원' ;";
			st.executeUpdate(trg1);
			st.executeUpdate(trg2);
			}
        catch (SQLException ex) {
            System.out.println("DB 연결 실패!");
            throw ex;}
	}
	
	public void view_local(String T_name, String T_name_dst)throws SQLException {
		try {
			connection =DriverManager.getConnection(url,user,password);
			st = connection.createStatement();
			String sql_query= "create view "+T_name+" as(select id as serv_id, name3 as serv_name , name4 as serv_purpose, \r\n"
					+ "name6 as serv_target, name7 as select_criteria, name8 as serv_contents, name9 as application_url, name10 as application_procedure, name12 as required_documents, name14 as institution, \r\n"
					+ "name16 as inquiries, name17 as Inquiries_contact, name19 as serv_class, name20 as age_group, name21 as serv_area, name22 as target_characteristic\r\n"
					+ "from "+T_name_dst+"\r\n"
					+ "); ";

			st.executeUpdate(sql_query);
			}
        catch (SQLException ex) {
            System.out.println("DB 연결 실패!");
            throw ex;}
	}
	public void view_central(String T_name, String T_name_dst) throws SQLException{
		try {
			connection =DriverManager.getConnection(url,user,password);
			st = connection.createStatement();
			String sql_query="create view "+T_name+" as ( select id as serv_id, name2 as subsidies_program, name3 as serv_name , name4 as serv_purpose, name6 as serv_target, name7 as select_criteria, name8 as serv_contents, name9 as application_url, name10 as application_procedure, name12 as required_documents, name14 as ministry, \r\n"
					+ "name16 as inquiries, name17 as Inquiries_contact, name19 as serv_class, name20 as age_group, name21 as serv_area, name22 as target_characteristic\r\n"
					+ "from "+T_name_dst+"\r\n"
					+ "); ";

			st.executeUpdate(sql_query);
			}
        catch (SQLException ex) {
            System.out.println("DB 연결 실패!");
            throw ex;}
	}
	public void view_subsidy(String T_name, String T_name_dst) throws SQLException{
		try {
			connection =DriverManager.getConnection(url,user,password);
			st = connection.createStatement();
			String sql_query="create view "+T_name+" as (select name1 as program_name, name2 as expenditure, \r\n"
					+ "name3 as budget, name4 as ministry\r\n"
					+ "from "+T_name_dst+"\r\n"
					+ "); ";

			st.executeUpdate(sql_query);
			}
        catch (SQLException ex) {
            System.out.println("DB 연결 실패!");
            throw ex;}
	}
	public void create_budget(String T_name, String T_name2, String T_name3) throws SQLException{
		try {
			connection =DriverManager.getConnection(url,user,password);
			st = connection.createStatement();
			String sql_query="create table "+T_name+" as (select B.ministry, count(*), sum(cast (split_part(budget,'천원',1) as FLOAT))\r\n"
					+ "from (select * from PUBLIC."+T_name2+" as CA natural join PUBLIC."+T_name3+" as S) as B\r\n"
					+ "group by B.ministry);";

			st.executeUpdate(sql_query);
			}
        catch (SQLException ex) {
            System.out.println("DB 연결 실패!");
            throw ex;}
	}

	public String select_from_budget(String name1,String name2,String name3) throws SQLException, ParseException{
		JSONObject sendObject = new JSONObject();
		JSONArray sendArray = new JSONArray();
		int i=0;
		try {
			connection =DriverManager.getConnection(url,user,password);
			st = connection.createStatement();
			String query8 = "select "+name1+","+name2+","+name3+" from budget_by_ministry";
	    	rs= st.executeQuery(query8);
        	while(rs.next()) {
        		JSONObject informationObject = new JSONObject();
        		informationObject.put("ministry",rs.getString("ministry"));
        		informationObject.put("count",rs.getString("count"));
        		informationObject.put("sum",rs.getString("sum"));
        		
        		sendObject.put(Integer.toString(i),informationObject);
        		i++;
        	}
			}
        catch (SQLException ex) {
            System.out.println("DB 연결 실패!");
            throw ex;} 	
    	return sendObject.toString();    	
	}
	public String select_from_budget2(String name1,String name2,String name3) throws SQLException, ParseException{
		String rere=null;
		try {
			connection =DriverManager.getConnection(url,user,password);
			st = connection.createStatement();
			String query8 = "select "+name1+","+name2+","+name3+" from budget_by_ministry";
	    	rs= st.executeQuery(query8);
        	while(rs.next()) {
					rere += rs.getString("ministry");
					rere +=",";
					rere += rs.getString("count");
					rere +=",";
					rere += rs.getString("sum");
					rere +="|";
        	}
			}
        catch (SQLException ex) {
            System.out.println("DB 연결 실패!");
            throw ex;}
    	
    	return rere.toString();
    	
	}
	public String local_button(String name1,String name2,String name3,String name4) throws SQLException, ParseException{
		String rere=null;
		try {
			connection =DriverManager.getConnection(url,user,password);
			st = connection.createStatement();
			String query8 = "select serv_name,serv_contents,age_group from "+ name1+" where age_group like '%"+name2+"%'"+" and serv_class like '%"+name3+"%' and target_characteristic like '%"+name4+"%'";
	    	rs= st.executeQuery(query8);
        	while(rs.next()) {
					rere += rs.getString("serv_name");
					rere +=",";
					rere += rs.getString("serv_contents");
					rere +=",";
					rere += rs.getString("age_group");
					rere +="|";
        	}
			}
        catch (SQLException ex) {
            System.out.println("DB 연결 실패!");
            throw ex;}
    	
    	return rere.toString();
    	
	}
	public String local_search(String name1,String name2) throws SQLException, ParseException{
		String rere=null;
		try {
			connection =DriverManager.getConnection(url,user,password);
			st = connection.createStatement();
			String query8 = "select serv_name,serv_contents,age_group from "+ name1+" where serv_contents like '%"+name2+"%'";
	    	rs= st.executeQuery(query8);
        	while(rs.next()) {
					rere += rs.getString("serv_name");
					rere +=",";
					rere += rs.getString("serv_contents");
					rere +=",";
					rere += rs.getString("age_group");
					rere +="|";
        	}
			}
        catch (SQLException ex) {
            System.out.println("DB 연결 실패!");
            throw ex;}
    	
    	return rere.toString();
    	
	}
	public String select_from_subsidy(String name1) throws SQLException, ParseException{
		String rere=null;
		try {
			connection =DriverManager.getConnection(url,user,password);
			st = connection.createStatement();
			String query8 = "select program_name,budget,ministry from government_subsidy where ministry like '%"+name1+"%'";
	    	rs= st.executeQuery(query8);
        	while(rs.next()) {
					rere += rs.getString("program_name");
					rere +=",";
					rere += rs.getString("budget");
					rere +=",";
					rere += rs.getString("ministry");
					rere +="|";
        	}
			}
        catch (SQLException ex) {
            System.out.println("DB 연결 실패!");
            throw ex;}
    	
    	return rere.toString();
    	
	}
	public String central_join_subsidy(String name1,String name2,String name3) throws SQLException, ParseException{
		String rere=null;
		try {
			connection =DriverManager.getConnection(url,user,password);
			st = connection.createStatement();
			String query8 = "select serv_name,ministry,sum from \r\n"
					+ "(select * from PUBLIC.Central_Administrative_Agency as CA \r\n"
					+ "	  natural join PUBLIC.budget_by_ministry as S) as B\r\n"
					+ "WHERE age_group like '%"+name1+"%' and serv_class like '%"+name2+"%' and target_characteristic like '%"+name3+"%' and cast(sum as FLOAT) < 20000000000 ;";
			rs= st.executeQuery(query8);
        	while(rs.next()) {
					rere += rs.getString("serv_name");
					rere +=",";
					rere += rs.getString("ministry");
					rere +=",";
					rere += rs.getString("sum");
					rere +="|";
        	}
			}
        catch (SQLException ex) {
            System.out.println("DB 연결 실패!");
            throw ex;}
    	
    	return rere.toString();
    	
	}
	public String i_like(String name1) throws SQLException, ParseException{
		String rere=null;
		try {
			int break_flag=0;
			connection =DriverManager.getConnection(url,user,password);
			st = connection.createStatement();
			String[] table = {"Central_Administrative_Agency","kangwon","kyeongki","kyeongnam","kyeongbuk","gwangju","daegu","daejeon","busan","seoul","sejong","ulsan","incheon","jeonnam","jeonbuk","cheongbuk","cheongnam","jeju"};
			for (int i=0; i<table.length;i++)
			{String query8 = "select serv_name,serv_contents,age_group from "+table[i]+" where serv_purpose like '%"+name1+"%'";
			rs= st.executeQuery(query8);
        	while(rs.next()) {
					rere += rs.getString("serv_name");
					rere +=",";
					rere += rs.getString("serv_contents");
					rere +=",";
					rere += rs.getString("age_group");
					rere +="|";
					break;}
        	if (rere != null) break_flag=1;
        	if (break_flag != 0) break;}
			}
        catch (SQLException ex) {
            System.out.println("DB 연결 실패!");
            throw ex;}
    	
    	return rere.toString();
    	
	}
	
	public static sql getInstance(){
		if(instance == null){ //최초 한번만 new 연산자를 통하여 메모리에 할당한다.
			instance = new sql();
		}		
		return instance;
	}
	
	public String hi() {return "안녕";}
	
	
}
