custom-format-handlers
======================

Custom handlers for JBoss LogManager.  If you want to choose certain loggers to
forego formatting, then these custom handlers are the way to go.  Perhaps you
have a logging framework in your application that already formats your log
messages and you just don't want JBoss messing with that formatting any more.
Whatever your reason, here's an example of how to install these two handlers:

```
<custom-handler name="CONSOLE2" class="com.redhat.gss.logging.SelectiveFormattingConsoleHandler" module="com.redhat.gss.logging">
    <level name="INFO"/>
    <formatter>
        <pattern-formatter pattern="%K{level}%d{HH:mm:ss,SSS} %-5p [%c] (%t) %s%E%n"/>
    </formatter>
    <properties>
        <property name="unformattedLoggers" value="stdout"/>
    </properties>
</custom-handler>
<custom-handler name="FILE2" class="com.redhat.gss.logging.SelectiveFormattingPeriodicSizeRotatingFileHandler" module="com.redhat.gss.logging">
    <formatter>
        <pattern-formatter pattern="%d{HH:mm:ss,SSS} %-5p [%c] (%t) %s%E%n"/>
    </formatter>
    <properties>
        <property name="unformattedLoggers" value="stdout"/>
        <property name="file" value="${jboss.server.log.dir}/server.log"/>
        <property name="suffix" value=".yyyy-MM-dd"/>
        <property name="append" value="true"/>
    </properties>
</custom-handler>
```

There's also a diff you can apply, included in this project.  To install the
actual code, you need to create the following folder structure in EAP 6.1:

```
$JBOSS_HOME/system/layers/base/com/redhat/gss/logging/main
```

Put the files `custom-logging-handlers.jar` and `module.xml` in there.  Of
course the jar can be built in this project via `mvn package`.
