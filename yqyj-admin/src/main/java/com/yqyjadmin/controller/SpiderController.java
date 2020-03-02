package com.yqyjadmin.controller;


import com.yqyjcommon.pojo.R;
import io.swagger.annotations.Api;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/spider")
@Api(value = "/spider", tags = {"爬虫相关接口"})
public class SpiderController {

    @GetMapping("/run")
    public R test(String keyword) {
        String url = "https://piao.qunar.com/ticket/list.htm?keyword=+"+ keyword +"&region=&from=mpl_search_suggest";
        String html = "";
        try {
            html = getHtml(url);
        }catch (IOException e){
            e.printStackTrace();
            return new R(20001,"爬虫爬取数据异常！");
        }
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        if (html!=null){
            //6.Jsoup解析html
            Document document = Jsoup.parse(html);
            Elements sight_item_caption = document.getElementsByClass("sight_item");
            for (Element element : sight_item_caption) {
                HashMap<String, Object> map = new HashMap<>();
                Elements name = element.getElementsByClass("name");
                map.put("title",name.text());
                Elements area = element.getElementsByClass("area");
                map.put("area", area.text());
                Elements address = element.getElementsByClass("address color999").select("span");
                map.put("address", address.text());
                Elements intro = element.getElementsByClass("intro color999");
                map.put("intro", intro.text());
                Elements price = element.getElementsByClass("sight_item_price");
                map.put("price", price.select("em").text());
                Elements mounth_sold = element.getElementsByClass("hot_num");
                map.put("mounth_sold", mounth_sold.text());
                String cover_img = element.attr("data-sight-img-u-r-l");
                map.put("cover_img", cover_img);
                maps.add(map);
            }
        }
        return new R(maps);
    }


    public String getHtml(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();//1.生成httpclient，相当于该打开一个浏览器
        CloseableHttpResponse response = null;
        HttpGet request = new HttpGet(url); //2.创建get请求，相当于在浏览器地址栏输入 网址

        //3.执行get请求，相当于在输入地址栏后敲回车键
        response = httpClient.execute(request);

        //4.判断响应状态为200，进行处理
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            //5.获取响应内容
            HttpEntity httpEntity = response.getEntity();
            String html = EntityUtils.toString(httpEntity, "utf-8");
            return html;
        } else {
            //如果返回状态不是200，比如404（页面不存在）等，根据情况做处理，这里略
            System.out.println("返回状态不是200");
            System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
            return null;
        }
    }

}
