package org.cneko.simplemusic.fabric;

import org.cneko.simplemusic.SimpleMusic;
import net.fabricmc.api.ModInitializer;
import org.cneko.simplemusic.fabric.client.commands.MusicCommand;

public final class SimpleMusicFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        SimpleMusic.init();
        MusicCommand.init();
    }
}
