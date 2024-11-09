package org.cneko.simplemusic.neoforge.client.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import org.cneko.simplemusic.client.music.MusicPlayer;
import org.cneko.simplemusic.client.music.MusicThread;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.network.chat.Component.translatable;
import static org.cneko.simplemusic.neoforge.client.util.CommandUtil.getAllMusic;

public class MusicCommand{

    @SubscribeEvent
    public static void onRegisterCommands(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(
                LiteralArgumentBuilder.<CommandSourceStack>literal("simplemusic")
                .then(argument("music", StringArgumentType.greedyString())
                        .suggests(getAllMusic)
                        .executes(MusicCommand::playMusic)
                )
                .then(LiteralArgumentBuilder.<CommandSourceStack>literal("stop")
                        .executes(MusicCommand::stopMusic)
                )
        );
        event.getDispatcher().register(
                LiteralArgumentBuilder.<CommandSourceStack>literal("sm")
                        .redirect(event.getDispatcher().getRoot().getChild("simplemusic"))
        );
    }

    public static int stopMusic(CommandContext<CommandSourceStack> context) {
        MusicThread.Companion.stopPlay();
        context.getSource().sendSystemMessage(translatable("command.simplemusic.stop.successful"));
        return 1;
    }

    public static int playMusic(CommandContext<CommandSourceStack> context) {
        String music = StringArgumentType.getString(context, "music");
        // 播放音乐
        MusicPlayer musicPlayer = new MusicPlayer(music);
        musicPlayer.setMcPlayer(context.getSource().getPlayer());
        boolean result = musicPlayer.play();
        if (result) {
            context.getSource().sendSystemMessage(translatable("command.simplemusic.play.successful", music));
        } else {
            context.getSource().sendSystemMessage(translatable("command.simplemusic.play.failed",music));
        }
        return 1;
    }
}
