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
	
	public Parser(String path){ this.pathFile = FileSystems.getDefault().getPath(new File("").getAbsolutePath()+path); }
	
	public Path getPathFile(){ return this.pathFile; }
	
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
				imacs.add(new ImacHeader(object.getString("name"), object.getString("type"), i++));
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
	
	public Imac find(int id){
		InputStream is = null;
		
		try{
			is = Files.newInputStream(pathFile);
			JsonReader reader = Json.createReader(is);
			JsonObject jO = reader.readObject();
			JsonArray jA = jO.getJsonArray("imacs");
			
			ArrayList<ImacHeader> imacs = new ArrayList<ImacHeader>();
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
	
	private Imac newImac(JsonObject obj, int id){
		String name = obj.getString("name");
		String type = obj.getString("type");

		float life = Float.parseFloat(obj.getString("life"));
		String phrase = obj.getString("catchPhrase");
		//int level = Integer.parseInt(obj.getString("level"));
		float precision = Float.parseFloat(obj.getString("precision"));
		
		ArrayList<Attack> attacks = new ArrayList<Attack>();
		JsonArray jA = obj.getJsonArray("attacks");

		for(JsonObject jO : jA.getValuesAs(JsonObject.class)){
			attacks.add(this.newAttack(jO));
		}
		
		return new Imac(name, type, id, life, phrase, /*level*/1, precision /*,attacks*/);
	}
	
	private Attack newAttack(JsonObject obj){

		String name = obj.getString("name");
		
		Type type;
		switch(obj.getString("type")){
			case "Type 1":
				type = new Type();
				break;
			default :
				type = new Type();
				break;
		}
		
		Map<String, Float> power = new HashMap<String, Float>();
		
		JsonArray jA = obj.getJsonArray("power");
		for(JsonObject jO : jA.getValuesAs(JsonObject.class)){
			power.put(jO.getString("Type"), Float.parseFloat(jO.getString("Value")));
		}
		
		return new Attack(name, type, power);
	}
	
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
