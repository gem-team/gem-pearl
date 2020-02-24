package com.gemframework.model.vo.view;

import lombok.Builder;
import lombok.Data;

@Data
public class Home {

    @Data
    @Builder
    public static class Order{
        private int waitPay;
        private int timeoutPay;
        private int payOffs;
        private int sended;
        private int timeoutSend;
        private int close;
        private int success;
        private int refund;
    }

}
