package imacstadium.parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import imacstadium.imac.Attack;
import imacstadium.imac.Imac;
import imacstadium.imac.ImacHeader;
import imacstadium.imac.Type;


public class Parser {

	private Path pathFile;
	
	/*-----CONSTRUCTOR-------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * @param path
	 * 	The relative path to the json file.
	 */
	public Parser(String path){ this.pathFile = FileSystems.getDefault().getPath(new File("").getAbsolutePath()+path); }
	/*-----------------------------------------------------------------------------------------------*/
	
	/*-----GETTER------------------------------------------------------------------------------------*/
	/*------------GET PATH FILE----------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Return the path file of the json file.
	 * @return A Path object to the json file.
	 */
	public Path getPathFile(){ return this.pathFile; }
	/*-----------------------------------------------------------------------------------------------*/
	
	/*-----OTHER FUNCTIONS---------------------------------------------------------------------------*/
	/*------------PARSE FILE-------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Generate an ImacHeader ArrayList.
	 * @return An ImacHeader ArrayList generate from the json file.
	 */
	public ArrayList<ImacHeader> parseFile(){
		
		InputStream is = null;
		
		try{
			is = Files.newInputStream(pathFile);
			JsonReader reader = Json.createReader(is);
			JsonObject jO = reader.readObject();
			JsonArray jA = jO.getJsonArray("imacs");
			
			ArrayList<ImacHeader> imacs = new ArrayList<ImacHeader>();
			
			int i = 0;
			
			for(JsonObject object : jA.getValuesAs(JsonObject.class)){
				String type;
				try{ type = object.getString("type"); }
				catch(NullPointerException e){ type = ""; }
				imacs.add(new ImacHeader(i++, object.getString("name"), type));
			}
			
			return imacs;
		}
		catch(IOException e){ System.out.println("ERROR LOAD FILE PARSER\t----> "+this);}
		finally{
			 try {if (is != null) is.close();}
	         catch (IOException e) { e.printStackTrace(); }
		}
		return null;
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------FIND-------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Generate an Imac.
	 * @param id
	 * 	Index of the imac in the json file.
	 * @return An Imac that is at a certain index in json file.
	 */
	public Imac find(int id){
		InputStream is = null;
		
		try{
			is = Files.newInputStream(pathFile);
			JsonReader reader = Json.createReader(is);
			JsonObject jO = reader.readObject();
			JsonArray jA = jO.getJsonArray("imacs");
			
			JsonObject obj = jA.getValuesAs(JsonObject.class).get(id);
			
			return this.newImac(obj, id);
			
		}
		catch(IOException e){ System.out.println("ERROR LOAD FILE PARSER\t----> "+this);}
		finally{
			 try {if (is != null) is.close();}
	         catch (IOException e) { e.printStackTrace(); }
		}
		return null;
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------NEW IMAC---------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	private Imac newImac(JsonObject obj, int id){
		String name = obj.getString("name");
		String type;
		try{ type = obj.getString("type"); }
		catch(NullPointerException e){ type = ""; }
		
		float life = Float.parseFloat(obj.getString("life"));
		String phrase = obj.getString("catchPhrase");
		
		Attack[] attacks = new Attack[4];
		JsonArray jA = obj.getJsonArray("attacks");

		int i = 0;
		for(JsonObject jO : jA.getValuesAs(JsonObject.class)){
			attacks[i++] = this.newAttack(jO);
		}

		return new Imac(id, name, type, life, phrase, attacks);
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------NEW ATTACK-------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/	
	private Attack newAttack(JsonObject obj){

		String name = obj.getString("name");
		
		
		/*
		 * Temp 
		 */
		
		Map<String, Float> bonus = new HashMap <String, Float>();
		bonus.put("Type 1", (float)10);
		bonus.put("Type 2", (float)-10);
		
		Type type;
		try{
			switch(obj.getString("type")){
				case "Type 1":
					type = new Type("Type 1",bonus);
					break;
				default :
					type = new Type("Type 1",bonus);
					break;
			}
		}
		catch(NullPointerException e){
			type = new Type("Default",bonus);
		}
		
		
		float power = Float.parseFloat(obj.getString("power"));
		
		return new Attack(power, name, type);
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	@Override
	public String toString(){
		return "Parser : [ pathFile = "+this.pathFile+" ]";
	}
	/*
	public static void main(String[] args) {
		String path = "/data/setting/Imac_List.json";
		Parser parse = new Parser(path);
		
		System.out.println(parse);
		ArrayList<ImacHeader> imacs = parse.parseFile();
		System.out.println(imacs);
		
		System.out.println(parse.find(1));
		
	}
	*/
}
