package com.kb.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegistsThread extends Thread {
    private String url;
    private String phone;
    private String password;
    public RegistsThread(String url, String phone, String password){
        this.url = url;
        this.phone = phone;
        this.password=password;
    }
    /**
     * 向指定 URL 发送POST方法的请求
     * @return 所代表远程资源的响应结果
     * @throws Exception
     */
    public void doPost(){
        PrintWriter out = null;
        BufferedReader in = null;
        try{
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl
                    .openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setUseCaches(false);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);

            OutputStream outputStream = conn.getOutputStream();
            String content = "loginName=" + phone+"password="+password;
            if (content != null && !content.trim().equals("")){
                // 获取URLConnection对象对应的输出流
                out = new PrintWriter(outputStream);
                // 发送请求参数
                out.print(content);
                // flush输出流的缓冲
                out.flush();
            }
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            StringBuffer sb=new StringBuffer();
            String line;
            while ((line = in.readLine()) != null){
                 sb.append(line);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally{
            try{
                if (out != null){
                    out.close();
                }
                if (in != null){
                    in.close();
                }
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }
    @Override
    public void run() {
        super.run();
        doPost();
    }
}
