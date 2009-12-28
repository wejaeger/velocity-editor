/*
 * $Id$
 *
 * Copyright (c) 2009 T-Systems International GmbH.
 * All rights reserved.
 * This software is the confidential and proprietary information
 * of T-Systems International GmbH.
 *
 */
package com.tsi.netbeans.modules.languages.velocity.parser;

import javax.swing.text.Document;
import org.netbeans.spi.editor.errorstripe.UpToDateStatusProvider;
import org.netbeans.spi.editor.errorstripe.UpToDateStatusProviderFactory;

/**
 *
 * @author <a href="mailto:werner.jaeger@t-systems.com">Werner Jaeger</a>
 */
public class VTLUpToDateStatusProviderFactory implements UpToDateStatusProviderFactory
{
   /**
    * Creates new {@code VTLUpToDateStatusProviderFactory}.
    */
   public VTLUpToDateStatusProviderFactory()
   {
   }

   public UpToDateStatusProvider createUpToDateStatusProvider(final Document document)
   {
      return(VTLUpToDateStatusProvider.forDocument(document));
   }
}
