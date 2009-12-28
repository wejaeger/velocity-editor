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

import com.tsi.netbeans.modules.languages.velocity.parser.VTLParser;
import java.util.HashMap;
import java.util.Map;
import javax.swing.event.DocumentEvent;
import org.netbeans.api.editor.fold.Fold;
import org.netbeans.spi.editor.fold.FoldHierarchyTransaction;
import org.netbeans.spi.editor.fold.FoldManager;
import org.netbeans.spi.editor.fold.FoldOperation;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;

/**
 *
 * @author <a href="mailto:werner.jaeger@t-systems.com">Werner Jaeger</a>
 */
public class VTLFoldManager implements FoldManager
{
   private FoldOperation          m_Operation;
   private VTLFoldAnalyser        m_Analyser;
   private Map<VTLFoldInfo, Fold> m_CurrentFolds;
   private FileObject             m_File;

   /**
    * Creates new {@code VTLFoldManager}.
    */
   public VTLFoldManager()
   {
   }

   public void init(final FoldOperation operation)
   {
      m_Operation = operation;
   }

   public void initFolds(final FoldHierarchyTransaction transaction)
   {

      final DataObject localDataObject = (DataObject)m_Operation.getHierarchy().getComponent().getDocument().getProperty("stream");

      if (localDataObject != null)
      {
         m_CurrentFolds = new HashMap<VTLFoldInfo, Fold>();
         m_Analyser     = new VTLFoldAnalyser(m_Operation, m_CurrentFolds);
         m_File         = localDataObject.getPrimaryFile();

         VTLParser.registerAnalyser(m_File, m_Analyser);
      }
   }

   public void insertUpdate(final DocumentEvent evt, final FoldHierarchyTransaction transaction)
   {
   }

   public void removeUpdate(final DocumentEvent evt, final FoldHierarchyTransaction transaction)
   {
   }

   public void changedUpdate(final DocumentEvent evt, final FoldHierarchyTransaction transaction)
   {
   }

   public void removeEmptyNotify(final Fold epmtyFold)
   {
   }

   public void removeDamagedNotify(final Fold damagedFold)
   {
   }

   public void expandNotify(final Fold expandedFold)
   {
   }

   public void release()
   {
      VTLParser.deregisterAnalyser(m_File, m_Analyser);
      m_CurrentFolds = null;
      m_Analyser     = null;
      m_File         = null;
   }
}
