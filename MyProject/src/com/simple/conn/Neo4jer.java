package com.simple.conn;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.neo4j.driver.v1.*;

public class Neo4jer {
    static String HOST="localhost";
    //static String HOST="10.22.217.84";
    static String USER="neo4j";
    static String PASS="admin";
    private Driver createDrive(){
        return GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic( "neo4j", "admin" ) );
    }
    public String SelAll(){
        String query = "MATCH (a)-[r]-(b) RETURN a,r,b";
        String stringConnection = "bolt://" + HOST + ":7687";
        Driver driver = createDrive();
        //Driver driver = GraphDatabase.driver(stringConnection, AuthTokens.basic(USER, PASS));
        Session session = driver.session();
        StatementResult result = session.run(query);
        session.close();
        driver.close();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        JSONArray ja = new JSONArray();
        String stringResult="";
        while (result.hasNext()) {
            Record record = result.next();
            ja.add(gson.toJson(record.asMap()));
            //stringResult = gson.toJson(record.asMap());
        }
        //JSONObject parse = JSONObject.fromObject(stringResult);
        return ja.toString();

        //return JSONObject.fromObject(gson.toJson(result.next().asMap())).toString();
//        String stringResult="";
//        while (result.hasNext()) {
//            Record record = result.next();
//            stringResult = gson.toJson(record.asMap());
//        }
//        JSONObject jsonResult = new JSONObject();
//        JSONObject parse = JSONObject.fromObject(stringResult);
//        System.out.println(parse.toString());
    }
}
