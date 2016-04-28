package Imac;

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
	
}
