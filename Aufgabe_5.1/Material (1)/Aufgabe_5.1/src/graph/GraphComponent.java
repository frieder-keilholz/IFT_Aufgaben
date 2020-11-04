package graph;

import java.io.Serializable;

/**
 * An interface defining either a <tt>Vertex</tt> or an <tt>Edge</tt> in
 * a <tt>Graph</tt>.
 */
public interface GraphComponent extends Serializable {
	/**
	 * Set the string describing the GraphComponent
	 * @param text - the string describing the object
	 */
	public void setString(String text);
	/**
	 * @return the string describing the GraphComponent
	 */
	public String toString();
}
