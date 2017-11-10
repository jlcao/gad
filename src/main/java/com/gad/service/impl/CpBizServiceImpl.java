/*
 * @(#) CpBizServiceImpl.java ,2017年11月10日
 *
 * Copyright 2017 zbj.com, Inc. ALL rights reserved.
 * ZHUBAJIE PROPRIETARY/CONFIDENTIAL. Use is subject to lincese trems.
 */
package com.gad.service.impl;

import com.gad.domin.dto.CpBuyResult;
import com.gad.domin.dto.PlanConfig;
import com.gad.domin.entity.OptimalPlanEntity;
import com.gad.service.CpBizService;
import org.springframework.stereotype.Service;

/**
 * @author caojianlong(caojianlong@zbj.com)
 * @date 2017/11/10
 */
@Service
public class CpBizServiceImpl implements CpBizService {

    @Override
    public CpBuyResult buy(OptimalPlanEntity optimalPlan, PlanConfig config) {
        return null;
    }
}
