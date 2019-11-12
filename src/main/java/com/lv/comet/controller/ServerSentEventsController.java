package com.lv.comet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Random;

/**
 * @author 吕明亮
 * @Date : 2019/11/12 16:22
 * @Description: 服务器推模型Server-sent-events(SSE)
 * <p>
 * 严格地说，HTTP 协议无法做到服务器主动推送信息。但是，有一种变通方法，就是服务器向客户端声明，接下来要发送的是流信息（streaming）。
 * 也就是说，发送的不是一次性的数据包，而是一个数据流，会连续不断地发送过来。这时，客户端不会关闭连接，会一直等着服务器发过来的新的数据流，视频播放就是这样的例子。本质上，这种通信就是以流信息的方式，完成一次用时很长的下载。
 * SSE 就是利用这种机制，使用流信息向浏览器推送信息。它基于 HTTP 协议，目前除了 IE/Edge，其他浏览器都支持。
 * SSE 与 WebSocket 作用相似，都是建立浏览器与服务器之间的通信渠道，然后服务器向浏览器推送信息。
 * 总体来说，WebSocket 更强大和灵活。因为它是全双工通道，可以双向通信；SSE 是单向通道，只能服务器向浏览器发送，因为流信息本质上就是下载。如果浏览器向服务器发送信息，就变成了另一次 HTTP 请求。
 * SSE 也有自己的优点。
 * 	SSE 使用 HTTP 协议，现有的服务器软件都支持。WebSocket 是一个独立协议。
 * 	SSE 属于轻量级，使用简单；WebSocket 协议相对复杂。
 * 	SSE 默认支持断线重连，WebSocket 需要自己实现。
 * 	SSE 一般只用来传送文本，二进制数据需要编码后传送，WebSocket 默认支持传送二进制数据。
 * 	SSE 支持自定义发送的消息类型。
 */
@RestController
public class ServerSentEventsController {

    private static Logger logger = LoggerFactory.getLogger(ServerSentEventsController.class);

    @RequestMapping("/nobleMetal")
    public ModelAndView stock() {
        logger.info("stock() 方法被调用");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/WEB-INF/views/nobleMetal.jsp");
        return mv;
    }

    @RequestMapping(value = "needPrice")
    @ResponseBody
    public void push(HttpServletResponse response) {
        logger.info("push() 方法被调用");
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("utf-8");
        Random r = new Random();
        /*服务器数据发送完*/
        int sendCount = 0;
        try {
            PrintWriter pw = response.getWriter();
            while (true) {
                if (pw.checkError()) {
                    System.out.println("客户端断开连接");
                    return;
                }
                Thread.sleep(1000);
                //字符串拼接
                StringBuilder sb = new StringBuilder("");
                sb.append("retry:2000\n")
                        .append("data:")
                        .append((r.nextInt(1000) + 50) + ",")
                        .append((r.nextInt(800) + 100) + ",")
                        .append((r.nextInt(2000) + 150) + ",")
                        .append((r.nextInt(1500) + 100) + ",")
                        .append("\n\n");

                pw.write(sb.toString());
                pw.flush();
                sendCount++;
                if (sendCount >= 100) {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
