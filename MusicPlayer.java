import Player_v0.PlayList;
import Player_v0.PlayStatus;
import Player_v0.Player;
import Player_v0.SongInfo;

public class MusicPlayer {
    private PlayList playList;      //播放列表
    private Player player;          //核心播放控制器
    private PlayStatus playStatus;

    public MusicPlayer(PlayList playList, Player player){
        this.playList = playList;
        this.player = player;
        playStatus = PlayStatus.STOP;
    }

    public void onPlay(){   //暂停，继续;
        playStatus = player.getStatus();
        System.out.println("onPlay : " + playStatus);
        if(playStatus == PlayStatus.STOP){          //从播放列表头开始播放
            SongInfo curMusic = playList.getCurMusic();
            if(curMusic != null){
                player.onPlay(curMusic);
                playStatus = PlayStatus.PLAY;
            }else{
                System.out.println("出错!");
            }


        }else if(playStatus == PlayStatus.PAUSE){   //暂停状态
            player.resumePlay();
            playStatus = PlayStatus.PLAY;
        }else if(playStatus == PlayStatus.PLAY){    //播放状态
            player.pausePlay();
            playStatus = PlayStatus.PAUSE;
        }
    }

    public void stopPlay(){  //终止播放;
        if(playStatus == PlayStatus.PAUSE || playStatus == PlayStatus.PLAY)
            player.stopPlay();
    }

    public void prevSong(){
        if(playStatus != PlayStatus.STOP){
            SongInfo prevMusic = playList.getPrevMusic();
            player.onPlay(prevMusic);

        }else{              //从列表头开始播放
            this.onPlay();
        }

    }

    public void nextSong(){
        if(playStatus != PlayStatus.STOP){
            SongInfo nextMusic = playList.getNextMusic();
            player.onPlay(nextMusic);

        }else{              //从列表头开始播放
            this.onPlay();
        }

    }
}
