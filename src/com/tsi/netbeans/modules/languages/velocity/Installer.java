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

import com.tsi.netbeans.modules.languages.velocity.editor.VTLSettingsInitializer;
import org.netbeans.editor.Settings;
import org.openide.modules.ModuleInstall;

/**
 * Manages the VTL module's lifecycle.
 *
 * @author <a href="mailto:werner.jaeger@t-systems.com">Werner Jaeger</a>
 */
public class Installer extends ModuleInstall
{
   /**
    * Called when an already-installed module is restored (during startup).
    *
    * <p>
    *    Registers {@link
    *    com.tsi.netbeans.modules.languages.velocity.editor.VTLSettingsInitializer
    *    VTLSettingsInitializer}.
    * </p>
    */
   @Override public void restored()
   {
      Settings.addInitializer(new VTLSettingsInitializer());
   }
}
