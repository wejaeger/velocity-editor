/*
 * $Id$
 *
 * Copyright (c) 2009 T-Systems International GmbH.
 * All rights reserved.
 * This software is the confidential and proprietary information
 * of T-Systems International GmbH.
 *
 */
package com.tsi.netbeans.modules.languages.velocity.editor;

import java.util.Map;
import org.netbeans.editor.Settings;
import org.netbeans.editor.SettingsNames;

/**
 *
 * @author <a href="mailto:werner.jaeger@t-systems.com">Werner Jaeger</a>
 */
public class VTLSettingsInitializer extends Settings.AbstractInitializer
{
   public static final String NAME = "vtl-settings-initializer";

   /**
    * Creates new {@code VTLSettingsInitializer}.
    */
   public VTLSettingsInitializer()
   {
      super(NAME);
   }

   @SuppressWarnings("unchecked")
   public void updateSettingsMap(final Class kitClass, final Map settingsMap)
   {
      if (kitClass == VTLEditorKit.class)
         settingsMap.put(SettingsNames.CODE_FOLDING_ENABLE, Boolean.TRUE);
   }
}
