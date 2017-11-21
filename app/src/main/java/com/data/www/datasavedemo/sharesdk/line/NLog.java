//package com.data.www.datasavedemo.sharesdk.line;
//
//import android.content.Context;
//import android.util.LogPrinter;
//
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.net.UnknownHostException;
//import java.util.HashMap;
//
///**
// * Created by qwh on 2017/9/25.
// */
//
//public class NLog {
//    public abstract class NLog {
//        private static boolean disable;
//        private static LogPrinter printer = new LogPrinter();
//        private static HashMap<String, NLog> loggers = new HashMap();
//
//        public static void disable() {
//            disable = true;
//        }
//
//        public NLog() {
//            loggers.put(this.getSDKTag(), this);
//            if(loggers.size() == 1) {
//                loggers.put("__FIRST__", this);
//            }
//
//        }
//
//        public static void setContext(Context context) {
//            if(context != null) {
//                printer.setContext(context);
//                NativeErrorHandler.prepare(context);
//            }
//
//        }
//
//        public static void setCollector(String sdkTag, LogCollector collector) {
//            printer.setCollector(sdkTag, collector);
//        }
//
//        protected static final NLog getInstanceForSDK(final String sdkTag, boolean createNew) {
//            NLog instance = (NLog)loggers.get(sdkTag);
//            if(instance == null) {
//                instance = (NLog)loggers.get("__FIRST__");
//            }
//
//            if(instance == null && createNew) {
//                instance = new NLog() {
//                    protected String getSDKTag() {
//                        return sdkTag;
//                    }
//                };
//            }
//
//            return instance;
//        }
//
//        protected abstract String getSDKTag();
//
//        public final int v(Throwable t) {
//            return disable?0:printer.println(this.getSDKTag(), 2, 0, this.getStackTraceString(t));
//        }
//
//        public final int v(Object format, Object... args) {
//            if(disable) {
//                return 0;
//            } else {
//                String s = format.toString();
//                String message = args.length > 0?String.format(s, args):s;
//                return printer.println(this.getSDKTag(), 2, 0, message);
//            }
//        }
//
//        public final int v(Throwable throwable, Object format, Object... args) {
//            if(disable) {
//                return 0;
//            } else {
//                String s = format.toString();
//                String message = (args.length > 0?String.format(s, args):s) + '\n' + this.getStackTraceString(throwable);
//                return printer.println(this.getSDKTag(), 2, 0, message);
//            }
//        }
//
//        public final int d(Throwable t) {
//            return disable?0:printer.println(this.getSDKTag(), 3, 0, this.getStackTraceString(t));
//        }
//
//        public final int d(Object format, Object... args) {
//            if(disable) {
//                return 0;
//            } else {
//                String s = format.toString();
//                String message = args.length > 0?String.format(s, args):s;
//                return printer.println(this.getSDKTag(), 3, 0, message);
//            }
//        }
//
//        public final int d(Throwable throwable, Object format, Object... args) {
//            if(disable) {
//                return 0;
//            } else {
//                String s = format.toString();
//                String message = (args.length > 0?String.format(s, args):s) + '\n' + this.getStackTraceString(throwable);
//                return printer.println(this.getSDKTag(), 3, 0, message);
//            }
//        }
//
//        public final int i(Throwable t) {
//            return disable?0:printer.println(this.getSDKTag(), 4, 0, this.getStackTraceString(t));
//        }
//
//        public final int i(Object format, Object... args) {
//            if(disable) {
//                return 0;
//            } else {
//                String s = format.toString();
//                String message = args.length > 0?String.format(s, args):s;
//                return printer.println(this.getSDKTag(), 4, 0, message);
//            }
//        }
//
//        public final int i(Throwable throwable, Object format, Object... args) {
//            if(disable) {
//                return 0;
//            } else {
//                String s = format.toString();
//                String message = (args.length > 0?String.format(s, args):s) + '\n' + this.getStackTraceString(throwable);
//                return printer.println(this.getSDKTag(), 4, 0, message);
//            }
//        }
//
//        public final int w(Throwable t) {
//            return disable?0:printer.println(this.getSDKTag(), 5, 0, this.getStackTraceString(t));
//        }
//
//        public final int w(Object format, Object... args) {
//            if(disable) {
//                return 0;
//            } else {
//                String s = format.toString();
//                String message = args.length > 0?String.format(s, args):s;
//                return printer.println(this.getSDKTag(), 5, 0, message);
//            }
//        }
//
//        public final int w(Throwable throwable, Object format, Object... args) {
//            if(disable) {
//                return 0;
//            } else {
//                String s = format.toString();
//                String message = (args.length > 0?String.format(s, args):s) + '\n' + this.getStackTraceString(throwable);
//                return printer.println(this.getSDKTag(), 5, 0, message);
//            }
//        }
//
//        public final int w(String m) {
//            return disable?0:printer.println(this.getSDKTag(), 5, 0, m);
//        }
//
//        public final int e(Throwable t) {
//            return disable?0:printer.println(this.getSDKTag(), 6, 0, this.getStackTraceString(t));
//        }
//
//        public final int e(Object format, Object... args) {
//            if(disable) {
//                return 0;
//            } else {
//                String s = format.toString();
//                String message = args.length > 0?String.format(s, args):s;
//                return printer.println(this.getSDKTag(), 6, 0, message);
//            }
//        }
//
//        public final int e(Throwable throwable, Object format, Object... args) {
//            if(disable) {
//                return 0;
//            } else {
//                String s = format.toString();
//                String message = (args.length > 0?String.format(s, args):s) + '\n' + this.getStackTraceString(throwable);
//                return printer.println(this.getSDKTag(), 6, 0, message);
//            }
//        }
//
//        public int wtf(Throwable t) {
//            return disable?0:printer.println(this.getSDKTag(), 6, 0, this.getStackTraceString(t));
//        }
//
//        public final int crash(Throwable t) {
//            return disable?0:printer.println(this.getSDKTag(), 6, 1, this.getStackTraceString(t));
//        }
//
//        public final void nativeCrashLog(String log) {
//            if(!disable) {
//                printer.nativeCrashLog(this.getSDKTag(), log);
//            }
//        }
//
//        private final String getStackTraceString(Throwable tr) {
//            if(tr == null) {
//                return "";
//            } else {
//                for(Throwable t = tr; t != null; t = t.getCause()) {
//                    if(t instanceof UnknownHostException) {
//                        return "";
//                    }
//                }
//
//                StringWriter sw = new StringWriter();
//                PrintWriter pw = new PrintWriter(sw);
//                tr.printStackTrace(pw);
//                return sw.toString();
//            }
//        }
//
//        static {
//            MobUncaughtExceptionHandler.register();
//        }
//}
