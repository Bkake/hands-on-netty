package fr.codeworks.handler;

import fr.codeworks.usecase.ChatUseCase;
import fr.codeworks.usecase.ImmutableChatUseCase;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

import static fr.codeworks.tools.ChatTool.buildPrompt;
import static fr.codeworks.tools.ChatTool.getUserName;

public class ChatServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = Logger.getLogger(ChatServerHandler.class.getName());

    private static final ConcurrentMap<String, Channel> channelCache = new ConcurrentHashMap<>();


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        var username = getUserName.apply(ctx);
        buildPrompt.accept(ctx.channel(), username);
        channelCache.putIfAbsent(username, ctx.channel());
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        var in = (String) msg;
        var userName = getUserName.apply(ctx);

        new ChatUseCase().process(ImmutableChatUseCase.Request.builder()
                .context(ctx)
                .username(userName)
                .messageIn(in)
                .channelCache(channelCache)
                .build());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.warning(cause.getMessage());
        ctx.close();
    }

}
