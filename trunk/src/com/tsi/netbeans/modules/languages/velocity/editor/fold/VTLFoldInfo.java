package com.tsi.netbeans.modules.languages.velocity.editor.fold;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Position;

final class VTLFoldInfo implements Comparable
{
   enum State
   {
      OLD,
      NEW,
      UNTOUCHED;
   }

   private Position m_iStart;
   private Position m_iEnd;
   private boolean  m_fCollapseByDefault;
   private State    m_State;

   VTLFoldInfo(final Document document, final int iStart, final int iEnd, final boolean fCollapseByDefault) throws BadLocationException
   {
      m_iStart             = document.createPosition(iStart);
      m_iEnd               = document.createPosition(iEnd);
      m_fCollapseByDefault = fCollapseByDefault;
      m_State              = State.NEW;
   }

   void setState(final State state)
   {
      m_State = state;
   }

   State getState()
   {
      return(m_State);
   }

   @Override
   public int hashCode()
   {
      return(1);
   }

   @Override
   public boolean equals(final Object oObject)
   {
      final boolean fRet;

      if (oObject instanceof VTLFoldInfo)
         fRet = (compareTo(oObject) == 0);
      else
         fRet = false;

      return(fRet);
   }

   public int compareTo(final Object object)
   {
      final VTLFoldInfo localFoldInfo = (VTLFoldInfo)object;
      final int         iRet;

      if (m_iStart.getOffset() < localFoldInfo.m_iStart.getOffset())
         iRet = -1;
      else if (m_iStart.getOffset() > localFoldInfo.m_iStart.getOffset())
         iRet = 1;
      else if (m_iEnd.getOffset() < localFoldInfo.m_iEnd.getOffset())
         iRet = -1;
      else if (m_iEnd.getOffset() > localFoldInfo.m_iEnd.getOffset())
         iRet = 1;
      else
         iRet = 0;

      return(iRet);
   }
}
