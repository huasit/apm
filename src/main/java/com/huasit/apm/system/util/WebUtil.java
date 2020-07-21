package com.huasit.apm.system.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;

public class WebUtil {

    /**
     *
     */
    public static void addCookie(HttpServletResponse response, String key, String value) throws Exception {
        addCookie(response, key, value, "/", 60 * 60 * 24 * 365);
    }

    /**
     *
     */
    public static void addCookie(HttpServletResponse response, String key, String value, String path, int maxAge)
            throws Exception {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     *
     */
    public static String getCookies(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies && cookies.length > 0) {
            String value = null;
            for (int i = 0; i < cookies.length; i++) {
                Cookie newCookie = cookies[i];
                if (newCookie.getName().equals(key) && newCookie.getValue() != null
                        && !newCookie.getValue().equals("")) {
                    value = newCookie.getValue();
                    break;
                }
            }
            if (value != null) {
                try {
                    value = URLDecoder.decode(value, "UTF-8");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return value;
            }
        }
        return null;
    }

    /**
     *
     */
    public static void cleanCookies(HttpServletResponse response, String key) {
        cleanCookies(response, key, "/");
    }

    /**
     *
     */
    public static void cleanCookies(HttpServletResponse response, String key, String path) {
        Cookie cookie = new Cookie(key, null);
        cookie.setPath(path);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    /**
     *
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        String[] headers = new String[]{"X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP"};
        for (String header : headers) {
            String i = request.getHeader(header);
            if (i != null && !"".equals(i) && !"unkown".equalsIgnoreCase(i)) {
                ip = i.contains(",") ? i.split(",")[0] : i;
                break;
            }
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    /**
     *
     */
    public static void excelExport(String fileName, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        response.setContentType("application/x-download;charset=UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename="
                + fileName + ".xls");
        OutputStream outputStream = response.getOutputStream();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        byte[] b = new byte[1024];
        while ((byteArrayInputStream.read(b)) > 0) {
            outputStream.write(b);
        }
        byteArrayInputStream.close();
        byteArrayOutputStream.close();
        outputStream.flush();
    }

    /**
     *
     */
    public static void excelWithError(String error, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        out.println("<!DOCTYPE HTML>");
        out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
        out.println("<head>");
        out.println("<meta http-equiv=\"content-type\" content=\"text/html;charset=UTF-8\">");
        out.println("<script>");
        out.println("alert(\""+ error +"\");");
        out.println("window.close();");
        out.println("</script>");
        out.println("</head>");
        out.println("</html>");
    }
}