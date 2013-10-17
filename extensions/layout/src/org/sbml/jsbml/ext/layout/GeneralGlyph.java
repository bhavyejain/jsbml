/*
 * $Id: ReactionGlyph.java 1464 2013-03-06 10:52:43Z andreas-draeger $
 * $URL: https://jsbml.svn.sourceforge.net/svnroot/jsbml/trunk/extensions/layout/src/org/sbml/jsbml/ext/layout/ReactionGlyph.java $
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 *
 * Copyright (C) 2009-2013 jointly by the following organizations:
 * 1. The University of Tuebingen, Germany
 * 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK
 * 3. The California Institute of Technology, Pasadena, CA, USA
 * 4. The University of California, San Diego, La Jolla, CA, USA
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */
package org.sbml.jsbml.ext.layout;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Map;

import javax.swing.tree.TreeNode;

import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.util.ListOfWithName;

/**
 * @author Nicolas Rodriguez
 * @author Sebastian Fr&ouml;lich
 * @author Andreas Dr&auml;ger
 * @since 1.0
 * @version $Rev: 1464 $
 */
public class GeneralGlyph extends AbstractReferenceGlyph {

	/**
	 * Generated serial version identifier.
	 */
	private static final long serialVersionUID = 8770691813350594995L;

	/**
	 * 
	 */
	private Curve curve;
	
	/**
	 * 
	 */
	private ListOf<ReferenceGlyph> listOfReferenceGlyphs; 

	private ListOf<GraphicalObject> listOfSubGlyphs;
	
	

	/**
	 * 
	 */
	public GeneralGlyph() {
	  super();
	}

	/**
	 * 
	 * @param level
	 * @param version
	 */
	public GeneralGlyph(int level, int version) {
		super(level, version);
	}
	
	/**
	 * 
	 * @param generalGlyph
	 */
	public GeneralGlyph(GeneralGlyph generalGlyph) {
		super(generalGlyph);
		if (generalGlyph.isSetCurve()) {
			this.curve = generalGlyph.getCurve().clone();
		}
		if (generalGlyph.isSetListOfReferenceGlyphs()) {
			this.listOfReferenceGlyphs = generalGlyph
					.getListOfReferenceGlyphs().clone();
		}
		if (generalGlyph.isSetListOfSubGlyphs()) {
			this.listOfSubGlyphs = generalGlyph.getListOfSubGlyphs().clone();
		}
	}
	
	/**
	 * 
	 * @param id
	 */
	public GeneralGlyph(String id) {
		super(id);
	}
	
	/**
	 * 
	 * @param id
	 * @param level
	 * @param version
	 */
	public GeneralGlyph(String id, int level, int version) {
		super(id, level, version);
	}

	
	/**
	 * Returns {@code true}, if listOfSubGlyphs contains at least one element.
	 *
	 * @return {@code true}, if listOfSubGlyphs contains at least one element, 
	 *         otherwise {@code false}
	 */
	public boolean isSetListOfSubGlyphs() {
		if ((listOfSubGlyphs == null) || listOfSubGlyphs.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * Returns the listOfSubGlyphs. Creates it if it is not already existing.
	 *
	 * @return the listOfSubGlyphs
	 */
	public ListOf<GraphicalObject> getListOfSubGlyphs() {
		if (!isSetListOfSubGlyphs()) {
			listOfSubGlyphs = new ListOfWithName<GraphicalObject>(getLevel(), getVersion(), LayoutConstants.listOfSubGlyphs);
			listOfSubGlyphs.addNamespace(LayoutConstants.namespaceURI);
			listOfSubGlyphs.setSBaseListType(ListOf.Type.other);
			registerChild(listOfSubGlyphs);
		}
		return listOfSubGlyphs;
	}

	/**
	 * Sets the given {@code ListOf<GraphicalObject>}. If listOfSubGlyphs
	 * was defined before and contains some elements, they are all unset.
	 *
	 * @param listOfSubGlyphs
	 */
	public void setListOfSubGlyphs(ListOf<GraphicalObject> listOfSubGlyphs) 
	{
		unsetListOfSubGlyphs();
		
		if (listOfSubGlyphs != null) 
		{
			this.listOfSubGlyphs = getListOfSubGlyphs(); // initializing a new ListOfWithName with the proper settings
			this.listOfSubGlyphs.addAll(listOfSubGlyphs);
			registerChild(this.listOfSubGlyphs);
		}
	}

	/**
	 * Returns {@code true}, if listOfSubGlyphs contain at least one element, 
	 *         otherwise {@code false}
	 *
	 * @return {@code true}, if listOfSubGlyphs contain at least one element, 
	 *         otherwise {@code false}
	 */
	public boolean unsetListOfSubGlyphs() {
		if (isSetListOfSubGlyphs()) {
			ListOf<GraphicalObject> oldSubGlyphs = this.listOfSubGlyphs;
			this.listOfSubGlyphs = null;
			oldSubGlyphs.fireNodeRemovedEvent();
			return true;
		}
		return false;
	}

	/**
	 * Adds a new {@link GraphicalObject} to the listOfSubGlyphs.
	 * <p>The listOfSubGlyphs is initialized if necessary.
	 *
	 * @param subGlyph the element to add to the list
	 * @return true (as specified by {@link Collection.add})
	 */
	public boolean addSubGlyph(GraphicalObject subGlyph) {
		return getListOfSubGlyphs().add(subGlyph);
	}

	/**
	 * Removes an element from the listOfSubGlyphs.
	 *
	 * @param subGlyph the element to be removed from the list
	 * @return true if the list contained the specified element
	 * @see List#remove(Object)
	 */
	public boolean removeSubGlyph(GraphicalObject subGlyph) {
		if (isSetListOfSubGlyphs()) {
			return getListOfSubGlyphs().remove(subGlyph);
		}
		return false;
	}

	/**
	 * Removes an element from the listOfSubGlyphs at the given index.
	 *
	 * @param i the index where to remove the {@link SubGlyph}
	 * @throws IndexOutOfBoundsException if the listOf is not set or
	 * if the index is out of bound (index < 0 || index > list.size)
	 */
	public void removeSubGlyph(int i) {
		if (!isSetListOfSubGlyphs()) {
			throw new IndexOutOfBoundsException(Integer.toString(i));
		}
		getListOfSubGlyphs().remove(i);
	}


	/**
	 * Creates a new {@link SpeciesReferenceGlyph} element and adds it to the ListOfSubGlyphs list
	 */
	public SpeciesReferenceGlyph createSpeciesReferenceGlyph() {
		return createSpeciesReferenceGlyph(null);
	}

	/**
	 * Creates a new {@link SpeciesReferenceGlyph} element and adds it to the ListOfSubGlyphs list
	 *
	 * @return a new {@link SpeciesReferenceGlyph} element
	 */
	public SpeciesReferenceGlyph createSpeciesReferenceGlyph(String id) {
		SpeciesReferenceGlyph subGlyph = new SpeciesReferenceGlyph(id, getLevel(), getVersion());
		addSubGlyph(subGlyph);
		return subGlyph;
	}

	/**
	 * Creates a new {@link TextGlyph} element and adds it to the ListOfSubGlyphs list
	 */
	public TextGlyph createTextGlyph() {
		return createTextGlyph(null);
	}

	/**
	 * Creates a new {@link TextGlyph} element and adds it to the ListOfSubGlyphs list
	 *
	 * @return a new {@link TextGlyph} element
	 */
	public TextGlyph createTextGlyph(String id) {
		TextGlyph subGlyph = new TextGlyph(id, getLevel(), getVersion());
		addSubGlyph(subGlyph);
		return subGlyph;
	}


	/**
	 * Creates a new {@link SpeciesGlyph} element and adds it to the ListOfSubGlyphs list
	 */
	public SpeciesGlyph createSpeciesGlyph() {
		return createSpeciesGlyph(null);
	}

	/**
	 * Creates a new {@link SpeciesGlyph} element and adds it to the ListOfSubGlyphs list
	 *
	 * @return a new {@link SpeciesGlyph} element
	 */
	public SpeciesGlyph createSpeciesGlyph(String id) {
		SpeciesGlyph subGlyph = new SpeciesGlyph(id, getLevel(), getVersion());
		addSubGlyph(subGlyph);
		return subGlyph;
	}

	/**
	 * Creates a new {@link CompartmentGlyph} element and adds it to the ListOfSubGlyphs list
	 */
	public CompartmentGlyph createCompartmentGlyph() {
		return createCompartmentGlyph(null);
	}

	/**
	 * Creates a new {@link CompartmentGlyph} element and adds it to the ListOfSubGlyphs list
	 *
	 * @return a new {@link CompartmentGlyph} element
	 */
	public CompartmentGlyph createCompartmentGlyph(String id) {
		CompartmentGlyph subGlyph = new CompartmentGlyph(id, getLevel(), getVersion());
		addSubGlyph(subGlyph);
		return subGlyph;
	}

	/**
	 * Creates a new {@link ReactionGlyph} element and adds it to the ListOfSubGlyphs list
	 */
	public ReactionGlyph createReactionGlyph() {
		return createReactionGlyph(null);
	}

	/**
	 * Creates a new {@link ReactionGlyph} element and adds it to the ListOfSubGlyphs list
	 *
	 * @return a new {@link ReactionGlyph} element
	 */
	public ReactionGlyph createReactionGlyph(String id) {
		ReactionGlyph subGlyph = new ReactionGlyph(id, getLevel(), getVersion());
		addSubGlyph(subGlyph);
		return subGlyph;
	}

	/**
	 * Creates a new {@link GraphicalObject} element and adds it to the ListOfSubGlyphs list
	 */
	public GraphicalObject createGraphicalObject() {
		return createGraphicalObject(null);
	}

	/**
	 * Creates a new {@link GraphicalObject} element and adds it to the ListOfSubGlyphs list
	 *
	 * @return a new {@link GraphicalObject} element
	 */
	public GraphicalObject createGraphicalObject(String id) {
		GraphicalObject subGlyph = new GraphicalObject(id, getLevel(), getVersion());
		addSubGlyph(subGlyph);
		return subGlyph;
	}

	
	/**
	 * Appends the specified element to the end of the
	 * {@link #listOfReferenceGlyphs}.
	 * 
	 * @param glyph
	 * @return {@code true} (as specified by {@link Collection#add(E)})
	 * @throws NullPointerException
	 *             if the specified element is null and this list does not
	 *             permit null elements
	 * @throws IllegalArgumentException
	 *             if some property of this element prevents it from being added
	 *             to this list
	 */
	public boolean addSpeciesReferenceGlyph(ReferenceGlyph glyph) {
		return getListOfReferenceGlyphs().add(glyph);
	}

	/* (non-Javadoc)
	 * @see org.sbml.jsbml.ext.layout.GraphicalObject#clone()
	 */
	public GeneralGlyph clone() {
		return new GeneralGlyph(this);
	}

	/**
	 * Creates and adds a new {@link ReferenceGlyph}
	 * @param id the identifier for the {@link ReferenceGlyph} to be created.
	 * @return a new {@link ReferenceGlyph}.
	 */
	public ReferenceGlyph createReferenceGlyph(String id) {
		ReferenceGlyph glyph = new ReferenceGlyph(id, getLevel(), getVersion());
		addSpeciesReferenceGlyph(glyph);
		return glyph;
	}
	
  /**
   * Creates and adds a new {@link ReferenceGlyph}
   * 
   * @param id the identifier for the {@link ReferenceGlyph} to be created.
   * @param glyph corresponding {@link GraphicalObject} ID.
   * @return a new {@link ReferenceGlyph} that points to the given {@code glyph}.
   */
  public ReferenceGlyph createSpeciesReferenceGlyph(String id, String speciesGlyph) {
    ReferenceGlyph glyph = createReferenceGlyph(id);
    glyph.setGlyph(speciesGlyph);
    return glyph;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractNamedSBase#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object object) {
    boolean equals = super.equals(object);
    if (equals) {
      GeneralGlyph reactionGlyph = (GeneralGlyph) object;
      equals &= reactionGlyph.isSetCurve() == isSetCurve();
      if (equals && isSetCurve()) {
        equals &= reactionGlyph.getCurve().equals(getCurve());
      }
      equals &= reactionGlyph.isSetListOfSubGlyphs() == isSetListOfSubGlyphs();
      if (equals && isSetListOfSubGlyphs()) {
        equals &= reactionGlyph.getListOfSubGlyphs().equals(getListOfSubGlyphs());
      }
      equals &= reactionGlyph.isSetListOfReferenceGlyphs() == isSetListOfReferenceGlyphs();
      if (equals && isSetListOfReferenceGlyphs()) {
        equals &= reactionGlyph.getListOfReferenceGlyphs().equals(getListOfReferenceGlyphs());
      }
    }
    return equals;
  }

  /* (non-Javadoc)
	 * @see org.sbml.jsbml.ext.layout.GraphicalObject#getChildAt(int)
	 */
	@Override
	public TreeNode getChildAt(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		int count = super.getChildCount(), pos = 0;
		if (index < count) {
			return super.getChildAt(index);
		} else {
			index -= count;
		}
		if (isSetListOfSubGlyphs()) {
			if (pos == index) {
				return getListOfSubGlyphs();
			}
			pos++;
		}
		if (isSetListOfReferenceGlyphs()) {
			if (pos == index) {
				return getListOfReferenceGlyphs();
			}
			pos++;
		}
		if (isSetCurve()) {
			if (pos == index) {
				return getCurve();
			}
			pos++;
		}
		throw new IndexOutOfBoundsException(MessageFormat.format(
		  "Index {0,number,integer} >= {1,number,integer}",
			index, +((int) Math.min(pos, 0))));
	}
  
	/* (non-Javadoc)
	 * @see org.sbml.jsbml.ext.layout.GraphicalObject#getChildCount()
	 */
	@Override
	public int getChildCount() {
		int count = super.getChildCount();
		if (isSetCurve()) {
			count++;
		}
		if (isSetListOfReferenceGlyphs()) {
			count++;
		}
		if (isSetListOfSubGlyphs()) {
			count++;
		}
		
		return count;
	}
	
	/**
	 * 
	 * @return
	 */
	public Curve getCurve() {
		return curve;
	}

	/**
	 * If the {@link #listOfReferenceGlyphs} has not yet been initialized, this
	 * will be done by this method.
	 * 
	 * @return the {@link #listOfReferenceGlyphs}
	 */
	public ListOf<ReferenceGlyph> getListOfReferenceGlyphs() {
		if (!isSetListOfReferenceGlyphs()) {
			listOfReferenceGlyphs = new ListOf<ReferenceGlyph>();
			listOfReferenceGlyphs.addNamespace(LayoutConstants.namespaceURI);
			listOfReferenceGlyphs.setSBaseListType(ListOf.Type.other);
			registerChild(listOfReferenceGlyphs);
		}
		return listOfReferenceGlyphs;
	}

	
	/* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractSBase#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 953;
    int hashCode = super.hashCode();
    if (isSetCurve()) {
      hashCode += prime * getCurve().hashCode();
    }
    if (isSetListOfReferenceGlyphs()) {
      hashCode += prime * getListOfReferenceGlyphs().hashCode();
    }
    if (isSetListOfSubGlyphs()) {
        hashCode += prime * getListOfSubGlyphs().hashCode();
      }

    return hashCode;
  }
	
	/**
	 * @return
	 */
	public boolean isSetCurve() {
		return curve != null;
	}

	/**
	 * @return
	 */
	public boolean isSetListOfReferenceGlyphs() {
		return listOfReferenceGlyphs != null;
	}

	/* (non-Javadoc)
	 * @see org.sbml.jsbml.AbstractNamedSBase#readAttribute(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean readAttribute(String attributeName, String prefix,
			String value) {
		boolean isAttributeRead = super.readAttribute(attributeName, prefix,
				value);

		if (!isAttributeRead) {

			isAttributeRead = true;			
			if (attributeName.equals(LayoutConstants.reference)) {				
				setReference(value);
			} else {
				return false;
			}
		}

		return isAttributeRead;
	}

	/**
	 * 
	 * @param curve
	 */
	public void setCurve(Curve curve) {
		if (this.curve != null) {
			Curve oldValue = this.curve;
			this.curve = null;
			oldValue.fireNodeRemovedEvent();
		}
		this.curve = curve;
		registerChild(this.curve);
	}

	/**
	 * 
	 * @param listOfReferenceGlyph
	 */
	public void setListOfSpeciesReferencesGlyph(ListOf<ReferenceGlyph> listOfReferenceGlyph) 
	{
		unsetListOfSpeciesReferencesGlyph();
		this.listOfReferenceGlyphs = listOfReferenceGlyph;
		
		if (listOfReferenceGlyph != null)
		{
			listOfReferenceGlyph.setSBaseListType(ListOf.Type.other);
		}
		
		registerChild(this.listOfReferenceGlyphs);
	}
	
	
	/**
	 * 
	 */
	private void unsetListOfSpeciesReferencesGlyph() {
		if (this.listOfReferenceGlyphs != null) {
			ListOf<ReferenceGlyph> oldValue = this.listOfReferenceGlyphs;
			this.listOfReferenceGlyphs = null;
			oldValue.fireNodeRemovedEvent();
		}
	}

	/**
	 * 
	 */
	public void unsetReaction() {
		unsetReference();
	}
	
	/* (non-Javadoc)
	 * @see org.sbml.jsbml.ext.layout.GraphicalObject#writeXMLAttributes()
	 */
	@Override
	public Map<String, String> writeXMLAttributes() {
		Map<String, String> attributes = super.writeXMLAttributes();
		
		if (isSetReference()) {
			attributes.put(LayoutConstants.shortLabel + ':'
					+ LayoutConstants.reference, getReference());
		}

		return attributes;
	}

}
