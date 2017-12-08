/*
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 * Copyright (C) 2009-2017 jointly by the following organizations:
 * 1. The University of Tuebingen, Germany
 * 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK
 * 3. The California Institute of Technology, Pasadena, CA, USA
 * 4. The University of California, San Diego, La Jolla, CA, USA
 * 5. The Babraham Institute, Cambridge, UK
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */
package org.sbml.jsbml.ext.fbc.converters;

import java.io.IOException;
import java.util.Properties;

import javax.xml.stream.XMLStreamException;

import org.sbml.jsbml.Model;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLException;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.TidySBMLWriter;
import org.sbml.jsbml.Unit;
import org.sbml.jsbml.UnitDefinition;
import org.sbml.jsbml.ext.fbc.FBCConstants;
import org.sbml.jsbml.ext.fbc.FBCModelPlugin;
import org.sbml.jsbml.ext.fbc.FBCSpeciesPlugin;
import org.sbml.jsbml.ext.fbc.FluxBound;
import org.sbml.jsbml.util.CobraUtil;
import org.sbml.jsbml.util.SBMLtools;
import org.sbml.jsbml.util.converters.SBMLConverter;

/**
 * Converts old COBRA SBML files to SBML FBCV1
 * 
 * @author Thomas Hamm
 * @author Nicolas Rodriguez
 * @since 1.2
 */
public class CobraToFbcV1Converter implements SBMLConverter {
  // public void cobraToFbcV1(SBMLDocument cobraSBMLdocument) {
  //
  // }

  /*
   * (non-Javadoc)
   * @see org.sbml.jsbml.util.converters.SBMLConverter#convert(org.sbml.jsbml.
   * SBMLDocument)
   */
  @Override
  public SBMLDocument convert(SBMLDocument sbmlDocument) throws SBMLException {
   
    Properties pElementsNote = new Properties();
    Model model = sbmlDocument.getModel();
    // only SBMLDocuments with version smaller than three are converted
    if (sbmlDocument.getLevel() < 3) {
    // set SBMLDocument to level 3 version 1 
      SBMLtools.setLevelAndVersion(sbmlDocument, 3, 1);
      FBCConstants fbcConstants = new FBCConstants();
      sbmlDocument.enablePackage(fbcConstants.getNamespaceURI(3, 1, 1));
    // set the units of the model
      if (!model.isSetSubstanceUnits()) {
        model.setSubstanceUnits("substance");
      }
      if (!model.isSetTimeUnits()) {
        model.setTimeUnits("second");
      }
      if (!model.isSetVolumeUnits()) {
        model.setVolumeUnits("volume");
      }
      if (!model.isSetAreaUnits()) {
        model.setAreaUnits("area");
      }    
      if (!model.isSetLengthUnits()) {
        model.setLengthUnits("metre");
      }
      if (!model.isSetExtentUnits()) {
        model.setExtentUnits("substance");
      }
    // add unit definition substance
      if (model.getUnitDefinitionById("substance") == null) {
        UnitDefinition unitDefinitionSub = new UnitDefinition("substance");
        model.addUnitDefinition(unitDefinitionSub);
        unitDefinitionSub.createUnit(Unit.Kind.MOLE);
      }
    // add unit definition volume
      if (model.getUnitDefinitionById("volume") == null) {
        UnitDefinition unitDefinitionVol = new UnitDefinition("volume");
        model.addUnitDefinition(unitDefinitionVol);
        unitDefinitionVol.createUnit(Unit.Kind.LITRE);
      }
    // add unit definition area
      if (model.getUnitDefinitionById("area") == null) {
        UnitDefinition unitDefinitionAre = new UnitDefinition("area");
        model.addUnitDefinition(unitDefinitionAre);
        unitDefinitionAre.createUnit(Unit.Kind.METRE);
        unitDefinitionAre.getUnit(0).setExponent(2);
      }
    
      for (Species species : model.getListOfSpecies()) {
    // initialize default values for species attributes   
        if (species.isSetHasOnlySubstanceUnits() == false) {
          species.setHasOnlySubstanceUnits(false);
        }
        if (species.isSetBoundaryCondition() == false) {
          species.setBoundaryCondition(false);
        }
        if (species.isSetConstant() == false) {
          species.setConstant(false);
        }
        if (species.isSetSubstanceUnits() == false) {
          species.setSubstanceUnits("substance");
        }
      
    // parse the COBRA SBML file and extract and set the values for formula and charge
        pElementsNote = CobraUtil.parseCobraNotes(species);
        FBCSpeciesPlugin fbcSpeciesPlugin = (FBCSpeciesPlugin)species.getPlugin("fbc");
      
        if (pElementsNote.getProperty("FORMULA") != null) {
          fbcSpeciesPlugin.setChemicalFormula(pElementsNote.getProperty("FORMULA"));
        }
      
        if (species.isSetCharge() == true) {
          fbcSpeciesPlugin.setCharge(species.getCharge());
          species.unsetCharge();
        
        } else if (pElementsNote.getProperty("CHARGE") != null) {
          fbcSpeciesPlugin.setCharge(Integer.parseInt(pElementsNote.getProperty("CHARGE")));
        }
      }
   
      FBCModelPlugin fbcModelPlugin = (FBCModelPlugin)model.getPlugin("fbc");
      for (Reaction reaction : model.getListOfReactions()) {
    // initialize default values for reaction attributes reversible and fast 
        if (reaction.isSetReversible() == false) {
          reaction.setReversible(true);
        }
        if (reaction.isSetFast() == false) {
          reaction.setFast(false);
        }
    // get lower and upper flux bound from the kinetic law, set them in the list of flux bounds, and delete the kinetic law  
        if (reaction.getKineticLaw().getParameter("LOWER_BOUND").isSetValue()) {
          FluxBound fluxBoundLo = new FluxBound();
          fluxBoundLo.setReaction(reaction.getId());
          fluxBoundLo.setOperation(FluxBound.Operation.GREATER_EQUAL);
          fluxBoundLo.setValue(reaction.getKineticLaw().getParameter("LOWER_BOUND").getValue());
          fbcModelPlugin.addFluxBound(fluxBoundLo);
        }
        if (reaction.getKineticLaw().getParameter("UPPER_BOUND").isSetValue()) {
          FluxBound fluxBoundUp = new FluxBound();
          fluxBoundUp.setReaction(reaction.getId());
          fluxBoundUp.setOperation(FluxBound.Operation.LESS_EQUAL);
          fluxBoundUp.setValue(reaction.getKineticLaw().getParameter("UPPER_BOUND").getValue());
          fbcModelPlugin.addFluxBound(fluxBoundUp);
        }
        if (reaction.isSetKineticLaw()) {
          reaction.unsetKineticLaw();
        }
      }
    }
  return sbmlDocument;
  }
  
  public static void main(String[] args) throws XMLStreamException, IOException {
   
   // read document 
    SBMLReader sbmlReader = new SBMLReader();
    SBMLDocument doc = sbmlReader.readSBMLFromFile(args[0]);
   // convert and write document
    CobraToFbcV1Converter cobraToFbcV1Converter = new CobraToFbcV1Converter();
    TidySBMLWriter tidySBMLWriter = new TidySBMLWriter();
    tidySBMLWriter.writeSBMLToFile(cobraToFbcV1Converter.convert(doc),args[1]);
  }
}
