package com.tmri.enforcement.app.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.tmri.enforcement.app.R;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MsgHolder> {
    private Context mContext;
    private List<String> msgList;


    public MessageAdapter(Context mContext) {
        this.mContext = mContext;
        msgList = new ArrayList<>();
    }

    @Override
    public MessageAdapter.MsgHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageAdapter.MsgHolder(LayoutInflater.from(mContext).inflate(R.layout.item,parent,false));
    }

    @Override
    public void onBindViewHolder(MessageAdapter.MsgHolder holder, final int position) {
        holder.nameTv.setText(msgList.get(position));
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    public void addMessage(String msg) {
        msgList.add(msg);
        notifyItemInserted(msgList.size()-1);
    }

    public void clearMsgList(){
        msgList.clear();
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onClick(BluetoothDevice device);
    }

    class MsgHolder extends RecyclerView.ViewHolder{
        private TextView nameTv;
        public MsgHolder(View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.name);
        }
    }

}
