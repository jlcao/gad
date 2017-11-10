/*
 * @(#) PlanDetailDTO.java ,2017年11月10日
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
public class PlanDetailDTO implements Serializable {

    private Float profit;

    private Boolean curBool;

    private String jhfaCode;

    private Integer currentError;

    private Integer currentCorrect;

    /**
     * 胜算
     */
    private Float winRate;

    private Integer errorCount;

    private String jhfaName;

    private Integer maxCorrent;

    public Float getProfit() {
        return profit;
    }

    public void setProfit(Float profit) {
        this.profit = profit;
    }

    public Boolean getCurBool() {
        return curBool;
    }

    public void setCurBool(Boolean curBool) {
        this.curBool = curBool;
    }

    public String getJhfaCode() {
        return jhfaCode;
    }

    public void setJhfaCode(String jhfaCode) {
        this.jhfaCode = jhfaCode;
    }

    public Integer getCurrentError() {
        return currentError;
    }

    public void setCurrentError(Integer currentError) {
        this.currentError = currentError;
    }

    public Integer getCurrentCorrect() {
        return currentCorrect;
    }

    public void setCurrentCorrect(Integer currentCorrect) {
        this.currentCorrect = currentCorrect;
    }

    public Float getWinRate() {
        return winRate;
    }

    public void setWinRate(Float winRate) {
        this.winRate = winRate;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public String getJhfaName() {
        return jhfaName;
    }

    public void setJhfaName(String jhfaName) {
        this.jhfaName = jhfaName;
    }

    public Integer getMaxCorrent() {
        return maxCorrent;
    }

    public void setMaxCorrent(Integer maxCorrent) {
        this.maxCorrent = maxCorrent;
    }
}
