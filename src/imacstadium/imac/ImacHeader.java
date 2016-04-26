package imacstadium.imac;

public class ImacHeader {
	protected String name;
	protected String type;
	protected int id;
	
	public ImacHeader(String name, String type, int id){
		this.name = name;
		this.type = type;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ImacHeader [name=" + name + ", type=" + type + ", id=" + id + "]";
	}
	
	
	
}
