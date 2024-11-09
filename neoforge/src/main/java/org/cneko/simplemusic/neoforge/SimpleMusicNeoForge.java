package org.cneko.simplemusic.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import org.cneko.simplemusic.SimpleMusic;
import net.neoforged.fml.common.Mod;
import org.cneko.simplemusic.neoforge.client.commands.MusicCommand;

@Mod(SimpleMusic.MOD_ID)
public final class SimpleMusicNeoForge {
    public SimpleMusicNeoForge(IEventBus bus, ModContainer container) {
        // Run our common setup.
        SimpleMusic.init();

    }
}
