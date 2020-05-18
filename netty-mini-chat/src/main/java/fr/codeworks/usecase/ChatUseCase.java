package fr.codeworks.usecase;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.immutables.value.Value.Enclosing;
import org.immutables.value.Value.Immutable;

import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static fr.codeworks.tools.ChatTool.MSG_SEPARATOR;
import static fr.codeworks.tools.ChatTool.buildMessage;
import static fr.codeworks.tools.ChatTool.buildPrompt;
import static fr.codeworks.tools.ChatTool.isBroadCast;
import static java.util.Objects.nonNull;

@Enclosing
public class ChatUseCase {
    private static final Logger LOGGER = Logger.getLogger(ChatUseCase.class.getName());

    public void process(Request request) {
        if (isBroadCast.test(request.messageIn())) {
            oneToMany(request.messageIn(), request.username(), request.channelCache());
        } else {
            oneToOne(request.context(), request.messageIn(), request.channelCache());
        }

        buildPrompt.accept(request.context().channel(), request.username());
    }


    private void oneToMany(String in, String userName, ConcurrentMap<String, Channel> channelCache) {
        LOGGER.info("Broad Cast message ");

        channelCache.entrySet().stream()
                .filter(entry -> !userName.equals(entry.getKey()))
                .forEach(entry -> {
                    entry.getValue().writeAndFlush(buildMessage.apply(userName, in));
                    buildPrompt.accept(entry.getValue(), entry.getKey());
                });
    }

    private void oneToOne(ChannelHandlerContext ctx, String in, ConcurrentMap<String, Channel> channelCache) {
            var splitIn = Pattern.compile(MSG_SEPARATOR).splitAsStream(in).collect(Collectors.toList());
            var targetUserName = splitIn.get(0);
            var targetChannel = channelCache.get(targetUserName);

            if (nonNull(targetChannel)) {
                targetChannel.writeAndFlush(buildMessage.apply(targetUserName, splitIn.get(1)));
                buildPrompt.accept(targetChannel, targetUserName);
            } else {
                ctx.channel().writeAndFlush("No user named with ["+ targetUserName +"].\r\n");
            }
    }

    @Immutable
    public interface Request {
        ChannelHandlerContext context();
        String username();
        String messageIn();
        ConcurrentMap<String, Channel> channelCache();
    }
}
