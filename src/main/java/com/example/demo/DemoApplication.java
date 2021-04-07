package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        run();
    }
    public static void run(){

        System.out.println("START");
//        main.run();
        try {
            String apiURL = "https://thingplugpf.sktiot.com:9443/ThingPlug?division=user&function=login";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("user_id", "2018wonju");
            con.setRequestProperty("password", "sqisoft74307!");
            int responseCode = con.getResponseCode();
            BufferedReader br;
            System.out.println("ifstart");
            if(responseCode==200) { // 정상 호출
                System.out.println("200");
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            System.out.println("-----------0---------");
            br.close();

            System.out.println("-----------0.1---------");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            System.out.println("-----------0.2---------");
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            System.out.println("-----------0.3---------");
            Document document = documentBuilder.parse(response.toString());
            System.out.println("----------1----------");
            System.out.println("response:"+response.toString());
            System.out.println("----------2----------");
            System.out.println("response:"+document.getDocumentElement().getNodeName());
            System.out.println("----------3----------");
//        System.out.println(document.getAttributes("user_id"));

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
