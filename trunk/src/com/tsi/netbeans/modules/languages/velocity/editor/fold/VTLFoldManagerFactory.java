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

import org.netbeans.spi.editor.fold.FoldManager;
import org.netbeans.spi.editor.fold.FoldManagerFactory;

/**
 *
 * @author <a href="mailto:werner.jaeger@t-systems.com">Werner Jaeger</a>
 */
public class VTLFoldManagerFactory implements FoldManagerFactory
{
   /**
    * Creates new {@code VTLFoldManagerFactory}.
    */
   public VTLFoldManagerFactory()
   {
   }

   public FoldManager createFoldManager()
   {
      return(new VTLFoldManager());
   }
}
