package com.lindroid.goodsattributeschoose.bean;

/**
 * @author Lin
 * @date 2018/2/17
 * @function
 */

public class OptionBean {
    private String optionName;
    private int status;

    public OptionBean(String optionName, int status) {
        this.optionName = optionName;
        this.status = status;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
