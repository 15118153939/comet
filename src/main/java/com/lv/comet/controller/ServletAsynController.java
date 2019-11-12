package com.lv.comet.controller;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author 吕明亮
 * @Date : 2019/11/12 15:38
 * @Description: 基于 AJAX 的长轮询-Spring带来的DeferedResult
 */
@RestController
public class ServletAsynController {

    ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern(" servlet-asyn-one-%d").daemon(true).build());
    private static final Log logger = LogFactory.getLog(ServletAsynController.class);

    @RequestMapping("/pushnews")
    public ModelAndView news() {
        logger.info("news() 方法被调用");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/WEB-INF/views/pushNews.jsp");
        return mv;
    }


    @RequestMapping(value = "/realTimeNews")
    @ResponseBody
    /*在WebInitializer中要加上servlet.setAsyncSupported(true);*/
    public DeferredResult<String> realtimeNews(HttpServletRequest request) {
        final DeferredResult<String> dr = new DeferredResult<String>();
        logger.info("realtimeNews() 方法被调用");
        executor.submit(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int index = new Random().nextInt(NEWS.length);
            dr.setResult(NEWS[index]);
        });
        return dr;
    }


    public static final String[] NEWS = {
            "震惊！美国总统看到后都惊呆了！",
            "整个X国X洲都慌了：X公司X亿收购了这家企业",
            "震惊！男人看了会沉默，女人看了会流泪！不转不是中国人！",
            "这几条人生定律，看后一生受益！【深度好文】", "这位XX如今成长为XX巨鳄：曾被XX公司拒绝",
            "X游戏十大最变态武器，第一名竟然是它！",
            "一男子在XX买了一个XX，接下来不可思议的一幕发生了！",
            "一小伙吃了XX，竟然当场死亡！盘点那些XXX的食物",
            "XX动漫十大最强角色，第一名令人大跌眼镜！",
            "XX电影禁播的秘密！XX年后终于解禁！",
            "生活中致命的N大常识！",
            "绝密视频流出，速看！",
            "如今X国最缺的是什么？大学教授这么说！",
            "XX不可告人的秘密：真相令所有人脸红心跳!",
            "中国人打美国，暴爽！没WIFI也要看！不看不是中国人！",
            "震惊！朴槿惠终生未嫁原来是心系一个中国男人！",
            "震惊！六岁儿童公众场合口出狂言竟无人指责！中国的教育怎么了！",
            "致富秘诀！它简直就是转运神器！我竟然才知道！",
            "他来自山村，却成为北上广的精英，退休后守着一座海岛，和心爱的名媛终老",
            "年轻貌美女孩抛弃7个备胎，最终与高富帅情定终生！",
            "太可怕了！女子睡前在床上做了这件事，竟导致不孕不育！",
            "震惊！著名LOL玩家和DOTA玩家互斥对方不算男人，现场数万人围观！"};

}
