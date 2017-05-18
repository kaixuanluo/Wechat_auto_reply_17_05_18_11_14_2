package com.example.a90678.lkx_common_17_05_17_16_45.common.bean;


import java.util.List;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2017/1/18 14:04 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2017/1/18 14:04 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public class EmailListBean extends BaseListResult<EmailListBean.EmailList> {

    Data data;

    public Data getData() {
        return data;
    }

    @Override
    public List<EmailList> getList() {
        return getData().getRows();
    }

    public class Data {

        List<EmailList> rows;

        public List<EmailList> getRows() {
            return rows;
        }
    }

    public class EmailList {

        private String id;
        private String mailType;
        private String sendName;
        private String sendCode;
        private String title;
        private String content;
        private String recvTime;
        private String isRead;
        private String isUrgent;
        private String isHasAttachment;
        private String isReceipt;
        private String recverNames;
        private String recverCodes;
        private List<ToBean> to;
        private List<CcBean> cc;
        private List<BccBean> bcc;
        private List<AttachmetinfosBean> attachmetinfos;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMailType() {
            return mailType;
        }

        public void setMailType(String mailType) {
            this.mailType = mailType;
        }

        public String getSendName() {
            return sendName;
        }

        public void setSendName(String sendName) {
            this.sendName = sendName;
        }

        public String getSendCode() {
            return sendCode;
        }

        public void setSendCode(String sendCode) {
            this.sendCode = sendCode;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getRecvTime() {
            return recvTime;
        }

        public void setRecvTime(String recvTime) {
            this.recvTime = recvTime;
        }

        public String getIsRead() {
            return isRead;
        }

        public void setIsRead(String isRead) {
            this.isRead = isRead;
        }

        public String getIsUrgent() {
            return isUrgent;
        }

        public void setIsUrgent(String isUrgent) {
            this.isUrgent = isUrgent;
        }

        public String getIsHasAttachment() {
            return isHasAttachment;
        }

        public void setIsHasAttachment(String isHasAttachment) {
            this.isHasAttachment = isHasAttachment;
        }

        public String getIsReceipt() {
            return isReceipt;
        }

        public void setIsReceipt(String isReceipt) {
            this.isReceipt = isReceipt;
        }

        public String getRecverNames() {
            return recverNames;
        }

        public void setRecverNames(String recverNames) {
            this.recverNames = recverNames;
        }

        public String getRecverCodes() {
            return recverCodes;
        }

        public void setRecverCodes(String recverCodes) {
            this.recverCodes = recverCodes;
        }

        public List<ToBean> getTo() {
            return to;
        }

        public void setTo(List<ToBean> to) {
            this.to = to;
        }

        public List<CcBean> getCc() {
            return cc;
        }

        public void setCc(List<CcBean> cc) {
            this.cc = cc;
        }

        public List<BccBean> getBcc() {
            return bcc;
        }

        public void setBcc(List<BccBean> bcc) {
            this.bcc = bcc;
        }

        public List<AttachmetinfosBean> getAttachmetinfos() {
            return attachmetinfos;
        }

        public void setAttachmetinfos(List<AttachmetinfosBean> attachmetinfos) {
            this.attachmetinfos = attachmetinfos;
        }

        public class ToBean {
            /**
             * name : sample string 1
             * address : sample string 2
             */

            private String name;
            private String address;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }

        public class CcBean {
            /**
             * name : sample string 1
             * address : sample string 2
             */

            private String name;
            private String address;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }

        public class BccBean {
            /**
             * name : sample string 1
             * address : sample string 2
             */

            private String name;
            private String address;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }

        public class AttachmetinfosBean {
            /**
             * id : sample string 1
             * name : sample string 2
             * realname : sample string 3
             * type : sample string 4
             * size : 5.0
             * path : sample string 6
             */

            private String id;
            private String name;
            private String realname;
            private String type;
            private double size;
            private String path;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public double getSize() {
                return size;
            }

            public void setSize(double size) {
                this.size = size;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }
        }
    }
}
