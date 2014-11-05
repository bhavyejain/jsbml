package org.sbml.jsbml.ext.dyn;

import java.text.MessageFormat;
import java.util.Map;
import javax.swing.tree.TreeNode;
import org.sbml.jsbml.Event;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.util.StringTools;

/**
 * @author Harold Gomez
 * @since 1.0
 */
public class DynEventPlugin extends DynSBasePlugin {

	/**
	 * Generated serial version identifier.
	 */
	private static final long serialVersionUID = 3183158090508499169L;

	/**
	 * Indicates whether all model components are affected by the dynEvent
	 */
	private Boolean applyToAll;

	/**
	 * List of model components affected by the dynEvent
	 */
	protected ListOf<DynElement> listOfDynElements;

	/**
	 * Empty constructor
	 */
	public DynEventPlugin() {
		super();
		initDefaults();
	}

	/**
	 * Constructor
	 * 
	 * @param event
	 */
	public DynEventPlugin(Event event) {
		super(event);
		initDefaults();
	}

	/**
	 * Constructor
	 * 
	 * @param dynEventPlugin
	 */
	public DynEventPlugin(DynEventPlugin dynEventPlugin) {
		super(dynEventPlugin);
		if (isSetListOfDynElements()) {
			setListOfDynElements(dynEventPlugin.getListOfDynElements().clone());
		}
		if (dynEventPlugin.isSetApplyToAll()) {
			setApplyToAll(new Boolean(dynEventPlugin.getApplyToAll()));
		}
	}

	/**
	 * Initializes custom Class attributes
	 * */
	private void initDefaults() {
		listOfDynElements = new ListOf<DynElement>();
		applyToAll = false;
		listOfDynElements.setNamespace(DynConstants.namespaceURI);
		listOfDynElements.setSBaseListType(ListOf.Type.other);

		if (isSetExtendedSBase()) {
			extendedSBase.registerChild(listOfDynElements);
		}
	}

	/**
	 * @return applyToAll
	 */
	public boolean isApplyToAll() {
		return getApplyToAll();
	}

	/**
	 * Returns the value of applyToAll
	 * 
	 * @return the value of applyToAll
	 */
	public boolean getApplyToAll() {
		if (isSetApplyToAll()) {
			return applyToAll;
		}
		return false;
	}

	/**
	 * Returns whether applyToAll is set
	 * 
	 * @return whether applyToAll is set
	 */
	public boolean isSetApplyToAll() {
		return this.applyToAll != false;
	}

	/**
	 * Sets the value of applyToAll
	 */
	public void setApplyToAll(boolean applyToAll) {
		boolean oldApplyToAll = this.applyToAll;
		this.applyToAll = applyToAll;
		firePropertyChange(DynConstants.applyToAll, oldApplyToAll,
				this.applyToAll);
	}

	/**
	 * Unsets the applyToAll field
	 * 
	 * @return {@code true}, if applyToAll was set before, otherwise
	 *         {@code false}
	 */
	public boolean unsetApplyToAll() {
		if (isSetApplyToAll()) {
			boolean oldApplyToAll = this.applyToAll;
			this.applyToAll = false;
			firePropertyChange(DynConstants.applyToAll, oldApplyToAll,
					this.applyToAll);
			return true;
		}
		return false;
	}

	/**
	 * Returns the value of listOfDynElements
	 * 
	 * @return the value of listOfDynElements
	 */
	public ListOf<DynElement> getListOfDynElements() {
		if (!isSetListOfDynElements()) {
			listOfDynElements = new ListOf<DynElement>();
			listOfDynElements.setNamespace(DynConstants.namespaceURI);
			listOfDynElements.setSBaseListType(ListOf.Type.other);
			extendedSBase.registerChild(listOfDynElements);
		}
		return listOfDynElements;
	}

	/**
	 * Returns whether listOfDynElements is set
	 * 
	 * @return whether listOfDynElements is set
	 */
	public boolean isSetListOfDynElements() {
		if ((listOfDynElements == null) || listOfDynElements.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * Sets the value of listOfDynElements
	 */
	public void setListOfDynElements(ListOf<DynElement> listOfDynElements) {
		unsetListOfDynElements();
		if (!isSetListOfDynElements()) {
			this.listOfDynElements = new ListOf<DynElement>();
		} else {
			this.listOfDynElements = listOfDynElements;
		}
		if ((this.listOfDynElements != null)
				&& (this.listOfDynElements.getSBaseListType() != ListOf.Type.other)) {
			this.listOfDynElements.setSBaseListType(ListOf.Type.other);
		}
		if (isSetExtendedSBase()) {
			extendedSBase.registerChild(listOfDynElements);
		}
	}

	/**
	 * Unsets the variable listOfDynElements
	 * 
	 * @return {@code true}, if listOfDynElements was set before, otherwise
	 *         {@code false}
	 */
	public boolean unsetListOfDynElements() {
		if (isSetListOfDynElements()) {
			ListOf<DynElement> oldListOfDynElements = this.listOfDynElements;
			this.listOfDynElements = null;
			firePropertyChange(DynConstants.listOfDynElements,
					oldListOfDynElements, this.listOfDynElements);
			return true;
		}
		return false;
	}

	/**
	 * Creates a new DynElement element and adds it to the listOfDynElements
	 * list
	 */
	public DynElement createDynElement() {
		return createDynElement(null);
	}

	/**
	 * Creates a new {@link DynElement} element and adds it to the
	 * ListOfDynElements list
	 *
	 * @return a new {@link DynElement} element
	 */
	public DynElement createDynElement(String id) {
		DynElement dynElement = new DynElement(id, null, getLevel(), getVersion());
		addDynElement(dynElement);
		return dynElement;
	}

	/**
	 * Adds a new {@link DynElement} to the listOfDynElements. The
	 * listOfDynElements is initialized if necessary.
	 *
	 * @param dynElement
	 *            the element to add to the list
	 * @return {@code true} (as specified by {@link Collection.add})
	 */
	public boolean addDynElement(DynElement dynElement) {
		return getListOfDynElements().add(dynElement);
	}

	/**
	 * Removes the ith element from the listOfDynElements
	 * 
	 * @param i
	 */
	public void removeDynElement(int i) {
		if (!isSetListOfDynElements()) {
			throw new IndexOutOfBoundsException(Integer.toString(i));
		}
		getListOfDynElements().remove(i);
	}

	/**
	 * Removes an element from the listOfDynElements
	 *
	 * @param dynElement
	 *            the element to be removed from the list
	 * @return {@code true} if the list contained the specified element
	 * @see List#remove(Object)
	 */
	public boolean removeDynElement(DynElement dynElement) {
		if (isSetListOfDynElements()) {
			return getListOfDynElements().remove(dynElement);
		}
		return false;
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		if (childIndex < 0 || childIndex >= 1) {
			throw new IndexOutOfBoundsException(MessageFormat.format(
					"Index {0,number,integer} >= {1,number,integer}",
					childIndex, +Math.min(getChildCount(), 0)));
		}

		return listOfDynElements;
	}

	@Override
	public int getChildCount() {
		return isSetListOfDynElements() ? 1 : 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DynEventPlugin other = (DynEventPlugin) obj;
		if (applyToAll != other.applyToAll) {
			return false;
		}
		if (listOfDynElements == null) {
			if (other.listOfDynElements != null) {
				return false;
			}
		} else if (!listOfDynElements.equals(other.listOfDynElements)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 3371;
		int hashCode = super.hashCode();
		hashCode = prime
				* hashCode
				+ ((listOfDynElements == null) ? 0 : listOfDynElements
						.hashCode());
		if (isSetApplyToAll()) {
			hashCode += prime * applyToAll.hashCode();
		}
		return hashCode;
	}

	@Override
	public DynEventPlugin clone() {
		return new DynEventPlugin(this);
	}

	@Override
	public String toString() {
		return "DynEventPlugin [listOfDynElements=" + listOfDynElements
				+ ", applyToAll=" + applyToAll + "]";
	}

	@Override
	public Map<String, String> writeXMLAttributes() {
		Map<String, String> attributes = super.writeXMLAttributes();
		if (isSetApplyToAll()) {
			attributes.put(DynConstants.shortLabel + ":applyToAll",
					String.valueOf(applyToAll));
		}
		return attributes;
	}

	@Override
	public boolean readAttribute(String attributeName, String prefix,
			String value) {
		boolean isAttributeRead = false;
		if (attributeName.equals(DynConstants.applyToAll)) {
			try {
				setApplyToAll(StringTools.parseSBMLBoolean(value));
				isAttributeRead = true;
			} catch (Exception e) {
				  MessageFormat.format(
				            DynConstants.bundle.getString("COULD_NOT_READ_EVENT"), value,
				            DynConstants.applyToAll);
			}

		}
		return isAttributeRead;
	}

}
