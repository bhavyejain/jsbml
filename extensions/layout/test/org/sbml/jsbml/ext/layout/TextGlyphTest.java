/*
 * $Id: SpeciesReferenceGlyphTest.java 1708 2014-05-06 01:00:00Z yvazirabad $
 * $URL: https://svn.code.sf.net/p/jsbml/code/trunk/extensions/layout/test/org/sbml/jsbml/ext/layout/TestGlyphTest.java $
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
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */

package org.sbml.jsbml.ext.layout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.sbml.jsbml.NamedSBase;


public class TextGlyphTest {

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.TextGlyph#getGraphicalObject()}.
   */
  @Test
  public void testGetGraphicalObject() {
    TextGlyph test=new TextGlyph();
    test.setGraphicalObject("testName");
    assertEquals("Error",test.getGraphicalObject(),"testName");
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.TextGlyph#getGraphicalObjectInstance()}.
   */
  @Test
  public void testGetGraphicalObjectInstance() {
    TextGlyph test=new TextGlyph();
    test.setGraphicalObject("testName");
    assertTrue(test.getGraphicalObjectInstance()==null);
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.TextGlyph#getOriginOfText()}.
   */
  @Test
  public void testGetOriginOfText() {
    assertTrue(new TextGlyph().getOriginOfText() instanceof String);
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.TextGlyph#getOriginOfTextInstance()}.
   */
  @Test
  public void testGetOriginOfTextInstance() {
    String str="Reference";
    TextGlyph test=new TextGlyph();
    test.setOriginOfText(str);
    assertTrue(test.getOriginOfTextInstance()==null);
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.TextGlyph#getText()}.
   */
  @Test
  public void testGetText() {
    String reaction="reaction";
    TextGlyph test=new TextGlyph();
    test.setText(reaction);
    assertTrue(reaction.equals(test.getText()));
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.TextGlyph#isSetGraphicalObject()}.
   */
  @Test
  public void testIsSetGraphicalObject() {
    TextGlyph text=new TextGlyph();
    text.setGraphicalObject("text");
    assertTrue(text.isSetGraphicalObject());
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.TextGlyph#isSetGraphicalObjectInstance()}.
   */
  @Test
  public void testIsSetGraphicalObjectInstance() {
    TextGlyph test=new TextGlyph();
    test.setGraphicalObject("testName");
    assertTrue(!test.isSetGraphicalObjectInstance());
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.TextGlyph#isSetOriginOfText()}.
   */
  @Test
  public void testIsSetOriginOfText() {
    TextGlyph test=new TextGlyph();
    test.setReference("TextGlyph");
    assertTrue(test.isSetOriginOfText());
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.TextGlyph#isSetText()}.
   */
  @Test
  public void testIsSetText() {
    String text="TEXT";
    TextGlyph test=new TextGlyph();
    test.setText(text);
    assertTrue(text instanceof String);
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.TextGlyph#setGraphicalObject()}.
   */
  @Test
  public void testSetGraphicalObject() {
    String str="Graphics";
    TextGlyph test=new TextGlyph();
    test.setGraphicalObject(str);
    assertTrue(str.equals(test.getGraphicalObject()));

  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.TextGlyph#setOriginOfText(NamedSBase)}.
   */
  @Test
  public void testSetOriginOfTextNamedSBase() {
    String str="Glyph2";
    TextGlyph glyph1=new TextGlyph("Glyph1");
    TextGlyph glyph2=new TextGlyph(str);
    glyph1.setOriginOfText(glyph2);
    assertTrue(str.equals(glyph1.getOriginOfText()));
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.TextGlyph#setOriginOfText(String)}.
   */
  @Test
  public void testSetOriginOfTextString() {
    String str="Reference";
    TextGlyph test=new TextGlyph();
    test.setOriginOfText(str);
    assertTrue(str.equals(test.getOriginOfText()));
  }

  /**
   * Test method for {@link org.sbml.jsbml.ext.layout.TextGlyph#setText()}.
   */
  @Test
  public void testSetText() {
    String text="TEXT";
    TextGlyph test=new TextGlyph();
    test.setText(text);
    assertTrue(text.equals(test.getText()));
  }
}