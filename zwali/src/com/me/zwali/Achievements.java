package com.me.zwali;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Achievements 
{
	String[] duBool = new String[26];
	String[] description = new String[26];
	String[] money = new String[26];
	String[] exp = new String[26];
	String username = "";
	
	List<String> achList = new ArrayList<String>(10);
	
	public void populate()
	{
		try {
		      //use buffering, reading one line at a time
		      //FileReader always assumes default encoding is OK!
		      BufferedReader input =  new BufferedReader(new FileReader("src/Tables/achievements"));
		      try {
		        String line = null; //not declared within while loop
		        /*
		        * readLine is a bit quirky :
		        * it returns the content of a line MINUS the newline.
		        * it returns null only for the END of the stream.
		        * it returns an empty String if two newlines appear in a row.
		        */
		        while (( line = input.readLine()) != null)
		        {
		        	achList.add(line);
		        }
		      }
		       catch(Exception ex)
		        {ex.printStackTrace();}
		      finally
				{
					input.close();
				}
		      
		    }
		    catch (IOException ex){
		      ex.printStackTrace();
		    }
	
		
		try {
		      //use buffering, reading one line at a time
		      //FileReader always assumes default encoding is OK!
		      BufferedReader input =  new BufferedReader(new FileReader("res/nickname"));
		      try {
		        
		       this.username = input.readLine();
		      }
		       catch(Exception ex)
		        {ex.printStackTrace();}
		      finally
				{
					input.close();
				}
		      
		    }
		    catch (IOException ex){
		      ex.printStackTrace();
		    }
	}
	
	public void update()
	{
		 for(int i=0;i<achList.size();i++)
	        {
			 String line = achList.get(i);
			 	int contBarra = 0;
	        	char[] strActual = line.toCharArray();
				char[] duBool = new char[5];
				char[] description = new char[100];
				char[] money = new char[3];
				char[] exp = new char[3];
				for(int a=0;a<line.length();a++)
				{
					if(strActual[a] == '/') contBarra++;
					if (contBarra == 0) 
					{
						duBool[a] = strActual[a];	
						this.duBool[i] = duBool.toString();
					}
					else if (contBarra == 1) 
					{
						description[a] = strActual[a];	
						this.description[i] = description.toString();
					}
					else if (contBarra == 2) 
					{
						money[a] = strActual[a];	
						this.money[i] = money.toString();
					}
					else if (contBarra == 3) 
					{
						exp[a] = strActual[a];	
						this.exp[i] = exp.toString();
					}
				}
	        }
	}
	
	public void save()
	{
		
	}
	
	public int getUserMoney()
	{
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			conn = DriverManager.getConnection("jdbc:mysql:///zombocalypsedb", "root", "");
			if (!conn.isClosed())
			{
			Statement s = conn.createStatement();
			s.executeQuery("select money from users where username='"+username+"'");
			ResultSet rs = s.getResultSet();
			while(rs.next())
			{
				String moneydaDB = rs.getString("money");
				return Integer.parseInt(moneydaDB);
			}
			
			s.close();
			}
		}
		catch (Exception e)
		{
			System.err.println("Exception: " + e.getMessage());
		}
		finally
		{
			
			try
			{
				if (conn != null)
				{
					conn.close();
				}
			}
			catch (SQLException e)
			{
 
			}
		}
		return 0;
	}
	
	public int getUserExp()
	{
Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			conn = DriverManager.getConnection("jdbc:mysql:///zombocalypsedb", "root", "");
			if (!conn.isClosed())
			{
			Statement s = conn.createStatement();
			s.executeQuery("select exp from users where username='"+username+"'");
			ResultSet rs = s.getResultSet();
			while(rs.next())
			{
				String moneydaDB = rs.getString("exp");
				return Integer.parseInt(moneydaDB);
			}
			
			s.close();
			}
		}
		catch (Exception e)
		{
			System.err.println("Exception: " + e.getMessage());
		}
		finally
		{
			
			try
			{
				if (conn != null)
				{
					conn.close();
				}
			}
			catch (SQLException e)
			{
 
			}
		}
		return 0;
	}
}
