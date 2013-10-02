/*
 * $Id$
 * $URL$
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 *
 * Copyright (C) 2009-2013 jointly by the following organizations:
 * 1. The University of Tuebingen, Germany
 * 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK
 * 3. The California Institute of Technology, Pasadena, CA, USA
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */
package org.sbml.jsbml.ext.fbc;

import java.text.MessageFormat;
import java.util.Map;

import javax.swing.tree.TreeNode;

import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.ext.AbstractSBasePlugin;

/**
 * 
 * @author Nicolas Rodriguez
 * @version $Rev$
 * @since 1.0
 * @date 27.10.2011
 */
public class FBCModelPlugin extends AbstractSBasePlugin {

  /**
   * Generated serial version identifier.
   */
  private static final long serialVersionUID = -7451190347195219863L;

  /**
   * 
   */
  private ListOf<FluxBound> listOfFluxBounds;

  /**
   * 
   */
  private ListOfObjectives listOfObjectives = new ListOfObjectives();

  /**
   * 
   * @param model
   */
  public FBCModelPlugin(Model model) {
    super(model);
  }

  /* (non-Javadoc)
   * @see java.lang.Object#clone()
   */
  public FBCModelPlugin clone() {
    return new FBCModelPlugin(this);
  }

  /**
   * Clone constructor
   */
  public FBCModelPlugin(FBCModelPlugin obj) {
    super(obj.getExtendedSBase());

    if (obj.isSetListOfFluxBounds()) {
      setListOfFluxBounds(obj.getListOfFluxBounds().clone());
    }

    if (obj.isSetListOfObjectives()) {
      setListOfObjectives((ListOfObjectives) obj.getListOfObjectives().clone());
    }
  }


  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.SBasePlugin#readAttribute(java.lang.String, java.lang.String, java.lang.String)
   */
  public boolean readAttribute(String attributeName, String prefix, String value) {
    return false;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.SBasePlugin#writeXMLAttributes()
   */
  public Map<String, String> writeXMLAttributes() {
    return null;
  }

  public int getChildCount() {
    int count = 0;

    if (isSetListOfFluxBounds()) {
      count++;
    }
    if (isSetListOfObjectives()) {
      count++;
    }

    return count;
  }

  public TreeNode getChildAt(int index) {
    if (index < 0) {
      throw new IndexOutOfBoundsException(index + " < 0");
    }

    int pos = 0;

    if (isSetListOfFluxBounds()) {
      if (pos == index) {
        return getListOfFluxBounds();
      }
      pos++;
    }
    if (isSetListOfObjectives()) {
      if (pos == index) {
        return getListOfObjectives();
      }
      pos++;
    }

    throw new IndexOutOfBoundsException(MessageFormat.format(
      "Index {0,number,integer} >= {1,number,integer}", index,
      +((int) Math.min(pos, 0))));
  }

  public boolean getAllowsChildren() {
    return true;
  }



  /**
   * Returns {@code true}, if listOfFluxBounds contains at least one element.
   *
   * @return {@code true}, if listOfFluxBounds contains at least one element, 
   *         otherwise {@code false}
   */
  public boolean isSetListOfFluxBounds() {
    if ((listOfFluxBounds == null) || listOfFluxBounds.isEmpty()) {
      return false;
    }
    return true;
  }

  /**
   * Returns the listOfFluxBounds. Creates it if it is not already existing.
   *
   * @return the listOfFluxBounds
   */
  public ListOf<FluxBound> getListOfFluxBounds() {
    if (!isSetListOfFluxBounds()) {
      listOfFluxBounds = new ListOf<FluxBound>(extendedSBase.getLevel(),
          extendedSBase.getVersion());
      listOfFluxBounds.addNamespace(FBCConstants.namespaceURI);
      listOfFluxBounds.setSBaseListType(ListOf.Type.other);
      extendedSBase.registerChild(listOfFluxBounds);
    }
    return listOfFluxBounds;
  }

  /**
   * Sets the given {@code ListOf<FluxBound>}. If listOfFluxBounds
   * was defined before and contains some elements, they are all unset.
   *
   * @param listOfFluxBounds
   */
  public FluxBound getFluxBound(int i) {
    return getListOfFluxBounds().get(i);
  }

  /**
   * 
   * @param listOfFluxBounds
   */
  public void setListOfFluxBounds(ListOf<FluxBound> listOfFluxBounds) {
    unsetListOfFluxBounds();
    this.listOfFluxBounds = listOfFluxBounds;
    extendedSBase.registerChild(this.listOfFluxBounds);
  }

  /**
   * Returns {@code true}, if listOfFluxBounds contain at least one element, 
   *         otherwise {@code false}
   *
   * @return {@code true}, if listOfFluxBounds contain at least one element, 
   *         otherwise {@code false}
   */
  public boolean unsetListOfFluxBounds() {
    if (isSetListOfFluxBounds()) {
      ListOf<FluxBound> oldFluxBounds = this.listOfFluxBounds;
      this.listOfFluxBounds = null;
      oldFluxBounds.fireNodeRemovedEvent();
      return true;
    }
    return false;
  }

  /**
   * Adds a new {@link FluxBound} to the listOfFluxBounds.
   * <p>The listOfFluxBounds is initialized if necessary.
   *
   * @param fluxBound the element to add to the list
   * @return {@code true} (as specified by {@link Collection.add})
   */
  public boolean addFluxBound(FluxBound fluxBound) {
    return getListOfFluxBounds().add(fluxBound);
  }

  /**
   * Removes an element from the listOfFluxBounds.
   *
   * @param fluxBound the element to be removed from the list
   * @return true if the list contained the specified element
   * @see List#remove(Object)
   */
  public boolean removeFluxBound(FluxBound fluxBound) {
    if (isSetListOfFluxBounds()) {
      return getListOfFluxBounds().remove(fluxBound);
    }
    return false;
  }

  /**
   * Removes an element from the listOfFluxBounds at the given index.
   *
   * @param i the index where to remove the {@link FluxBound}
   * @throws IndexOutOfBoundsException if the listOf is not set or
   * if the index is out of bound (index < 0 || index > list.size)
   */
  public void removeFluxBound(int i) {
    if (!isSetListOfFluxBounds()) {
      throw new IndexOutOfBoundsException(Integer.toString(i));
    }
    getListOfFluxBounds().remove(i);
  }


  /**
   * Creates a new FluxBound element and adds it to the ListOfFluxBounds list
   */
  public FluxBound createFluxBound() {
    return createFluxBound(null);
  }

  /**
   * Creates a new {@link FluxBound} element and adds it to the ListOfFluxBounds list
   *
   * @return a new {@link FluxBound} element
   */
  public FluxBound createFluxBound(String id) {
    FluxBound fluxBound = new FluxBound(id, getExtendedSBase().getLevel(), getExtendedSBase().getVersion());
    addFluxBound(fluxBound);
    return fluxBound;
  }

  /**
   * Returns {@code true}, if listOfObjectives contains at least one element.
   *
   * @return {@code true}, if listOfObjectives contains at least one element, 
   *         otherwise {@code false}
   */
  public boolean isSetListOfObjectives() {
    if ((listOfObjectives == null) || listOfObjectives.isEmpty()) {
      return false;
    }
    return true;
  }

  /**
   * Returns the listOfObjectives. Creates it if it is not already existing.
   *
   * @return the listOfObjectives
   */
  public ListOf<Objective> getListOfObjectives() {
    if (!isSetListOfObjectives()) {
      listOfObjectives = new ListOfObjectives(extendedSBase.getLevel(),
        extendedSBase.getVersion());
      listOfObjectives.addNamespace(FBCConstants.namespaceURI);
      listOfObjectives.setSBaseListType(ListOf.Type.other);
      extendedSBase.registerChild(listOfObjectives);
    }
    return listOfObjectives;
  }

  /**
   * Sets the given {@code ListOf<Objective>}. If listOfObjectives
   * was defined before and contains some elements, they are all unset.
   *
   * @param listOfObjectives
   */
  public void setListOfObjectives(ListOfObjectives listOfObjectives) { // TODO make a method that is taking ListOf<Objective> and transform it into ListOfObjectives is necessary
    unsetListOfObjectives();
    this.listOfObjectives = listOfObjectives;
    extendedSBase.registerChild(this.listOfObjectives);
  }

  /**
   * Returns {@code true}, if listOfObjectives contain at least one element, 
   *         otherwise {@code false}
   *
   * @return {@code true}, if listOfObjectives contain at least one element, 
   *         otherwise {@code false}
   */
  public boolean unsetListOfObjectives() {
    if (isSetListOfObjectives()) {
      ListOf<Objective> oldObjectives = this.listOfObjectives;
      this.listOfObjectives = null;
      oldObjectives.fireNodeRemovedEvent();
      return true;
    }
    return false;
  }

  /**
   * Adds a new {@link Objective} to the listOfObjectives.
   * <p>The listOfObjectives is initialized if necessary.
   *
   * @param objective the element to add to the list
   * @return true (as specified by {@link Collection.add})
   */
  public boolean addObjective(Objective objective) {
    return getListOfObjectives().add(objective);
  }

  /**
   * Removes an element from the listOfObjectives.
   *
   * @param objective the element to be removed from the list
   * @return true if the list contained the specified element
   * @see List#remove(Object)
   */
  public boolean removeObjective(Objective objective) {
    if (isSetListOfObjectives()) {
      return getListOfObjectives().remove(objective);
    }
    return false;
  }

  /**
   * Removes an element from the listOfObjectives at the given index.
   *
   * @param i the index where to remove the {@link Objective}
   * @throws IndexOutOfBoundsException if the listOf is not set or
   * if the index is out of bound (index < 0 || index > list.size)
   */
  public void removeObjective(int i) {
    if (!isSetListOfObjectives()) {
      throw new IndexOutOfBoundsException(Integer.toString(i));
    }
    getListOfObjectives().remove(i);
  }

  /**
   * Creates a new Objective element and adds it to the ListOfObjectives list
   */
  public Objective createObjective() {
    return createObjective(null);
  }

  /**
   * Creates a new {@link Objective} element and adds it to the ListOfObjectives list
   *
   * @return a new {@link Objective} element
   */
  public Objective createObjective(String id) {
    Objective objective = new Objective(id, getExtendedSBase().getLevel(), getExtendedSBase().getVersion());
    addObjective(objective);
    return objective;
  }


  /**
   * 
   * @param i
   * @return
   */
  public Objective getObjective(int i) {
    if (isSetListOfObjectives() && (i >= 0) && (i < listOfObjectives.size())) {
      return listOfObjectives.get(i);
    }
    throw new IndexOutOfBoundsException(Integer.toString(i));
  }

  /**
   * 
   * @return
   */
  public String getActiveObjective()
  {
    if (isSetListOfObjectives())
    {
      return listOfObjectives.getActiveObjective();
    }

    return "";
  }

  /**
   * 
   * @param activeObjective
   */
  public void setActiveObjective(String activeObjective)
  {
    ((ListOfObjectives) getListOfObjectives()).setActiveObjective(activeObjective);
  }

}
