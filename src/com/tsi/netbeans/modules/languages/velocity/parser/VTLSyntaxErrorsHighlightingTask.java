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

import com.tsi.netbeans.modules.languages.velocity.jcclexer.ParseException;
import com.tsi.netbeans.modules.languages.velocity.jcclexer.Token;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;
import org.netbeans.modules.parsing.spi.Parser.Result;
import org.netbeans.modules.parsing.spi.ParserResultTask;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.netbeans.modules.parsing.spi.SchedulerEvent;
import org.netbeans.spi.editor.hints.ErrorDescription;
import org.netbeans.spi.editor.hints.ErrorDescriptionFactory;
import org.netbeans.spi.editor.hints.HintsController;
import org.netbeans.spi.editor.hints.Severity;
import org.openide.text.NbDocument;
import org.openide.util.Exceptions;

/**
 *
 * @author <a href="mailto:werner.jaeger@t-systems.com">Werner Jaeger</a>
 */
public class VTLSyntaxErrorsHighlightingTask extends ParserResultTask
{
   /**
    * Creates new {@code VTLSyntaxErrorsHighlightingTask}.
    */
   public VTLSyntaxErrorsHighlightingTask()
   {
   }

   @Override
   public void run(final Result result, final SchedulerEvent event)
   {
      try
      {
         final VTLParserResult        vtlResult    = (VTLParserResult)result;
         final List<ParseException>   syntaxErrors = vtlResult.getParser().getSyntaxErrors();
         final Document               document     = result.getSnapshot().getSource().getDocument(false);
         final List<ErrorDescription> errors       = new ArrayList<ErrorDescription>();

         VTLUpToDateStatusProvider sp = VTLUpToDateStatusProvider.forDocument(document);

         if (sp != null)
            sp.setProcessingStatus();

         for (final ParseException syntaxError : syntaxErrors)
         {
            final Token token  = syntaxError.currentToken;
            final int   iStart = NbDocument.findLineOffset((StyledDocument)document, Math.max(token.beginLine - 1, 0)) + Math.max(token.beginColumn - 1, 0);
            final int   iEnd   = NbDocument.findLineOffset((StyledDocument)document, Math.max(token.endLine - 1, 0)) + token.endColumn;

            final ErrorDescription errorDescription = ErrorDescriptionFactory.createErrorDescription
            (
               Severity.ERROR,
               syntaxError.getMessage(),
               document,
               document.createPosition(iStart),
               document.createPosition(iEnd)
            );

            errors.add(errorDescription);
         }
         HintsController.setErrors(document, "velocity", errors);

         if (sp != null)
            sp.setOkStatus();
      }
      catch (BadLocationException ex1)
      {
         Exceptions.printStackTrace(ex1);
      }
      catch (org.netbeans.modules.parsing.spi.ParseException ex1)
      {
         Exceptions.printStackTrace(ex1);
      }
   }

   @Override
   public int getPriority()
   {
      return(100);
   }

   @Override
   public Class<? extends Scheduler> getSchedulerClass()
   {
      return(Scheduler.EDITOR_SENSITIVE_TASK_SCHEDULER);
   }

   @Override
   public void cancel()
   {
   }
}
