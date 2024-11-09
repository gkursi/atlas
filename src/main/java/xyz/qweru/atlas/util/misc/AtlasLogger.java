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
        String prefix = STACK_WALKER.getCallerClass().getSimpleName() + " > ";
        logger.info("{}{}", prefix, fastFormat(msg, List.of(args)));
    }

    public void warn(String msg, Object... args) {
        String prefix = STACK_WALKER.getCallerClass().getSimpleName() + " > ";
        logger.warn("{}{}", prefix, fastFormat(msg, List.of(args)));
    }

    public void error(String msg, Object... args) {
        String prefix = STACK_WALKER.getCallerClass().getSimpleName() + " > ";
        logger.error("{}{}", prefix, fastFormat(msg, List.of(args)));
    }

    public void debug(String msg, Object... args) {
        String prefix = STACK_WALKER.getCallerClass().getSimpleName() + " > ";
        if(AtlasApi.debug) logger.info("DEBUG | {}{}", prefix, fastFormat(msg, List.of(args)));
    }

    /**
     * slightly faster than <code>String#format</code>
     * @param str the target string
     * @param args the arguments
     * @return formatted string
     */
    public static String fastFormat(String str, List<Object> args) {
        String finalStr = "";
        boolean fp = false;
        int i = 0;

        args = new ArrayList<>(args);

        for (char aChar : str.toCharArray()) {
            if (args.isEmpty()) {
                finalStr += str.substring(i);
                break;
            }

            if (aChar == '}' && fp) {
                finalStr += args.remove(0);
                fp = false;
            } else if (aChar == '{') {
                fp = true;
            } else {
                if (fp) {
                    finalStr += '{';
                    fp = false;
                }
                finalStr += aChar;
            }
            i++;
        }

        if (fp) {
            finalStr += "{";
        }

        return finalStr;
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
