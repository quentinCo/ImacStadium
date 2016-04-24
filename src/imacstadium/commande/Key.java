package imacstadium.commande;

public class Key implements Commande {
	private char key;
	
	public Key(char key){
		this.key = key;
	}
	
	public char getKey(){ return key; }

	@Override
	public Object action() {
		return "La touche actioné est " + key;
	}

	@Override
	public String toString() {
		return "Key [key=" + key + "]";
	}
	
}
