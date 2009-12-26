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

import java.util.Map;
import java.util.WeakHashMap;
import javax.swing.text.Document;
import org.netbeans.spi.editor.errorstripe.UpToDateStatus;
import org.netbeans.spi.editor.errorstripe.UpToDateStatusProvider;

/**
 *
 * @author <a href="mailto:werner.jaeger@t-systems.com">Werner Jaeger</a>
 */
class VTLUpToDateStatusProvider extends UpToDateStatusProvider
{
   private static final Map<Document, VTLUpToDateStatusProvider> m_Cache = new WeakHashMap<Document, VTLUpToDateStatusProvider>();

   private final Document m_Document;

   private UpToDateStatus m_Status;

   /**
    * Creates new {@code VTLUpToDateStatusProvider}.
    */
   private VTLUpToDateStatusProvider(final Document document)
   {
      m_Document = document;
      m_Status   = UpToDateStatus.UP_TO_DATE_DIRTY;
      m_Cache.put(document, this);
   }

   static VTLUpToDateStatusProvider forDocument(final Document document)
   {
      final VTLUpToDateStatusProvider localUpToDateStatusProvider;
      final VTLUpToDateStatusProvider localUpToDateStatusProvider1 = m_Cache.get(document);
      if (localUpToDateStatusProvider1 == null)
         localUpToDateStatusProvider = new VTLUpToDateStatusProvider(document);
      else
         localUpToDateStatusProvider = localUpToDateStatusProvider1;
      
      return(localUpToDateStatusProvider);
   }

   @Override
   public UpToDateStatus getUpToDate()
   {
      return(m_Status);
   }

   void setProcessingStatus()
   {
      final UpToDateStatus oldStatus = m_Status;
      m_Status = UpToDateStatus.UP_TO_DATE_PROCESSING;
      firePropertyChange(PROP_UP_TO_DATE, oldStatus, m_Status);
   }

   void setDirtyStatus()
   {
      final UpToDateStatus oldStatus = m_Status;
      m_Status = UpToDateStatus.UP_TO_DATE_DIRTY;
      firePropertyChange(PROP_UP_TO_DATE, oldStatus, m_Status);
   }

   void setOkStatus()
   {
      final UpToDateStatus oldStatus = m_Status;
      m_Status = UpToDateStatus.UP_TO_DATE_OK;
      firePropertyChange(PROP_UP_TO_DATE, oldStatus, m_Status);
   }
}
