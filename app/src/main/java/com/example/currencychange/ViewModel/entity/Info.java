package com.example.currencychange.ViewModel.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
    public class Info {

        @SerializedName("timestamp")
        @Expose
        private Integer timestamp;
        @SerializedName("rate")
        @Expose
        private Double rate;

        public Integer getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Integer timestamp) {
            this.timestamp = timestamp;
        }

        public Double getRate() {
            return rate;
        }

        public void setRate(Double rate) {
            this.rate = rate;
        }

    }

