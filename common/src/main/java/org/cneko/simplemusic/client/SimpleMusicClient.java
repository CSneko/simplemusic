package org.cneko.simplemusic.client;

import org.cneko.simplemusic.client.music.MusicPlayer;
import org.cneko.simplemusic.util.FileUtil;

public class SimpleMusicClient {
    public static void init(){
        FileUtil.Companion.createFolder(MusicPlayer.Companion.getMidiPath());
        FileUtil.Companion.createFolder(MusicPlayer.Companion.getMp3Path());
    }
}
