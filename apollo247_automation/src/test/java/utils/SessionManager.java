package utils;

/**
 * Holds per-thread login state so that the Background login step
 * runs only ONCE per thread across all scenarios on that thread.
 *
 * In a parallel run each thread logs in independently (once) and
 * reuses that authenticated session for every scenario it executes.
 */
public class SessionManager {

    private static final ThreadLocal<Boolean> loggedIn = ThreadLocal.withInitial(() -> false);

    private SessionManager() {}

    public static boolean isLoggedIn() {
        return Boolean.TRUE.equals(loggedIn.get());
    }

    public static void markLoggedIn() {
        loggedIn.set(true);
    }

    public static void reset() {
        loggedIn.set(false);
    }
}