/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package com.redhat.gss.logging;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class SelectiveFormatter extends Formatter
{
  private Formatter delegate = null;
  private Set<String> unformattedLoggers = null;
  
  @Override
  public String format(LogRecord record)
  {
    if(unformattedLoggers != null)
    {
      for(String unformattedLogger : unformattedLoggers)
      {
        if(record.getLoggerName().equals(unformattedLogger))
        {
          return record.getMessage() + "\n";
        }
      }
    }
    return delegate.format(record);
  }

  public SelectiveFormatter(Formatter formatter)
  {
    delegate = formatter;
  }

  public Set<String> getUnformattedLoggers()
  {
    return this.unformattedLoggers;
  }

  public void setUnformattedLoggers(String loggers)
  {
    if(loggers == null)
      return;

    unformattedLoggers = new HashSet<String>();

    for(String logger : loggers.split(","))
    {
      unformattedLoggers.add(logger);
    }
  }
  
  public void removeUnformattedLogger(String logger)
  {
    unformattedLoggers.remove(logger);
  }
  
  public void addUnformattedLogger(String logger)
  {
    unformattedLoggers.add(logger);
  }
  
  public Formatter getDelegate()
  {
    return this.delegate;
  }
  
  public void setDelegate(Formatter delegate)
  {
    this.delegate = delegate;
  }
}
