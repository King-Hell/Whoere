package cn.edu.sdu.litong.whoere;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by hasee on 2017/5/2.
 */

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
    private List<Msg> mMsgList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;
        TextView leftName;
        TextView rightName;
        TextView systemMsg;
        public ViewHolder(View view){
            super(view);
            leftLayout=(LinearLayout)view.findViewById(R.id.left_layout);
            rightLayout=(LinearLayout)view.findViewById(R.id.right_layout);
            leftMsg=(TextView)view.findViewById(R.id.left_msg);
            rightMsg=(TextView)view.findViewById(R.id.right_msg);
            leftName=(TextView)view.findViewById(R.id.left_name);
            rightName=(TextView)view.findViewById(R.id.right_name);
            systemMsg=(TextView)view.findViewById(R.id.system_message);
        }
    }
    public MsgAdapter(List<Msg> msgList){
        mMsgList=msgList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Msg msg=mMsgList.get(position);
        if(msg.getType()==Msg.TYPE_RECEIVED){
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.systemMsg.setVisibility(View.GONE);
            holder.leftMsg.setText(msg.getContent());
            holder.leftName.setText(msg.getUsername());
        }else if(msg.getType()==Msg.TYPE_SENT){
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.leftLayout.setVisibility(View.GONE);
            holder.systemMsg.setVisibility(View.GONE);
            holder.rightMsg.setText(msg.getContent());
            holder.rightName.setText(msg.getUsername());
        }else if(msg.getType()==Msg.TYPE_SYSTEM){
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.systemMsg.setText(msg.getContent());
        }
    }
    @Override
    public int getItemCount(){
        return mMsgList.size();
    }
}
