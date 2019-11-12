package com.lv.comet.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 吕明亮
 * @Date : 2019/11/12 11:33
 * @Description:   服务器推送技术:Ajax 短轮询
 * 就是用一个定时器不停的去网站上请求数据
 *
 * 服务端基本不用改造，服务器沉重压力，和资源浪费。 数据同步不及时。
 *
 */
@RestController
public class AjaxShortPollingController {
    private static final Log logger = LogFactory.getLog(AjaxShortPollingController.class);

    @RequestMapping("/time")
    public ModelAndView normal() {

        logger.info("normal() 方法被调用");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/WEB-INF/views/showtime.jsp");
        return mv;

    }

    //@RequestMapping(value = "/showTime", produces = "text/html;charset=UTF-8")
    @ResponseBody
    @GetMapping("/showTime")
    public String getTime() {
        logger.info("getTime() 方法被调用");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(new Date());
    }
}
