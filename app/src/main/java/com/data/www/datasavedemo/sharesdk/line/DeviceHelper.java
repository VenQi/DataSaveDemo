//package com.data.www.datasavedemo.sharesdk.line;
//
//import android.app.Activity;
//import android.app.UiModeManager;
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.content.pm.Signature;
//import android.location.Location;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.os.Build;
//import android.os.Environment;
//import android.os.Handler;
//import android.os.IBinder;
//import android.os.Looper;
//import android.os.Message;
//import android.os.Parcel;
//import android.os.SystemClock;
//import android.text.TextUtils;
//import android.util.Base64;
//import android.view.View;
//import android.view.inputmethod.InputMethodManager;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStreamReader;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.Inet4Address;
//import java.net.InetAddress;
//import java.net.NetworkInterface;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//import java.util.Random;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by qwh on 2017/9/25.
// */
//
//public class DeviceHelper {
//    private Context context;
//    private static DeviceHelper deviceHelper;
//
//    public static synchronized DeviceHelper getInstance(Context c) {
//        if(deviceHelper == null && c != null) {
//            deviceHelper = new DeviceHelper(c);
//        }
//
//        return deviceHelper;
//    }
//
//    private DeviceHelper(Context context) {
//        this.context = context.getApplicationContext();
//    }
//
//    public boolean isRooted() {
//        return false;
//    }
//
//    public String getSSID() {
//        try {
//            if(this.checkPermission("android.permission.ACCESS_WIFI_STATE")) {
//                Object t = this.getSystemServiceSave("wifi");
//                if(t == null) {
//                    return null;
//                }
//
//                StringBuilder sb = new StringBuilder();
//                sb.append("ge");
//                sb.append("tC");
//                sb.append("on");
//                sb.append("ne");
//                sb.append("ct");
//                sb.append("io");
//                sb.append("nI");
//                sb.append("nf");
//                sb.append("o");
//                Object info = ReflectHelper.invokeInstanceMethod(t, sb.toString(), new Object[0]);
//                if(info != null) {
//                    StringBuilder sb1 = new StringBuilder();
//                    sb1.append("ge");
//                    sb1.append("tS");
//                    sb1.append("SI");
//                    sb1.append("D");
//                    String ssid = (String)ReflectHelper.invokeInstanceMethod(info, sb1.toString(), new Object[0]);
//                    return ssid == null?null:ssid.replace("\"", "");
//                }
//            }
//        } catch (Throwable var6) {
//            MobLog.getInstance().d(var6);
//        }
//
//        return null;
//    }
//
//    public String getBssid() {
//        try {
//            if(this.checkPermission("android.permission.ACCESS_WIFI_STATE")) {
//                Object t = this.getSystemServiceSave("wifi");
//                if(t == null) {
//                    return null;
//                }
//
//                StringBuilder sb = new StringBuilder();
//                sb.append("ge");
//                sb.append("tC");
//                sb.append("on");
//                sb.append("ne");
//                sb.append("ct");
//                sb.append("io");
//                sb.append("nI");
//                sb.append("nf");
//                sb.append("o");
//                Object info = ReflectHelper.invokeInstanceMethod(t, sb.toString(), new Object[0]);
//                if(info != null) {
//                    StringBuilder sb1 = new StringBuilder();
//                    sb1.append("ge");
//                    sb1.append("tB");
//                    sb1.append("SS");
//                    sb1.append("ID");
//                    String bssid = (String)ReflectHelper.invokeInstanceMethod(info, sb1.toString(), new Object[0]);
//                    return bssid == null?null:bssid;
//                }
//            }
//        } catch (Throwable var6) {
//            MobLog.getInstance().d(var6);
//        }
//
//        return null;
//    }
//
//    public String getMacAddress() {
//        if(VERSION.SDK_INT >= 23) {
//            String t = null;
//
//            try {
//                t = this.getHardwareAddressFromShell("wlan0");
//            } catch (Throwable var9) {
//                MobLog.getInstance().d(var9);
//                t = null;
//            }
//
//            if(t == null) {
//                try {
//                    t = this.getCurrentNetworkHardwareAddress();
//                } catch (Throwable var8) {
//                    MobLog.getInstance().d(var8);
//                    t = null;
//                }
//            }
//
//            if(t == null) {
//                try {
//                    String[] sb = this.listNetworkHardwareAddress();
//                    if(sb.length > 0) {
//                        t = sb[0];
//                    }
//                } catch (Throwable var7) {
//                    MobLog.getInstance().d(var7);
//                    t = null;
//                }
//            }
//
//            if(t != null) {
//                return t;
//            }
//        }
//
//        try {
//            Object t1 = this.getSystemServiceSave("wifi");
//            if(t1 == null) {
//                return null;
//            }
//
//            StringBuilder sb1 = new StringBuilder();
//            sb1.append("ge");
//            sb1.append("tC");
//            sb1.append("on");
//            sb1.append("ne");
//            sb1.append("ct");
//            sb1.append("io");
//            sb1.append("nI");
//            sb1.append("nf");
//            sb1.append("o");
//            Object info = ReflectHelper.invokeInstanceMethod(t1, sb1.toString(), new Object[0]);
//            if(info != null) {
//                StringBuilder sb1 = new StringBuilder();
//                sb1.append("ge");
//                sb1.append("tM");
//                sb1.append("ac");
//                sb1.append("Ad");
//                sb1.append("dr");
//                sb1.append("es");
//                sb1.append("s");
//                String mac = (String)ReflectHelper.invokeInstanceMethod(info, sb1.toString(), new Object[0]);
//                return mac == null?null:mac.trim();
//            }
//        } catch (Throwable var6) {
//            MobLog.getInstance().w(var6);
//        }
//
//        return null;
//    }
//
//    private String getCurrentNetworkHardwareAddress() throws Throwable {
//        Enumeration nis = NetworkInterface.getNetworkInterfaces();
//        if(nis == null) {
//            return null;
//        } else {
//            ArrayList interfaces = Collections.list(nis);
//            Iterator var3 = interfaces.iterator();
//
//            while(true) {
//                NetworkInterface intf;
//                Enumeration ias;
//                do {
//                    if(!var3.hasNext()) {
//                        return null;
//                    }
//
//                    intf = (NetworkInterface)var3.next();
//                    ias = intf.getInetAddresses();
//                } while(ias == null);
//
//                ArrayList addrs = Collections.list(ias);
//                Iterator var7 = addrs.iterator();
//
//                while(var7.hasNext()) {
//                    InetAddress add = (InetAddress)var7.next();
//                    if(!add.isLoopbackAddress() && add instanceof Inet4Address) {
//                        byte[] mac = null;
//                        if(VERSION.SDK_INT >= 9) {
//                            mac = intf.getHardwareAddress();
//                        }
//
//                        if(mac != null) {
//                            StringBuilder buf = new StringBuilder();
//                            byte[] var11 = mac;
//                            int var12 = mac.length;
//
//                            for(int var13 = 0; var13 < var12; ++var13) {
//                                byte aMac = var11[var13];
//                                buf.append(String.format("%02x:", new Object[]{Byte.valueOf(aMac)}));
//                            }
//
//                            if(buf.length() > 0) {
//                                buf.deleteCharAt(buf.length() - 1);
//                            }
//
//                            return buf.toString();
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    private String[] listNetworkHardwareAddress() throws Throwable {
//        Enumeration nis = NetworkInterface.getNetworkInterfaces();
//        if(nis == null) {
//            return null;
//        } else {
//            ArrayList interfaces = Collections.list(nis);
//            HashMap macs = new HashMap();
//            Iterator names = interfaces.iterator();
//
//            while(names.hasNext()) {
//                NetworkInterface wlans = (NetworkInterface)names.next();
//                byte[] eths = null;
//                if(VERSION.SDK_INT >= 9) {
//                    eths = wlans.getHardwareAddress();
//                }
//
//                if(eths != null) {
//                    StringBuilder rmnets = new StringBuilder();
//                    byte[] dummys = eths;
//                    int usbs = eths.length;
//
//                    for(int rmnetUsbs = 0; rmnetUsbs < usbs; ++rmnetUsbs) {
//                        byte others = dummys[rmnetUsbs];
//                        rmnets.append(String.format("%02x:", new Object[]{Byte.valueOf(others)}));
//                    }
//
//                    if(rmnets.length() > 0) {
//                        rmnets.deleteCharAt(rmnets.length() - 1);
//                    }
//
//                    macs.put(wlans.getName(), rmnets.toString());
//                }
//            }
//
//            ArrayList var14 = new ArrayList(macs.keySet());
//            ArrayList var15 = new ArrayList();
//            ArrayList var16 = new ArrayList();
//            ArrayList var17 = new ArrayList();
//            ArrayList var18 = new ArrayList();
//            ArrayList var19 = new ArrayList();
//            ArrayList var20 = new ArrayList();
//            ArrayList var21 = new ArrayList();
//
//            while(var14.size() > 0) {
//                String macArr = (String)var14.remove(0);
//                if(macArr.startsWith("wlan")) {
//                    var15.add(macArr);
//                } else if(macArr.startsWith("eth")) {
//                    var16.add(macArr);
//                } else if(macArr.startsWith("rev_rmnet")) {
//                    var17.add(macArr);
//                } else if(macArr.startsWith("dummy")) {
//                    var18.add(macArr);
//                } else if(macArr.startsWith("usbnet")) {
//                    var19.add(macArr);
//                } else if(macArr.startsWith("rmnet_usb")) {
//                    var20.add(macArr);
//                } else {
//                    var21.add(macArr);
//                }
//            }
//
//            Collections.sort(var15);
//            Collections.sort(var16);
//            Collections.sort(var17);
//            Collections.sort(var18);
//            Collections.sort(var19);
//            Collections.sort(var20);
//            Collections.sort(var21);
//            var14.addAll(var15);
//            var14.addAll(var16);
//            var14.addAll(var17);
//            var14.addAll(var18);
//            var14.addAll(var19);
//            var14.addAll(var20);
//            var14.addAll(var21);
//            String[] var22 = new String[var14.size()];
//
//            for(int i = 0; i < var22.length; ++i) {
//                var22[i] = (String)macs.get(var14.get(i));
//            }
//
//            return var22;
//        }
//    }
//
//    private String getHardwareAddressFromShell(String networkCard) {
//        String line = null;
//        BufferedReader br = null;
//
//        try {
//            StringBuilder t = new StringBuilder();
//            t.append("ca");
//            t.append("t ");
//            t.append("/s");
//            t.append("ys");
//            t.append("/c");
//            t.append("la");
//            t.append("ss");
//            t.append("/n");
//            t.append("et");
//            t.append("/");
//            t.append(networkCard);
//            t.append("/a");
//            t.append("dd");
//            t.append("re");
//            t.append("ss");
//            Process p = Runtime.getRuntime().exec(t.toString());
//            InputStreamReader isr = new InputStreamReader(p.getInputStream());
//            br = new BufferedReader(isr);
//            line = br.readLine();
//        } catch (Throwable var15) {
//            MobLog.getInstance().d(var15);
//        } finally {
//            if(br != null) {
//                try {
//                    br.close();
//                } catch (Throwable var14) {
//                    ;
//                }
//            }
//
//        }
//
//        return TextUtils.isEmpty(line)?null:line;
//    }
//
//    public String getModel() {
//        String model = Build.MODEL;
//        if(!TextUtils.isEmpty(model)) {
//            model = model.trim();
//        }
//
//        return model;
//    }
//
//    public String getManufacturer() {
//        return Build.MANUFACTURER;
//    }
//
//    public String getDeviceId() {
//        String deviceId = this.getIMEI();
//        return TextUtils.isEmpty(deviceId) && VERSION.SDK_INT >= 9?this.getSerialno():deviceId;
//    }
//
//    public String getIMEI() {
//        Object phone = this.getSystemServiceSave("phone");
//        if(phone == null) {
//            return null;
//        } else {
//            String deviceId = null;
//
//            try {
//                if(this.checkPermission("android.permission.READ_PHONE_STATE")) {
//                    StringBuilder t = new StringBuilder();
//                    t.append("ge");
//                    t.append("tD");
//                    t.append("ev");
//                    t.append("ic");
//                    t.append("eI");
//                    t.append("d");
//                    deviceId = (String)ReflectHelper.invokeInstanceMethod(phone, t.toString(), new Object[0]);
//                }
//            } catch (Throwable var4) {
//                MobLog.getInstance().w(var4);
//            }
//
//            return TextUtils.isEmpty(deviceId)?null:deviceId.trim();
//        }
//    }
//
//    public String getSerialno() {
//        String serialno = null;
//        if(VERSION.SDK_INT >= 9) {
//            try {
//                StringBuilder t = new StringBuilder();
//                t.append("an");
//                t.append("dr");
//                t.append("oi");
//                t.append("d.");
//                t.append("os");
//                t.append(".S");
//                t.append("ys");
//                t.append("te");
//                t.append("mP");
//                t.append("ro");
//                t.append("pe");
//                t.append("rt");
//                t.append("ie");
//                t.append("s");
//                ReflectHelper.importClass(t.toString());
//                StringBuilder sb1 = new StringBuilder();
//                sb1.append("ge");
//                sb1.append("t");
//                serialno = (String)ReflectHelper.invokeStaticMethod("SystemProperties", sb1.toString(), new Object[]{"ro.serialno", "unknown"});
//            } catch (Throwable var4) {
//                MobLog.getInstance().d(var4);
//                serialno = null;
//            }
//        }
//
//        if(!TextUtils.isEmpty(serialno)) {
//            serialno = serialno.trim();
//        }
//
//        return serialno;
//    }
//
//    public String getDeviceData() {
//        String data = this.getModel() + "|" + this.getOSVersionInt() + "|" + this.getManufacturer() + "|" + this.getCarrier() + "|" + this.getScreenSize();
//        String deviString = this.getDeviceKey();
//        return this.Base64AES(data, deviString.substring(0, 16));
//    }
//
//    public String getDeviceDataNotAES() {
//        return this.getModel() + "|" + this.getOSVersionInt() + "|" + this.getManufacturer() + "|" + this.getCarrier() + "|" + this.getScreenSize();
//    }
//
//    public String Base64AES(String msg, String key) {
//        String result = null;
//
//        try {
//            result = Base64.encodeToString(Data.AES128Encode(key, msg), 0);
//            if(result.contains("\n")) {
//                result = result.replace("\n", "");
//            }
//        } catch (Throwable var5) {
//            MobLog.getInstance().w(var5);
//        }
//
//        return result;
//    }
//
//    public int getOSVersionInt() {
//        return VERSION.SDK_INT;
//    }
//
//    public String getOSVersionName() {
//        return VERSION.RELEASE;
//    }
//
//    public String getOSLanguage() {
//        return Locale.getDefault().getLanguage();
//    }
//
//    public String getAppLanguage() {
//        return this.context.getResources().getConfiguration().locale.getLanguage();
//    }
//
//    public String getOSCountry() {
//        return Locale.getDefault().getCountry();
//    }
//
//    public String getScreenSize() {
//        int[] size = ResHelper.getScreenSize(this.context);
//        return this.context.getResources().getConfiguration().orientation == 1?size[0] + "x" + size[1]:size[1] + "x" + size[0];
//    }
//
//    public String getCarrier() {
//        try {
//            Object t = this.getSystemServiceSave("phone");
//            if(t == null) {
//                return "-1";
//            } else {
//                StringBuilder sb = new StringBuilder();
//                sb.append("ge");
//                sb.append("tS");
//                sb.append("im");
//                sb.append("Op");
//                sb.append("er");
//                sb.append("at");
//                sb.append("or");
//                String operator = (String)ReflectHelper.invokeInstanceMethod(t, sb.toString(), new Object[0]);
//                if(TextUtils.isEmpty(operator)) {
//                    operator = "-1";
//                }
//
//                return operator;
//            }
//        } catch (Throwable var4) {
//            MobLog.getInstance().w(var4);
//            return "-1";
//        }
//    }
//
//    public String getCarrierName() {
//        Object tm = this.getSystemServiceSave("phone");
//        if(tm == null) {
//            return null;
//        } else {
//            try {
//                if(this.checkPermission("android.permission.READ_PHONE_STATE")) {
//                    StringBuilder t = new StringBuilder();
//                    t.append("ge");
//                    t.append("tS");
//                    t.append("im");
//                    t.append("Op");
//                    t.append("er");
//                    t.append("at");
//                    t.append("or");
//                    t.append("Na");
//                    t.append("me");
//                    String operator = (String)ReflectHelper.invokeInstanceMethod(tm, t.toString(), new Object[0]);
//                    if(TextUtils.isEmpty(operator)) {
//                        operator = null;
//                    }
//
//                    return operator;
//                }
//            } catch (Throwable var4) {
//                MobLog.getInstance().w(var4);
//            }
//
//            return null;
//        }
//    }
//
//    public String getMCC() {
//        String imsi = this.getIMSI();
//        return imsi != null && imsi.length() >= 3?imsi.substring(0, 3):null;
//    }
//
//    public String getMNC() {
//        String imsi = this.getIMSI();
//        return imsi != null && imsi.length() >= 5?imsi.substring(3, 5):null;
//    }
//
//    public String getSimSerialNumber() {
//        try {
//            if(this.checkPermission("android.permission.READ_PHONE_STATE")) {
//                Object t = this.getSystemServiceSave("phone");
//                if(t == null) {
//                    return "-1";
//                }
//
//                StringBuilder sb = new StringBuilder();
//                sb.append("ge");
//                sb.append("tS");
//                sb.append("im");
//                sb.append("Se");
//                sb.append("ri");
//                sb.append("al");
//                sb.append("Nu");
//                sb.append("mb");
//                sb.append("er");
//                return (String)ReflectHelper.invokeInstanceMethod(t, sb.toString(), new Object[0]);
//            }
//        } catch (Throwable var3) {
//            MobLog.getInstance().w(var3);
//        }
//
//        return "-1";
//    }
//
//    public String getLine1Number() {
//        try {
//            if(this.checkPermission("android.permission.READ_PHONE_STATE")) {
//                Object t = this.getSystemServiceSave("phone");
//                if(t == null) {
//                    return "-1";
//                }
//
//                StringBuilder sb = new StringBuilder();
//                sb.append("ge");
//                sb.append("tL");
//                sb.append("in");
//                sb.append("e1");
//                sb.append("Nu");
//                sb.append("mb");
//                sb.append("er");
//                return (String)ReflectHelper.invokeInstanceMethod(t, sb.toString(), new Object[0]);
//            }
//        } catch (Throwable var3) {
//            MobLog.getInstance().w(var3);
//        }
//
//        return "-1";
//    }
//
//    public String getBluetoothName() {
//        try {
//            StringBuilder e = new StringBuilder();
//            e.append("an");
//            e.append("dr");
//            e.append("oi");
//            e.append("d.");
//            e.append("bl");
//            e.append("ue");
//            e.append("to");
//            e.append("ot");
//            e.append("h.");
//            e.append("Bl");
//            e.append("ue");
//            e.append("to");
//            e.append("ot");
//            e.append("hA");
//            e.append("da");
//            e.append("pt");
//            e.append("er");
//            ReflectHelper.importClass(e.toString());
//            if(this.checkPermission("android.permission.BLUETOOTH")) {
//                StringBuilder sb1 = new StringBuilder();
//                sb1.append("ge");
//                sb1.append("tD");
//                sb1.append("ef");
//                sb1.append("au");
//                sb1.append("lt");
//                sb1.append("Ad");
//                sb1.append("ap");
//                sb1.append("te");
//                sb1.append("r");
//                Object myDevice = ReflectHelper.invokeStaticMethod("BluetoothAdapter", sb1.toString(), new Object[0]);
//                if(myDevice != null) {
//                    StringBuilder sb2 = new StringBuilder();
//                    sb2.append("ge");
//                    sb2.append("tN");
//                    sb2.append("am");
//                    sb2.append("e");
//                    return (String)ReflectHelper.invokeInstanceMethod(myDevice, sb2.toString(), new Object[0]);
//                }
//            }
//        } catch (Throwable var5) {
//            MobLog.getInstance().d(var5);
//        }
//
//        return null;
//    }
//
//    public String getSignMD5() {
//        try {
//            PackageInfo e = this.context.getPackageManager().getPackageInfo(this.getPackageName(), 64);
//            Signature[] signs = e.signatures;
//            return Data.MD5(signs[0].toByteArray());
//        } catch (Exception var3) {
//            MobLog.getInstance().w(var3);
//            return null;
//        }
//    }
//
//    private Object getSystemService(String name) {
//        return this.getSystemServiceSave(name);
//    }
//
//    private Object getSystemServiceSave(String name) {
//        try {
//            return this.context.getSystemService(name);
//        } catch (Throwable var3) {
//            MobLog.getInstance().w(var3);
//            return null;
//        }
//    }
//
//    public String getNetworkType() {
//        ConnectivityManager conn = (ConnectivityManager)this.getSystemServiceSave("connectivity");
//        if(conn == null) {
//            return "none";
//        } else {
//            try {
//                if(!this.checkPermission("android.permission.ACCESS_NETWORK_STATE")) {
//                    return "none";
//                }
//            } catch (Throwable var4) {
//                MobLog.getInstance().w(var4);
//                return "none";
//            }
//
//            NetworkInfo network = conn.getActiveNetworkInfo();
//            if(network != null && network.isAvailable()) {
//                int type = network.getType();
//                switch(type) {
//                    case 0:
//                        if(this.is4GMobileNetwork()) {
//                            return "4G";
//                        }
//
//                        return this.isFastMobileNetwork()?"3G":"2G";
//                    case 1:
//                        return "wifi";
//                    case 2:
//                    case 3:
//                    case 4:
//                    case 5:
//                    default:
//                        return String.valueOf(type);
//                    case 6:
//                        return "wimax";
//                    case 7:
//                        return "bluetooth";
//                    case 8:
//                        return "dummy";
//                    case 9:
//                        return "ethernet";
//                }
//            } else {
//                return "none";
//            }
//        }
//    }
//
//    public String getNetworkTypeForStatic() {
//        String networkType = this.getNetworkType().toLowerCase();
//        return !TextUtils.isEmpty(networkType) && !"none".equals(networkType)?(!networkType.startsWith("4g") && !networkType.startsWith("3g") && !networkType.startsWith("2g")?(networkType.startsWith("wifi")?"wifi":"other"):"cell"):"none";
//    }
//
//    public String getDetailNetworkTypeForStatic() {
//        String networkType = this.getNetworkType().toLowerCase();
//        return !TextUtils.isEmpty(networkType) && !"none".equals(networkType)?(networkType.startsWith("wifi")?"wifi":(networkType.startsWith("4g")?"4g":(networkType.startsWith("3g")?"3g":(networkType.startsWith("2g")?"2g":(networkType.startsWith("bluetooth")?"bluetooth":networkType))))):"none";
//    }
//
//    public int getPlatformCode() {
//        return 1;
//    }
//
//    private boolean is4GMobileNetwork() {
//        Object phone = this.getSystemServiceSave("phone");
//        if(phone == null) {
//            return false;
//        } else {
//            try {
//                StringBuilder t = new StringBuilder();
//                t.append("ge");
//                t.append("tN");
//                t.append("et");
//                t.append("wo");
//                t.append("rk");
//                t.append("Ty");
//                t.append("pe");
//                int type = ((Integer)ReflectHelper.invokeInstanceMethod(phone, t.toString(), new Object[0])).intValue();
//                return type == 13;
//            } catch (Throwable var4) {
//                MobLog.getInstance().w(var4);
//                return false;
//            }
//        }
//    }
//
//    private boolean isFastMobileNetwork() {
//        Object phone = this.getSystemServiceSave("phone");
//        if(phone == null) {
//            return false;
//        } else {
//            try {
//                StringBuilder t = new StringBuilder();
//                t.append("ge");
//                t.append("tN");
//                t.append("et");
//                t.append("wo");
//                t.append("rk");
//                t.append("Ty");
//                t.append("pe");
//                int type = ((Integer)ReflectHelper.invokeInstanceMethod(phone, t.toString(), new Object[0])).intValue();
//                switch(type) {
//                    case 0:
//                        return false;
//                    case 1:
//                        return false;
//                    case 2:
//                        return false;
//                    case 3:
//                        return true;
//                    case 4:
//                        return false;
//                    case 5:
//                        return true;
//                    case 6:
//                        return true;
//                    case 7:
//                        return false;
//                    case 8:
//                        return true;
//                    case 9:
//                        return true;
//                    case 10:
//                        return true;
//                    case 11:
//                        return false;
//                    case 12:
//                        return true;
//                    case 13:
//                        return true;
//                    case 14:
//                        return true;
//                    case 15:
//                        return true;
//                }
//            } catch (Throwable var4) {
//                MobLog.getInstance().w(var4);
//            }
//
//            return false;
//        }
//    }
//
//    public JSONArray getRunningApp() {
//        JSONArray appNmes = new JSONArray();
//        Object am = this.getSystemServiceSave("activity");
//        if(am == null) {
//            return appNmes;
//        } else {
//            try {
//                StringBuilder t = new StringBuilder();
//                t.append("ge");
//                t.append("tR");
//                t.append("un");
//                t.append("ni");
//                t.append("ng");
//                t.append("Ap");
//                t.append("pP");
//                t.append("ro");
//                t.append("ce");
//                t.append("ss");
//                t.append("es");
//                List apps = (List)ReflectHelper.invokeInstanceMethod(am, t.toString(), new Object[0]);
//                if(apps == null) {
//                    return appNmes;
//                }
//
//                Iterator var5 = apps.iterator();
//
//                while(var5.hasNext()) {
//                    Object app = var5.next();
//                    StringBuilder sb1 = new StringBuilder();
//                    sb1.append("pr");
//                    sb1.append("oc");
//                    sb1.append("es");
//                    sb1.append("sN");
//                    sb1.append("am");
//                    sb1.append("e");
//                    appNmes.put(ReflectHelper.getInstanceField(app, sb1.toString()));
//                }
//            } catch (Throwable var8) {
//                MobLog.getInstance().w(var8);
//            }
//
//            return appNmes;
//        }
//    }
//
//    public String getRunningAppStr() throws JSONException {
//        JSONArray apps = this.getRunningApp();
//        StringBuilder sb = new StringBuilder();
//
//        for(int i = 0; i < apps.length(); ++i) {
//            if(i > 0) {
//                sb.append(',');
//            }
//
//            sb.append(String.valueOf(apps.get(i)));
//        }
//
//        return sb.toString();
//    }
//
//    public String getDeviceKey() {
//        String deviceKey = null;
//
//        try {
//            deviceKey = this.getDeviceKeyWithDuid("comm/dbs/.duid");
//        } catch (Throwable var5) {
//            MobLog.getInstance().w(var5);
//        }
//
//        if(TextUtils.isEmpty(deviceKey) || deviceKey.length() < 40) {
//            deviceKey = this.genDeviceKey();
//        }
//
//        if(!TextUtils.isEmpty(deviceKey) && deviceKey.length() >= 40) {
//            return deviceKey.trim();
//        } else {
//            try {
//                deviceKey = this.getLocalDeviceKey();
//            } catch (Throwable var4) {
//                MobLog.getInstance().w(var4);
//                deviceKey = null;
//            }
//
//            if(!TextUtils.isEmpty(deviceKey) && deviceKey.length() >= 40) {
//                return deviceKey.trim();
//            } else {
//                if(TextUtils.isEmpty(deviceKey) || deviceKey.length() < 40) {
//                    deviceKey = this.getCharAndNumr(40);
//                }
//
//                if(deviceKey != null) {
//                    try {
//                        deviceKey = deviceKey.trim();
//                        this.saveLocalDeviceKey(deviceKey);
//                    } catch (Throwable var3) {
//                        MobLog.getInstance().w(var3);
//                    }
//                }
//
//                return deviceKey;
//            }
//        }
//    }
//
//    private String genDeviceKey() {
//        String newKey = null;
//
//        try {
//            String t = this.getMacAddress();
//            String udid = this.getDeviceId();
//            String model = this.getModel();
//            String data = t + ":" + udid + ":" + model;
//            byte[] bytes = Data.SHA1(data);
//            newKey = Data.byteToHex(bytes);
//        } catch (Throwable var7) {
//            MobLog.getInstance().d(var7);
//            newKey = null;
//        }
//
//        return newKey;
//    }
//
//    public String getCharAndNumr(int length) {
//        long currentTime = System.currentTimeMillis();
//        long elapseTime = SystemClock.elapsedRealtime();
//        long realTime = currentTime ^ elapseTime;
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append(realTime);
//        Random random = new Random();
//
//        for(int i = 0; i < length; ++i) {
//            String charOrNum = random.nextInt(2) % 2 == 0?"char":"num";
//            if("char".equalsIgnoreCase(charOrNum)) {
//                char charValue = (char)(97 + random.nextInt(26));
//                stringBuffer.insert(i + 1, charValue);
//            } else {
//                stringBuffer.insert(stringBuffer.length(), random.nextInt(10));
//            }
//        }
//
//        return stringBuffer.toString().substring(0, 40);
//    }
//
//    private String getDeviceKeyWithDuid(String duidFilePath) throws Throwable {
//        HashMap map = null;
//
//        try {
//            File deviceInfo = new File(ResHelper.getCacheRoot(this.context), duidFilePath);
//            if(deviceInfo.exists() && deviceInfo.isFile()) {
//                ObjectInputStream deviceKey = null;
//
//                try {
//                    FileInputStream t = new FileInputStream(deviceInfo);
//                    deviceKey = new ObjectInputStream(t);
//                    map = (HashMap)deviceKey.readObject();
//                } catch (Throwable var19) {
//                    MobLog.getInstance().w(var19);
//                } finally {
//                    if(deviceKey != null) {
//                        try {
//                            deviceKey.close();
//                        } catch (Throwable var18) {
//                            ;
//                        }
//                    }
//
//                }
//            }
//        } catch (Throwable var21) {
//            MobLog.getInstance().w(var21);
//        }
//
//        if(map == null) {
//            return null;
//        } else {
//            HashMap deviceInfo1 = (HashMap)map.get("deviceInfo");
//            if(deviceInfo1 == null) {
//                return null;
//            } else {
//                String deviceKey1 = "";
//
//                try {
//                    String t1 = (String)deviceInfo1.get("mac");
//                    String deviceId = (String)deviceInfo1.get("imei");
//                    if(TextUtils.isEmpty(deviceId) && VERSION.SDK_INT >= 9) {
//                        deviceId = (String)deviceInfo1.get("serialno");
//                    }
//
//                    String model = (String)deviceInfo1.get("model");
//                    String data = t1 + ":" + deviceId + ":" + model;
//                    byte[] bytes = Data.SHA1(data);
//                    deviceKey1 = Data.byteToHex(bytes);
//                } catch (Throwable var17) {
//                    MobLog.getInstance().d(var17);
//                    deviceKey1 = null;
//                }
//
//                return deviceKey1;
//            }
//        }
//    }
//
//    private String getLocalDeviceKey() throws Throwable {
//        if(!this.getSdcardState()) {
//            return null;
//        } else {
//            String sdPath = this.getSdcardPath();
//            File cacheRoot = new File(sdPath, "ShareSDK");
//            File keyFile;
//            if(cacheRoot.exists()) {
//                keyFile = new File(cacheRoot, ".dk");
//                if(keyFile.exists()) {
//                    boolean fis = keyFile.renameTo(new File(ResHelper.getCacheRoot(this.context), ".dk"));
//                    if(fis) {
//                        keyFile.delete();
//                    }
//                }
//            }
//
//            keyFile = new File(ResHelper.getCacheRoot(this.context), ".dk");
//            if(!keyFile.exists()) {
//                return null;
//            } else {
//                FileInputStream fis1 = new FileInputStream(keyFile);
//                ObjectInputStream ois = new ObjectInputStream(fis1);
//                Object key = ois.readObject();
//                String strKey = null;
//                if(key != null && key instanceof char[]) {
//                    char[] cKey = (char[])((char[])key);
//                    strKey = String.valueOf(cKey);
//                }
//
//                ois.close();
//                return strKey;
//            }
//        }
//    }
//
//    private void saveLocalDeviceKey(String key) throws Throwable {
//        if(this.getSdcardState()) {
//            File keyFile = new File(ResHelper.getCacheRoot(this.context), ".dk");
//            if(keyFile.exists()) {
//                keyFile.delete();
//            }
//
//            FileOutputStream fos = new FileOutputStream(keyFile);
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            char[] cKey = key.toCharArray();
//            oos.writeObject(cKey);
//            oos.flush();
//            oos.close();
//        }
//    }
//
//    public String getPackageName() {
//        return this.context.getPackageName();
//    }
//
//    public String getAppName() {
//        String appName = this.context.getApplicationInfo().name;
//        if(appName != null) {
//            if(VERSION.SDK_INT < 25 || appName.endsWith(".*")) {
//                return appName;
//            }
//
//            try {
//                ReflectHelper.importClass(appName);
//                appName = null;
//            } catch (Throwable var3) {
//                ;
//            }
//        }
//
//        int appLbl = this.context.getApplicationInfo().labelRes;
//        if(appLbl > 0) {
//            appName = this.context.getString(appLbl);
//        } else {
//            appName = String.valueOf(this.context.getApplicationInfo().nonLocalizedLabel);
//        }
//
//        return appName;
//    }
//
//    public int getAppVersion() {
//        try {
//            PackageManager t = this.context.getPackageManager();
//            PackageInfo pi = t.getPackageInfo(this.context.getPackageName(), 0);
//            return pi.versionCode;
//        } catch (Throwable var3) {
//            MobLog.getInstance().d(var3);
//            return 0;
//        }
//    }
//
//    public String getAppVersionName() {
//        try {
//            PackageManager t = this.context.getPackageManager();
//            PackageInfo pi = t.getPackageInfo(this.context.getPackageName(), 0);
//            return pi.versionName;
//        } catch (Throwable var3) {
//            MobLog.getInstance().d(var3);
//            return "1.0";
//        }
//    }
//
//    public ArrayList<HashMap<String, String>> getInstalledApp(boolean includeSystemApp) {
//        try {
//            ArrayList t = new ArrayList();
//
//            try {
//                StringBuilder apps = new StringBuilder();
//                apps.append("pm");
//                apps.append(" l");
//                apps.append("is");
//                apps.append("t ");
//                apps.append("pa");
//                apps.append("ck");
//                apps.append("ag");
//                apps.append("es");
//                Process pm = Runtime.getRuntime().exec(apps.toString());
//                InputStreamReader isr = new InputStreamReader(pm.getInputStream(), "utf-8");
//                BufferedReader pkg = new BufferedReader(isr);
//
//                for(String pi = pkg.readLine(); pi != null; pi = pkg.readLine()) {
//                    pi = pi.toLowerCase().trim();
//                    if(pi.startsWith("package:")) {
//                        pi = pi.substring("package:".length()).trim();
//                        t.add(pi);
//                    }
//                }
//
//                pkg.close();
//                pm.destroy();
//            } catch (Throwable var13) {
//                MobLog.getInstance().w(var13);
//            }
//
//            ArrayList apps1 = new ArrayList();
//            PackageManager pm1 = this.context.getPackageManager();
//            Iterator isr1 = t.iterator();
//
//            while(isr1.hasNext()) {
//                String pkg1 = (String)isr1.next();
//                PackageInfo pi1 = null;
//
//                try {
//                    pi1 = pm1.getPackageInfo(pkg1, 0);
//                } catch (Throwable var12) {
//                    MobLog.getInstance().d(var12);
//                }
//
//                if(pi1 != null && (includeSystemApp || !this.isSystemApp(pi1))) {
//                    HashMap app = new HashMap();
//                    app.put("pkg", pi1.packageName);
//                    String appName = pi1.applicationInfo.name;
//                    if(appName == null) {
//                        int appLbl = pi1.applicationInfo.labelRes;
//                        if(appLbl > 0) {
//                            CharSequence label = pm1.getText(pi1.packageName, appLbl, pi1.applicationInfo);
//                            if(label != null) {
//                                appName = label.toString().trim();
//                            }
//                        }
//
//                        if(appName == null) {
//                            appName = String.valueOf(pi1.applicationInfo.nonLocalizedLabel);
//                        }
//                    }
//
//                    app.put("name", appName);
//                    app.put("version", pi1.versionName);
//                    apps1.add(app);
//                }
//            }
//
//            return apps1;
//        } catch (Throwable var14) {
//            MobLog.getInstance().w(var14);
//            return new ArrayList();
//        }
//    }
//
//    private boolean isSystemApp(PackageInfo pi) {
//        boolean isSysApp = (pi.applicationInfo.flags & 1) == 1;
//        boolean isSysUpd = (pi.applicationInfo.flags & 128) == 1;
//        return isSysApp || isSysUpd;
//    }
//
//    public String getNetworkOperator() {
//        Object tm = this.getSystemServiceSave("phone");
//        if(tm == null) {
//            return null;
//        } else {
//            try {
//                StringBuilder t = new StringBuilder();
//                t.append("ge");
//                t.append("tN");
//                t.append("et");
//                t.append("wo");
//                t.append("rk");
//                t.append("Op");
//                t.append("er");
//                t.append("at");
//                t.append("or");
//                return (String)ReflectHelper.invokeInstanceMethod(tm, t.toString(), new Object[0]);
//            } catch (Throwable var3) {
//                MobLog.getInstance().w(var3);
//                return null;
//            }
//        }
//    }
//
//    public boolean checkPermission(String permission) throws Throwable {
//        int res;
//        if(VERSION.SDK_INT >= 23) {
//            try {
//                StringBuilder t = new StringBuilder();
//                t.append("an");
//                t.append("dr");
//                t.append("oi");
//                t.append("d.");
//                t.append("co");
//                t.append("nt");
//                t.append("en");
//                t.append("t.");
//                t.append("Co");
//                t.append("nt");
//                t.append("ex");
//                t.append("t");
//                ReflectHelper.importClass(t.toString());
//                StringBuilder sb1 = new StringBuilder();
//                sb1.append("ch");
//                sb1.append("ec");
//                sb1.append("kS");
//                sb1.append("el");
//                sb1.append("fP");
//                sb1.append("er");
//                sb1.append("mi");
//                sb1.append("ss");
//                sb1.append("io");
//                sb1.append("n");
//                Integer ret = (Integer)ReflectHelper.invokeInstanceMethod(this.context, sb1.toString(), new Object[]{permission});
//                res = ret == null?-1:ret.intValue();
//            } catch (Throwable var6) {
//                MobLog.getInstance().d(var6);
//                res = -1;
//            }
//        } else {
//            res = this.context.getPackageManager().checkPermission(permission, this.getPackageName());
//        }
//
//        return res == 0;
//    }
//
//    public String getTopTaskPackageName() {
//        boolean hasPer = false;
//
//        try {
//            hasPer = this.checkPermission("android.permission.GET_TASKS");
//        } catch (Throwable var8) {
//            MobLog.getInstance().w(var8);
//            hasPer = false;
//        }
//
//        if(hasPer) {
//            try {
//                Object t = this.getSystemServiceSave("activity");
//                if(t == null) {
//                    return null;
//                }
//
//                StringBuilder sb;
//                List processInfos;
//                StringBuilder sb1;
//                if(VERSION.SDK_INT <= 20) {
//                    sb = new StringBuilder();
//                    sb.append("ge");
//                    sb.append("tR");
//                    sb.append("un");
//                    sb.append("ni");
//                    sb.append("ng");
//                    sb.append("Ta");
//                    sb.append("sk");
//                    sb.append("s");
//                    processInfos = (List)ReflectHelper.invokeInstanceMethod(t, sb.toString(), new Object[]{Integer.valueOf(1)});
//                    sb1 = new StringBuilder();
//                    sb1.append("to");
//                    sb1.append("pA");
//                    sb1.append("ct");
//                    sb1.append("iv");
//                    sb1.append("it");
//                    sb1.append("y");
//                    Object processName1 = ReflectHelper.getInstanceField(processInfos.get(0), sb1.toString());
//                    StringBuilder sb2 = new StringBuilder();
//                    sb2.append("ge");
//                    sb2.append("tP");
//                    sb2.append("ac");
//                    sb2.append("ka");
//                    sb2.append("ge");
//                    sb2.append("Na");
//                    sb2.append("me");
//                    return (String)ReflectHelper.invokeInstanceMethod(processName1, sb2.toString(), new Object[0]);
//                }
//
//                sb = new StringBuilder();
//                sb.append("ge");
//                sb.append("tR");
//                sb.append("un");
//                sb.append("ni");
//                sb.append("ng");
//                sb.append("Ap");
//                sb.append("pP");
//                sb.append("ro");
//                sb.append("ce");
//                sb.append("ss");
//                sb.append("es");
//                processInfos = (List)ReflectHelper.invokeInstanceMethod(t, sb.toString(), new Object[0]);
//                sb1 = new StringBuilder();
//                sb1.append("pr");
//                sb1.append("oc");
//                sb1.append("es");
//                sb1.append("sN");
//                sb1.append("am");
//                sb1.append("e");
//                String processName = (String)ReflectHelper.getInstanceField(processInfos.get(0), sb1.toString());
//                return processName.split(":")[0];
//            } catch (Throwable var9) {
//                MobLog.getInstance().d(var9);
//            }
//        }
//
//        return null;
//    }
//
//    public boolean amIOnForeground() {
//        try {
//            Object t = this.currentActivityThread();
//            StringBuilder sb2 = new StringBuilder();
//            sb2.append("mA");
//            sb2.append("ct");
//            sb2.append("iv");
//            sb2.append("it");
//            sb2.append("ie");
//            sb2.append("s");
//            Map activities = (Map)ReflectHelper.getInstanceField(t, sb2.toString());
//            Iterator var4 = activities.values().iterator();
//
//            while(var4.hasNext()) {
//                Object activity = var4.next();
//                StringBuilder sb3 = new StringBuilder();
//                sb3.append("st");
//                sb3.append("op");
//                sb3.append("pe");
//                sb3.append("d");
//                Boolean stopped = (Boolean)ReflectHelper.getInstanceField(activity, sb3.toString());
//                if(!stopped.booleanValue()) {
//                    return true;
//                }
//            }
//        } catch (Throwable var8) {
//            MobLog.getInstance().w(var8);
//        }
//
//        return false;
//    }
//
//    public boolean getSdcardState() {
//        try {
//            return this.checkPermission("android.permission.WRITE_EXTERNAL_STORAGE") && "mounted".equals(Environment.getExternalStorageState());
//        } catch (Throwable var2) {
//            MobLog.getInstance().w(var2);
//            return false;
//        }
//    }
//
//    public String getSdcardPath() {
//        return Environment.getExternalStorageDirectory().getAbsolutePath();
//    }
//
//    public String getAndroidID() {
//        String androidId = Secure.getString(this.context.getContentResolver(), "android_id");
//        MobLog.getInstance().i("getAndroidID === " + androidId, new Object[0]);
//        return androidId;
//    }
//
//    public String getAdvertisingID() throws Throwable {
//        if(Looper.myLooper() == Looper.getMainLooper()) {
//            throw new Throwable("Do not call this function from the main thread !");
//        } else {
//            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
//            intent.setPackage("com.google.android.gms");
//            DeviceHelper.GSConnection gsc = new DeviceHelper.GSConnection(null);
//            String adsid = null;
//
//            Parcel input;
//            try {
//                this.context.bindService(intent, gsc, 1);
//                IBinder t = gsc.takeBinder();
//                input = Parcel.obtain();
//                Parcel output = Parcel.obtain();
//                input.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
//                t.transact(1, input, output, 0);
//                output.readException();
//                adsid = output.readString();
//                output.recycle();
//                input.recycle();
//                String var7 = adsid;
//                return var7;
//            } catch (Throwable var11) {
//                MobLog.getInstance().d(var11);
//                input = adsid;
//            } finally {
//                this.context.unbindService(gsc);
//            }
//
//            return input;
//        }
//    }
//
//    public void hideSoftInput(View view) {
//        Object service = this.getSystemServiceSave("input_method");
//        if(service != null) {
//            InputMethodManager imm = (InputMethodManager)service;
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
//    }
//
//    public void showSoftInput(View view) {
//        Object service = this.getSystemServiceSave("input_method");
//        if(service != null) {
//            InputMethodManager imm = (InputMethodManager)service;
//            imm.toggleSoftInputFromWindow(view.getWindowToken(), 2, 0);
//        }
//    }
//
//    public boolean isMainProcess(int pid) {
//        try {
//            Object t = this.getSystemServiceSave("activity");
//            StringBuilder sb = new StringBuilder();
//            sb.append("ge");
//            sb.append("tR");
//            sb.append("un");
//            sb.append("ni");
//            sb.append("ng");
//            sb.append("Ap");
//            sb.append("pP");
//            sb.append("ro");
//            sb.append("ce");
//            sb.append("ss");
//            sb.append("es");
//            List rps = (List)ReflectHelper.invokeInstanceMethod(t, sb.toString(), new Object[0]);
//            if(rps == null) {
//                return pid <= 0;
//            } else {
//                String application = null;
//                int myPid = pid <= 0?android.os.Process.myPid():pid;
//                Iterator var7 = rps.iterator();
//
//                while(var7.hasNext()) {
//                    Object appProcess = var7.next();
//                    StringBuilder sb1 = new StringBuilder();
//                    sb1.append("pi");
//                    sb1.append("d");
//                    int apid = ((Integer)ReflectHelper.getInstanceField(appProcess, sb1.toString())).intValue();
//                    if(apid == myPid) {
//                        StringBuilder sb2 = new StringBuilder();
//                        sb2.append("pr");
//                        sb2.append("oc");
//                        sb2.append("es");
//                        sb2.append("sN");
//                        sb2.append("am");
//                        sb2.append("e");
//                        application = (String)ReflectHelper.getInstanceField(appProcess, sb2.toString());
//                        break;
//                    }
//                }
//
//                return this.getPackageName().equals(application);
//            }
//        } catch (Throwable var12) {
//            MobLog.getInstance().w(var12);
//            return false;
//        }
//    }
//
//    public String getIMSI() {
//        Object phone = this.getSystemServiceSave("phone");
//        if(phone == null) {
//            return null;
//        } else {
//            String imsi = null;
//
//            try {
//                if(this.checkPermission("android.permission.READ_PHONE_STATE")) {
//                    StringBuilder t = new StringBuilder();
//                    t.append("ge");
//                    t.append("tS");
//                    t.append("ub");
//                    t.append("sc");
//                    t.append("ri");
//                    t.append("be");
//                    t.append("rI");
//                    t.append("d");
//                    imsi = (String)ReflectHelper.invokeInstanceMethod(phone, t.toString(), new Object[0]);
//                }
//            } catch (Throwable var4) {
//                MobLog.getInstance().w(var4);
//            }
//
//            return TextUtils.isEmpty(imsi)?null:imsi;
//        }
//    }
//
//    public String getIPAddress() {
//        try {
//            if(this.checkPermission("android.permission.INTERNET")) {
//                Enumeration e = NetworkInterface.getNetworkInterfaces();
//
//                while(e.hasMoreElements()) {
//                    NetworkInterface intf = (NetworkInterface)e.nextElement();
//                    Enumeration enumIpAddr = intf.getInetAddresses();
//
//                    while(enumIpAddr.hasMoreElements()) {
//                        InetAddress inetAddress = (InetAddress)enumIpAddr.nextElement();
//                        if(!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
//                            return inetAddress.getHostAddress();
//                        }
//                    }
//                }
//            }
//        } catch (Throwable var5) {
//            MobLog.getInstance().w(var5);
//        }
//
//        return "0.0.0.0";
//    }
//
//    public Location getLocation(int GPSTimeout, int networkTimeout, boolean useLastKnown) {
//        try {
//            if(this.checkPermission("android.permission.ACCESS_FINE_LOCATION")) {
//                LocationHelper t = new LocationHelper();
//                return t.getLocation(this.context, GPSTimeout, networkTimeout, useLastKnown);
//            }
//        } catch (Throwable var5) {
//            MobLog.getInstance().d(var5);
//        }
//
//        return null;
//    }
//
//    public HashMap<String, String> ping(String address, int count, int packetsize) {
//        ArrayList sucRes = new ArrayList();
//
//        int fldCount;
//        try {
//            String sucCount = "ping -c " + count + " -s " + packetsize + " " + address;
//            fldCount = packetsize + 8;
//            Process min = Runtime.getRuntime().exec(sucCount);
//            InputStreamReader max = new InputStreamReader(min.getInputStream());
//            BufferedReader average = new BufferedReader(max);
//
//            for(String map = average.readLine(); map != null; map = average.readLine()) {
//                if(map.startsWith(fldCount + " bytes from")) {
//                    if(map.endsWith("ms")) {
//                        map = map.substring(0, map.length() - 2).trim();
//                    } else if(map.endsWith("s")) {
//                        map = map.substring(0, map.length() - 1).trim() + "000";
//                    }
//
//                    int item = map.indexOf("time=");
//                    if(item > 0) {
//                        map = map.substring(item + 5).trim();
//
//                        try {
//                            sucRes.add(Float.valueOf(Float.parseFloat(map)));
//                        } catch (Throwable var13) {
//                            MobLog.getInstance().w(var13);
//                        }
//                    }
//                }
//            }
//
//            min.waitFor();
//        } catch (Throwable var14) {
//            MobLog.getInstance().d(var14);
//        }
//
//        int var15 = sucRes.size();
//        fldCount = count - sucRes.size();
//        float var16 = 0.0F;
//        float var17 = 0.0F;
//        float var18 = 0.0F;
//        if(var15 > 0) {
//            var16 = 3.4028235E38F;
//
//            for(int var20 = 0; var20 < var15; ++var20) {
//                float var19 = ((Float)sucRes.get(var20)).floatValue();
//                if(var19 < var16) {
//                    var16 = var19;
//                }
//
//                if(var19 > var17) {
//                    var17 = var19;
//                }
//
//                var18 += var19;
//            }
//
//            var18 /= (float)var15;
//        }
//
//        HashMap var21 = new HashMap();
//        var21.put("address", address);
//        var21.put("transmitted", String.valueOf(count));
//        var21.put("received", String.valueOf(var15));
//        var21.put("loss", String.valueOf(fldCount));
//        var21.put("min", String.valueOf(var16));
//        var21.put("max", String.valueOf(var17));
//        var21.put("avg", String.valueOf(var18));
//        return var21;
//    }
//
//    public int getCellId() {
//        try {
//            if(this.checkPermission("android.permission.ACCESS_COARSE_LOCATION")) {
//                Object t = this.getSystemServiceSave("phone");
//                if(t != null) {
//                    StringBuilder sb = new StringBuilder();
//                    sb.append("ge");
//                    sb.append("tC");
//                    sb.append("el");
//                    sb.append("lL");
//                    sb.append("oc");
//                    sb.append("at");
//                    sb.append("io");
//                    sb.append("n");
//                    Object loc = ReflectHelper.invokeInstanceMethod(t, sb.toString(), new Object[0]);
//                    if(loc != null) {
//                        StringBuilder sb1 = new StringBuilder();
//                        sb1.append("ge");
//                        sb1.append("tC");
//                        sb1.append("id");
//                        return ((Integer)ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(loc, sb1.toString(), new Object[0]), Integer.valueOf(-1))).intValue();
//                    }
//                }
//            }
//        } catch (Throwable var5) {
//            MobLog.getInstance().d(var5);
//        }
//
//        return -1;
//    }
//
//    public int getCellLac() {
//        try {
//            if(this.checkPermission("android.permission.ACCESS_COARSE_LOCATION")) {
//                Object t = this.getSystemServiceSave("phone");
//                if(t != null) {
//                    StringBuilder sb = new StringBuilder();
//                    sb.append("ge");
//                    sb.append("tC");
//                    sb.append("el");
//                    sb.append("lL");
//                    sb.append("oc");
//                    sb.append("at");
//                    sb.append("io");
//                    sb.append("n");
//                    Object loc = ReflectHelper.invokeInstanceMethod(t, sb.toString(), new Object[0]);
//                    if(loc != null) {
//                        StringBuilder sb1 = new StringBuilder();
//                        sb1.append("ge");
//                        sb1.append("tL");
//                        sb1.append("ac");
//                        return ((Integer)ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(loc, sb1.toString(), new Object[0]), Integer.valueOf(-1))).intValue();
//                    }
//                }
//            }
//        } catch (Throwable var5) {
//            MobLog.getInstance().d(var5);
//        }
//
//        return -1;
//    }
//
//    public String getDeviceType() {
//        UiModeManager um = (UiModeManager)this.getSystemServiceSave("uimode");
//        if(um != null) {
//            int type = um.getCurrentModeType();
//            switch(type) {
//                case 1:
//                    return "NO_UI";
//                case 2:
//                    return "DESK";
//                case 3:
//                    return "CAR";
//                case 4:
//                    return "TELEVISION";
//                case 5:
//                    return "APPLIANCE";
//                case 6:
//                    return "WATCH";
//            }
//        }
//
//        return "UNDEFINED";
//    }
//
//    public Activity getTopActivity() {
//        try {
//            Object thread = this.currentActivityThread();
//            StringBuilder sb2 = new StringBuilder();
//            sb2.append("mA");
//            sb2.append("ct");
//            sb2.append("iv");
//            sb2.append("it");
//            sb2.append("ie");
//            sb2.append("s");
//            Map activities = (Map)ReflectHelper.getInstanceField(thread, sb2.toString());
//            Iterator var4 = activities.values().iterator();
//
//            Object activity;
//            StringBuilder sb3;
//            Boolean stopped;
//            StringBuilder sb4;
//            while(var4.hasNext()) {
//                activity = var4.next();
//                sb3 = new StringBuilder();
//                sb3.append("pa");
//                sb3.append("us");
//                sb3.append("ed");
//                stopped = (Boolean)ReflectHelper.getInstanceField(activity, sb3.toString());
//                if(!stopped.booleanValue()) {
//                    sb4 = new StringBuilder();
//                    sb4.append("ac");
//                    sb4.append("ti");
//                    sb4.append("vi");
//                    sb4.append("ty");
//                    return (Activity)ReflectHelper.getInstanceField(activity, sb4.toString());
//                }
//            }
//
//            var4 = activities.values().iterator();
//
//            while(var4.hasNext()) {
//                activity = var4.next();
//                sb3 = new StringBuilder();
//                sb3.append("st");
//                sb3.append("op");
//                sb3.append("pe");
//                sb3.append("d");
//                stopped = (Boolean)ReflectHelper.getInstanceField(activity, sb3.toString());
//                if(!stopped.booleanValue()) {
//                    sb4 = new StringBuilder();
//                    sb4.append("ac");
//                    sb4.append("ti");
//                    sb4.append("vi");
//                    sb4.append("ty");
//                    return (Activity)ReflectHelper.getInstanceField(activity, sb4.toString());
//                }
//            }
//        } catch (Throwable var9) {
//            ;
//        }
//
//        return null;
//    }
//
//    public Object currentActivityThread() {
//        final ReflectRunnable mainThreadAct = new ReflectRunnable() {
//            public Object run(Object arg) {
//                try {
//                    StringBuilder t = new StringBuilder();
//                    t.append("an");
//                    t.append("dr");
//                    t.append("oi");
//                    t.append("d.");
//                    t.append("ap");
//                    t.append("p.");
//                    t.append("Ac");
//                    t.append("ti");
//                    t.append("vi");
//                    t.append("ty");
//                    t.append("Th");
//                    t.append("re");
//                    t.append("ad");
//                    String clzName = ReflectHelper.importClass(t.toString());
//                    StringBuilder sb1 = new StringBuilder();
//                    sb1.append("cu");
//                    sb1.append("rr");
//                    sb1.append("en");
//                    sb1.append("tA");
//                    sb1.append("ct");
//                    sb1.append("iv");
//                    sb1.append("it");
//                    sb1.append("yT");
//                    sb1.append("hr");
//                    sb1.append("ea");
//                    sb1.append("d");
//                    return ReflectHelper.invokeStaticMethod(clzName, sb1.toString(), new Object[0]);
//                } catch (Throwable var5) {
//                    MobLog.getInstance().w(var5);
//                    return null;
//                }
//            }
//        };
//        if(Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
//            return mainThreadAct.run((Object)null);
//        } else {
//            final Object lock = new Object();
//            final Object[] output = new Object[1];
//            synchronized(lock) {
//                UIHandler.sendEmptyMessage(0, new Handler.Callback() {
//                    public boolean handleMessage(Message msg) {
//                        Object var2 = lock;
//                        synchronized(lock) {
//                            try {
//                                output[0] = mainThreadAct.run((Object)null);
//                                lock.notify();
//                            } catch (Throwable var5) {
//                                MobLog.getInstance().w(var5);
//                            }
//
//                            return false;
//                        }
//                    }
//                });
//
//                try {
//                    lock.wait();
//                } catch (Throwable var7) {
//                    MobLog.getInstance().w(var7);
//                }
//            }
//
//            return output[0];
//        }
//    }
//
//    public ArrayList<HashMap<String, Object>> getAvailableWifiList() {
//        try {
//            if(this.checkPermission("android.permission.ACCESS_WIFI_STATE")) {
//                Object t = this.getSystemServiceSave("wifi");
//                if(t == null) {
//                    return null;
//                }
//
//                StringBuilder sb = new StringBuilder();
//                sb.append("ge");
//                sb.append("tS");
//                sb.append("ca");
//                sb.append("nR");
//                sb.append("es");
//                sb.append("ul");
//                sb.append("ts");
//                List list = (List)ReflectHelper.invokeInstanceMethod(t, sb.toString(), new Object[0]);
//                if(list == null) {
//                    return null;
//                }
//
//                StringBuilder sb1 = new StringBuilder();
//                sb1.append("SS");
//                sb1.append("ID");
//                sb1.append(",B");
//                sb1.append("SS");
//                sb1.append("ID");
//                sb1.append(",h");
//                sb1.append("es");
//                sb1.append("si");
//                sb1.append("d,");
//                sb1.append("an");
//                sb1.append("qp");
//                sb1.append("Do");
//                sb1.append("ma");
//                sb1.append("in");
//                sb1.append("Id");
//                sb1.append(",c");
//                sb1.append("ap");
//                sb1.append("ab");
//                sb1.append("il");
//                sb1.append("it");
//                sb1.append("ie");
//                sb1.append("s,");
//                sb1.append("le");
//                sb1.append("ve");
//                sb1.append("l,");
//                sb1.append("fr");
//                sb1.append("eq");
//                sb1.append("ue");
//                sb1.append("nc");
//                sb1.append("y,");
//                sb1.append("ch");
//                sb1.append("an");
//                sb1.append("ne");
//                sb1.append("lW");
//                sb1.append("id");
//                sb1.append("th");
//                sb1.append(",c");
//                sb1.append("en");
//                sb1.append("te");
//                sb1.append("rF");
//                sb1.append("re");
//                sb1.append("q0");
//                sb1.append(",c");
//                sb1.append("en");
//                sb1.append("te");
//                sb1.append("rF");
//                sb1.append("re");
//                sb1.append("q1");
//                sb1.append(",t");
//                sb1.append("im");
//                sb1.append("es");
//                sb1.append("ta");
//                sb1.append("mp");
//                sb1.append(",s");
//                sb1.append("ee");
//                sb1.append("n,");
//                sb1.append("is");
//                sb1.append("Au");
//                sb1.append("to");
//                sb1.append("Jo");
//                sb1.append("in");
//                sb1.append("Ca");
//                sb1.append("nd");
//                sb1.append("id");
//                sb1.append("at");
//                sb1.append("e,");
//                sb1.append("nu");
//                sb1.append("mI");
//                sb1.append("pC");
//                sb1.append("on");
//                sb1.append("fi");
//                sb1.append("gF");
//                sb1.append("ai");
//                sb1.append("lu");
//                sb1.append("re");
//                sb1.append("s,");
//                sb1.append("bl");
//                sb1.append("ac");
//                sb1.append("kL");
//                sb1.append("is");
//                sb1.append("tT");
//                sb1.append("im");
//                sb1.append("es");
//                sb1.append("ta");
//                sb1.append("mp");
//                sb1.append(",u");
//                sb1.append("nt");
//                sb1.append("ru");
//                sb1.append("st");
//                sb1.append("ed");
//                sb1.append(",n");
//                sb1.append("um");
//                sb1.append("Co");
//                sb1.append("nn");
//                sb1.append("ec");
//                sb1.append("ti");
//                sb1.append("on");
//                sb1.append(",n");
//                sb1.append("um");
//                sb1.append("Us");
//                sb1.append("ag");
//                sb1.append("e,");
//                sb1.append("di");
//                sb1.append("st");
//                sb1.append("an");
//                sb1.append("ce");
//                sb1.append("Cm");
//                sb1.append(",d");
//                sb1.append("is");
//                sb1.append("ta");
//                sb1.append("nc");
//                sb1.append("eS");
//                sb1.append("dC");
//                sb1.append("m,");
//                sb1.append("fl");
//                sb1.append("ag");
//                sb1.append("s");
//                String[] fields = TextUtils.split(sb1.toString(), ",");
//                StringBuilder sb2 = new StringBuilder();
//                sb2.append("wi");
//                sb2.append("fi");
//                sb2.append("Ss");
//                sb2.append("id");
//                sb2.append(",v");
//                sb2.append("en");
//                sb2.append("ue");
//                sb2.append("Na");
//                sb2.append("me");
//                sb2.append(",o");
//                sb2.append("pe");
//                sb2.append("ra");
//                sb2.append("to");
//                sb2.append("rF");
//                sb2.append("ri");
//                sb2.append("en");
//                sb2.append("dl");
//                sb2.append("yN");
//                sb2.append("am");
//                sb2.append("e");
//                String[] fldsToString = TextUtils.split(sb2.toString(), ",");
//                ArrayList result = new ArrayList();
//
//                HashMap map;
//                for(Iterator var9 = list.iterator(); var9.hasNext(); result.add(map)) {
//                    Object sr = var9.next();
//                    map = new HashMap();
//                    String[] t1 = fields;
//                    int anqpLines = fields.length;
//
//                    int var14;
//                    String fld;
//                    for(var14 = 0; var14 < anqpLines; ++var14) {
//                        fld = t1[var14];
//
//                        try {
//                            map.put(fld, ReflectHelper.getInstanceField(sr, fld));
//                        } catch (Throwable var20) {
//                            MobLog.getInstance().w(var20);
//                        }
//                    }
//
//                    t1 = fldsToString;
//                    anqpLines = fldsToString.length;
//
//                    for(var14 = 0; var14 < anqpLines; ++var14) {
//                        fld = t1[var14];
//
//                        try {
//                            Object t2 = ReflectHelper.getInstanceField(sr, fld);
//                            map.put(fld, t2 == null?null:t2.toString());
//                        } catch (Throwable var19) {
//                            MobLog.getInstance().w(var19);
//                        }
//                    }
//
//                    StringBuilder var22;
//                    try {
//                        var22 = new StringBuilder();
//                        var22.append("is");
//                        var22.append("80");
//                        var22.append("21");
//                        var22.append("1m");
//                        var22.append("cR");
//                        var22.append("es");
//                        var22.append("po");
//                        var22.append("nd");
//                        var22.append("er");
//                        Object var23 = ReflectHelper.invokeInstanceMethod(sr, var22.toString(), new Object[0]);
//                        map.put("is80211McRTTResponder", var23);
//                    } catch (Throwable var18) {
//                        MobLog.getInstance().w(var18);
//                    }
//
//                    try {
//                        var22 = new StringBuilder();
//                        var22.append("an");
//                        var22.append("qp");
//                        var22.append("Li");
//                        var22.append("ne");
//                        var22.append("s");
//                        List var24 = (List)ReflectHelper.getInstanceField(sr, var22.toString());
//                        map.put("anqpLines", var24 == null?null:new ArrayList(var24));
//                    } catch (Throwable var17) {
//                        MobLog.getInstance().w(var17);
//                    }
//                }
//
//                return result;
//            }
//        } catch (Throwable var21) {
//            MobLog.getInstance().w(var21);
//        }
//
//        return null;
//    }
//
//    public boolean scanWifiList() {
//        try {
//            if(this.checkPermission("android.permission.CHANGE_WIFI_STATE")) {
//                Object t = this.getSystemServiceSave("wifi");
//                if(t == null) {
//                    return false;
//                }
//
//                StringBuilder sb = new StringBuilder();
//                sb.append("st");
//                sb.append("ar");
//                sb.append("tS");
//                sb.append("ca");
//                sb.append("n");
//                return ((Boolean)ReflectHelper.invokeInstanceMethod(t, sb.toString(), new Object[0])).booleanValue();
//            }
//        } catch (Throwable var3) {
//            MobLog.getInstance().w(var3);
//        }
//
//        return false;
//    }
//
//    public int getStatusBarHeight() {
//        try {
//            String t = ReflectHelper.importClass("com.android.internal.R$dimen");
//            int resId = ((Integer)ReflectHelper.getStaticField(t, "status_bar_height")).intValue();
//            return this.context.getResources().getDimensionPixelSize(resId);
//        } catch (Throwable var3) {
//            MobLog.getInstance().d(var3);
//            return -1;
//        }
//    }
//
//    private class GSConnection implements ServiceConnection {
//        boolean got;
//        private final BlockingQueue<IBinder> iBinders;
//
//        private GSConnection() {
//            this.got = false;
//            this.iBinders = new LinkedBlockingQueue();
//        }
//
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            try {
//                this.iBinders.put(service);
//            } catch (Throwable var4) {
//                MobLog.getInstance().w(var4);
//            }
//
//        }
//
//        public void onServiceDisconnected(ComponentName name) {
//        }
//
//        public IBinder takeBinder() throws InterruptedException {
//            if(this.got) {
//                throw new IllegalStateException();
//            } else {
//                this.got = true;
//                return (IBinder)this.iBinders.poll(1500L, TimeUnit.MILLISECONDS);
//            }
//        }
//    }
//}
