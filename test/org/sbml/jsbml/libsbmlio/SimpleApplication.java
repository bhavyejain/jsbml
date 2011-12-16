/*
 * $Id: FormulaTest.java 102 2009-12-13 19:52:50Z andreas-draeger $
 * $URL: https://jsbml.svn.sourceforge.net/svnroot/jsbml/trunk/src/org/sbml/jsbml/io/FormulaTest.java $
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 *
 * Copyright (C) 2009-2011 jointly by the following organizations:
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
package org.sbml.jsbml.libsbmlio;

import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.test.gui.JTreeOfSBML;
import org.sbml.jsbml.xml.libsbml.LibSBMLChangeListener;
import org.sbml.jsbml.xml.libsbml.LibSBMLReader;

/**
 * @author Andreas Dr&auml;ger
 * @date 2011-02-03
 */
public class SimpleApplication {

	/**
	 * @param args the path to a valid SBML file.
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		try {
			// Load LibSBML:
			System.loadLibrary("sbmlj");
			// Extra check to be sure we have access to libSBML:
			Class.forName("org.sbml.libsbml.libsbml");

			// Read SBML file using LibSBML and convert it to JSBML:
			LibSBMLReader reader = new LibSBMLReader();
			SBMLDocument doc = reader.convertSBMLDocument(args[0]);
			
			org.sbml.libsbml.SBMLDocument libDoc = reader.getOriginalModel().getSBMLDocument();
			doc.addTreeNodeChangeListener(new LibSBMLChangeListener(doc, libDoc));
			
			/*
			 * Some tests
			 */
			Model model = doc.getModel();
			if (model == null) {
				model = doc.createModel("test_model");
			}
			model.createCompartmentType("ct001");
			model.createCompartment("c001");
			model.createAlgebraicRule();
			model.createAssignmentRule();
			model.createConstraint();
			//model.createDelay();
			model.createEvent("ev001");
			model.createEventAssignment();
			model.createFunctionDefinition("func001");
			model.createInitialAssignment();
			model.createParameter("param001");
			model.createKineticLaw();
			//model.createKineticParameter("param001");
			
			// Run some application:
			new JTreeOfSBML(doc);
			
			// TODO: perform changes in the model.
			
			System.out.println(new org.sbml.libsbml.SBMLWriter().writeSBMLToString(libDoc));
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
