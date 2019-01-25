package Player_v1;

import Player_v0.PlayList;
import Player_v0.SongInfo;

import java.util.LinkedList;

/**
 * 在播放模式的处理上，有两种方式：
 * 1. 设置多个播放模式（枚举型），然后调用者只需要设置成其中一个标记即可。
 * 2. 抽象出来，搞个接口，然后针对不同的播放模式，写个类，然后调用者需要提供一个实例即可。
 * 3. 考虑到拓展性，毕竟以后可能会有别的播放模式，很多个if不太合适，采用方案二。
 */

public class MPlayList implements PlayList {
    //还需考虑到播放模式

    private LinkedList<SongInfo> playList;
    private int curIndex;   //当前播放索引
    private PlayMode playMode;

    public MPlayList(){
        curIndex = 0;
    }

    @Override
    public SongInfo getCurMusic() {
        return playList.get(curIndex);
    }

    @Override
    public SongInfo getPrevMusic() {
        return playList.get(playMode.getPrevIndex());       //针对不同播放模式，返回不同Index
    }

    @Override
    public SongInfo getNextMusic() {
        return playList.get(playMode.getNextIndex());
    }

    @Override
    public void setCurIndex(int curIndex) {
        this.curIndex = curIndex;
    }

    public void setPlayMode(PlayMode playMode){
        this.playMode = playMode;
    }
}
