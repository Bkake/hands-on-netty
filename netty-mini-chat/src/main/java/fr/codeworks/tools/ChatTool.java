package fr.codeworks.tools;

import io.netty.channel.ChannelHandlerContext;

import java.util.function.Function;

public final class ChatTool {

    public static final Function<ChannelHandlerContext, String> buildPrompt = ctx -> {
        String remoteAddress = ctx.channel().remoteAddress().toString();
        String[] usernameSplit = remoteAddress.split(":");
        return "CodeWorkers-"+usernameSplit[usernameSplit.length - 1] + ">";
    };


    private ChatTool(){
        // Do nothing
    }
}
