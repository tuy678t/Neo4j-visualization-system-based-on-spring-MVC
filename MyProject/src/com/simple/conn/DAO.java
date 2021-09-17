package com.simple.conn;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.json.JSONArray;
import org.neo4j.driver.v1.*;
import org.springframework.stereotype.Repository;

import static org.neo4j.driver.v1.Values.parameters;

@Repository
public class DAO {
    static String HOST="localhost";
    //static String HOST="10.22.217.84";
    static String USER="neo4j";
    static String PASS="admin";

    private Driver createDrive(){
        return GraphDatabase.driver("bolt://" + HOST + ":7687", AuthTokens.basic( USER, PASS ) );
    }

    public String QueryAll(){
        String querystr = "MATCH (a)-[r]-(b) RETURN a,r,b";
        return query(querystr).toString();
    }


    public String CreateOne(){
        Driver driver = createDrive();
        Session session = driver.session();
        session.run( "CREATE (a:Test {attr1: {attr1}, attr2: {attr2}})",
                parameters( "attr1", "test1", "attr2", "test2" ) );
        session.close();
        driver.close();
        return "Successful Create";
    }

    public String DeleteOne(){
        Driver driver = createDrive();
        Session session = driver.session();
        session.run( "Match (a:Test {attr1: {attr1}}) Delete a",
                parameters( "attr1", "test1") );
        session.close();
        driver.close();
        return "Successful Delete";
    }

    public String UpdateOne(){
        Driver driver = createDrive();
        Session session = driver.session();
        session.run( "Match (a:Test {attr2: {attr2}}) Set a.attr2={attr3}",
                parameters( "attr2", "test2","attr3","test3") );
        session.close();
        driver.close();
        return "Successful Update";
    }


    private JSONArray query(String cypher){
        JSONArray ja = new JSONArray();
        Driver driver = createDrive();
        Session session = driver.session();
        StatementResult result = session.run(cypher);
        session.close();
        driver.close();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        while (result.hasNext()) {
            Record record = result.next();
            ja.add(gson.toJson(record.asMap()));
        }
        return ja;
    }

}
