/*
 * $Id$
 *
 * Copyright (c) 2009 T-Systems International GmbH.
 * All rights reserved.
 * This software is the confidential and proprietary information
 * of T-Systems International GmbH.
 *
 */
package com.tsi.netbeans.modules.languages.velocity.editor.fold;

import javax.swing.JComponent;
import javax.swing.text.JTextComponent;
import org.netbeans.editor.CodeFoldingSideBar;
import org.netbeans.editor.SideBarFactory;

/**
 * Creates VTL code folding sidebar to be added in the editor sidebar.
 *
 * @author <a href="mailto:werner.jaeger@t-systems.com">Werner Jaeger</a>
 */
public class VTLCodeFoldingSideBarFactory implements SideBarFactory
{
   /**
    * Creates new {@code VTLCodeFoldingSideBarFactory}.
    */
   public VTLCodeFoldingSideBarFactory()
   {
   }

   /**
    * {@inheritDoc}
    */
   public JComponent createSideBar(final JTextComponent jtc)
   {
      return(new CodeFoldingSideBar(jtc));
   }
}
