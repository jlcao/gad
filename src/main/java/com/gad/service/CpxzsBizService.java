/*
 * @(#) CpxzsBizService.java ,2017年11月10日
 *
 * Copyright 2017 zbj.com, Inc. ALL rights reserved.
 * ZHUBAJIE PROPRIETARY/CONFIDENTIAL. Use is subject to lincese trems.
 */
package com.gad.service;

import com.gad.domin.dto.PlanConfig;
import com.gad.domin.entity.OptimalPlanEntity;

/**
 * @author caojianlong
 * @title CpxzsBizService
 * @date 2017/11/10
 * @since v1.0.0
 */
public interface CpxzsBizService {

    /**
     * 拉取最优计划
     * @return
     * @param config
     */
    OptimalPlanEntity getOptimalPlan(PlanConfig config);

    /**
     * 下一场是否开始
     * @return
     */
    boolean hashNext();

}
