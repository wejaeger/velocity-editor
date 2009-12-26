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
import com.tsi.netbeans.modules.languages.velocity.jcclexer.node.VelocityAnalyser;
import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeListener;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.api.Task;
import org.netbeans.modules.parsing.spi.ParseException;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.parsing.spi.SourceModificationEvent;
import org.openide.filesystems.FileObject;

/**
 *
 * @author <a href="mailto:werner.jaeger@t-systems.com">Werner Jaeger</a>
 */
public class VTLParser extends Parser
{
   private static final Map<FileObject, Set<VelocityAnalyser>> m_Analysers = new HashMap<FileObject, Set<VelocityAnalyser>>();

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
         m_Parser.addDirective("parse", new Directive(Directive.LINE));
         m_Parser.addDirective("evaluate", new Directive(Directive.LINE));
         m_Parser.addDirective("define", new Directive(Directive.BLOCK));
         final SimpleNode sn = m_Parser.parse(new StringReader(snapshot.getText().toString()), snapshot.getSource().getFileObject().getNameExt());

         if (sn != null)
         {
            final Set<VelocityAnalyser> analysers = m_Analysers.get(sme.getModifiedSource().getFileObject());

            if (analysers != null)
            {
               for (final VelocityAnalyser analyser : analysers)
               {
                  analyser.openTransaction();
                  analyser.visit(sn, null);
                  analyser.commitTransaction();
               }
            }
         }
      }
      catch (com.tsi.netbeans.modules.languages.velocity.jcclexer.ParseException pe)
      {
         Logger.getLogger(VTLParser.class.getName()).log(Level.WARNING, null, pe);
      }
   }

   /**
    * Register an analyser that {@code visit} methode gets called each
    * time the {@link #parse()} methode is done with creating an AST.
    *
    * @param fo the file object for which to register the given analyser.
    *           Must not be {@code null}.
    * @param analyser a reference to an analyser that's visit method shall be
    *        called. If the passed analyser is already registered this methode
    *        returns silently. Must not be {@code null}.
    *
    * @return
    * @throws ParseException
    */
   public static void registerAnalyser(final FileObject fo, final VelocityAnalyser analyser)
   {
      final Set<VelocityAnalyser> analysers = m_Analysers.get(fo);

      if (analysers == null)
      {
         final Set<VelocityAnalyser> analyserSet = new HashSet<VelocityAnalyser>();
         analyserSet.add(analyser);
         m_Analysers.put(fo, analyserSet);
      }
      else
         analysers.add(analyser);
   }

   /**
    * De register an registered analyser.
    *
    * @param fo the file object for which to remove the given analyser.
    *           Must not be {@code null}.
    * @param analyser a reference to an analyser to be removed. If the passed
    *        analyser id not registered this methode returns silently.
    *        Must not be {@code null}.
    *
    * @return
    * @throws ParseException
    */
   public static void deregisterAnalyser(final FileObject fo, final VelocityAnalyser analyser)
   {
      final Set<VelocityAnalyser> analysers = m_Analysers.get(fo);

      if (analysers != null)
         analysers.remove(analyser);
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
