package Player_v1;

import Player_v0.PlayStatus;
import Player_v0.Player;
import Player_v0.SongInfo;

import javax.sound.sampled.*;

public class MPlayer implements Player {            //实现播放器 第一次尝试

    private Thread playerThread;         //播放音乐的线程;
    private volatile PlayStatus playStatus = PlayStatus.STOP;           //可见性
    private volatile int curFrame;                   //当前帧
    private AudioInputStream audioInputStream;      //音频流
    private AudioFormat audioFormat;               //音频格式
    private SourceDataLine sourceDataLine;        // 输出设备

    @Override
    public void onPlay(SongInfo info) { //开始播放
        this.init(info);        //初始化音频设备
        //开始播放
        if(playStatus == PlayStatus.STOP){      //终止状态
            playerThread = new Thread(new PlayRunnable(0));
            playerThread.start();
            System.out.println("开始播放");

        }else if(playStatus == PlayStatus.PLAY){    //播放状态
            //终止之前的线程，建立新的线程
            playStatus = PlayStatus.STOP;           //设置为终止
            playerThread = new Thread(new PlayRunnable(0));
            playerThread.start();
            System.out.println("先终止，再开线程。");
        }
    }
    private void init(SongInfo songInfo){
        audioInputStream = songInfo.getAudioStream();       //获取音频流
        System.out.println("init 歌曲名：" + songInfo.getSongName());
        // 获得文件输入流的音频格式类对象
        audioFormat = audioInputStream.getFormat();
        // 转换mp3文件编码
        if (audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
            audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    audioFormat.getSampleRate(), 16,
                    audioFormat.getChannels(),
                    audioFormat.getChannels() * 2,
                    audioFormat.getSampleRate(), false);
            audioInputStream = AudioSystem.getAudioInputStream(audioFormat,
                    audioInputStream);
        }

        // 打开输出设备
        DataLine.Info dataLineInfo = new DataLine.Info(
                SourceDataLine.class, audioFormat,
                AudioSystem.NOT_SPECIFIED);
        try{
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            sourceDataLine.open(audioFormat);
        }catch (Exception e){
            e.printStackTrace();
        }
        sourceDataLine.start();
    }

    @Override
    public void stopPlay() {        //终止播放
        playStatus = PlayStatus.STOP;
    }

    @Override
    public void pausePlay() {       //暂停播放
        playStatus = PlayStatus.PAUSE;
    }

    /**
     * 恢复播放
     */
    @Override
    public void resumePlay() {      //恢复播放
        if(playStatus == PlayStatus.PAUSE){
            playStatus = PlayStatus.STOP;           //设置为终止
            playerThread = new Thread(new PlayRunnable(curFrame));
            playerThread.start();
            System.out.println("先终止，再开线程。");        //木有办法，现在只能这么做
        }
    }

    @Override
    public Player_v0.PlayStatus getStatus() {
        return playStatus;
    }


    /**
     * 目前主要的问题是，如何暂停播放？
     * - 既然一帧一帧播放，记录下播放的帧数，然后恢复播放的时候，跳到这一帧播放
     */

    class PlayRunnable implements Runnable{
        int tmpFrame;       //当前播放的帧数

        public PlayRunnable(int tmpFrame){
            this.tmpFrame = tmpFrame;
        }
        @Override
        public void run() {     //实现播放任务
            int cnt = -1;
            byte tempBuffer[] = new byte[2048];

            playStatus = PlayStatus.PLAY;       //设置处于播放状态
            tmpFrame = 0;
            try {

                if(!sourceDataLine.isOpen()){           //打开输出设备
                    System.out.println("打开输出设备");
                    sourceDataLine.open(audioFormat);
                    sourceDataLine.start();
                }
                //先跳到指定的帧
                for(int i = 0; i < tmpFrame; i++){
                    audioInputStream.read(tempBuffer, 0, tempBuffer.length);
                }
                // 读取数据到缓存数据
                while ((cnt = audioInputStream.read(tempBuffer, 0,
                        tempBuffer.length)) != -1) {
                    if(playStatus == PlayStatus.STOP){      //终止状态
                        System.out.println("设置为终止状态");
                        break;
                    }
                    if (playStatus == PlayStatus.PAUSE) {     //暂停状态
                        curFrame = tmpFrame;
                        System.out.println("设置为暂停状态");
                        break;
                    }

                    if (cnt > 0) {
                        // 写入缓存数据
                        sourceDataLine.write(tempBuffer, 0, cnt);       //这就是传说中的播放?
                        ++tmpFrame;
                    }

                }
                // Block等待临时数据被输出为空
                sourceDataLine.drain();
//				如果打开则播放完毕后不能再次播放
                sourceDataLine.close();             //这里有点问题？？
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
            if(cnt == -1){
                System.out.println("播放完毕!");
                System.out.println("当前帧 : " + tmpFrame);
                playStatus = PlayStatus.STOP;
            }
        }
    }//PlayRunnable
}

