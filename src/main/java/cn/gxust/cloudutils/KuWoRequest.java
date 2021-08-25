package cn.gxust.cloudutils;

import cn.gxust.pojo.PlayBean;
import cn.gxust.utils.Log4jUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KuWoRequest {

    private static final String baseURL  = "http://159.75.38.125:8823";
    private static final String searchURL = "/search?keyword=";
    private static final String realURL = "/getSongDownloadUrl?rid=";
    private static final String lrcURL = "http://m.kuwo.cn/newh5/singles/songinfoandlrc?musicId=";


    public static void main(String[] args) {
        String u = "http://www.kuwo.cn/playlist_detail/3179739619";
        KuWoRequest k = new KuWoRequest();
        System.out.println(k.get(u));
    }

    private String get(String URL){
        try {
            Connection.Response response = Jsoup.connect(URL)
                    .method(Connection.Method.GET)
                    .ignoreContentType(true)
                    .execute();
            return response.body();
        }catch (Exception e){
            Log4jUtils.logger.error("", e);
        }
        return "";
    }


    public void searchMusic(String name, List<PlayBean> list, int pn, int rn){
//        String URL = baseURL + searchURL + URLEncoder.encode(name, StandardCharsets.UTF_8);
        String URL = baseURL + searchURL + URLEncoder.encode(name, StandardCharsets.UTF_8) + "&pn=" + pn + "&rn=" + rn;
        String json = get(URL);
        int beginIndex = list.size();
        JSONArray jsonArray = JSON.parseArray(json);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject t = jsonArray.getJSONObject(i);
            String musicName = t.getString("songName");
            String musicId = t.getString("musicID");
            String mp3Url = t.getString("musicID");
            String artistName = t.getString("artist");
            String imageUrl = t.getString("pic");
            String album = t.getString("album");
            PlayBean playBean = new PlayBean(musicName, musicId, mp3Url, artistName, imageUrl, album);
            playBean.setIndexName(++beginIndex);
            list.add(playBean);
        }
    }

    public String spiderLrc(String songId) {
        String URL  = lrcURL + songId;
        String r = get(URL);
        JSONObject j = JSON.parseObject(r);
        String lrc = j.getJSONObject("data").getString("lrclist");
        return toLrc(lrc);
    }

    private String toLrc(String s){
        // 按指定模式在字符串查找
        String pattern = "(?<=:).*?(?=[,}])";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(s);
        StringBuilder sb = new StringBuilder();
        List<String> wordList = new ArrayList<>();
        List<String> timeList = new ArrayList<>();
        //复数歌词，单数时间
        int count = 0;
        while(m.find()){
            String res = m.group();
            res = res.replaceAll("\"", "");
            if (count++ % 2 != 0){
                String rt = "[%s:%s]";
                double d = Double.parseDouble(res);
                int oneMinute = 60;
                int c = (int) d / oneMinute;
                d -= c * oneMinute;
                String min = String.valueOf(c);
                String sec = String.format("%.2f", d);
                if (c < 60){
                    min = "0" + c;
                }
                if (d < 10){
                    sec = "0" + sec;
                }
                res = String.format(rt, min, sec);
            }
            if (count % 2 != 0){
                wordList.add(res);
            }else {
                timeList.add(res);
            }
        }
        for (int i = 0; i < wordList.size(); i++) {
            sb.append(timeList.get(i)).append(wordList.get(i)).append("\n");
        }
        return sb.toString();
    }

    public void getMusicDetail(PlayBean playBean) {

    }

    public String getReal(String url) {
        String URL = baseURL + realURL +  url;
        return get(URL).replaceAll("\\[", "")
                .replaceAll("\"", "")
                .replaceAll("]","");
    }



}
