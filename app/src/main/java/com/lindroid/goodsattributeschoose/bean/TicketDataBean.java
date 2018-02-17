package com.lindroid.goodsattributeschoose.bean;

import java.util.List;

/**
 * @author Lin
 * @date 2018/1/18
 * @function
 */

public class TicketDataBean {


    private List<TicketBean> ticketData;

    public List<TicketBean> getTicketData() {
        return ticketData;
    }

    public void setTicketData(List<TicketBean> ticketData) {
        this.ticketData = ticketData;
    }

    public static class TicketBean {
        /**
         * ticketID : 1
         * price : 100
         * date : 2月13日
         * time : 09:00
         * level : 硬座
         * limitedNum : 2
         * remainNum : 5
         */

        private int ticketID;
        private int price;
        private String date;
        private String time;
        private String level;
        private int limitedNum;
        private int remainNum;

        public int getTicketID() {
            return ticketID;
        }

        public void setTicketID(int ticketID) {
            this.ticketID = ticketID;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public int getLimitedNum() {
            return limitedNum;
        }

        public void setLimitedNum(int limitedNum) {
            this.limitedNum = limitedNum;
        }

        public int getRemainNum() {
            return remainNum;
        }

        public void setRemainNum(int remainNum) {
            this.remainNum = remainNum;
        }
    }
}
