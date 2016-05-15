package com.example.admin.graduationproject.utils;

import android.text.TextUtils;


public class LogUtils {

    protected static final String TAG = "Hotel";


    /**
     * check if the log is disabled
     *
     * @return
     */
    private static boolean isEnabled() {
        return Config.DEBUG;
    }

    /**
     * build the complete message
     *
     * @param message
     * @return
     */
    private static String buildMessage(String message) {
        /*
		 * 自定义信息
		 */
        return TextUtils.isEmpty(message) ? "no message" : message;
    }

    /**
     * send a VERBOSE log message.
     *
     * @param msg
     */
    public static void v(String msg) {
        if (isEnabled()) {
            android.util.Log.v(TAG, buildMessage(msg));
        }
    }

    /**
     * send a VERBOSE log message with specific tag
     *
     * @param msg
     */
    public static void v(String tag, String msg) {
        if (isEnabled()) {
            android.util.Log.v(tag, buildMessage(msg));
        }
    }

    /**
     * send a VERBOSE log message with specific tag and throwable
     *
     * @param msg
     */
    public static void v(String tag, String msg, Throwable throwable) {
        if (isEnabled()) {
            android.util.Log.v(tag, buildMessage(msg), throwable);
        }
    }

    /**
     * send a VERBOSE log message and log the exception
     *
     * @param msg
     * @param throwable
     */
    public static void v(String msg, Throwable throwable) {
        if (isEnabled()) {
            android.util.Log.v(TAG, buildMessage(msg), throwable);
        }
    }

    /**
     * send a DEBUG log message
     *
     * @param msg
     */
    public static void d(String msg) {
        if (isEnabled()) {
            android.util.Log.d(TAG, buildMessage(msg));
        }
    }

    /**
     * send a DEBUG log message with specific tag
     *
     * @param msg
     */
    public static void d(String tag, String msg) {
        if (isEnabled()) {
            android.util.Log.d(tag, buildMessage(msg));
        }
    }

    /**
     * send a DEBUG log message with specific tag and throwable
     *
     * @param msg
     */
    public static void d(String tag, String msg, Throwable throwable) {
        if (isEnabled()) {
            android.util.Log.d(tag, buildMessage(msg), throwable);
        }
    }

    /**
     * send a DEBUG log message and log the exception
     *
     * @param msg
     * @param throwable
     */
    public static void d(String msg, Throwable throwable) {
        if (isEnabled()) {
            android.util.Log.d(TAG, buildMessage(msg), throwable);
        }
    }

    /**
     * send a INFO log message
     *
     * @param msg
     */
    public static void i(String msg) {
        if (isEnabled()) {
            android.util.Log.i(TAG, buildMessage(msg));
        }
    }

    /**
     * send a INFO log message　with specific tag
     *
     * @param msg
     */
    public static void i(String tag, String msg) {
        if (isEnabled()) {
            android.util.Log.i(tag, buildMessage(msg));
        }
    }

    /**
     * send a INFO log message　with specific tag and throwable
     *
     * @param msg
     */
    public static void i(String tag, String msg, Throwable throwable) {
        if (isEnabled()) {
            android.util.Log.i(tag, buildMessage(msg), throwable);
        }
    }

    /**
     * send a INFO log message and log the exception
     *
     * @param msg
     * @param throwable
     */
    public static void i(String msg, Throwable throwable) {
        if (isEnabled()) {
            android.util.Log.i(TAG, buildMessage(msg), throwable);
        }
    }

    /**
     * send a ERROR log message
     *
     * @param msg
     */
    public static void e(String msg) {
        if (isEnabled()) {
            android.util.Log.e(TAG, buildMessage(msg));
        }
    }

    /**
     * send a ERROR log message with specific tag
     *
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (isEnabled()) {
            android.util.Log.e(tag, buildMessage(msg));
        }
    }

    /**
     * send a ERROR log message with specific tag and throwable
     *
     * @param msg
     */
    public static void e(String tag, String msg, Throwable throwable) {
        if (isEnabled()) {
            android.util.Log.e(tag, buildMessage(msg), throwable);
        }
    }

    /**
     * send a ERROR log message and log the exception
     *
     * @param msg
     * @param throwable
     */
    public static void e(String msg, Throwable throwable) {
        if (isEnabled()) {
            android.util.Log.e(TAG, buildMessage(msg), throwable);
        }
    }

    /**
     * send a WARN log message
     *
     * @param msg
     */
    public static void w(String msg) {
        if (isEnabled()) {
            android.util.Log.w(TAG, buildMessage(msg));
        }
    }

    /**
     * send a WARN log message with specific tag
     *
     * @param msg
     */
    public static void w(String tag, String msg) {
        if (isEnabled()) {
            android.util.Log.w(tag, buildMessage(msg));
        }
    }

    /**
     * send a WARN log message with specific tag and throwable
     *
     * @param msg
     */
    public static void w(String tag, String msg, Throwable throwable) {
        if (isEnabled()) {
            android.util.Log.w(tag, buildMessage(msg), throwable);
        }
    }

    /**
     * send a WARN log message and log the exception
     *
     * @param msg
     * @param throwable
     */
    public static void w(String msg, Throwable throwable) {
        if (isEnabled()) {
            android.util.Log.w(TAG, buildMessage(msg), throwable);
        }
    }
}
