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

import com.tsi.netbeans.modules.languages.velocity.jcclexer.Directive;
import com.tsi.netbeans.modules.languages.velocity.jcclexer.VelocityParser;
import com.tsi.netbeans.modules.languages.velocity.jcclexer.node.SimpleNode;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeListener;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.api.Task;
import org.netbeans.modules.parsing.spi.ParseException;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.parsing.spi.SourceModificationEvent;

/**
 *
 * @author <a href="mailto:werner.jaeger@t-systems.com">Werner Jaeger</a>
 */
public class VTLParser extends Parser
{
   private final VelocityParser m_Parser;

   private Snapshot m_Snapshot;

   /**
    * Creates new {@code VTLParser}.
    */
   public VTLParser()
   {
      m_Parser = new VelocityParser();
   }

   @Override
   public void parse(final Snapshot snapshot, final Task task, final SourceModificationEvent sme) throws ParseException
   {
      m_Snapshot = snapshot;

      try
      {
         m_Parser.addDirective("include", new Directive(Directive.LINE));
         m_Parser.addDirective("parse", new Directive(Directive.LINE));
         m_Parser.addDirective("evaluate", new Directive(Directive.LINE));
         m_Parser.addDirective("define", new Directive(Directive.BLOCK));
         final SimpleNode sn = m_Parser.parse(new StringReader(snapshot.getText().toString()), snapshot.getSource().getFileObject().getNameExt());
      }
      catch (com.tsi.netbeans.modules.languages.velocity.jcclexer.ParseException pe)
      {
         Logger.getLogger(VTLParser.class.getName()).log(Level.WARNING, null, pe);
      }
   }

   @Override
   public Result getResult(final Task task) throws ParseException
   {
      return(new VTLParserResult(m_Snapshot, m_Parser));
   }

   @Override
   public void cancel()
   {
   }

   @Override
   public void addChangeListener(final ChangeListener cl)
   {
   }

   @Override
   public void removeChangeListener(final ChangeListener cl)
   {
   }
}
