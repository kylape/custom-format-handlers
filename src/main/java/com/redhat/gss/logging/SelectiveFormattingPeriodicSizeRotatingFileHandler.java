/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package com.redhat.gss.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Formatter;

import org.jboss.logmanager.handlers.PeriodicSizeRotatingFileHandler;

public class SelectiveFormattingPeriodicSizeRotatingFileHandler extends PeriodicSizeRotatingFileHandler
{
  private String unformattedLoggers = null;
  
  public void setFile(String fileName) throws FileNotFoundException
  {
    setFile(new File(fileName));
  }
  
  public String getUnformattedLoggers()
  {
    return this.unformattedLoggers;
  }
  
  public void setUnformattedLoggers(String unformattedLoggers)
  {
    this.unformattedLoggers = unformattedLoggers;
    if(getFormatter() instanceof SelectiveFormatter)
    {
      ((SelectiveFormatter)getFormatter()).setUnformattedLoggers(unformattedLoggers);
    }
  }
  
  @Override
  public void setFormatter(Formatter formatter)
  {
    SelectiveFormatter selFmt = new SelectiveFormatter(formatter);
    selFmt.setUnformattedLoggers(unformattedLoggers);
    super.setFormatter(selFmt);
  }
}
