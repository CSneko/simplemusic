package org.cneko.simplemusic.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import org.cneko.simplemusic.client.SimpleMusicClient;

public final class SimpleMusicFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SimpleMusicClient.init();
    }
}
