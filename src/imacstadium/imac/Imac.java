package imacstadium.imac;

public class Imac {

	private float life;
	private String catchPhrase;
	private int level;
	private float precision;
	private boolean alive;
	public Imac(float life, String catchPhrase, int level, float precision, boolean alive) {
		super();
		this.life = life;
		this.catchPhrase = catchPhrase;
		this.level = level;
		this.precision = precision;
		this.alive = alive;
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
		return (float)15.6;
	}
}
