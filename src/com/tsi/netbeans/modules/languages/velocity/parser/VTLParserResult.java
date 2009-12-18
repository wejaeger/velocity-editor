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

import com.tsi.netbeans.modules.languages.velocity.jcclexer.VelocityParser;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.spi.Parser.Result;

/**
 *
 * @author <a href="mailto:werner.jaeger@t-systems.com">Werner Jaeger</a>
 */
public class VTLParserResult extends Result
{
   private final VelocityParser m_Parser;

   private boolean m_fValid;

   VTLParserResult(final Snapshot snapshot, final VelocityParser parser)
   {
      super(snapshot);

      m_Parser = parser;
      m_fValid = true;
   }

   public VelocityParser getParser() throws org.netbeans.modules.parsing.spi.ParseException
   {
      if (!m_fValid)
         throw new org.netbeans.modules.parsing.spi.ParseException ();

      return(m_Parser);
   }

   @Override
   protected void invalidate()
   {
      m_fValid = false;
   }
}
