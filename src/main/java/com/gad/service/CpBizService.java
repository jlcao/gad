/*
 * @(#) CpBizService.java ,2017年11月10日
 *
 * Copyright 2017 zbj.com, Inc. ALL rights reserved.
 * ZHUBAJIE PROPRIETARY/CONFIDENTIAL. Use is subject to lincese trems.
 */
package com.gad.service;

import com.gad.domin.dto.CpBuyResult;
import com.gad.domin.dto.PlanConfig;
import com.gad.domin.entity.OptimalPlanEntity;

/**
 * @author caojianlong
 * @title CpBizService
 * @date 2017/11/10
 * @since v1.0.0
 */
public interface CpBizService {

    /**
     * 购买
     * @param optimalPlan
     * @param config
     * @return
     */
    CpBuyResult buy(OptimalPlanEntity optimalPlan, PlanConfig config);

}
