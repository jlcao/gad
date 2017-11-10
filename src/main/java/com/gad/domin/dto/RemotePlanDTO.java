/*
 * @(#) RemotePlanDTO.java ,2017年11月10日
 *
 * Copyright 2017 zbj.com, Inc. ALL rights reserved.
 * ZHUBAJIE PROPRIETARY/CONFIDENTIAL. Use is subject to lincese trems.
 */
package com.gad.domin.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author caojianlong(caojianlong@zbj.com)
 * @date 2017/11/10
 */
public class RemotePlanDTO implements Serializable {
    private Boolean loginStatus;

    private Boolean success;

    private List<PlanDetailDTO> expressionName;

    private String firstPlanResult;

    private List<FvDTO> fvList;

    public Boolean getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<PlanDetailDTO> getExpressionName() {
        return expressionName;
    }

    public void setExpressionName(List<PlanDetailDTO> expressionName) {
        this.expressionName = expressionName;
    }

    public String getFirstPlanResult() {
        return firstPlanResult;
    }

    public void setFirstPlanResult(String firstPlanResult) {
        this.firstPlanResult = firstPlanResult;
    }

    public List<FvDTO> getFvList() {
        return fvList;
    }

    public void setFvList(List<FvDTO> fvList) {
        this.fvList = fvList;
    }
}
