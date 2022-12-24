package com.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;

@Service
public class Database
{
	
	@Autowired
	private DatabaseConfigProperties databaseConfigProperties;

	private Connection c;

	public Connection getConnection()
	{
		return c;
	}
	
	public boolean apriConnessione()
	{
		try
		{
			Class.forName(databaseConfigProperties.getDriver());
			
			c = DriverManager.getConnection(
								databaseConfigProperties.getPath(),
			 					databaseConfigProperties.getUsername(),
			  					databaseConfigProperties.getPassword());
			return true;
		} catch (Exception e)
		{
			System.out.println("Controlla:\n-Percorso DB\n-User DB\n-Password DB\n-Connettore");
			e.printStackTrace();
			return false;
		}
	}
	
	public void chiudiConnessione()
	{
		try
		{
			c.close();
		} catch (Exception e)
		{
			System.out.println("Non riesco a chiudere la connessione");
			e.printStackTrace();
		}
	}
	
	
}