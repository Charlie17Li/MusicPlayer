package Player_v0;

import javax.sound.sampled.AudioInputStream;

public interface SongInfo {
    String getSongName();    //歌曲名;
    String getSongPath();    //歌曲路径;
    String getPlayer();
    AudioInputStream getAudioStream();  //音频流

}
