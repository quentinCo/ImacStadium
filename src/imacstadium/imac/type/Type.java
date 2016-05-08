package imacstadium.imac.type;

import java.util.HashMap;
import java.util.Map;

public class Type {
	
	/**
     * The name of the Type, name is not mutable.
     * 
     * @see Type#getName()
     * @see Type#Type(String, Map<String, Float>)
     */
	protected final String name;
	
	/**
     * The bonus of the Type, bonus is not mutable.
     * 
     * @see Type#getBonus()
     * @see Type#Type(String, Map<String, Float>)
     */
	protected final Map<String, Float> bonus;

	
	
	/***********
	 * GETTERS *
	 ***********/

	/**
	 * Return the name of the Type.
	 * 
	 * @return	A String instance, corresponding to the name of the Type.
	 *
	 */
	public String getName() {
		return name;
	}

	/**
	 * Return the bonus Map of the Type.
	 * 
	 * @return	A Map<String, Float> instance, corresponding to the bonus or malus of the Type.
	 *
	 */
	public Map<String, Float> getBonus() {
		return bonus;
	}

	
	
	
	/****************
	 * CONSTRUCTORS *
	 ****************/

	/**
	 * Type constructor
	 * 
	 * @param name
	 * 		The name of the Type.
	 * @param bonus
	 * 		The bonus Map of the Type.
	 * 
	 * @see Type#name
	 * @see Type#bonus
	 */
	public Type(String name, Map<String, Float> bonus) {
		this.name = name;
		this.bonus = bonus;
	}
	
	/**
	 * Type constructor
	 * 
	 * @param name
	 * 		The name of the Type.
	 * 
	 * @see Type#name
	 * @see Type#bonus
	 */
	public Type(String name) {
		this.name = name;
		this.bonus = new HashMap <String, Float>();
	}

	/**
	 * Type constructor
	 * 
	 * @see Type#name
	 * @see Type#bonus
	 */
	public Type() {
		this.name = "Default";
		this.bonus = new HashMap <String, Float>();
	}
	

	/***********
	 * ACTIONS *
	 ***********/
	
	/**
	 * Return the effect of the attack according to the opponent's type.
	 * 
	 * @param opponentType
	 * 		The type name of the opponent Imac.
	 * 
	 * @return A Float instance, corresponding to the bonus or the malus of the attack according to the opponent's type.
	 *
	 * @see Type#Type(String, Map)
	 */
	public float effect(String opponentType){
		if (bonus.containsKey(opponentType)){
			return bonus.get(opponentType);
		}
		else {
			return 0;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bonus == null) ? 0 : bonus.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Type other = (Type) obj;
		if (bonus == null) {
			if (other.bonus != null)
				return false;
		} else if (!bonus.equals(other.bonus))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Type =" + name;
	}

}
