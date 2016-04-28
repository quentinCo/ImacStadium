package imacstadium.imac;

abstract class Attack {
	
	/**
     * The unique identifier of the Attack, id is not mutable.
     * 
     * @see Attack#getId()
     * @see Attack#Attack(int, float, String, Type)
     */
	private final int id;
	
	/**
     * The power of the Attack.
     * 
     * @see Attack#getPower()
     * @see Attack#Attack(int, float, String, Type)
     */
	private float power;
	
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
	 * Return the id of the Attack.
	 * 
	 * @return	An integer instance, corresponding to the unique identifier of the Attack.
	 *
	 */
	public int getId() {
		return id;
	}

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
	 * @param id
	 * 		The unique identifier of the Attack.
	 * @param power
	 * 		The power of the Attack.
	 * @param name
	 * 		The name of the Attack.
	 * @param typeAttack
	 * 		The type of the Attack.
	 * 
	 * @see Attack#id
	 * @see Attack#power
	 * @see Attack#name
	 * @see Attack#typeAttack
	 */
	public Attack(int id, float power, String name, Type typeAttack) {
		this.id = id;
		this.power = power;
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
	 * @see Type#effect(Type)
	 */
	public float powerAttack(Type opponentType ) {
		return power + typeAttack.effect(opponentType);
	}

}
