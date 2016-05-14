package imacstadium.imac;

public class ImacHeader {
	
	/**
     * The id of the ImacHeader, id is not mutable.
     * 
     * @see ImacHeader#getId()
     * @see ImacHeader#ImacHeader(int, String, String)
     */
	private final int id;
	
	/**
     * The name of the ImacHeader, name is not mutable.
     * 
     * @see ImacHeader#getName()
     * @see ImacHeader#ImacHeader(int, String, String)
     */
	private final String name;
	
	/**
     * The type of the ImacHeader, typeImac is not mutable.
     * 
     * @see ImacHeader#getTypeImac()
     * @see ImacHeader#ImacHeader(int, String, String)
     */
	private final String typeImac;

	
	
	/***********
	 * GETTERS *
	 ***********/
	
	/**
	 * Return the id of the ImacHeader
	 * 
	 * @return	An integer instance, corresponding to the id of the ImacHeader
	 *
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Return the name of the ImacHeader
	 * 
	 * @return	A String instance, corresponding to the name of the ImacHeader
	 *
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Return the type of the ImacHeader
	 * 
	 * @return	A String instance, corresponding to the type of the ImacHeader
	 *
	 */
	public String getTypeImac() {
		return typeImac;
	}
	

	
	/****************
	 * CONSTRUCTORS *
	 ****************/
	
	/**
	 * 	ImacHeader constructor
	 * 
	 * @param id
	 * 		The unique identifier of the ImacHeader.
	 * @param name
	 * 		The name of the ImacHeader.
	 * @param typeImac
	 * 		The type of the ImacHeader.
	 * 
	 * @see ImacHeader#id
	 * @see ImacHeader#name
	 * @see ImacHeader#typeImac
	 */
	public ImacHeader(int id, String name, String typeImac) {
		this.id = id;
		this.name = name;
		this.typeImac = typeImac;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((typeImac == null) ? 0 : typeImac.hashCode());
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
		ImacHeader other = (ImacHeader) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (typeImac == null) {
			if (other.typeImac != null)
				return false;
		} else if (!typeImac.equals(other.typeImac))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ImacHeader [id=" + id + ", name=" + name + ", typeImac=" + typeImac + "]";
	}
	
}
