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
package org.sbml.jsbml.ext.comp;

import org.sbml.jsbml.LevelVersionError;

/**
 * 
 * @author Nicolas Rodriguez
 * @version $Rev$
 * @since 1.0
 */
public class Deletion extends AbstractNamedSBaseRef {

	/**
	 * Generated serial version identifier.
	 */
	private static final long serialVersionUID = -568486978934971953L;

	/**
	 * Creates an Deletion instance 
	 */
	public Deletion() {
		super();
		initDefaults();
	}

	/**
	 * Creates a Deletion instance with an id. 
	 * 
	 * @param id
	 */
	public Deletion(String id) {
		super(id);
		initDefaults();
	}

	/**
	 * Creates a Deletion instance with a level and version. 
	 * 
	 * @param level
	 * @param version
	 */
	public Deletion(int level, int version) {
		this(null, null, level, version);
	}

	/**
	 * Creates a Deletion instance with an id, level, and version. 
	 * 
	 * @param id
	 * @param level
	 * @param version
	 */
	public Deletion(String id, int level, int version) {
		this(id, null, level, version);
	}

	/**
	 * Creates a Deletion instance with an id, name, level, and version. 
	 * 
	 * @param id
	 * @param name
	 * @param level
	 * @param version
	 */
	public Deletion(String id, String name, int level, int version) {
		super(id, name, level, version);
		if (getLevelAndVersion().compareTo(
				Integer.valueOf(CompConstant.MIN_SBML_LEVEL),
				Integer.valueOf(CompConstant.MIN_SBML_VERSION)) < 0) {
			throw new LevelVersionError(getElementName(), level, version);
		}
		initDefaults();
	}

	/**
	 * Clone constructor
	 */
	public Deletion(Deletion obj) {
		super(obj);
	}

	/**
	 * clones this class
	 */
	public Deletion clone() {
		return new Deletion(this);
	}

	/**
	 * Initializes the default values using the namespace.
	 */
	public void initDefaults() {
		addNamespace(CompConstant.namespaceURI);
		// TODO: init default values here if necessary, e.g.:
		// bar = null;
	}


	public boolean isIdMandatory() {
		return false;
	}
}