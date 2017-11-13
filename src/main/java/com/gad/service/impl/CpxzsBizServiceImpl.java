/*
 * @(#) CpxzsBizServiceImpl.java ,2017年11月10日
 *
 * Copyright 2017 zbj.com, Inc. ALL rights reserved.
 * ZHUBAJIE PROPRIETARY/CONFIDENTIAL. Use is subject to lincese trems.
 */
package com.gad.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gad.common.FileUtils;
import com.gad.domin.dto.FvDTO;
import com.gad.domin.dto.PeriodInfoDTO;
import com.gad.domin.dto.RemotePlanDTO;
import com.gad.domin.dto.PlanConfig;
import com.gad.domin.entity.OptimalPlanEntity;
import com.gad.service.CpxzsBizService;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author caojianlong(caojianlong@zbj.com)
 * @date 2017/11/10
 */
@Service
public class CpxzsBizServiceImpl implements CpxzsBizService {

    private Boolean isLogin;

    private static String cookie;

    private String userName = "YuYuYuYu";

    private String password = "py2008221";

    public static final String LOGIN_URL = "http://www.cpxzs.com/userAccount/popLogin";

    private static final String PLAN_URL = "http://www.cpxzs.com/jhfa/jhfaPlan";

    private static final String NEXT_PERIOD = "http://www.cpxzs.com/caipiaoNumber/queryNextPeriod";

    private String nowPlanCode = "ssc040";

    private PeriodInfoDTO nowPeriod;

    private Boolean newPeriodFlage = Boolean.TRUE;


    @Override
    public OptimalPlanEntity getOptimalPlan(PlanConfig config)  {
        if (!isLogin) {
            try {
                login(userName, password);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            refreshPeriod();

            if (newPeriodFlage) {
                // 处理远程数据

                newPeriodFlage = false;
                RemotePlanDTO remotePlanDTO = getPlanList(config);

                // 判断是否是可以下单的
                List<FvDTO> fvs = remotePlanDTO.getFvList();

                Boolean bl = Boolean.TRUE;
                for (FvDTO fv : fvs) {
                    if (nowPeriod.getItem().compareTo(fv.getItem()) > 0) {
                        bl = Boolean.FALSE;
                    }
                }

                if (bl) {
                    //



                }


            }




        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private RemotePlanDTO getPlanList(PlanConfig config) throws IOException {
        String content = getPvList(config.getOffset(), config.getNumber(), config.getKeepPeriosd(), nowPlanCode);
        RemotePlanDTO planDTO = JSONObject.parseObject(content, RemotePlanDTO.class);
        return planDTO;
    }

    private void refreshPeriod() throws IOException {
        String str = getNextPeriod();
        PeriodInfoDTO newPeriod = JSONObject.parseObject(str, PeriodInfoDTO.class);
        if (newPeriod.getHour() == 0 && newPeriod.getMinute() <= 3) {
            this.nowPeriod = newPeriod;
            newPeriodFlage = Boolean.TRUE;
        }
        if (nowPeriod == null) {
            this.nowPeriod = newPeriod;
        }
    }


    @Override
    public boolean hashNext() {
        return false;
    }


    private static void login(String userName, String password) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(LOGIN_URL);
        setCommonHeaders(request);
        request.setHeader("Origin","http://www.cpxzs.com");
        request.setHeader("Referer","http://www.cpxzs.com/");

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("loginName", userName));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("autoLogin", "1"));

        HttpEntity entity = EntityBuilder.create().setParameters(params).build();
        request.setEntity(entity);

        HttpResponse response = client.execute(request);

        Header[] headers = response.getAllHeaders();

        for (Header header : headers) {
            if (header.getName().equals("Set-Cookie")) {
                String str = header.getValue();
                for (String tmp : str.split(";")) {
                    if (tmp.contains("__cfduid")) {
                        cookie = tmp;
                    }
                }
            }
        }
    }

    private static String getNextPeriod() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(NEXT_PERIOD);
        setCommonHeaders(request);
        request.setHeader("Referer","http://www.cpxzs.com/jhfa/excellentPlan");
        HttpResponse response = client.execute(request);
        HttpEntity resEntity = response.getEntity();
        String content = new String(FileUtils.readInputStream(resEntity.getContent()));

        return content;
    }

    /**
     *
     * @param offset  1,个  2 十 3 百 4 千 5 万
     * @param number  号码个数
     * @param keepPeriosd 追加期数
     * @return
     * @throws IOException
     */
    private static String getPvList(Integer offset,Integer number,Integer keepPeriosd,String nowPlanCode) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(PLAN_URL +
                "?condition=dw&jhfaPlanType=0&type=&jhfawei=" + offset + "&jhfadanmaCount=" + number + "&jhfazhuiqiCount=" + keepPeriosd + "&times=10&bonus=19.5&yeildRate=20&activity=2&pattern=&jhfaName=" + nowPlanCode + "&sort=&_=1510290244720");
        setCommonHeaders(request);
        request.setHeader("Referer","http://www.cpxzs.com/jhfa/excellentPlan");
        HttpResponse response = client.execute(request);
        HttpEntity resEntity = response.getEntity();
        String content = new String(FileUtils.readInputStream(resEntity.getContent()));

        return content;
    }

    private static void setCommonHeaders(HttpRequest request) {
        request.setHeader("Accept","application/json, text/javascript, */*; q=0.01");
        request.setHeader("Accept-Encoding","gzip, deflate");
        request.setHeader("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.6");
        request.setHeader("Cache-Control","max-age=0");
        request.setHeader("Connection","keep-alive");
        request.setHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        request.setHeader("Host","www.cpxzs.com");
        request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
        request.setHeader("X-Requested-With", "XMLHttpRequest");
        request.setHeader("Cookie", "dwJhfaPlanName=ssc040; sort=; " + cookie + "; cf_clearance=c3c87848375cf0b3ad5f442c34e255d266ea1fc3-1510201628-604800; UM_distinctid=15f9f081fe03cc-07be06f0f4c2a2-3e63430c-1fa400-15f9f081fe145b; yjs_id=TW96aWxsYS81LjAgKFdpbmRvd3MgTlQgMTAuMDsgV09XNjQpIEFwcGxlV2ViS2l0LzUzNy4zNiAoS0hUTUwsIGxpa2UgR2Vja28pIENocm9tZS82MS4wLjMxNjMuMTAwIFNhZmFyaS81MzcuMzZ8d3d3LmNweHpzLmNvbXwxNTEwMjAxNjMwODEzfGh0dHA6Ly93d3cuY3B4enMuY29tLw; cpv2_ucc=6baba60ec0824c96aaecb6b60234e21b; cpv2_us=f6ba6a695c9d413e98299c6dc65279a8; pina=C4AX3JADIASNI; pin=Z4ZBDZ23U72COFNTXRGNC2QWK4BGN2YJLE5SPWI; pinw=GVLODUQAMN73DGSKLEUEQQG2WI; ctrl_time=1; CNZZDATA1256965011=957901092-1510283771-%7C1510289315");

    }


    public static void main(String[] args) throws IOException, InterruptedException {
        //login("YuYuYuYu", "py2008221");
        Thread.sleep(1000);
        System.out.println();
        System.out.println();
        System.out.println("===============" + getPvList(5, 5, 3, "ssc022"));
        System.out.println();
        System.out.println("==============="+getNextPeriod());
    }
}
