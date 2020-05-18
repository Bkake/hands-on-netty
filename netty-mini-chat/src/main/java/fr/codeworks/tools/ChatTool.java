package fr.codeworks.tools;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public final class ChatTool {

    public static final String MSG_SEPARATOR = "::";


    public static final Function<ChannelHandlerContext, String> getUserName = ctx -> {
        var remoteAddress = ctx.channel().remoteAddress().toString();
        var usernameSplit = remoteAddress.split(":");
        return "CodeWorkers-"+usernameSplit[usernameSplit.length - 1];
    };

    public static final BiConsumer<Channel, String> buildPrompt = (channel, userName) ->
            channel.writeAndFlush(userName + ">");

    public static final BinaryOperator<String> buildMessage = (userName, message) ->
            " @" + userName + " : " + message;

    public static final Predicate<String> isBroadCast = msg -> !msg.contains(MSG_SEPARATOR);


    private ChatTool(){
        // Do nothing
    }
}
