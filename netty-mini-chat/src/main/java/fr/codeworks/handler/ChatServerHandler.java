package fr.codeworks.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Logger;

import static fr.codeworks.tools.ChatTool.buildPrompt;

public class ChatServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOGGER = Logger.getLogger(ChatServerHandler.class.getName());

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(buildPrompt.apply(ctx));
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String strMsg = (String) msg;
        ctx.write(strMsg);
        ctx.writeAndFlush(buildPrompt.apply(ctx));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.warning(cause.getMessage());
        ctx.close();
    }


}
