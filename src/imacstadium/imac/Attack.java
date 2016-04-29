package imacstadium.imac;

public class Attack {

	/**
     * The power of the Attack.
     * 
     * @see Attack#getPower()
     * @see Attack#Attack(int, float, String, Type)
     */
	private float power;
	
	/**
     * The fall of the Imac's attacks precision.
     * 
     * @see Attack#getDownPrecision()
     * @see Attack#Attack(int, float, float, String, Type)
     */
	private final float downPrecision;
	
	/**
     * The name of the Attack, name is not mutable.
     * 
     * @see Attack#getName()
     * @see Attack#Attack(int, float, String, Type)
     */
	private final String name;
	
	/**
     * The type of the Attack, typeAttack is not mutable.
     * 
     * @see Attack#getTypeAttack()
     * @see Attack#Attack(int, float, String, Type)
     */
	private final Type typeAttack;
	
	
	
	/***********
	 * GETTERS *
	 ***********/

	/**
	 * Return the power of the Attack.
	 * 
	 * @return	A float instance, corresponding to the power of the Attack.
	 *
	 */
	public float getPower() {
		return power;
	}
	
	/**
	 * Return the fall of the Imac's attacks precision.
	 * 
	 * @return	A float instance, corresponding to the fall of the Imac's attacks precision.
	 *
	 */
	public float getDownPrecision() {
		return downPrecision;
	}

	/**
	 * Return the name of the Attack.
	 * 
	 * @return	A String instance, corresponding to the name of the Attack.
	 *
	 */
	public String getName() {
		return name;
	}

	/**
	 * Return the type of the Attack.
	 * 
	 * @return	A Type instance, corresponding to the type of the Attack.
	 *
	 */
	public Type getTypeAttack() {
		return typeAttack;
	}

	
	
	/****************
	 * CONSTRUCTORS *
	 ****************/

	/**
	 * Attack constructor
	 * 
	 * @param power
	 * 		The power of the Attack.
	 * @param downPrecision
	 * 		The fall of the Imac's attacks precision.
	 * @param name
	 * 		The name of the Attack.
	 * @param typeAttack
	 * 		The type of the Attack.
	 * 
	 * @see Attack#power
	 * @see Attack#name
	 * @see Attack#typeAttack
	 */
	public Attack(float power, float downPrecision, String name, Type typeAttack) {
		this.power = power;
		this.downPrecision = downPrecision;
		this.name = name;
		this.typeAttack = typeAttack;
	}



	/***********
	 * ACTIONS *
	 ***********/

	/**
	 * Return the power of the attack according to the opponent's type.
	 * 
	 * @param opponentType
	 * 		The type of the opponent Imac.
	 * 
	 * @return A float instance, corresponding to the power of the attack according to the opponent's type.
	 * 
	 * @see Type#Type(String, java.util.Map)
	 * @see Type#effect(String)
	 */
	public float powerAttack(String opponentType ) {
		return power + typeAttack.effect(opponentType);
	}

}
