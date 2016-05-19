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

import imacstadium.imac.Attack;
import imacstadium.imac.Imac;
import imacstadium.imac.ImacHeader;
import imacstadium.imac.type.Atchoum;
import imacstadium.imac.type.Dormeur;
import imacstadium.imac.type.Grincheux;
import imacstadium.imac.type.Joyeux;
import imacstadium.imac.type.Prof;
import imacstadium.imac.type.Simplet;
import imacstadium.imac.type.Timide;
import imacstadium.imac.type.Type;


public class Parser {

	private Path pathFile;
	private String absolutePath;
	
	/*-----CONSTRUCTOR-------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * @param path
	 * 	The relative path to the json file.
	 */
	public Parser(String path){
		this.absolutePath = new File("").getAbsolutePath();
		this.pathFile = FileSystems.getDefault().getPath(this.absolutePath + path);
	}
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
		
		String url_img = this.absolutePath + "/data/picture/imacs/";
		try{ url_img += obj.getString("image"); }
		catch(NullPointerException e){ url_img += "default.png"; }
		
		float precision;
		try{ precision = Float.parseFloat(obj.getString("precision")); }
		catch(NullPointerException e){ precision = 1; }
		
		float life = Float.parseFloat(obj.getString("life"));
		String phrase = obj.getString("catchPhrase");
		
		Attack[] attacks = new Attack[4];
		JsonArray jA = obj.getJsonArray("attacks");

		int i = 0;
		for(JsonObject jO : jA.getValuesAs(JsonObject.class)){
			attacks[i++] = this.newAttack(jO);
		}

		return new Imac(id, name, type, life, url_img, phrase, attacks, precision);
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------NEW ATTACK-------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/	
	private Attack newAttack(JsonObject obj){

		String name = obj.getString("name");
		
		float downPrecision;
		try{ downPrecision = Float.parseFloat(obj.getString("downPrecision")); }
		catch(NullPointerException e){ downPrecision = 0; }
		
		Type type;
		try{
			switch(obj.getString("type")){
				case "Atcoum":
					type = new Atchoum();
					break;
				case "Prof":
					type = new Prof();
					break;
				case "Dormeur":
					type = new Dormeur();
					break;
				case "Grincheux":
					type = new Grincheux();
					break;
				case "Joyeux":
					type = new Joyeux();
					break;
				case "Timide":
					type = new Timide();
					break;
				case "Simplet":
					type = new Simplet();
					break;
				default :
					type = new Type();
					break;
			}
		}
		catch(NullPointerException e){
			type = new Type();
		}
		
		
		float power = Float.parseFloat(obj.getString("power"));
		return new Attack(power, downPrecision, name, type);
		//return new Attack(power, name, type);
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	@Override
	public String toString(){
		return "Parser : [ pathFile = "+this.pathFile+" ]";
	}
}
