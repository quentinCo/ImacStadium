package imacstadium.imac;

import java.util.Map;

abstract class Type {
	
	/**
     * The name of the Type, name is not mutable.
     * 
     * @see Type#getName()
     * @see Type#Type(String, Map<String, Float>)
     */
	private final String name;
	
	/**
     * The bonus of the Type, bonus is not mutable.
     * 
     * @see Type#getBonus()
     * @see Type#Type(String, Map<String, Float>)
     */
	private final Map<String, Float> bonus;

	
	
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

	

	/***********
	 * ACTIONS *
	 ***********/
	
	/**
	 * Return the effect of the attack according to the opponent's type.
	 * 
	 * @param opponentType
	 * 		The type of the opponent Imac.
	 * 
	 * @return A Float instance, corresponding to the bonus or the malus of the attack according to the opponent's type.
	 *
	 * @see Type#Type(String, Map)
	 */
	public float effect(Type opponentType){
		if (bonus.containsKey(opponentType.name)){
			return bonus.get(opponentType.name);
		}
		else {
			return 0;
		}
	}

	@Override
	public String toString() {
		return "Type =" + name;
	}

}
