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

import java.util.Collection;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.parsing.spi.ParserFactory;

/**
 *
 * @author <a href="mailto:werner.jaeger@t-systems.com">Werner Jaeger</a>
 */
public class VTLParserFactory extends ParserFactory
{
   /**
    * Creates new {@code VTLParserFactory}.
    */
   public VTLParserFactory()
   {
   }

   @Override
   public Parser createParser(final Collection<Snapshot> snapshots)
   {
      return(new VTLParser());
   }
}
