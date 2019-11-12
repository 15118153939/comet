package com.lv.comet.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 吕明亮
 * @Date : 2019/11/12 15:30
 * @Description:
 */
@RestController
public class IndexController {
    private static final Log logger = LogFactory.getLog(AjaxShortPollingController.class);
    @RequestMapping("/index")
    public ModelAndView index() {
        logger.info("index() 方法被调用");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/WEB-INF/views/index.jsp");
        return mv;
    }
}
