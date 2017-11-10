/*
 * @(#) CoreScheduler.java ,2017年11月10日
 *
 * Copyright 2017 zbj.com, Inc. ALL rights reserved.
 * ZHUBAJIE PROPRIETARY/CONFIDENTIAL. Use is subject to lincese trems.
 */
package com.gad.task;

import com.gad.domin.dto.MessageDTO;
import com.gad.common.PlanConfigurationBuilder;
import com.gad.domin.dto.CpBuyResult;
import com.gad.domin.dto.PlanConfig;
import com.gad.domin.entity.OptimalPlanEntity;
import com.gad.service.CpBizService;
import com.gad.service.CpxzsBizService;
import com.gad.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author caojianlong(caojianlong@zbj.com)
 * @date 2017/11/10
 */

@Service
public class CoreScheduler implements Runnable {

    @Autowired
    CpxzsBizService cpxzsBizService;

    @Autowired
    CpBizService cpBizService;

    @Autowired
    PlanConfigurationBuilder configurationBuilder;

    @Autowired
    NoticeService noticeService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run() {
        if (!cpxzsBizService.hashNext()) {
            logger.info("下一场次暂时还未开始,休息一下重试");
            return;
        }
        MessageDTO dto = new MessageDTO();
        try {

            //- 2.获取份额配置 和 计划配置
            PlanConfig config = configurationBuilder.getConfig();

            //- 1.拉取最优计划
            OptimalPlanEntity optimalPlan = cpxzsBizService.getOptimalPlan(config);



            if (!optimalPlan.getNewPlanFlag()) {
                logger.info("该计划还未更新,休息一下重试");
                return;
            }

            //- 3.购买计划
            CpBuyResult res = cpBizService.buy(optimalPlan, config);

            if (res.isSuccess()) {
                logger.info("恭喜购入计划成功哦！");
                dto.setContent(String.format("恭喜！ 购入成功！！！  %s期, 追加 %d 期 , 配购比例 %s ,购入总金额 %f "));
            } else {
                // - 计划购入失败，邮件通知
                dto.setContent(String.format("警报 ！ 购入失败！！！  %s期, 追加 %d 期 , 配购比例 %s ,购入总金额 %f "));
            }
        } catch (Exception e) {
            dto.setContent(String.format("警报 ！ 购入失败！！！  %s期, 追加 %d 期 , 配购比例 %s ,购入总金额 %f \n 失败原因:\n%s"));
            logger.warn("主流程失败，请核查", e);
        }

        noticeService.notice(dto);
    }
}
