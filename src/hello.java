package o;

//201721118 백종성

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
//import java.sql.SQLException;
//import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.*;		//Connection, Statement, ResultSet
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileInputStream;


public class hello {
	public static void main(String[] args) throws IOException, ParseException,  ParserConfigurationException, SAXException, SQLException, XPathExpressionException
	 {
		//1. 파이썬에서 소관부처명을 텍스트파일로 받아오기 ㅇ==>47개 + 각 부처들의 갯수(나중에 필요함)
		//2. java main에서 txt파일 load해서 부처명을 전부 다 읽어오고 parsing
		//3. org_name에는 부처명이, org_num에는 각 부처의 service 갯수가 ㅇㅇ
		//3. 하나씩 읽어올떄마다 orgCode 메소드의 매개변수로 전달해주고 해당 기관code 받아오면
		//4. 다음 메소드에 기관코드 전달해주고 서비스id 받아오기
		//5. 다음 메소드에 서비스id 전달해줘서 csv파일 만들기
		
		// 이때 쿼리부분에서 안쓰는 줄+ 의미 맞지 않은 줄은 주석표시하기
		// "행정자치부" 삭제 ==> 기관코드가 없음 ==> 총 46개로 변경
		 Scanner scan = new Scanner(System.in);
		sourcecode nice =new sourcecode();
		sql sql_ = new sql();
		String s=null;
		try{
            String filePath = "C:\\Users\\jsbae\\OneDrive\\바탕 화면\\백종성(바탕화면)\\2021년 1학기\\DB\\팀프로젝트\\DB_teamproject.txt";
            FileInputStream fileStream = new FileInputStream(filePath);
            byte readBuffer[]  = new byte[fileStream.available()];
            while (fileStream.read(readBuffer) != -1);
            s = new String(readBuffer,"utf-8");
			//객체 사용을 다한 경우 스트림을 닫아준다
			fileStream.close(); //스트림 닫기
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

		String org_name[] = s.split("\n");
		int org_num[] = new int[10]; //46
		String org_code[] = new String[10];
		for (int i =0; i< 10; i++) {
			String temp[] = org_name[i].split("\t");
			org_name[i]=temp[0];
			org_num[i]=Integer.parseInt(temp[1]);
		}
		
		for (int i =0; i< 10; i++)
			org_code[i]=new String(nice.orgCode(org_name[i]));	
		
		for (int i =0; i< 10; i++)
			System.out.println("check with our txt file\t" + org_name[i].toString() +"\t"+ Integer.toString(org_num[i]) +"\t" + org_code[i]);
		
		File file = new File("C:\\Users\\jsbae\\OneDrive\\바탕 화면\\백종성(바탕화면)\\2021년 1학기\\DB\\팀프로젝트\\DB_teamproject_include_code.txt");
			try {
			    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			    for (int i =0; i< 10; i++)
			    {
			    	writer.write(org_name[i].toString() +"\t"+ Integer.toString(org_num[i]) +"\t" + org_code[i]);
			    	writer.newLine();
			    }
			    writer.close();
			} catch (IOException e) {
			    e.printStackTrace();
			}
			System.out.println("Continue? (Enter 1 for continue)");
			scan.nextLine();

		String serviceid[]=null;			//총 1732개
		for (int i =0; i< 10; i++)
		{
			serviceid=nice.getServiceId(org_code[i], org_name[i], Integer.toString(1),  Integer.toString(100));
		File file1 = new File("C:\\Users\\jsbae\\OneDrive\\바탕 화면\\백종성(바탕화면)\\2021년 1학기\\DB\\팀프로젝트\\DB_file\\DB_teamproject_"+org_name[i]+".txt");
		try {
		    BufferedWriter writer = new BufferedWriter(new FileWriter(file1));
		    for (int j =0; j< serviceid.length; j++)
		    {
		    	writer.write(serviceid[j].toString());
		    	writer.newLine();
		    }
		   writer.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}}
		
		System.out.println("Continue? (Enter 1 for continue)");
		scan.nextLine();
		int count=0;
		File file_final = new File("C:\\Users\\jsbae\\OneDrive\\바탕 화면\\백종성(바탕화면)\\2021년 1학기\\DB\\팀프로젝트\\DB_file\\DB_teamproject_tocsv_2.txt");
		BufferedWriter writer_final = new BufferedWriter(new FileWriter(file_final));
		for (int i =0; i< 1; i++)	 /*46으로 바꾸기 + 아래 주석 풀기!!*/
		{serviceid=nice.getServiceId(org_code[i], org_name[i], Integer.toString(1),  Integer.toString(100));
		try {
			if(i==0)
				 writer_final.write("svcId"+","+"svcNm"+","+"jrsdDptAllNm"+","+"svcEditDt"+","+"svcPpo"+","+"sportFr"+","+"svcCts"+","+"slctnStdr"+","+"dupImprtySvc"+","+"sportTg");
			
		    for (int j =0; j< serviceid.length; j++)
		    {	nice.getSvc(serviceid[j].toString(),writer_final);
		    count++;
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}}
		System.out.println("all service counts = "+Integer.toString(count));
		
		System.out.println("Continue? (Enter 1 for continue)");
		scan.nextLine();
		
		sql_.createT_centeral("센트럴");
		sql_.createT_centeral("충청북도");sql_.createT_centeral("충청남도");sql_.createT_centeral("제주도");sql_.createT_centeral("전라북도");sql_.createT_centeral("전라남도");               sql_.createT_centeral("인천");sql_.createT_centeral("울산");sql_.createT_centeral("세종");sql_.createT_centeral("서울");sql_.createT_centeral("부산");sql_.createT_centeral("대전");                       sql_.createT_centeral("대구");sql_.createT_centeral("광주");sql_.createT_centeral("경상북도");sql_.createT_centeral("경상남도");sql_.createT_centeral("경기도");sql_.createT_centeral("강원도");
		sql_.createT_money("보조금");
		
		sql_.copy("센트럴" , "cen");
		sql_.copy("충청북도" , "cheongbuk");sql_.copy("충청남도" , "cheongnam");sql_.copy("제주도" , "jeju");sql_.copy("전라북도" , "jeon");sql_.copy("전라남도" , "jeonnam");               sql_.copy("인천" , "incheon");sql_.copy("울산" , "ulsan");sql_.copy("세종" , "sejong");sql_.copy("서울" , "seoulseoul");sql_.copy("부산" , "busan");sql_.copy("대전" , "daejeon");                                 sql_.copy("대구" , "daegu");sql_.copy("광주" , "gwangju");sql_.copy("경상북도" , "kyeongbuk");sql_.copy("경상남도" , "kyeongnam");sql_.copy("경기도" , "kyeongki");sql_.copy("강원도" , "kangwon");
		sql_.copy("보조금" , "money");
		sql_.trigger1("센트럴","보조금");
		sql_.trigger2("센트럴","보조금");
		sql_.trigger3("센트럴","보조금");
		
		sql_.do_trigger("보조금");
		
		sql_.view_local("cheongbuk","충청북도");sql_.view_local("cheongnam","충청남도");sql_.view_local("jeju","제주도");sql_.view_local("jeonbuk","전라북도");sql_.view_local("jeonnam","전라남도");sql_.view_local("incheon","인천");sql_.view_local("ulsan","울산");sql_.view_local("sejong","세종");sql_.view_local("seoul","서울");sql_.view_local("busan","부산");sql_.view_local("daejeon","대전");sql_.view_local("daegu","대구");sql_.view_local("gwangju","광주");sql_.view_local("kyeongbuk","경상북도");sql_.view_local("kyeongnam","경상남도");sql_.view_local("kyeongki","경기도");sql_.view_local("kangwon","강원도");
		sql_.view_central("Central_Administrative_Agency","센트럴");
		sql_.view_subsidy("Government_Subsidy","보조금");
		sql_.create_budget("Budget_by_ministry","central_administrative_agency","government_subsidy");

		
	 }
}