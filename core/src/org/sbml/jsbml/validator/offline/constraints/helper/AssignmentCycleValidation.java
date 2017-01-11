/*
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 *
 * Copyright (C) 2009-2017 jointly by the following organizations:
 * 1. The University of Tuebingen, Germany
 * 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK
 * 3. The California Institute of Technology, Pasadena, CA, USA
 * 4. The University of California, San Diego, La Jolla, CA, USA
 * 5. The Babraham Institute, Cambridge, UK
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */

package org.sbml.jsbml.validator.offline.constraints.helper;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.apache.log4j.Logger;
import org.sbml.jsbml.ASTNode;
import org.sbml.jsbml.ASTNode.Type;
import org.sbml.jsbml.Assignment;
import org.sbml.jsbml.ExplicitRule;
import org.sbml.jsbml.InitialAssignment;
import org.sbml.jsbml.KineticLaw;
import org.sbml.jsbml.MathContainer;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.RateRule;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.Rule;
import org.sbml.jsbml.SBase;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.validator.offline.ValidationContext;
import org.sbml.jsbml.validator.offline.constraints.ValidationFunction;


/**
 * 
 * @author Roman
 * @since 1.2
 */
public class AssignmentCycleValidation
  implements ValidationFunction<SBase> {
  
  /**
   * 
   */
  private Set<String> visited = new HashSet<String>();
  /**
   * 
   */
  private Queue<SBase> toCheck = new LinkedList<SBase>();
  
  /**
   * A {@link Logger} for this class.
   */
  private static transient final Logger logger = Logger.getLogger(AssignmentCycleValidation.class);
  /**
   * A static boolean to avoid many unnecessary calls to {@link Logger#isDebugEnabled()}.
   */
  private static transient final boolean isDebugEnabled = logger.isDebugEnabled();

  @Override
  public boolean check(ValidationContext ctx, SBase sb) {
    
    if (sb instanceof RateRule) {
      // We do not need to test RateRule
      return true;
    }
    
    Model m = sb.getModel();
    
    if (m != null)
    {
      visited.clear();
      String currentId = getRelatedId(sb);
      
      if (isDebugEnabled) {
        logger.debug("Testing " + currentId);
      }
      
      if (currentId != null && !currentId.isEmpty())
      {
        // Collect the children
        checkChildren(m, sb);
        
        while (!toCheck.isEmpty())
        {
          SBase child = toCheck.poll();
          
          // Referred to this id?
          String childId = getRelatedId(child);
          
          // If this child wasn't visited yet
          if (childId != null && visited.add(childId)){
            
            if (isDebugEnabled) {
              logger.debug("Checking " + childId);
            }
            
            // Check if we are back at the first SBase
            if (childId.equals(currentId))
            {
              if (isDebugEnabled) {
                logger.debug("Found an assignment cycle with '" + childId + "'");
              }
              return false;
            }
            
            // Else check the children
            checkChildren(m, child);
          }
        }
      }
    }
    
    return true;
  }
  
  /**
   * Returns an id depending of the type of SBase given. If it is a {@link Reaction}, returns reaction.id, 
   * if it is an {@link Assignment}, returns assignment.variable (symbol or variable attribute), if it is 
   * a {@link Species}, returns species.compartment. For any other type of {@link SBase}, returns null. 
   * 
   * @param sb an {@link SBase}
   * @return an id depending of the type of SBase given. 
   */
  private String getRelatedId(SBase sb)
  {
    if (sb instanceof Reaction)
    {
      return ((Reaction) sb).getId();
    }
    else if (sb instanceof Assignment)
    {
      return ((Assignment) sb).getVariable();
    }
    else if (sb instanceof Species)
    {
      return ((Species) sb).getCompartment();
    }
    
    return null;
  }
  
  /**
   * 
   * @param m
   * @param sb
   */
  private void checkChildren(Model m, SBase sb)
  {
    if (sb instanceof ExplicitRule || sb instanceof InitialAssignment)
    {
      checkChildren(m, (MathContainer) sb);
    }
    else if (sb instanceof Reaction)
    {
      checkChildren(m, (Reaction) sb);
    }    
  }
  
  /**
   * 
   * @param m
   * @param r
   */
  private void checkChildren(Model m, MathContainer r)
  {
    if (r.isSetMath())
    {
      checkChildren(m, r.getMath());
    }
  }
  
  /**
   * 
   * @param m
   * @param r
   */
  private void checkChildren(Model m, Reaction r)
  {
    if (r.isSetKineticLaw())
    {
      KineticLaw kl = r.getKineticLaw();
      
      if (kl.isSetMath())
      {
        checkChildren(m, kl.getMath());
      }
    }
  }
  
  /**
   * 
   * @param m
   * @param math
   */
  private void checkChildren(Model m, ASTNode math)
  {
    if (isDebugEnabled) {
      logger.debug("Looking for ASTNode NAME");
    }
    Queue<ASTNode> children = new LinkedList<ASTNode>();
    
    children.add(math);
    
    while (!children.isEmpty())
    {
      ASTNode node = children.poll();
      
      if (node.getType() == Type.NAME)
      {
        if (isDebugEnabled) {
          logger.debug("Node is a name " + node.getName());
        }
        
        // If one of the nodes refer to Reaction, InitalAssignment or Rule
        String id = (node.getName() != null) ? node.getName() : "";
        
        SBase child = m.isSetListOfReactions() ? m.getReaction(id) : null;
        
        // Not a Reaction?
        if (child == null)
        {
          child = m.isSetListOfInitialAssignments() ? m.getInitialAssignmentBySymbol(id) : null;
          
          // Not InitialAssignment?
          if (child == null)
          {
            Rule r = m.isSetListOfRules() ? m.getRuleByVariable(id) : null;
            if (r != null && r.isAssignment())
            {
              child = r;
            }
            else
            {
              Species s = m.isSetListOfSpecies() ? m.getSpecies(id) : null;
              
              if (s != null && !s.hasOnlySubstanceUnits())
              {
                child = s;
              }
            }
          }
        }
        
        if (child != null)
        {
          if (isDebugEnabled) {
            logger.debug("Found Child");
          }
          toCheck.add(child);
        }
      }
      
      children.addAll(node.getListOfNodes());
    }
  }

}
