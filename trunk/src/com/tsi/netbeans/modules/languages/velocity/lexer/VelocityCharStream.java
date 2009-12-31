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

import com.tsi.netbeans.modules.languages.velocity.jcclexer.CharStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import org.netbeans.spi.lexer.LexerInput;

/**
 * Reads input chars from LexerInput, in place of standard InputStream.
 * 
 * @author <a href="mailto:werner.jaeger@t-systems.com">Werner Jaeger</a>
 */
public class VelocityCharStream implements CharStream
{
   static boolean m_fStaticFlag;
   private final LexerInput m_Input;

   /**
    * Creates new {@code VelocityCharStream}.
    */
   public VelocityCharStream(final LexerInput input)
   {
      m_Input = input;
   }

   VelocityCharStream(final Reader stream, final int i, final int i0)
   {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   VelocityCharStream(final InputStream stream, final String strEncoding, final int i, final int i0) throws UnsupportedEncodingException
   {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   @Override
   public char BeginToken() throws IOException
   {
      final int i = m_Input.read();

      if (i == LexerInput.EOF)
         throw new IOException("EOF encountered");

      return((char)i);
   }

   @Override
   public String GetImage()
   {
      return(m_Input.readText().toString());
   }

   @Override
   public char[] GetSuffix(final int iLen)
   {
      if (iLen > m_Input.readLength())
         throw new IllegalArgumentException();

      return(m_Input.readText(m_Input.readLength() - iLen, m_Input.readLength()).toString().toCharArray());
   }

   void ReInit(final Reader stream, final int i, final int i0)
   {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   void ReInit(final InputStream stream, final String strEncoding, final int i, final int i0) throws UnsupportedEncodingException
   {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   @Override
   public void backup(final int iAmmont)
   {
      m_Input.backup(iAmmont);
   }

   @Override
   public int getBeginColumn()
   {
      return(0);
   }

   @Override
   public int getBeginLine()
   {
      return(0);
   }

   @Override
   public int getEndColumn()
   {
      return(0);
   }

   @Override
   public int getEndLine()
   {
      return(0);
   }

   @Override
   public char readChar() throws IOException
   {
      final int i = m_Input.read();

      if (i == LexerInput.EOF)
         throw new IOException("EOF encountered");

      return((char)i);
   }

   @Override
   public int getColumn()
   {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   @Override
   public int getLine()
   {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   @Override
   public void Done()
   {
      throw new UnsupportedOperationException("Not yet implemented");
   }
}
