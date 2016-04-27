package imacstadium.parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import imacstadium.game.Game;
import imacstadium.game.Trainer;
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
		//int level = Integer.parseInt(obj.getString("level"));
		float precision = Float.parseFloat(obj.getString("precision"));
		
		Attack[] attacks = new Attack[4];
		JsonArray jA = obj.getJsonArray("attacks");

		int i = 0;
		for(JsonObject jO : jA.getValuesAs(JsonObject.class)){
			attacks[i++] = this.newAttack(jO);
		}
		
		return new Imac(id, name, type, /*level*/ 1, attacks, life, precision, phrase);
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------NEW ATTACK-------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/	
	private Attack newAttack(JsonObject obj){

		String name = obj.getString("name");
		
		Type type;
		try{
			switch(obj.getString("type")){
				case "Type 1":
					type = new Type();
					break;
				default :
					type = new Type();
					break;
			}
		}
		catch(NullPointerException e){
			type = null;
		}
		
		
		float power = Float.parseFloat(obj.getString("power"));
		
		return new Attack(name, type, power);
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
