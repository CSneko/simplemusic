package org.cneko.simplemusic.neoforge.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import org.cneko.simplemusic.SimpleMusic;
import org.cneko.simplemusic.client.SimpleMusicClient;
import org.cneko.simplemusic.neoforge.client.commands.MusicCommand;

@Mod(value = SimpleMusic.MOD_ID,dist = Dist.CLIENT)
public class SimpleMusicNeoForgeClient {
    public SimpleMusicNeoForgeClient(IEventBus bus, ModContainer container) {
        SimpleMusicClient.init();
        NeoForge.EVENT_BUS.addListener(MusicCommand::onRegisterCommands);
    }
}
