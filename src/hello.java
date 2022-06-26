package o;

//201721118 ������

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
		//1. ���̽㿡�� �Ұ���ó���� �ؽ�Ʈ���Ϸ� �޾ƿ��� ��==>47�� + �� ��ó���� ����(���߿� �ʿ���)
		//2. java main���� txt���� load�ؼ� ��ó���� ���� �� �о���� parsing
		//3. org_name���� ��ó����, org_num���� �� ��ó�� service ������ ����
		//3. �ϳ��� �о�Ë����� orgCode �޼ҵ��� �Ű������� �������ְ� �ش� ���code �޾ƿ���
		//4. ���� �޼ҵ忡 ����ڵ� �������ְ� ����id �޾ƿ���
		//5. ���� �޼ҵ忡 ����id �������༭ csv���� �����
		
		// �̶� �����κп��� �Ⱦ��� ��+ �ǹ� ���� ���� ���� �ּ�ǥ���ϱ�
		// "������ġ��" ���� ==> ����ڵ尡 ���� ==> �� 46���� ����
		 Scanner scan = new Scanner(System.in);
		sourcecode nice =new sourcecode();
		sql sql_ = new sql();
		String s=null;
		try{
            String filePath = "C:\\Users\\jsbae\\OneDrive\\���� ȭ��\\������(����ȭ��)\\2021�� 1�б�\\DB\\��������Ʈ\\DB_teamproject.txt";
            FileInputStream fileStream = new FileInputStream(filePath);
            byte readBuffer[]  = new byte[fileStream.available()];
            while (fileStream.read(readBuffer) != -1);
            s = new String(readBuffer,"utf-8");
			//��ü ����� ���� ��� ��Ʈ���� �ݾ��ش�
			fileStream.close(); //��Ʈ�� �ݱ�
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
		
		File file = new File("C:\\Users\\jsbae\\OneDrive\\���� ȭ��\\������(����ȭ��)\\2021�� 1�б�\\DB\\��������Ʈ\\DB_teamproject_include_code.txt");
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

		String serviceid[]=null;			//�� 1732��
		for (int i =0; i< 10; i++)
		{
			serviceid=nice.getServiceId(org_code[i], org_name[i], Integer.toString(1),  Integer.toString(100));
		File file1 = new File("C:\\Users\\jsbae\\OneDrive\\���� ȭ��\\������(����ȭ��)\\2021�� 1�б�\\DB\\��������Ʈ\\DB_file\\DB_teamproject_"+org_name[i]+".txt");
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
		File file_final = new File("C:\\Users\\jsbae\\OneDrive\\���� ȭ��\\������(����ȭ��)\\2021�� 1�б�\\DB\\��������Ʈ\\DB_file\\DB_teamproject_tocsv_2.txt");
		BufferedWriter writer_final = new BufferedWriter(new FileWriter(file_final));
		for (int i =0; i< 1; i++)	 /*46���� �ٲٱ� + �Ʒ� �ּ� Ǯ��!!*/
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
		
		sql_.createT_centeral("��Ʈ��");
		sql_.createT_centeral("��û�ϵ�");sql_.createT_centeral("��û����");sql_.createT_centeral("���ֵ�");sql_.createT_centeral("����ϵ�");sql_.createT_centeral("���󳲵�");               sql_.createT_centeral("��õ");sql_.createT_centeral("���");sql_.createT_centeral("����");sql_.createT_centeral("����");sql_.createT_centeral("�λ�");sql_.createT_centeral("����");                       sql_.createT_centeral("�뱸");sql_.createT_centeral("����");sql_.createT_centeral("���ϵ�");sql_.createT_centeral("��󳲵�");sql_.createT_centeral("��⵵");sql_.createT_centeral("������");
		sql_.createT_money("������");
		
		sql_.copy("��Ʈ��" , "cen");
		sql_.copy("��û�ϵ�" , "cheongbuk");sql_.copy("��û����" , "cheongnam");sql_.copy("���ֵ�" , "jeju");sql_.copy("����ϵ�" , "jeon");sql_.copy("���󳲵�" , "jeonnam");               sql_.copy("��õ" , "incheon");sql_.copy("���" , "ulsan");sql_.copy("����" , "sejong");sql_.copy("����" , "seoulseoul");sql_.copy("�λ�" , "busan");sql_.copy("����" , "daejeon");                                 sql_.copy("�뱸" , "daegu");sql_.copy("����" , "gwangju");sql_.copy("���ϵ�" , "kyeongbuk");sql_.copy("��󳲵�" , "kyeongnam");sql_.copy("��⵵" , "kyeongki");sql_.copy("������" , "kangwon");
		sql_.copy("������" , "money");
		sql_.trigger1("��Ʈ��","������");
		sql_.trigger2("��Ʈ��","������");
		sql_.trigger3("��Ʈ��","������");
		
		sql_.do_trigger("������");
		
		sql_.view_local("cheongbuk","��û�ϵ�");sql_.view_local("cheongnam","��û����");sql_.view_local("jeju","���ֵ�");sql_.view_local("jeonbuk","����ϵ�");sql_.view_local("jeonnam","���󳲵�");sql_.view_local("incheon","��õ");sql_.view_local("ulsan","���");sql_.view_local("sejong","����");sql_.view_local("seoul","����");sql_.view_local("busan","�λ�");sql_.view_local("daejeon","����");sql_.view_local("daegu","�뱸");sql_.view_local("gwangju","����");sql_.view_local("kyeongbuk","���ϵ�");sql_.view_local("kyeongnam","��󳲵�");sql_.view_local("kyeongki","��⵵");sql_.view_local("kangwon","������");
		sql_.view_central("Central_Administrative_Agency","��Ʈ��");
		sql_.view_subsidy("Government_Subsidy","������");
		sql_.create_budget("Budget_by_ministry","central_administrative_agency","government_subsidy");

		
	 }
}