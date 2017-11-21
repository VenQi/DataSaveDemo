//package com.data.www.datasavedemo.sharesdk.line;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.graphics.Bitmap;
//import android.net.http.SslError;
//import android.os.Message;
//import android.view.KeyEvent;
//import android.webkit.HttpAuthHandler;
//import android.webkit.SslErrorHandler;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//
///**
// * Created by qwh on 2017/9/25.
// */
//
//public class BaseWebClient extends WebViewClient {
//    public static final int ERROR_UNKNOWN = -1;
//    public static final int ERROR_HOST_LOOKUP = -2;
//    public static final int ERROR_UNSUPPORTED_AUTH_SCHEME = -3;
//    public static final int ERROR_AUTHENTICATION = -4;
//    public static final int ERROR_PROXY_AUTHENTICATION = -5;
//    public static final int ERROR_CONNECT = -6;
//    public static final int ERROR_IO = -7;
//    public static final int ERROR_TIMEOUT = -8;
//    public static final int ERROR_REDIRECT_LOOP = -9;
//    public static final int ERROR_UNSUPPORTED_SCHEME = -10;
//    public static final int ERROR_FAILED_SSL_HANDSHAKE = -11;
//    public static final int ERROR_BAD_URL = -12;
//    public static final int ERROR_FILE = -13;
//    public static final int ERROR_FILE_NOT_FOUND = -14;
//    public static final int ERROR_TOO_MANY_REQUESTS = -15;
//
//    public d() {
//    }
//
//    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//        return false;
//    }
//
//    public void onPageStarted(WebView view, String url, Bitmap favicon) {
//    }
//
//    public void onPageFinished(WebView view, String url) {
//    }
//
//    public void onLoadResource(WebView view, String url) {
//    }
//
//    public void onTooManyRedirects(WebView view, Message cancelMsg, Message continueMsg) {
//        cancelMsg.sendToTarget();
//    }
//
//    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//    }
//
//    public void onFormResubmission(WebView view, Message dontResend, Message resend) {
//        dontResend.sendToTarget();
//    }
//
//    public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
//    }
//
//    public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
//        if(view.getContext() instanceof Activity) {
//            Activity var4 = (Activity)view.getContext();
//            String var9 = DeviceHelper.getInstance(var4).getOSLanguage();
//            String[] var5;
//            String var6;
//            String var7;
//            String var8;
//            if("zh".equals(var9)) {
//                var5 = new String[]{String.valueOf(new char[]{'不', '受', '信', '任', '的', '证', '书', '。', '你', '要', '继', '续', '吗', '？'}), String.valueOf(new char[]{'证', '书', '已', '过', '期', '。', '你', '要', '继', '续', '吗', '？'}), String.valueOf(new char[]{'证', '书', 'I', 'D', '不', '匹', '配', '。', '你', '要', '继', '续', '吗', '？'}), String.valueOf(new char[]{'证', '书', '尚', '未', '生', '效', '。', '你', '要', '继', '续', '吗', '？'}), String.valueOf(new char[]{'证', '书', '错', '误', '。', '你', '要', '继', '续', '吗', '？'})};
//                var6 = String.valueOf(new char[]{'证', '书', '错', '误'});
//                var7 = String.valueOf(new char[]{'继', '续'});
//                var8 = String.valueOf(new char[]{'停', '止'});
//            } else {
//                var5 = new String[]{"Certificate is untrusted. Do you want to continue anyway?", "Certificate has expired. Do you want to continue anyway?", "Certificate ID is mismatched. Do you want to continue anyway?", "Certificate is not yet valid. Do you want to continue anyway?", "Certificate error. Do you want to continue anyway?"};
//                var6 = "SSL Certificate Error";
//                var7 = "Yes";
//                var8 = "No";
//            }
//
//            AlertDialog.Builder var10 = new AlertDialog.Builder(var4);
//            var10.setTitle(var6);
//            switch(error.getPrimaryError()) {
//                case 0:
//                    var10.setMessage(var5[3]);
//                    break;
//                case 1:
//                    var10.setMessage(var5[1]);
//                    break;
//                case 2:
//                    var10.setMessage(var5[2]);
//                    break;
//                case 3:
//                    var10.setMessage(var5[0]);
//                    break;
//                default:
//                    var10.setMessage(var5[4]);
//            }
//
//            var10.setCancelable(false);
//            var10.setPositiveButton(var7, new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//
//                    try {
//                        handler.proceed();
//                    } catch (Throwable var4) {
//                        cn.sharesdk.framework.utils.d.b().w(var4);
//                    }
//
//                }
//            });
//            var10.setNegativeButton(var8, new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                    handler.cancel();
//                }
//            });
//            var10.create().show();
//        } else {
//            handler.cancel();
//        }
//
//    }
//
//    public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
//        handler.cancel();
//    }
//
//    public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
//        return false;
//    }
//
//    public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
//    }
//
//    public void onScaleChanged(WebView view, float oldScale, float newScale) {
//    }
//}
