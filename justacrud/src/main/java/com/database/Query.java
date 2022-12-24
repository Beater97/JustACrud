package com.database;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;



@Service
public class Query
{
	@Autowired
	private Database db;
	@Autowired
	private Gson gson;
	
	public String executeUpdate(String query)
	{	
		Map<String,Boolean> map = new LinkedHashMap<>();
		String response ="";
		boolean result = false;
		try
		{
			db.apriConnessione();
			PreparedStatement ps = db.getConnection().prepareStatement(query);
			ps.executeUpdate();
			result = true;
			map.put("result", result);
		}
		catch (Exception e)
		{
		}
		map.put("result", result);
		response = gson.toJson(map);
		return response;
	}
	
	public String ExecuteQuery(String query)
	{
		ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Map<String, String> riga = new LinkedHashMap<String, String>();
		try
		{
			db.apriConnessione();
			PreparedStatement ps = db.getConnection().prepareStatement(query);
			ResultSet tabella = ps.executeQuery();
			
			while (tabella.next())
			{
					riga = new LinkedHashMap<>();
					for (int i = 0; i < tabella.getMetaData().getColumnCount(); i++)
					{
						riga.put(tabella.getMetaData().getColumnName(i+1),
								tabella.getString(tabella.getMetaData().getColumnName(i+1)));
					}
					result.add(riga);
			}
		} catch (Exception e)
		{
            e.printStackTrace();
		} finally
		{
			db.chiudiConnessione();
		}
		return gson.toJson(result);
	}
	
}
// FINE READ
