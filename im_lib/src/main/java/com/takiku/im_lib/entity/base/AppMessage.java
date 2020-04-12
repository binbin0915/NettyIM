package com.takiku.im_lib.entity.base;


import com.takiku.im_lib.protobuf.PackProtobuf;
import com.takiku.im_lib.util.StringUtil;

public  class AppMessage extends AbstractPack<AppBody> {
    private Head head;  // 消息头
    private String body;// 消息体
    public AppMessage(Builder builder){
        this.head=builder.head;
        this.body=builder.body;
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public String getBody() {
        return body == null ? "" : body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public static class Builder{
        private Head head=new Head();  // 消息头
        private String body;// 消息体
        public Builder setMsgId(String msgId){
              head.setMsgId(msgId);
              return this;
        }
        public Builder setFromId(String fromId){
            head.setFromId(fromId);
            return this;
        }

        public Builder setToId(String toId){
            head.setToId(toId);
            return this;
        }

        public Builder setMsgType(int msgType){
            head.setMsgType(msgType);
            return this;
        }

        public Builder setTimestamp(long timestamp){
            head.setTimestamp(timestamp);
            return this;
        }

        public Builder setExtend(String extend){
            head.setExtend(extend);
            return this;
        }

        public Builder setMsgContentType(int msgContentType){
            head.setMsgContentType(msgContentType);
            return this;
        }

        public AppMessage build(){
            return new AppMessage(this);
        }

    }


    @Override
    public void setPackType(int packType) {
        super.setPackType(packType);
    }

    public PackProtobuf.Msg build() {
        PackProtobuf.Msg.Builder builder = PackProtobuf.Msg.newBuilder();
        PackProtobuf.Head.Builder headBuilder = PackProtobuf.Head.newBuilder();
        headBuilder.setMsgType(getHead().getMsgType());
        headBuilder.setMsgContentType(getHead().getMsgContentType());
        if (!StringUtil.isEmpty(getHead().getMsgId()))
            headBuilder.setMsgId(getHead().getMsgId());
        if (!StringUtil.isEmpty(getHead().getFromId()))
            headBuilder.setFromId(getHead().getFromId());
        if (!StringUtil.isEmpty(getHead().getToId()))
            headBuilder.setToId(getHead().getToId());
        if (getHead().getTimestamp() != 0)
            headBuilder.setTimestamp(getHead().getTimestamp());
        if (!StringUtil.isEmpty(getHead().getExtend()))
            headBuilder.setExtend(getHead().getExtend());
        if (!StringUtil.isEmpty(getBody()))
            builder.setBody(getBody());
        builder.setHead(headBuilder);
        return builder.build();
    }
}
