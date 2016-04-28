package imacstadium.imac;

public class Attack {
	private String name;
	private Type type;
	private float power;
	
	
	public Attack(String name, Type type, float power){
		this.name = name;
		this.type = type;
		this.power = power;
	}
	
	public float powerAttack(String type){
		return this.power;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(power);
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attack other = (Attack) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(power) != Float.floatToIntBits(other.power))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Attack [name=" + name + ", type=" + type + ", power=" + power + "]";
	}
	
}
