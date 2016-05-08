package imacstadium.imac.type;

public class Dormeur extends Type {

	public Dormeur() {
		super("Dormeur");

		this.bonus.put("Prof",(float)15);
		this.bonus.put("Grincheux",(float)15);
		this.bonus.put("Joyeux",(float)5);
	}

}
