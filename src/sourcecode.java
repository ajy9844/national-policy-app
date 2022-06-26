package o;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.JSONParser; 
import org.json.simple.parser.ParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class sourcecode {

	public String orgCode(String readBuffer) throws IOException, ParseException{   

		 StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1741000/StanOrgCd/getStanOrgCdList"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=QW3TWyAiMqk%2F2%2BmuUIqvAAEh%2FQKwrtrEeYeBRrdgjF1ewTMm%2F%2FmdeRMlXiDpfWWOG7UkRk5v8xHRPN9JpkeB2g%3D%3D"); /*Service Key*/
	        //urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("-", "UTF-8")); /*공공데이터포털에서 받은 인증키*/
	        //urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        //urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("30", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*호출문서(xml, json) default : xml*/
	        urlBuilder.append("&" + URLEncoder.encode("full_nm","UTF-8") + "=" + URLEncoder.encode(readBuffer, "UTF-8")); /*기관명*/
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        System.out.println(sb.toString());
	        
	        JSONParser jsonParse = new JSONParser();
	        JSONObject jsonObj = (JSONObject) jsonParse.parse(sb.toString());
	        JSONArray stanArray = (JSONArray) jsonObj.get("StanOrgCd");
	        JSONObject stanobject = (JSONObject) stanArray.get(1); 	        
	        JSONArray row = (JSONArray) stanobject.get("row");
	        JSONObject rowobject = (JSONObject) row.get(0); 
	        rowobject.get("org_cd");
	        System.out.println(rowobject.get("org_cd").toString());
	  return rowobject.get("org_cd").toString();
    }
		
	public String[] getServiceId(String org_code, String org, String page, String num) throws IOException, ParseException, ParserConfigurationException, SAXException, XPathExpressionException{
		        StringBuilder urlBuilder = new StringBuilder("http://api.korea.go.kr/openapi/svc/list"); /*URL*/
		        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=QW3TWyAiMqk%2F2%2BmuUIqvAAEh%2FQKwrtrEeYeBRrdgjF1ewTMm%2F%2FmdeRMlXiDpfWWOG7UkRk5v8xHRPN9JpkeB2g%3D%3D"); /*Service Key*/
		        urlBuilder.append("&" + URLEncoder.encode("format","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*xml, html, excel*/
		        //urlBuilder.append("&" + URLEncoder.encode("srhQuery","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*검색대상: 서비스 명, 목적, 내용, 지원대상, 선정기준, 신청절차/방법, 서비스키워드*/
		        //urlBuilder.append("&" + URLEncoder.encode("sort","UTF-8") + "=" + URLEncoder.encode("RANK", "UTF-8")); /*검색결과 정렬 (RANK : 정확도순, DATE : 최신순, SORT_TTL : 서비스 가나다 순, VW_CNT : 조회순, default : RANK) ※ 기본은 역순정렬, 가나다 순일 경우 순차정렬*/
		        urlBuilder.append("&" + URLEncoder.encode("jrsdOrgCd","UTF-8") + "=" + URLEncoder.encode(org_code, "UTF-8")); /*공공서비스 소관기관 코드*/
		        urlBuilder.append("&" + URLEncoder.encode("jrsdOrgNm","UTF-8") + "=" + URLEncoder.encode(org, "UTF-8")); /*공공서비스 소관기관 명*/
		        //urlBuilder.append("&" + URLEncoder.encode("lrgAstCd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*공공서비스 대분류 코드 (데이터 제공형태가 excel일 경우 필수)*/
		        //urlBuilder.append("&" + URLEncoder.encode("mdmAstCd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*공공서비스 중분류 코드 (데이터 제공형태가 excel일 경우 필수)*/
		        //urlBuilder.append("&" + URLEncoder.encode("smallAstCd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*공공서비스 소분류 코드*/
		        urlBuilder.append("&" + URLEncoder.encode("pageIndex","UTF-8") + "=" + URLEncoder.encode(page, "UTF-8")); /*페이지 번호 (default : 1)*/
		        urlBuilder.append("&" + URLEncoder.encode("pageSize","UTF-8") + "=" + URLEncoder.encode(num, "UTF-8")); /*페이지 당 결과 수 (default : 10, max : 100)*/
		        URL url = new URL(urlBuilder.toString());
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("GET");
		        conn.setRequestProperty("Content-type", "application/json");
		        System.out.println("Response code: " + conn.getResponseCode());
		        BufferedReader rd;
		        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        } else {
		            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		        }
		        StringBuilder sb = new StringBuilder();
		        String line;
		        while ((line = rd.readLine()) != null) {
		            sb.append(line);
		        }
		        rd.close();
		        conn.disconnect();
		        //System.out.println(sb.toString());
		        
		        													//?
		        int count=0;
		        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		        factory.setNamespaceAware(true);
		        Document doc = null;
		        InputSource is = new InputSource(new StringReader(sb.toString()));
		        DocumentBuilder builder = factory.newDocumentBuilder();
		        doc = builder.parse(is);
	            XPathFactory xpathFactory = XPathFactory.newInstance();
	            XPath xpath = xpathFactory.newXPath();
	            XPathExpression expr = xpath.compile("//svcList/svc");
	            NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
	            System.out.println(nodeList.getLength());				//30개 잘 뽑히는데???
	            String serviceid[]=new String[nodeList.getLength()];
	            for (int i = 0; i < nodeList.getLength(); i++) {
	                NodeList child = nodeList.item(i).getChildNodes();
	                for (int j = 0; j < child.getLength(); j++) {
	                    Node node = child.item(j);
	                    if ((node.getNodeName().toString()).equals("svcId")) {
	                    System.out.println("현재 노드 이름 : " + node.getNodeName());
	                    System.out.println("현재 노드 값 : " + node.getTextContent());
	                    serviceid[count++]=node.getTextContent();
	                    System.out.println("");}                   
	                }
	            }
	            
	return serviceid;
	}
		
	public void getSvc(String serivceid, BufferedWriter writer_final) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException{
	        StringBuilder urlBuilder = new StringBuilder("http://api.korea.go.kr/openapi/svc"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=QW3TWyAiMqk%2F2%2BmuUIqvAAEh%2FQKwrtrEeYeBRrdgjF1ewTMm%2F%2FmdeRMlXiDpfWWOG7UkRk5v8xHRPN9JpkeB2g%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("format","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*xml, html*/
	        urlBuilder.append("&" + URLEncoder.encode("svcId","UTF-8") + "=" + URLEncoder.encode(serivceid, "UTF-8")); /*공공서비스 ID*/
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        //System.out.println(sb.toString());
	        
	        String []service=new String[10];
	        int count=0;
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        factory.setNamespaceAware(true);
	        Document doc = null;
	        InputSource is = new InputSource(new StringReader(sb.toString()));
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        doc = builder.parse(is);
            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            XPathExpression expr = xpath.compile("//svc");
            NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            System.out.println(nodeList.getLength());				//30개 잘 뽑히는데???
            String serviceid[]=new String[nodeList.getLength()];
            for (int i = 0; i < nodeList.getLength(); i++) {
                NodeList child = nodeList.item(i).getChildNodes();
                for (int j = 0; j < 10; j++) {
                    Node node = child.item(j);
                    service[count++] = node.getTextContent();}                   
                }
            
	       for (int j = 0; j<10; j++) {
	    	   writer_final.write(service[j].toString());
	    	   if (j!=9)
	    	   {writer_final.write(",");}
	       }
	       writer_final.newLine();
	}
}
