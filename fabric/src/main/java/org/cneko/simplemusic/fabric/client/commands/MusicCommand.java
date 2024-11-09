package org.cneko.simplemusic.fabric.client.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import org.cneko.simplemusic.client.music.MusicPlayer;
import org.cneko.simplemusic.client.music.MusicThread;


import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.minecraft.network.chat.Component.translatable;
import static org.cneko.simplemusic.fabric.client.util.CommandUtil.*;
public class MusicCommand {
    public static void init(){
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(LiteralArgumentBuilder.<FabricClientCommandSource>literal("simplemusic")
                    .then(argument("music", StringArgumentType.greedyString())
                            .suggests(getAllMusic)
                            .executes(MusicCommand::playMusic)
                    )
                    .then(LiteralArgumentBuilder.<FabricClientCommandSource>literal("stop")
                            .executes(MusicCommand::stopMusic)
                    )
            );
            dispatcher.register(LiteralArgumentBuilder.<FabricClientCommandSource>literal("sm")
                    .redirect(dispatcher.getRoot().getChild("simplemusic"))
            );
        });
    }

    public static int stopMusic(CommandContext<FabricClientCommandSource> context) {
        MusicThread.Companion.stopPlay();
        context.getSource().sendFeedback(translatable("command.simplemusic.stop.successful"));
        return 1;
    }

    public static int playMusic(CommandContext<FabricClientCommandSource> context) {
        String music = StringArgumentType.getString(context, "music");
        // 播放音乐
        MusicPlayer musicPlayer = new MusicPlayer(music);
        musicPlayer.setMcPlayer(context.getSource().getPlayer());
        boolean result = musicPlayer.play();
        if (result) {
            context.getSource().sendFeedback(translatable("command.simplemusic.play.successful", music));
        } else {
            context.getSource().sendFeedback(translatable("command.simplemusic.play.failed",music));
        }
        return 1;
    }
}
