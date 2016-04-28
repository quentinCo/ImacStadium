package imacstadium.imac;

import java.util.Arrays;

public class Imac extends ImacHeader{

	private float life;
	private Attack attacks[];
	private String catchPhrase;
	private int level;
	private float precision;
	private boolean alive;
	
	public Imac(int id, String name, String type, int level, Attack[] attacks, float life, float precision, String catchPhrase) {
		super(id, name, type);
		this.life = life;
		this.catchPhrase = catchPhrase;
		this.level = level;
		this.precision = precision;
		this.attacks = attacks;
		this.alive = true;
	}
	
	public float getLife() {
		return life;
	}
	public void setLife(float life) {
		this.life = life;
	}
	public String getCatchPhrase() {
		return catchPhrase;
	}
	public void setCatchPhrase(String catchPhrase) {
		this.catchPhrase = catchPhrase;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public float getPrecision() {
		return precision;
	}
	public void setPrecision(float precision) {
		this.precision = precision;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public void damage(float damage){
		this.life-=damage;
		if(this.life <= 0)this.alive = false;
	}
	
	public float attack(int idAttack, String type){
		return attacks[idAttack].powerAttack(type);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (alive ? 1231 : 1237);
		result = prime * result + Arrays.hashCode(attacks);
		result = prime * result + ((catchPhrase == null) ? 0 : catchPhrase.hashCode());
		result = prime * result + level;
		result = prime * result + Float.floatToIntBits(life);
		result = prime * result + Float.floatToIntBits(precision);
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
		if (level != other.level)
			return false;
		if (Float.floatToIntBits(life) != Float.floatToIntBits(other.life))
			return false;
		if (Float.floatToIntBits(precision) != Float.floatToIntBits(other.precision))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Imac [\n\tlife=" + life + "\n\tattacks=" + Arrays.toString(attacks) + "\n\tcatchPhrase=" + catchPhrase
				+ "\n\tlevel=" + level + "\n\tprecision=" + precision + "\n\talive=" + alive + "\n]";
	}
}
