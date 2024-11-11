package com.xg.supermarket.config;

import org.springframework.context.annotation.Configuration;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
@Configuration
public class ConstantsConfig {
   /*二维码相关数据*/
    public final static Map<String, Map> map = new HashMap();
    /*收银商品数据*/
    public final static Map<String, Map> cashierMap = new HashMap();
    public final static String FILE_UPLOAD_DIR = "D:/upload/";
    public final static String INPUT_NET_IP = getIntranetIp();
    public final static String OUTER_NET_IP = "http://"+getInternetIp(); //外网ip
    public final static String PORT = "8080";


    /*日期工具类常量*/
    public final static int CUT_OUT = 4;
    public final static int EVERYDAY_SECOND = 86400;
    public final static int MINUS_1 = -1;
    public final static int MINUS_7 = -7;
    public final static int MINUS_15 = -15;
    public final static int MINUS_3 = -3;
    public final static int MINUS_6 = -6;

    /**
     * 获得内网IP
     * @return 内网IP
     */
    private static String getIntranetIp(){
        try{
            return InetAddress.getLocalHost().getHostAddress();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得外网IP
     * @return 外网IP
     */
    private static String getInternetIp(){
        try{
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            Enumeration<InetAddress> addrs;
            while (networks.hasMoreElements())
            {
                addrs = networks.nextElement().getInetAddresses();
                while (addrs.hasMoreElements())
                {
                    ip = addrs.nextElement();
                    if (ip != null
                            && ip instanceof Inet4Address
                            && ip.isSiteLocalAddress()
                            && !ip.getHostAddress().equals(INPUT_NET_IP))
                    {
                        return ip.getHostAddress();
                    }
                }
            }

            // 如果没有外网IP，就返回内网IP
            return INPUT_NET_IP;
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
