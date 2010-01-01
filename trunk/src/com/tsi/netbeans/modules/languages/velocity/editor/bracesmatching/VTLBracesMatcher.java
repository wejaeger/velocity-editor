/*
 * $Id$
 *
 * Copyright (c) 2009 T-Systems International GmbH.
 * All rights reserved.
 * This software is the confidential and proprietary information
 * of T-Systems International GmbH.
 *
 */
package com.tsi.netbeans.modules.languages.velocity.editor.bracesmatching;

import com.tsi.netbeans.modules.languages.velocity.jcclexer.VelocityParserConstants;
import com.tsi.netbeans.modules.languages.velocity.lexer.VTLTokenId;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.BadLocationException;
import org.netbeans.api.lexer.Language;
import org.netbeans.api.lexer.Token;
import org.netbeans.api.lexer.TokenHierarchy;
import org.netbeans.api.lexer.TokenId;
import org.netbeans.api.lexer.TokenSequence;
import org.netbeans.spi.editor.bracesmatching.BracesMatcher;
import org.netbeans.spi.editor.bracesmatching.BracesMatcherFactory;
import org.netbeans.spi.editor.bracesmatching.MatcherContext;

/**
 * Implemnts a VTL braces matcher.
 *
 * @author <a href="mailto:werner.jaeger@t-systems.com">Werner Jaeger</a>
 */
public class VTLBracesMatcher implements BracesMatcher, BracesMatcherFactory
{
   private final MatcherContext m_Context;

   private int     m_iOriginOffset;
   private boolean m_fBackward;

   /**
    * Creates new {@code VTLBracesMatcher}.
    */
   public VTLBracesMatcher()
   {
      this(null);
   }

   /**
    * Creates new {@code VTLBracesMatcher}.
    */
   private VTLBracesMatcher(final MatcherContext mc)
   {
      m_Context = mc;
   }

   /**
    * {@inheritDoc}
    */
   public int[] findOrigin() throws InterruptedException, BadLocationException
   {
      final TokenHierarchy                         localTokenHierarchy = TokenHierarchy.get(m_Context.getDocument());
      final List<TokenSequence<? extends TokenId>> localList           = getTokenSequences(localTokenHierarchy, m_Context.getSearchOffset(), VTLTokenId.getLanguage());

      final int[] aiOrigins;
      if (!localList.isEmpty())
      {
         final TokenSequence<? extends TokenId> ts    = localList.get(localList.size() - 1);
         final Token<? extends TokenId>         token = ts.offsetToken();

         switch (token.id().ordinal())
         {
            case VelocityParserConstants.ELSEIF_DIRECTIVE:
            case VelocityParserConstants.ELSE_DIRECTIVE:
            case VelocityParserConstants.FOREACH_DIRECTIVE:
            case VelocityParserConstants.IF_DIRECTIVE:
            case VelocityParserConstants.MACRO_DIRECTIVE:
               aiOrigins       = new int[] {ts.offset(), ts.offset() + token.length()};
               m_iOriginOffset = aiOrigins[0];
               m_fBackward     = false;
               break;

            case VelocityParserConstants.END:
               aiOrigins       = new int[] {ts.offset(), ts.offset() + token.text().toString().trim().length()};
               m_iOriginOffset = aiOrigins[0];
               m_fBackward     = true;
               break;

            default:
               aiOrigins = null;
         }
      }
      else
         aiOrigins = null;

      return(aiOrigins);
   }

   /**
    * {@inheritDoc}
    */
   public int[] findMatches() throws InterruptedException, BadLocationException
   {
      final TokenHierarchy                         localTokenHierarchy = TokenHierarchy.get(m_Context.getDocument());
      final List<TokenSequence<? extends TokenId>> localList           = getTokenSequences(localTokenHierarchy, m_iOriginOffset, VTLTokenId.getLanguage());

      int[] aiMatches = null;

      if (!localList.isEmpty())
      {
         final TokenSequence<? extends TokenId> ts = localList.get(localList.size() - 1);

         ts.move(m_iOriginOffset);

         final boolean fHasNext;
         if (m_fBackward)
            fHasNext = true;
         else
            fHasNext = ts.moveNext();

         if (fHasNext)
         {
            int iLevel = 0;
            while ((iLevel >= 0) && (m_fBackward ? ts.movePrevious() : ts.moveNext()))
            {
               final Token<? extends TokenId> token = ts.offsetToken();

               switch (token.id().ordinal())
               {
                  case VelocityParserConstants.ELSEIF_DIRECTIVE:
                  case VelocityParserConstants.ELSE_DIRECTIVE:
                     break;

                  case VelocityParserConstants.FOREACH_DIRECTIVE:
                  case VelocityParserConstants.IF_DIRECTIVE:
                  case VelocityParserConstants.MACRO_DIRECTIVE:
                     if (m_fBackward)
                     {
                        if (iLevel == 0)
                           aiMatches = new int[] {ts.offset(), ts.offset() + token.length()};

                        iLevel--;
                     }
                     else
                        iLevel++;

                     break;

                  case VelocityParserConstants.END:
                     if (!m_fBackward)
                     {
                        if (iLevel == 0)
                           aiMatches = new int[] {ts.offset(), ts.offset() + token.length()};

                        iLevel--;
                     }
                     else
                        iLevel++;

                     break;

                  default:
               }
            }
         }
      }

      return(aiMatches);
   }

   /**
    * Creates a {@link VTLBracesMatcher} for searching a VTL document for
    * matching areas.
    *
    * @param mc The context to use for searching. It contains the position of
    *        a caret in a document and allows to report results.
    *
    * @return a reference to a newly created {@link VTLBracesMatcher} object.
    */
   public BracesMatcher createMatcher(final MatcherContext mc)
   {
      return(new VTLBracesMatcher(mc));
   }

   private static List<TokenSequence<? extends TokenId>> getTokenSequences(final TokenHierarchy<?> tokenHierarchy, final int iOriginOffset, final Language<? extends TokenId> language)
   {
      final List<TokenSequence<?>> localList = tokenHierarchy.embeddedTokenSequences(iOriginOffset, false);

      for (int i = localList.size() - 1; i >= 0; --i)
      {
         final TokenSequence<?> localTokenSequence = localList.get(i);
         if (localTokenSequence.language() == language)
            break;

         localList.remove(i);
      }

      return(new ArrayList<TokenSequence<? extends TokenId>>(localList));
   }
}
