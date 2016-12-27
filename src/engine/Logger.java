package engine;

import java.io.PrintStream;

/**
 * Created by Aedan Smith.
 *
 * Simple logger class for logging information.
 */

public class Logger {
    /**
     * The PrintStream for the Logger to write to.
     */
    @SuppressWarnings("WeakerAccess")
    public static final PrintStream out = System.out;

    /**
     * The PrintStream for the Logger to write errors to.
     */
    @SuppressWarnings("WeakerAccess")
    public static final PrintStream err = System.err;

    /**
     * The time at which the program was initialized. This is used to give timestamps of all logged events.
     */
    private static final long initTime = System.currentTimeMillis();

    /**
     * Logs a string to the Logger output.
     *
     * @param s The string to log.
     */
    public static void log(String s){
        out.println("[" + (System.currentTimeMillis() - initTime) + "]: " + s);
    }

    /**
     * Logs an exception to the Logger error output. This method flushes the standard Logger output before writing the
     * exception.
     *
     * @param e The exception to log.
     */
    public static void log(Exception e){
        out.flush();
        err.print("[" + (System.currentTimeMillis() - initTime) + "]: ");
        e.printStackTrace(err);
    }
}
