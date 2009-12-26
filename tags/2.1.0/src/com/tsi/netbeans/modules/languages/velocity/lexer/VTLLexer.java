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

import com.tsi.netbeans.modules.languages.velocity.jcclexer.VelocityParserTokenManager;
import org.netbeans.api.lexer.Token;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;

/**
 *
 * @author <a href="mailto:werner.jaeger@t-systems.com">Werner Jaeger</a>
 */
class VTLLexer implements Lexer<VTLTokenId>
{
    private final LexerRestartInfo<VTLTokenId> m_Info;
    private final VelocityParserTokenManager   m_VelocityParserTokenManager;

   /**
    * Creates new {@code VTLLexer}.
    */
   VTLLexer(final LexerRestartInfo<VTLTokenId> info)
   {
        m_Info                       = info;
        m_VelocityParserTokenManager = new VelocityParserTokenManager(new VelocityCharStream(info.input()));
   }

   @Override
   public Token<VTLTokenId> nextToken()
   {
      final Token<VTLTokenId> returnToken;

      final com.tsi.netbeans.modules.languages.velocity.jcclexer.Token token = m_VelocityParserTokenManager.getNextToken();

      if (m_Info.input().readLength() < 1)
         returnToken = null;
      else
         returnToken = m_Info.tokenFactory().createToken(VTLLanguageHierarchy.getToken(token.kind));

      return(returnToken);
   }

   @Override
   public Object state()
   {
      return(null);
   }

   @Override
   public void release()
   {
   }
}
