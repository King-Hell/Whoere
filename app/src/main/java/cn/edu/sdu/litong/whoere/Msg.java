package cn.edu.sdu.litong.whoere;

/**
 * Created by hasee on 2017/5/2.
 */

public class Msg {
    public static final int TYPE_RECEIVED=0;
    public static final int TYPE_SENT=1;
    public static final int TYPE_SYSTEM=2;
    private String content;
    private int type;
    private String username;
    public Msg(String content,int type,String username){
        this.content=content;
        this.type=type;
        this.username=username;
    }
    public String getContent(){
        return content;
    }
    public String getUsername(){return username;}
    public int getType(){
        return type;
    }
}
