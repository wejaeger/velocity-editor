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

import com.tsi.netbeans.modules.languages.velocity.jcclexer.Token;
import com.tsi.netbeans.modules.languages.velocity.jcclexer.node.ASTElseIfStatement;
import com.tsi.netbeans.modules.languages.velocity.jcclexer.node.ASTElseStatement;
import com.tsi.netbeans.modules.languages.velocity.jcclexer.node.ASTForEachStatement;
import com.tsi.netbeans.modules.languages.velocity.jcclexer.node.ASTIfStatement;
import com.tsi.netbeans.modules.languages.velocity.jcclexer.node.ASTMacroStatement;
import com.tsi.netbeans.modules.languages.velocity.jcclexer.node.SimpleNode;
import com.tsi.netbeans.modules.languages.velocity.jcclexer.node.VelocityAnalyser;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.netbeans.api.editor.fold.Fold;
import org.netbeans.api.editor.fold.FoldType;
import org.netbeans.spi.editor.fold.FoldHierarchyTransaction;
import org.netbeans.spi.editor.fold.FoldOperation;
import org.openide.text.NbDocument;

/**
 * Analyses an abstract syntax tree to find and add code folds to VTL editor.
 *
 * @author <a href="mailto:werner.jaeger@t-systems.com">Werner Jaeger</a>
 */
class VTLFoldAnalyser extends VelocityAnalyser
{
   private final FoldOperation          m_Operation;
   private final Map<VTLFoldInfo, Fold> m_CurrentFolds;
   private final StyledDocument         m_Document;

   private FoldHierarchyTransaction     m_Transaction;

   /**
    * Creates new {@code VTLFoldAnalyser}.
    */
   VTLFoldAnalyser(final FoldOperation operation, final Map<VTLFoldInfo, Fold> currentFolds)
   {
      m_Operation    = operation;
      m_CurrentFolds = currentFolds;
      m_Document     = (StyledDocument)m_Operation.getHierarchy().getComponent().getDocument();
   }

   @Override
   public Object visit(final ASTMacroStatement node, final Object oData)
   {
      return (visitImpl(node, oData));
   }

   @Override
   public Object visit(final ASTForEachStatement node, final Object oData)
   {
      return (visitImpl(node, oData));
   }

   @Override
   public Object visit(final ASTIfStatement node, final Object oData)
   {
      return (visitImpl(node, oData));
   }

   @Override
   public Object visit(final ASTElseIfStatement node, final Object oData)
   {
      return (visitImpl(node, oData));
   }

   @Override
   public Object visit(final ASTElseStatement node, final Object oData)
   {
      return (visitImpl(node, oData));
   }

   private Object visitImpl(final SimpleNode node, final Object oData)
   {
      final Token  firstToken = node.getFirstToken();
      final Token  lastToken  = node.getLastToken();
      final int    iStart     = NbDocument.findLineOffset(m_Document, firstToken.beginLine - 1) + firstToken.beginColumn - 1;
      final int    iEnd       = NbDocument.findLineOffset(m_Document, lastToken.endLine - 1) + lastToken.endColumn - (lastToken.image.length() - lastToken.image.trim().length());
      final String strDesc    = firstToken.image + firstToken.next.image + firstToken.next.next.image + " ...";
      try
      {
         final VTLFoldInfo info = new VTLFoldInfo(m_Document, iStart, iEnd, false);
         final Fold oldFold = m_CurrentFolds.get(info);
         if (oldFold == null)
         {
            final Fold fold = m_Operation.addToHierarchy(new FoldType(firstToken.image), strDesc, false, iStart, iEnd, 0, 0, info, m_Transaction);
            m_CurrentFolds.put(info, fold);
         }
         else
            ((VTLFoldInfo)m_Operation.getExtraInfo(oldFold)).setState(VTLFoldInfo.State.OLD);
      }
      catch (BadLocationException ble)
      {
         Logger.getLogger(VTLFoldAnalyser.class.getName()).log(Level.WARNING, null, ble);
      }
      return node.childrenAccept(this, oData);
   }

   public void openTransaction()
   {
      if (m_Transaction == null)
         m_Transaction = m_Operation.openTransaction();

      for (final Fold fold : m_CurrentFolds.values())
         ((VTLFoldInfo)m_Operation.getExtraInfo(fold)).setState(VTLFoldInfo.State.UNTOUCHED);
   }

   public void commitTransaction()
   {
      final Set<VTLFoldInfo> untouched = new HashSet<VTLFoldInfo>();
      for (final Fold fold : m_CurrentFolds.values())
      {
         final VTLFoldInfo info = ((VTLFoldInfo)m_Operation.getExtraInfo(fold));
         if (info.getState() == VTLFoldInfo.State.UNTOUCHED)
         {
            m_Operation.removeFromHierarchy(fold, m_Transaction);
           untouched.add(info);
         }
      }

      for (final VTLFoldInfo info : untouched)
         m_CurrentFolds.remove(info);

      if (m_Transaction != null)
         m_Transaction.commit();

      m_Transaction = null;
   }
}
