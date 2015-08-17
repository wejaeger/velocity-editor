/*
 * $Id$
 *
 * Copyright (c) 2009 T-Systems International GmbH.
 * All rights reserved.
 * This software is the confidential and proprietary information
 * of T-Systems International GmbH.
 *
 */
package com.tsi.netbeans.modules.languages.velocity;

import java.io.IOException;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataNode;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.nodes.CookieSet;
import org.openide.nodes.Node;
import org.openide.nodes.Children;
import org.openide.util.Lookup;
import org.openide.text.DataEditorSupport;

/**
 * Provides support for handling of data objects with multiple files.
 *
 * @author <a href="mailto:werner.jaeger@t-systems.com">Werner Jaeger</a>
 */
public class VTLDataObject extends MultiDataObject
{
   /**
    * Creates new {@code VTLDataObject}.
    *
    * @param fo the primary file object.
    * @param loader loader of this data object.
    *
    * @throws DataObjectExistsException if there is already a data object for
    *         this primary file
    * @throws IOException in case of an IO error.
    */
   public VTLDataObject(final FileObject fo, final MultiFileLoader loader) throws DataObjectExistsException, IOException
   {
      super(fo, loader);

      final CookieSet cookies = getCookieSet();
      cookies.add((Node.Cookie)DataEditorSupport.create(this, getPrimaryEntry(), cookies));
   }

   /**
    * {@inheritDoc}
    */
   @Override protected Node createNodeDelegate()
   {
      return(new DataNode(this, Children.LEAF, getLookup()));
   }

   /**
    * {@inheritDoc}
    */
   @Override public Lookup getLookup()
   {
      return(getCookieSet().getLookup());
   }
}
