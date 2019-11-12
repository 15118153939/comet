package com.lv.comet.controller;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author 吕明亮
 * @Date : 2019/11/12 16:42
 * @Description:
 */
@RestController
public class SsePayController {

    ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern(" servlet-asyn-one-%d").daemon(true).build());
    private static final Log logger = LogFactory.getLog(SsePayController.class);
    private static Map<String, SseEmitter> sseEmitters = new ConcurrentHashMap<>();

    @RequestMapping("/weChatPay")
    public ModelAndView stock() {
        logger.info("stock() 方法被调用");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/WEB-INF/views/weChatPay.jsp");
        return mv;
    }

    @RequestMapping(value = "/payMoney")
    @ResponseBody
    public SseEmitter pay(String weCharId) {
        SseEmitter emitter = new SseEmitter();
        sseEmitters.put(weCharId, emitter);
        executor.submit(new Pay(weCharId));
        return emitter;
    }

    private static class Pay implements Runnable {

        private String weCharId;

        public Pay(String weCharId) {
            this.weCharId = weCharId;
        }

        @Override
        public void run() {
            SseEmitter sseEmitter = sseEmitters.get(weCharId);
            try {
                logger.info("联系支付服务，准备扣款");
                Thread.sleep(500);
                sseEmitter.send("支付完成");
                logger.info("准备通知自动售货机");
                //售货机的动作
                Thread.sleep(1500);
                sseEmitter.send("已通知自动售货机C9出货，请勿走开！");
                sseEmitter.complete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
