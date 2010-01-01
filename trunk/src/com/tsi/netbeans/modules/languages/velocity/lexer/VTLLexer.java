/*
 * $Id$
 *
 * Copyright (c) 2009 T-Systems International GmbH.
 * All rights reserved.
 * This software is the confidential and proprietary information
 * of T-Systems International GmbH.
 *
 */
package com.tsi.netbeans.modules.languages.velocity.lexer;

import com.tsi.netbeans.modules.languages.velocity.jcclexer.VelocityParserConstants;
import com.tsi.netbeans.modules.languages.velocity.jcclexer.VelocityParserTokenManager;
import org.netbeans.api.lexer.Token;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;

/**
 * A lexer for the VTL language.
 *
 * @author <a href="mailto:werner.jaeger@t-systems.com">Werner Jaeger</a>
 */
class VTLLexer implements Lexer<VTLTokenId>
{
   private final LexerRestartInfo<VTLTokenId> m_Info;
   private final VelocityParserTokenManager   m_VelocityParserTokenManager;

   private Integer m_CurrLexState;

   /**
    * Creates new {@code VTLLexer}.
    *
    * @param info a reference to the lexer info object.
    *        Must not be {@code null}.
    */
   VTLLexer(final LexerRestartInfo<VTLTokenId> info)
   {
      m_Info                       = info;
      m_VelocityParserTokenManager = new VelocityParserTokenManager(new VelocityCharStream(info.input()));
      m_CurrLexState               = null;
   }

   /**
    * {@inheritDoc}
    */
   @Override public Token<VTLTokenId> nextToken()
   {
      final Token<VTLTokenId> returnToken;

      com.tsi.netbeans.modules.languages.velocity.jcclexer.Token token = m_VelocityParserTokenManager.getNextToken();

      if (token.kind == VelocityParserConstants.SINGLE_LINE_COMMENT_START || token.kind == VelocityParserConstants.SINGLE_LINE_COMMENT)
      {
         token = m_VelocityParserTokenManager.getNextToken();
         m_CurrLexState = Integer.valueOf(1);
      }
      else if (token.kind == VelocityParserConstants.IDENTIFIER)
      {
         while (true)
         {
            final int c = m_Info.input().read();
            m_Info.input().backup(1);
            if (c == '.')
            {
               token = m_VelocityParserTokenManager.getNextToken();
               token = m_VelocityParserTokenManager.getNextToken();
            }
            else
               break;
         }
         m_CurrLexState = Integer.valueOf(2);
      }
      else
         m_CurrLexState = null;

      if (m_Info.input().readLength() < 1)
         returnToken = null;
      else
         returnToken = m_Info.tokenFactory().createToken(VTLLanguageHierarchy.getToken(token.kind));

      return(returnToken);
   }

   /**
    * {@inheritDoc}
    */
   @Override public Object state()
   {
      return(null);
   }

   /**
    * {@inheritDoc}
    */
   @Override public void release()
   {
   }
}
