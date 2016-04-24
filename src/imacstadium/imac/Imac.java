package imacstadium.imac;

public class Imac {

	private float life;
	private int attack[] = {1,4,3,9};
	private String catchPhrase;
	private int level;
	private float precision;
	private boolean alive;
	
	public Imac(float life, String catchPhrase, int level, float precision) {
		super();
		this.life = life;
		this.catchPhrase = catchPhrase;
		this.level = level;
		this.precision = precision;
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
		return attack[idAttack];
	}
	
	/* Dream Team Functions */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (alive ? 1231 : 1237);
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
		return "Imac [\n\tlife=" + life + "\n\tcatchPhrase=" + catchPhrase + "\n\tlevel=" + level + "\n\tprecision=" + precision
				+ "\n\talive=" + alive + "\n]";
	}
}
