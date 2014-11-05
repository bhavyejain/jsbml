package org.sbml.jsbml.ext.dyn;

import java.text.MessageFormat;
import java.util.Map;
import javax.swing.tree.TreeNode;
import org.sbml.jsbml.SBase;
import org.sbml.jsbml.ext.AbstractSBasePlugin;
import org.sbml.jsbml.ext.dyn.CBO.Term;

/**
 * @author Harold Gomez
 * @since 1.0
 */
public class DynSBasePlugin extends AbstractSBasePlugin {

	/**
	 * Generated serial version identifier
	 */
	private static final long serialVersionUID = 3120251908117414039L;

	/**
	 * Cell Behavior Ontology Term associated with SBase object
	 * */
	private Term cboTerm;

	/**
	 * Empty constructor
	 * */
	public DynSBasePlugin() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param plugin
	 */
	public DynSBasePlugin(DynSBasePlugin plugin) {
		super(plugin);
		if (isSetCBOTerm()) {
			setCBOTerm(plugin.getCBOTerm());
		}
	}

	/**
	 * Constructor
	 * 
	 * @param extendedSBase
	 */
	public DynSBasePlugin(SBase extendedSBase) {
		super(extendedSBase);
	}

	/**
	 * Returns the value of CBO
	 * 
	 * @return the value of CBO
	 */
	public Term getCBOTerm() {
		if (isSetCBOTerm()) {
			return cboTerm;
		}
		return null;
	}

	/**
	 * Returns whether CBO is set
	 * 
	 * @return whether CBO is set
	 */
	public boolean isSetCBOTerm() {
		return this.cboTerm != null;
	}

	/**
	 * Sets the value of CBO
	 */
	public void setCBOTerm(Term cboTerm) {
		Term oldCboTerm = this.cboTerm;
		this.cboTerm = cboTerm;
		firePropertyChange(DynConstants.cboTerm, oldCboTerm, this.cboTerm);
	}

	/**
	 * Unsets the cboTerm field
	 * 
	 * @return {@code true}, if cboTerm was set before, otherwise {@code false}
	 */
	public boolean unsetCBOTerm() {
		if (isSetCBOTerm()) {
			Term oldCboTerm = this.cboTerm;
			this.cboTerm = null;
			firePropertyChange(DynConstants.cboTerm, oldCboTerm, this.cboTerm);
			return true;
		}
		return false;
	}

	@Override
	public SBase getParent() {
		if (isSetExtendedSBase()) {
			return (SBase) getExtendedSBase().getParent();
		}

		return null;
	}

	@Override
	public SBase getParentSBMLObject() {
		return getParent();
	}

	@Override
	public String getElementNamespace() {
		return DynConstants.getNamespaceURI(getLevel(), getVersion());
	}

	@Override
	public String getPackageName() {
		return DynConstants.packageName;
	}

	@Override
	public String getPrefix() {
		return DynConstants.shortLabel;
	}

	@Override
	public String getURI() {
		return getElementNamespace();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (!super.equals(object)) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}
		DynSBasePlugin other = (DynSBasePlugin) object;
		if (!cboTerm.getId().equals(other.cboTerm)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 4289;
		int hashCode = super.hashCode();
		if (isSetCBOTerm()) {
			hashCode += prime * cboTerm.hashCode();
		}
		return hashCode;
	}

	@Override
	public boolean readAttribute(String attributeName, String prefix,
			String value) {
		boolean isAttributeRead = false;
		if (attributeName.equals(DynConstants.cboTerm)) {
			try {
				setCBOTerm(CBO.getTerm(value));
				isAttributeRead = true;
			} catch (Exception e) {
				MessageFormat.format(
						DynConstants.bundle.getString("COULD_NOT_READ_CBO"), value,
						DynConstants.cboTerm);
			}

		}
		return isAttributeRead;
	}

	@Override
	public DynSBasePlugin clone() {
		return new DynSBasePlugin(this);
	}

	@Override
	public String toString() {
		return "DynSBasePlugin [cboTerm=" + cboTerm + "]";
	}

	@Override
	public Map<String, String> writeXMLAttributes() {
		Map<String, String> attributes = super.writeXMLAttributes();
		if (isSetCBOTerm()) {
			attributes.put(DynConstants.shortLabel + ":cboTerm",
					cboTerm.getId());

		}
		return attributes;
	}

	@Override
	public boolean getAllowsChildren() {
		return false;
	}

	@Override
	public TreeNode getChildAt(int arg0) {
		return null;
	}

	@Override
	public int getChildCount() {
		return 0;
	}

}
