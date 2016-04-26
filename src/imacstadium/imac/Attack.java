package imacstadium.imac;

import java.util.Map;

public class Attack {
	private String name;
	private Type type;
	private Map<String, Float> power;
	
	
	public Attack(String name, Type type, Map<String, Float> power){
		this.name = name;
		this.type = type;
		this.power = power;
	}


	@Override
	public String toString() {
		return "Attack [name=" + name + ", type=" + type + ", power=" + power + "]";
	}
	
}
