package xyz.qweru.atlas.util.misc;

import xyz.qweru.atlas.Atlas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.qweru.atlas.api.AtlasApi;
import xyz.qweru.atlas.util.annotations.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper
 * todo: remove hardcoded class names
 */
@Todo.WriteDocs
public class AtlasLogger {
    static final StackWalker STACK_WALKER = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
    private final Logger logger;

    public AtlasLogger(Logger logger) {
        this.logger = logger;
    }

    public AtlasLogger(String name) {
        this.logger = LoggerFactory.getLogger(name);
    }

    public void info(String msg, Object... args) {
        String classs = STACK_WALKER.getCallerClass().getSimpleName();
        String prefix = (classs.startsWith("Script$") || classs.startsWith("MemberBox") ? "Atlas Script" : classs)  + " > ";
        logger.info("{}{}", prefix, fastFormat(msg, List.of(args)));
    }

    public void warn(String msg, Object... args) {
        String classs = STACK_WALKER.getCallerClass().getSimpleName();
        String prefix = (classs.startsWith("Script$") || classs.startsWith("MemberBox") ? "Atlas Script" : classs)  + " > ";
        logger.warn("{}{}", prefix, fastFormat(msg, List.of(args)));
    }

    public void error(String msg, Object... args) {
        String classs = STACK_WALKER.getCallerClass().getSimpleName();
        String prefix = (classs.startsWith("Script$") || classs.startsWith("MemberBox") ? "Atlas Script" : classs)  + " > ";
        logger.error("{}{}", prefix, fastFormat(msg, List.of(args)));
    }

    public void debug(String msg, Object... args) {
        String classs = STACK_WALKER.getCallerClass().getSimpleName();
        String prefix = (classs.startsWith("Script$") || classs.startsWith("MemberBox") ? "Atlas Script" : classs)  + " > ";
        if(AtlasApi.debug) logger.info("DEBUG | {}{}", prefix, fastFormat(msg, List.of(args)));
    }

    /**
     * slightly faster than <code>String#format</code>
     * @param str the target string
     * @param args the arguments
     * @return formatted string
     */
    public static String fastFormat(String str, List<Object> args) {
        StringBuilder finalStr = new StringBuilder();
        boolean fp = false;
        int i = 0;

        args = new ArrayList<>(args);

        for (char aChar : str.toCharArray()) {
            if (args.isEmpty()) {
                finalStr.append(str.substring(i));
                break;
            }

            if (aChar == '}' && fp) {
                finalStr.append(args.removeFirst());
                fp = false;
            } else if (aChar == '{') {
                fp = true;
            } else {
                if (fp) {
                    finalStr.append('{');
                    fp = false;
                }
                finalStr.append(aChar);
            }
            i++;
        }

        if (fp) {
            finalStr.append("{");
        }

        return finalStr.toString();
    }

    public TimedLogger getTimed() {
        return new TimedLogger(this);
    }

    public static class TimedLogger {
        private final AtlasLogger log;
        private final TimerUtil timer;

        private TimedLogger(AtlasLogger logger) {
            log = logger;
            timer = new TimerUtil();
            timer.reset();
        }

        public void info(String msg, Object... args) {
            log.info( STACK_WALKER.getCallerClass().getSimpleName() + " > " + msg + " (took " + timer.getFromLast() + "ms)", args);
        }

        public void warn(String msg, Object... args) {
            log.warn( STACK_WALKER.getCallerClass().getSimpleName() + " > " + msg + " (took " + timer.getFromLast() + "ms)", args);
        }

        public void error(String msg, Object... args) {
            log.error( STACK_WALKER.getCallerClass().getSimpleName() + " > " + msg + " (took " + timer.getFromLast() + "ms)", args);        }

        public void debug(String msg, Object... args) {
            log.debug( STACK_WALKER.getCallerClass().getSimpleName() + " > " + msg + " (took " + timer.getFromLast() + "ms)", args);        }
    }

    public Logger getBase() {
        return logger;
    }

}
