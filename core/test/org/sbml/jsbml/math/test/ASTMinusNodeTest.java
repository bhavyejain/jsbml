/*
 * $Id$
 * $URL$
 * ---------------------------------------------------------------------------- 
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML> 
 * for the latest version of JSBML and more information about SBML. 
 * 
 * Copyright (C) 2009-2014  jointly by the following organizations: 
 * 1. The University of Tuebingen, Germany 
 * 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK 
 * 3. The California Institute of Technology, Pasadena, CA, USA 
 * 4. The University of California, San Diego, La Jolla, CA, USA
 * 5. The Babraham Institute, Cambridge, UK
 * 6. The University of Toronto, Toronto, ON, Canada
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation. A copy of the license agreement is provided 
 * in the file named "LICENSE.txt" included with this software distribution 
 * and also available online as <http://sbml.org/Software/JSBML/License>. 
 * ---------------------------------------------------------------------------- 
 */
package org.sbml.jsbml.math.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.sbml.jsbml.ASTNode.Type;
import org.sbml.jsbml.math.ASTCnIntegerNode;
import org.sbml.jsbml.math.ASTMinusNode;


/**
 * Test cases for {@link ASTMinusNode}
 * 
 * @author Victor Kofia
 * @version $Rev$
 * @since 1.0
 * @date Jul 25, 2014
 */
public class ASTMinusNodeTest {
  
  /**
   * Test method for {@link org.sbml.jsbml.math.ASTMinusNode#clone()}.
   */
  @Test
  public final void testClone() {
    ASTMinusNode minus = new ASTMinusNode();
    ASTMinusNode unknown = minus.clone();
    assertTrue(minus.equals(unknown));
  }
  
  /**
   * Test method for {@link org.sbml.jsbml.math.ASTMinusNode#clone()}.
   */
  @Test
  public final void testCloneWithChildren() {
    ASTMinusNode minus = new ASTMinusNode(new ASTCnIntegerNode(20), new ASTCnIntegerNode(40));
    ASTMinusNode unknown = minus.clone();
    assertTrue(minus.equals(unknown));
  }
  
  /**
   * Test method for {@link org.sbml.jsbml.math.ASTMinusNode#ASTMinusNode(org.sbml.jsbml.math.ASTMinusNode)}.
   */
  @Test
  public final void testCloneWithConstructor() {
    ASTMinusNode minus = new ASTMinusNode();
    ASTMinusNode unknown = minus.clone();
    assertTrue(minus.equals(unknown));
  }
  
  /**
   * Test method for {@link org.sbml.jsbml.math.ASTMinusNode#isAllowableType()}.
   */
  @Test
  public final void testIsAllowableType() {
    ASTMinusNode minus = new ASTMinusNode();
    assertTrue(minus.isAllowableType(Type.MINUS) && !minus.isAllowableType(null));
  }
  
}