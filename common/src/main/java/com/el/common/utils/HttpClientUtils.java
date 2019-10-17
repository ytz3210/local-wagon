package com.el.common.utils;

import net.sf.json.JSONObject;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 */
@SuppressWarnings("deprecation")
public class HttpClientUtils {


    /**
     * @param url
     * @param params
     * @return net.sf.json.JSONObject
     * @Author ZhangJun
     * @Description get请求(http)
     * @Date 2018/11/14 14:15
     **/
    public static JSONObject getPairData(String url, Map<String, Object> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        if (null == params) {
            params = new HashMap<String, Object>();
        }
        // 拼接参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            NameValuePair pair = null;
            String key = entry.getKey().toString();
            if (entry.getValue() == null) {
                pair = new BasicNameValuePair(key, null);
            } else {
                String value = entry.getValue().toString();
                pair = new BasicNameValuePair(key, value);
            }
            list.add(pair);
        }
        String str = "";
        CloseableHttpResponse response = null;
        try {
            //转换为键值对
            str = EntityUtils.toString(new UrlEncodedFormEntity(list, Consts.UTF_8));
            if (!str.equals("")) {
                str = "?" + str;
            }
            HttpGet httpGet = new HttpGet(url + str);
            httpGet.setHeader("Content-Type", "text/html;charset=utf-8");
            response = httpClient.execute(httpGet);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /** 请求发送成功，并得到响应 **/
        String result = null;
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity httpEntity = response.getEntity();
            try {
                result = EntityUtils.toString(httpEntity);
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return JSONObject.fromObject(result);
    }

    /**
     * @param url
     * @param sign
     * @param httpType
     * @return java.lang.String
     * @Author ZhangJun
     * @Description get请求(http)
     * @Date 2018/11/14 14:14
     **/
    public static String getSoucheData(String url, String sign, String httpType) {
        CloseableHttpClient httpclient = null;
        if (httpType == "http") {
            httpclient = HttpClients.createDefault();
        } else if (httpType == "https") {
            httpclient = HttpClientUtils.createSSLClientDefault();
        }
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("x-head-sign", sign);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpGet);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /** 请求发送成功，并得到响应 **/
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity httpEntity = response.getEntity();
            String result = null;
            try {
                result = EntityUtils.toString(httpEntity);
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
        return null;
    }

    /**
     * @param url
     * @param params
     * @return net.sf.json.JSONObject
     * @Author ZhangJun
     * @Description post请求(http)
     * @Date 2018/11/14 14:15
     **/
    public static JSONObject postPairData(String url, Map<String, Object> params) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        // 拼接参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            NameValuePair pair = null;
            String key = entry.getKey().toString();
            if (entry.getValue() == null) {
                pair = new BasicNameValuePair(key, null);
            } else {
                String value = entry.getValue().toString();
                pair = new BasicNameValuePair(key, value);
            }
            list.add(pair);
        }
        CloseableHttpResponse response = null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(list));
            response = httpclient.execute(httpPost);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /** 请求发送成功，并得到响应 **/
        JSONObject jsonObject = null;
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity httpEntity = response.getEntity();
            String result = null;
            try {
                result = EntityUtils.toString(httpEntity);
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 返回json格式：
            jsonObject = JSONObject.fromObject(result);
        }
        return jsonObject;
    }


    /**
     * @param url
     * @param jsonStr
     * @return net.sf.json.JSONObject
     * @Author ZhangJun
     * @Description post请求(http)
     * @Date 2018/11/14 14:14
     **/
    public static JSONObject postJsonData(String url, String jsonStr) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            StringEntity entity = new StringEntity(jsonStr, "utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            response = httpclient.execute(httpPost);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /** 请求发送成功，并得到响应 **/
        JSONObject jsonObject = null;
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity httpEntity = response.getEntity();
            String result = null;
            try {
                result = EntityUtils.toString(httpEntity);
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 返回json格式：
            jsonObject = JSONObject.fromObject(result);
        }
        return jsonObject;
    }


    /**
     * @param url
     * @param jsonStr
     * @param sign
     * @return net.sf.json.JSONObject
     * @Author ZhangJun
     * @Description post请求(http)
     * @Date 2018/11/14 14:13
     **/
    public static JSONObject postSoucheJsonData(String url, String jsonStr, String sign) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("x-head-sign", sign);
        CloseableHttpResponse response = null;
        try {
            StringEntity entity = new StringEntity(jsonStr, "utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            response = httpclient.execute(httpPost);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /** 请求发送成功，并得到响应 **/
        JSONObject jsonObject = null;
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity httpEntity = response.getEntity();
            String result = null;
            try {
                result = EntityUtils.toString(httpEntity);
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(result);
            // 返回json格式：
            jsonObject = JSONObject.fromObject(result);
        }
        return jsonObject;
    }

    /**
     * @param url
     * @param params
     * @return net.sf.json.JSONObject
     * @Author ZhangJun
     * @Description post请求(https)
     * @Date 2018/11/14 14:13
     **/
    public static JSONObject postPairDataHttps(String url,
                                               Map<String, Object> params) {
        CloseableHttpClient httpclient = HttpClientUtils.createSSLClientDefault();
        HttpPost httpPost = new HttpPost(url);
        // 拼接参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            NameValuePair pair = new BasicNameValuePair(key, value);
            list.add(pair);
        }
        CloseableHttpResponse response = null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(list));
            response = httpclient.execute(httpPost);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /** 请求发送成功，并得到响应 **/
        JSONObject jsonObject = null;
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity httpEntity = response.getEntity();
            String result = null;
            try {
                result = EntityUtils.toString(httpEntity);
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }// 返回json格式：
            jsonObject = JSONObject.fromObject(result);
        }
        return jsonObject;
    }

    /**
     * 为https创建带证书的连接
     **/
    public static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
                    null, new TrustStrategy() {
                        // 信任所有
                        public boolean isTrusted(X509Certificate[] chain,
                                                 String authType) throws CertificateException {
                            return true;
                        }
                    }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }

    /**
     * @param url
     * @param Mufile
     * @return net.sf.json.JSONObject
     * @Author ZhangJun
     * @Description post请求(http)
     * @Date 2018/11/14 14:13
     **/
    public static JSONObject postFile(String url, MultipartFile Mufile) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.setCharset(Charset.forName(HTTP.UTF_8));
        builder.setContentType(ContentType.MULTIPART_FORM_DATA);
        try {
            // 创建待处理的文件
            String fileName = Mufile.getOriginalFilename();
            ContentBody files = new ByteArrayBody(Mufile.getBytes(), fileName);
            builder.addPart("file", files);
			/*builder.addTextBody("limitsize",limitsize);
			builder.addTextBody("resize",resize);
			builder.addTextBody("height",height);
			builder.addTextBody("width",width);*/
            HttpEntity multipartEntity = builder.build();
            httpPost.setEntity(multipartEntity);
            response = httpclient.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /** 请求发送成功，并得到响应 **/
        JSONObject jsonObject = null;
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity httpEntity = response.getEntity();
            String result = null;
            try {
                result = EntityUtils.toString(httpEntity);
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 返回json格式：
            jsonObject = JSONObject.fromObject(result);
        }
        return jsonObject;
    }


    public static String doGet(String url, Map<String, String> param) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doGet(String url) {
        return doGet(url, null);
    }

    public static String doPost(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String doPost(String url) {
        return doPost(url, null);
    }

    public static String doPostJson(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }


}