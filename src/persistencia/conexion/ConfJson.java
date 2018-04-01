package persistencia.conexion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;

import dto.ConexionDTO;
import util.FileChecker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ConfJson 
{
	private static final String filename = "Servidor.txt";
	private static boolean exists()
	{
		return FileChecker.exists(filename);
	}
	
	public static ConexionDTO readJSON()
	{
		ConexionDTO conexion = null;
		if (!exists())
			return conexion;
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			Type collectionType = new TypeToken<ConexionDTO>(){}.getType();
			conexion = (ConexionDTO) new Gson().fromJson(br, collectionType);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return conexion;
	}
	
	public static boolean writeJSON(ConexionDTO conexion)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(conexion);
		try 
		{
			FileWriter writer = new FileWriter(filename);
			writer.write(json);
			writer.close();
			return true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
}
