package com.laman.biz.lottery.domain.repository;

import com.laman.biz.lottery.domain.entity.ScanerInfo;
import com.laman.fusion.base.base.BaseRepository;

/**
* @Title: LotteryRepository
* @Description:  扫描者自定义操作库
* @Author: Away
* @Date: 2018/5/23 14:34
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public interface ScannerRepository extends BaseRepository<ScanerInfo,Long>{

    ScanerInfo findByPhone(String phone);

}
