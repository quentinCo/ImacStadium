package imacstadium.imac.type;

public class Joyeux extends Type {

	public Joyeux() {
		super("Joyeux");

		this.bonus.put("Prof",(float)10);
		this.bonus.put("Grincheux",(float)10);
		this.bonus.put("Timide",(float)-5);
	}

}
