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

import static com.tsi.netbeans.modules.languages.velocity.jcclexer.VelocityParserConstants.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.netbeans.spi.lexer.LanguageHierarchy;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;

/**
 *
 * @author <a href="mailto:werner.jaeger@t-systems.com">Werner Jaeger</a>
 */
public class VTLLanguageHierarchy extends LanguageHierarchy<VTLTokenId>
{
   private static List<VTLTokenId>         m_Tokens;
   private static Map<Integer, VTLTokenId> m_IdToToken;

   /**
    * Creates new {@code VTLLanguageHierarchy}.
    */
   VTLLanguageHierarchy()
   {
   }

   private static void init()
   {
      m_Tokens = Arrays.<VTLTokenId>asList(new VTLTokenId[]
              {
                  new VTLTokenId ("EOF", "whitespace", EOF),
                  new VTLTokenId ("LBRACKET", "separator", LBRACKET),
                  new VTLTokenId ("RBRACKET", "separator", RBRACKET),
                  new VTLTokenId ("COMMA", "separator", COMMA),
                  new VTLTokenId ("DOUBLEDOT", "separator", DOUBLEDOT),
                  new VTLTokenId ("COLON", "separator", COLON),
                  new VTLTokenId ("LEFT_CURLEY", "separator", LEFT_CURLEY),
                  new VTLTokenId ("RIGHT_CURLEY", "separator", RIGHT_CURLEY),
                  new VTLTokenId ("LPAREN", "separator", LPAREN),
                  new VTLTokenId ("RPAREN", "separator", RPAREN),
                  new VTLTokenId ("WORD_IN", "keyword", WORD_IN),
                  new VTLTokenId ("REFMOD2_RPAREN", "separator", REFMOD2_RPAREN),
                  new VTLTokenId ("ESCAPE_DIRECTIVE", "", ESCAPE_DIRECTIVE),
                  new VTLTokenId ("SET_DIRECTIVE", "directive", SET_DIRECTIVE),
                  new VTLTokenId ("DOLLAR", "separator", DOLLAR),
                  new VTLTokenId ("DOLLARBANG", "separator", DOLLARBANG),
                  new VTLTokenId ("HASH", "separator", HASH),
                  new VTLTokenId ("SINGLE_LINE_COMMENT_START", "comment", SINGLE_LINE_COMMENT_START),
                  new VTLTokenId ("DOUBLE_ESCAPE", "", DOUBLE_ESCAPE),
                  new VTLTokenId ("ESCAPE", "", ESCAPE),
                  new VTLTokenId ("TEXT", "", TEXT),
                  new VTLTokenId ("SINGLE_LINE_COMMENT", "comment", SINGLE_LINE_COMMENT),
                  new VTLTokenId ("FORMAL_COMMENT", "comment", FORMAL_COMMENT),
                  new VTLTokenId ("MULTI_LINE_COMMENT", "comment", MULTI_LINE_COMMENT),
                  new VTLTokenId ("STRING_LITERAL", "string", STRING_LITERAL),
                  new VTLTokenId ("TRUE", "boolean", TRUE),
                  new VTLTokenId ("FALSE", "boolean", FALSE),
                  new VTLTokenId ("NEWLINE", "", NEWLINE),
                  new VTLTokenId ("MINUS", "operator", MINUS),
                  new VTLTokenId ("PLUS", "operator", PLUS),
                  new VTLTokenId ("MULTIPLY", "operator", MULTIPLY),
                  new VTLTokenId ("DIVIDE", "operator", DIVIDE),
                  new VTLTokenId ("MODULUS", "operator", MODULUS),
                  new VTLTokenId ("LOGICAL_AND", "operator", LOGICAL_AND),
                  new VTLTokenId ("LOGICAL_OR", "operator", LOGICAL_OR),
                  new VTLTokenId ("LOGICAL_LT", "operator", LOGICAL_LT),
                  new VTLTokenId ("LOGICAL_LE", "operator", LOGICAL_LE),
                  new VTLTokenId ("LOGICAL_GT", "operator", LOGICAL_GT),
                  new VTLTokenId ("LOGICAL_GE", "operator", LOGICAL_GE),
                  new VTLTokenId ("LOGICAL_EQUALS", "operator", LOGICAL_EQUALS),
                  new VTLTokenId ("LOGICAL_NOT_EQUALS", "operator", LOGICAL_NOT_EQUALS),
                  new VTLTokenId ("LOGICAL_NOT", "operator", LOGICAL_NOT),
                  new VTLTokenId ("EQUALS", "operator", EQUALS),
                  new VTLTokenId ("END", "directive", END),
                  new VTLTokenId ("FOREACH_DIRECTIVE", "directive", FOREACH_DIRECTIVE),
                  new VTLTokenId ("MACRO_DIRECTIVE", "directive", MACRO_DIRECTIVE),
                  new VTLTokenId ("MACROCALL_DIRECTIVE", "directive", MACROCALL_DIRECTIVE),
                  new VTLTokenId ("INCLUDE_DIRECTIVE", "directive", INCLUDE_DIRECTIVE),
                  new VTLTokenId ("IF_DIRECTIVE", "directive", IF_DIRECTIVE),
                  new VTLTokenId ("ELSEIF_DIRECTIVE", "directive", ELSEIF_DIRECTIVE),
                  new VTLTokenId ("ELSE_DIRECTIVE", "directive", ELSE_DIRECTIVE),
                  new VTLTokenId ("STOP_DIRECTIVE", "directive", STOP_DIRECTIVE),
                  new VTLTokenId ("DIGIT", "number", DIGIT),
                  new VTLTokenId ("INTEGER_LITERAL", "number", INTEGER_LITERAL),
                  new VTLTokenId ("FLOATING_POINT_LITERAL", "number", FLOATING_POINT_LITERAL),
                  new VTLTokenId ("EXPONENT", "operator", EXPONENT),
                  new VTLTokenId ("LETTER", "", LETTER),
                  new VTLTokenId ("DIRECTIVE_CHAR", "", DIRECTIVE_CHAR),
                  new VTLTokenId ("WORD", "", WORD),
                  new VTLTokenId ("BRACKETED_WORD", "", BRACKETED_WORD),
                  new VTLTokenId ("ALPHA_CHAR", "", ALPHA_CHAR),
                  new VTLTokenId ("ALPHANUM_CHAR", "", ALPHANUM_CHAR),
                  new VTLTokenId ("IDENTIFIER_CHAR", "", IDENTIFIER_CHAR),
                  new VTLTokenId ("IDENTIFIER", "identifier", IDENTIFIER),
                  new VTLTokenId ("DOT", "separator", DOT),
                  new VTLTokenId ("LCURLY", "separator", LCURLY),
                  new VTLTokenId ("RCURLY", "separator", RCURLY),
                  new VTLTokenId ("REFERENCE_TERMINATOR", "separator", REFERENCE_TERMINATOR),
                  new VTLTokenId ("DIRECTIVE_TERMINATOR", "separator", DIRECTIVE_TERMINATOR)
              });

      m_IdToToken = new HashMap<Integer, VTLTokenId>();

      for (VTLTokenId token : m_Tokens)
         m_IdToToken.put(token.ordinal(), token);
   }

   static synchronized VTLTokenId getToken(final int iId)
   {
      if (m_IdToToken == null)
         init();

      return(m_IdToToken.get(iId));
   }

   @Override
   protected Collection<VTLTokenId> createTokenIds()
   {
      if (m_Tokens == null)
         init();

      return(m_Tokens);
   }

   @Override
   protected Lexer<VTLTokenId> createLexer(final LexerRestartInfo<VTLTokenId> info)
   {
      return(new VTLLexer(info));
   }

   @Override
   protected String mimeType()
   {
       return("text/x-velocity");
   }
}
