import Player_v0.FileInput;
import Player_v0.PlayList;
import Player_v1.AutoFileInput;
import Player_v1.ListCycleMode;
import Player_v1.MPlayList;
import Player_v1.MPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame{
    private int mWidth;
    private int mHeight;

    private JButton mPlay;
    private JButton mPrev;
    private JButton mNext;

    MusicPlayer musicPlayer;

    public View(){
        initMembers();
        setBounds(100, 100, mWidth, mHeight);
        setLayout(null);
        initComponent();

    }

    private void initMembers(){
        mWidth = 1000;
        mHeight = 500;
    }

    private void initComponent(){
        ButtonAction buttonAction = new ButtonAction();
        mPlay = new JButton("Play");
        mPlay.setBounds(100, mHeight - 200, 100, 100);

        mPrev = new JButton("Prev");
        mPrev.setBounds(250, mHeight - 200, 100, 100);

        mNext = new JButton("Next");
        mNext.setBounds(400, mHeight - 200, 100, 100);

        mPlay.addActionListener(buttonAction);
        mPrev.addActionListener(buttonAction);
        mNext.addActionListener(buttonAction);
        this.add(mPlay);
        this.add(mPrev);
        this.add(mNext);

        //初始化播放器,需要一个播放列表，和一个播放控制器
        FileInput fileInput = new AutoFileInput();
        PlayList playList = new MPlayList();

        playList.setList(fileInput.getPlayList());  //播放列表
        playList.setCurIndex(0);                    //当前索引
        playList.setPlayMode(new ListCycleMode());  //列表循环模式
        musicPlayer = new MusicPlayer(playList, new MPlayer());
    }

    public static void main(String[] args){

        View view = new View();
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setVisible(true);

    }

    /*-----按钮监听器------*/
    class ButtonAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();
            if(obj == mPlay){
                musicPlayer.onPlay();
            }else if(obj == mPrev){
                musicPlayer.prevSong();
            }else if(obj == mNext){
                musicPlayer.nextSong();
            }else{
                System.out.println("something wrong ... ButtonAction");
            }
        }
    }
}

