package imacstadium.imac.type;

public class Prof extends Type {

	public Prof() {
		super("Prof");

		this.bonus.put("Dormeur",(float)5);
		this.bonus.put("Grincheux",(float)10);
		this.bonus.put("Joyeux",(float)15);
		this.bonus.put("Simplet",(float)10);
	}

}
