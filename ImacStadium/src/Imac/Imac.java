package Imac;

public class Imac extends ImacHeader {

	/**
     * The status of the Imac life. True by default.
     * 
     * @see Imac#getAlive()
     */
	private boolean alive = true;
	
	/**
     * The life of the Imac.
     * 
     * @see Imac#getLife()
     * @see Imac#Imac(int, String, String, float, String, Attack[])
     */
	private float life;
	
	/**
     * The catch phrase of the Imac.
     * 
     * @see Imac#getCatchPhrase()
     * @see Imac#Imac(int, String, String, float, String, Attack[])
     */
	private final String catchPhrase;
	
	/**
     * The id's attack table of the Imac.
     * 
     * @see Imac#getIdAttack()
     * @see Imac#Imac(int, String, String, float, String, Attack[])
     */
	private final Attack[] idAttack;

	
	
	/***********
	 * GETTERS *
	 ***********/

	/**
	 * Return the life status of the Imac
	 * 
	 * @return	A boolean instance, corresponding to the life status of the Imac
	 *
	 */
	public boolean getAlive() {
		return alive;
	}
	/**
	 * Return the life of the Imac
	 * 
	 * @return	A float instance, corresponding to the life of the Imac
	 *
	 */
	public float getLife() {
		return life;
	}

	/**
	 * Return the catch phrase of the Imac
	 * 
	 * @return	A String instance, corresponding to the catch phrase of the Imac
	 *
	 */
	public String getCatchPhrase() {
		return catchPhrase;
	}
	
	/**
	 * Return id's attack table of the Imac
	 * 
	 * @return	An Attack table instance, corresponding to the id's attack table of the Imac
	 *
	 */
	public Attack[] getIdAttack(){
		return idAttack;
	}
	

	
	/****************
	 * CONSTRUCTORS *
	 ****************/

	/**
	 * Imac constructor
	 * 
	 * @param id
	 * 		The unique identifier of the Imac.
	 * @param name
	 * 		The name of the Imac.
	 * @param typeImac
	 * 		The type of the Imac.
	 * @param life
	 * 		The life of the Imac.
	 * @param catchPhrase
	 * 		The catch phrase of the Imac.
	 * @param idAttack
	 * 		The id's attack table of the Imac.
	 * 
	 * @see ImacHeader#id
	 * @see ImacHeader#name
	 * @see ImacHeader#typeImac
	 * @see Imac#life
	 * @see Imac#catchPhrase
	 * @see Imac#idAttack
	 */
	public Imac(int id, String name, String typeImac, float life, String catchPhrase, Attack[] idAttack) {
		super(id, name, typeImac);
		this.life = life;
		this.catchPhrase = catchPhrase;
		this.idAttack = idAttack;
	}

	
	
	/***********
	 * ACTIONS *
	 ***********/

	
	/**
	 * Return true if the Imac is alive or false if he is dead.
	 * 
	 * @return A boolean instance, corresponding to the status of the Imac's life.
	 */
	public boolean isAlive() {
		if (life > 0) {
			return true;
		}
		else {
			return false;
		}	
	}
	
	/**
	 * Substract the damage to the Imac life.
	 * 
	 * @param damage
	 * 		The attack's power of the opponent Imac.
	 */
	public void damage(float damage) {
		if(life > damage) {
			life -= damage;
		}
		else {
			life = 0;
		}
	}

	/**
	 * 
	 * 
	 * @param id
	 * 		The unique identifier of the Imac attack used.
	 * @param opponentType
	 * 		The type of the opponent Imac.
	 * 
	 * @return The damage of the chosen attack that will be inflicted to the opponent Imac.
	 * 
	 * @see Type#Type(String, java.util.Map)
	 * @see Attack#powerAttack(Type)
	 */
	public float attack(int id, Type opponentType){
		return idAttack[id].powerAttack(opponentType);
	}

}
