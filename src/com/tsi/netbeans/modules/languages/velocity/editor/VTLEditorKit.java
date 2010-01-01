/*
 * $Id$
 *
 * Copyright (c) 2009 T-Systems International GmbH.
 * All rights reserved.
 * This software is the confidential and proprietary information
 * of T-Systems International GmbH.
 *
 */
package com.tsi.netbeans.modules.languages.velocity.editor;

import org.netbeans.modules.editor.NbEditorKit;

/**
 * The VTL editor kit class.
 *
 * @author <a href="mailto:werner.jaeger@t-systems.com">Werner Jaeger</a>
 */
public class VTLEditorKit extends NbEditorKit
{
   /**
    * Creates new {@code VTLEditorKit}.
    */
   public VTLEditorKit()
   {
   }

   /**
    * {@inheritDoc}
    */
   @Override public String getContentType()
   {
      return("text/x-velocity");
   }
}
