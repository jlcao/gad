/*
 * @(#) PlanConfig.java ,2017年11月10日
 *
 * Copyright 2017 zbj.com, Inc. ALL rights reserved.
 * ZHUBAJIE PROPRIETARY/CONFIDENTIAL. Use is subject to lincese trems.
 */
package com.gad.domin.dto;

import java.io.Serializable;

/**
 * @author caojianlong(caojianlong@zbj.com)
 * @date 2017/11/10
 */
public class PlanConfig implements Serializable {

    private Integer offset;
    private Integer number;
    private Integer keepPeriosd;

    public Integer getOffset() {
        return offset;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getKeepPeriosd() {
        return keepPeriosd;
    }

    public void setKeepPeriosd(Integer keepPeriosd) {
        this.keepPeriosd = keepPeriosd;
    }
}
