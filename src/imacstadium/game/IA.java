package imacstadium.game;

public class IA extends Trainer {

	public IA() {
		super();
	}
	public IA(String name) {
		super(name);
	}
	@Override
	public void play(Trainer opponent){
		int idAttack = (int)(Math.random()*3);
		this.imacAttack(opponent, idAttack);
	}
	@Override
	public boolean defeated() {
		if( validImacs.size() <= 0){
			//System.out.println("------------- "+this.name+" DEFEAT -------------");
			this.changeImac();
			//System.exit(0);
			return true;
		}
		return false;
	}
	
	@Override
	public void changeImac() {
		Game.getInstance().distributeImac(this);
		this.notify("changeImac");
	}
	
	
	
}
