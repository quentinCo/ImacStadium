package imacstadium.imac;

import java.util.Arrays;

import imacstadium.imac.exception.AttackFailException;
import imacstadium.imac.type.Type;

public class Imac extends ImacHeader {

	/**
     * The status of the Imac life. True by default.
     * 
     * @see Imac#getAlive()
     */
	private boolean alive;
	
	/**
     * The precision of the Imac.
     * 
     * @see Imac#getPrecision()
     */
	private float precision;
	
	/**
     * The life of the Imac.
     * 
     * @see Imac#getLife()
     */
	private float life;
	
	/**
     * The life totalt of the Imac.
     * 
     * @see Imac#getLifeTotal()
     */
	private final float lifeTotal;
	
	/**
     * The illustation of Imac
     * 
     * @see Imac#getUrlImg()
     */
	private final String url_img;
	
	/**
     * The catch phrase of the Imac.
     * 
     * @see Imac#getCatchPhrase()
     */
	private final String catchPhrase;
	
	/**
     * The id's attack table of the Imac.
     * 
     * @see Imac#getAttacks()
     */
	private final Attack[] attacks;

	
	
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
	 * Return the precision of the Imac
	 * 
	 * @return	A float instance, corresponding to the precision of the Imac
	 *
	 */
	public float getPrecision() {
		return precision;
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
	 * Return the life total of the Imac
	 * 
	 * @return	A float instance, corresponding to the total life of the Imac
	 *
	 */
	public float getLifeTotal() {
		return lifeTotal;
	}
	
	/**
	 * Return the url of the imac image
	 * 
	 * @return	A String instance, corresponding to url imac image
	 *
	 */
	public String getUrlImg() {
		return url_img;
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
	public Attack[] getAttacks(){
		return attacks;
	}
	
	/**
	 * Return an attack table of the Imac
	 * @param id
	 * 	The index of the imac's attack.
	 * @return	An Attack table instance, corresponding to the id's attack table of the Imac
	 *
	 */
	public Attack getAttack(int id){
		return attacks[id];
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
	 * @param url_img
	 * 		The url of the imac image.
	 * @param catchPhrase
	 * 		The catch phrase of the Imac.
	 * @param attacks
	 * 		The id's attack table of the Imac.
	 * @param precision
	 * 		The global precision of the imac.
	 * 
	 * @see ImacHeader#id
	 * @see ImacHeader#name
	 * @see ImacHeader#typeImac
	 * @see Imac#life
	 * @see Imac#url_img
	 * @see Imac#catchPhrase
	 * @see Imac#attacks
	 * @see Imac#precision
	 */
	public Imac(int id, String name, String typeImac, float life, String url_img, String catchPhrase, Attack[] attacks, float precision) {
		super(id, name, typeImac);
		this.alive= true;
		this.life = life;
		this.lifeTotal = life;
		this.url_img = url_img;
		this.catchPhrase = catchPhrase;
		this.attacks = attacks;
		this.precision = precision;
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
	 * Return true if the Imac is touched or false if the Imac is not touched.
	 * 
	 * @return A boolean instance, corresponding to the success of the attack according the precision of the Imac.
	 */
	public boolean isTouch(){
		if (Math.random() >= (1 - precision)){
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
		life -= damage;
		if(life < 0) {
			life = 0;
		}
	}

	/**
<<<<<<< HEAD
	 * Return the damage of the chosen attack that will be inflicted and down the opponent Imac's precision. 
=======
	 * Return the damage of the chosen attack that will be inflicted and down the opponent Imac's precisionb. 
>>>>>>> fb05e591feb2182d9df92f62aabe58d7a382b57a
	 * 
	 * @param id
	 * 		The unique identifier of the Imac's attack used.
	 * @param opponentImac
	 * 		The opponent Imac.
	 * 
	 * @return The damage of the chosen attack that will be inflicted to the opponent Imac.
	 * 
	 * @see Type#Type(String, java.util.Map)
	 * @see Imac#isTouch()
	 * @see Attack#getDownPrecision()
	 * @see Attack#powerAttack(String)
	 * @see AttackFailExeception
	 * 
	 * @throws AttackFailExeception
	 * 	Throws an execption if the imac fail its attack.
	 */
	public float attack(int id, Imac opponentImac) throws AttackFailException{
		if (isTouch()){
			opponentImac.downPrecision(attacks[id].getDownPrecision());
			return attacks[id].powerAttack(opponentImac.getTypeImac());
		}
		else throw new AttackFailException();
	}

	
	private void downPrecision(float down){
		this.precision -= down;
		if(this.precision <0.1) this.precision = (float)0.1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (alive ? 1231 : 1237);
		result = prime * result + Arrays.hashCode(attacks);
		result = prime * result + ((catchPhrase == null) ? 0 : catchPhrase.hashCode());
		result = prime * result + Float.floatToIntBits(life);
		result = prime * result + Float.floatToIntBits(lifeTotal);
		result = prime * result + Float.floatToIntBits(precision);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Imac other = (Imac) obj;
		if (alive != other.alive)
			return false;
		if (!Arrays.equals(attacks, other.attacks))
			return false;
		if (catchPhrase == null) {
			if (other.catchPhrase != null)
				return false;
		} else if (!catchPhrase.equals(other.catchPhrase))
			return false;
		if (Float.floatToIntBits(life) != Float.floatToIntBits(other.life))
			return false;
		if (Float.floatToIntBits(lifeTotal) != Float.floatToIntBits(other.lifeTotal))
			return false;
		if (Float.floatToIntBits(precision) != Float.floatToIntBits(other.precision))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Imac [\n"+super.toString()+", alive=" + alive + ", precision=" + precision + ", life=" + life + ", catchPhrase=" + catchPhrase
				+ ", attacks=" + Arrays.toString(attacks) + "\n]";
	}
	
}
