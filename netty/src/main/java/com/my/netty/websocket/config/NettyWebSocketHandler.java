package com.my.netty.websocket.config;

import com.alibaba.fastjson.JSON;

import com.my.netty.websocket.entity.JsonResult;
import com.my.netty.websocket.handlermapping.Mapping;
import com.my.netty.websocket.handlermapping.NettyHandlerMapping;
import com.my.netty.websocket.handlermapping.RequestObject;
import com.my.netty.websocket.util.BeanUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/10/27 17:19
 * @Version V1.0
 */
@Slf4j
@Component
public class NettyWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private WebSocketServerHandshaker handshaker;

    //TextWebSocketFrame是netty用于处理websocket发来的文本对象
    //所有正在连接的channel都会存在这里面，所以也可以间接代表在线的客户端
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    //客户端建立连接
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channelGroup.add(ctx.channel());
        log.info(ctx.channel().remoteAddress()+" 建立了链接!");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //首次连接是FullHttpRequest，处理参数 by zhengkai.blog.csdn.net
        if (null != msg && msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            String uri = request.uri();

            Map paramMap=getUrlParams(uri);
            log.info("接收到的参数是："+JSON.toJSONString(paramMap));
            //如果url包含参数，需要处理
            if(uri.contains("?")){
                uri=uri.substring(0,uri.indexOf("?"));
                System.out.println(uri);
                request.setUri(uri);
            }
            ctx.channel().attr(AttributeKey.valueOf("path")).set(uri);
            ctx.channel().attr(AttributeKey.valueOf("param")).set(paramMap);
            Mapping mapping = (Mapping) BeanUtil.getBeanByType(Mapping.class);
            RequestObject requestObject = mapping.getSourceMapping(uri);
            //获取 uri 对应的 请求
            NettyHandlerMapping nettyHandlerMapping = (NettyHandlerMapping)BeanUtil.getBeanByType(NettyHandlerMapping.class);
            nettyHandlerMapping.doRequestObject(requestObject,paramMap);
            Map<ChannelHandlerContext, RequestObject> mappingRequest = mapping.getMapping(uri);
            //链接和请求一一对应
            mappingRequest.put(ctx,requestObject);
            handleHttpRequest(ctx,request);
        }else if(msg instanceof TextWebSocketFrame){
            //当发送数据请求的时候执行的方法
            Object path = ctx.channel().attr(AttributeKey.valueOf("path")).get();
            //Map param = (Map)ctx.channel().attr(AttributeKey.valueOf("param")).get();
            log.info("path-----: "+path+", "+ctx.channel().id());
            //正常的TEXT消息类型
            TextWebSocketFrame frame=(TextWebSocketFrame)msg;
            frame.retain();
            log.info("客户端收到服务器数据：" +frame.text());
            SendAllMessages(ctx,frame,path+"");
        }
        super.channelRead(ctx, msg);
    }


    //channelRead0客户端发送消息处理
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //SendAllMessages(ctx,JsonResult.builder().data(msg.text()).build());
    }



    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Mapping mapping = (Mapping)BeanUtil.getBeanByType(Mapping.class);
        Map<String, Map<ChannelHandlerContext, RequestObject>> methodMap = mapping.getMethodMap();
        if(methodMap!=null&&methodMap.size()>0){
            for (Map.Entry<String, Map<ChannelHandlerContext, RequestObject>> stringMapEntry : methodMap.entrySet()) {
                Map<ChannelHandlerContext, RequestObject> value = stringMapEntry.getValue();
                if(value.containsKey(ctx)){
                    value.remove(ctx);
                }

            }
        }
        channelGroup.remove(ctx.channel());
        log.info(ctx.channel().remoteAddress()+"断开连接");
    }

    //出现异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    //发送给自己
    private void SendAllMessages(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocketFrame, String path) {
        NettyHandlerMapping nettyHandlerMapping = (NettyHandlerMapping)BeanUtil.getBeanByType(NettyHandlerMapping.class);

        Mapping mapping = (Mapping)BeanUtil.getBeanByType(Mapping.class);
        Map<ChannelHandlerContext, RequestObject> mappingRequest = mapping.getMapping(path);
        if(mappingRequest.containsKey(ctx)){
            RequestObject requestObject = mappingRequest.get(ctx);
            requestObject.setChannelHandlerContext(ctx);
            requestObject.setTextWebSocketFrame(textWebSocketFrame);
            Object handler = nettyHandlerMapping.handler(requestObject, requestObject == null ? null : requestObject.getParam());
            JsonResult build = JsonResult.builder().data(handler).build();
            Channel channel = ctx.channel();
            log.info("channel id :"+channel.id());
            channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(build)));
        }
    }

    private static Map getUrlParams(String url){
        Map<String,String> map = new HashMap<>();
        url = url.replace("?",";");
        if (!url.contains(";")){
            return map;
        }
        if (url.split(";").length > 0){
            String[] arr = url.split(";")[1].split("&");
            for (String s : arr){
                String[] split = StringUtils.split(s, "=");
                String key = "";
                String value = "";
                if(split != null && split.length > 0){
                    key = split[0];
                    if(split.length > 1){
                        value = split[1];
                    }
                }
                map.put(key,value);
            }
            return  map;

        }else{
            return map;
        }
    }


    /**
     * 唯一的一次http请求，用于创建websocket
     * */
    private void handleHttpRequest(ChannelHandlerContext ctx,
                                   FullHttpRequest req) {
        //要求Upgrade为websocket，过滤掉get/Post
        if (!req.decoderResult().isSuccess()
                || (!"websocket".equals(req.headers().get("Upgrade")))) {
            //若不是websocket方式，则创建BAD_REQUEST的req，返回给客户端
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        InetSocketAddress socketAddress = (InetSocketAddress)ctx.channel().remoteAddress();
        InetAddress address = socketAddress.getAddress();
        String hostAddress = address.getHostAddress();

        log.info("remoteAddress----{}", address);
        log.info("hostAddress----{}", hostAddress);
        NettyServer nettyServer = (NettyServer)BeanUtil.getBeanByName("nettyServer");
        int port = nettyServer.getPort();

        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                "ws://"+hostAddress+":"+port, null, false);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory
                    .sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    /**
     * 拒绝不合法的请求，并返回错误信息
     * */
    private static void sendHttpResponse(ChannelHandlerContext ctx,
                                         FullHttpRequest req, DefaultFullHttpResponse res) {
        // 返回应答给客户端
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(),
                    CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        // 如果是非Keep-Alive，关闭连接
        if (!isKeepAlive(req) || res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }
}
