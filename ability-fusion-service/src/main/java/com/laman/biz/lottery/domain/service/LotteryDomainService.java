package com.laman.biz.lottery.domain.service;

import com.laman.biz.lottery.app.dto.LotteryConfigDto;
import com.laman.biz.lottery.domain.entity.LotteryConfig;
import com.laman.biz.lottery.domain.repository.LotteryRepository;
import com.laman.fusion.base.base.BaseDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @Title: LotteryDomainService
* @Description:
* @Author: Away
* @Date: 2018/5/23 14:38
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
public class LotteryDomainService extends BaseDomainService<LotteryRepository,LotteryConfig,LotteryConfigDto>{

    private final LotteryRepository lotteryRepository;

    @Autowired
    public LotteryDomainService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }

    /**
     * @Method:  findConf
     * @Author: Away
     * @Version: 1.0
     * @See: 查找配置
     * @Param:
     * @Return: com.laman.biz.lottery.app.dto.LotteryConfigDto
     * @Exception:
     * @Date: 2018/5/23 14:41
     */
    public LotteryConfigDto findConf(){
        return toDto(lotteryRepository.findTop1ByOrderByRawAddTimeDesc(),LotteryConfigDto.class);
    }

}
