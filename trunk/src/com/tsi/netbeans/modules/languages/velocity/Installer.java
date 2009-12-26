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
 * Manages a module's lifecycle. Remember that an installer is optional and
 * often not needed at all.
 */
public class Installer extends ModuleInstall
{
   @Override
   public void restored()
   {
      Settings.addInitializer(new VTLSettingsInitializer());
   }
}
