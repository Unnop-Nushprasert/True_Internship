package org.acme;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.agroal.api.AgroalDataSource;
import io.quarkus.agroal.DataSource;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class CustomerService {

	@Inject
    ResultSetConverter converter;
	
	@Inject
    @DataSource("users")
    AgroalDataSource dataSource;
	
    public JsonArray getCustomerList()  {
    	try {
			Connection conn;
			conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from test.customer");
			JsonArray json = converter.convert(rs);
			stmt.close();
			conn.close();
			return json;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public JsonArray addOrder(JsonArray json)  {
    	try {
			Connection conn;
			conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			JsonObject obj = json.getJsonObject(0);
			String newrecord = obj.getInteger("CustomerID") + ", '" + obj.getString("ProductName") + "', " + obj.getInteger("Quantity");  
			stmt.executeUpdate("INSERT INTO test.order (CustomerID,ProductName,Quantity) " + "VALUES (" + newrecord +  ")");
			ResultSet rs=stmt.executeQuery("select * from test.order");
			JsonArray newjson = converter.convert(rs);
			stmt.close();
			conn.close();
			return newjson;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    }
    
 

}