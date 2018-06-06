//package com.laman.web.common.controller;
//
//import com.laman.biz.lottery.app.dto.QuestionnaireDto;
//import com.laman.biz.lottery.app.dto.ScanerInfoDto;
//import com.laman.biz.lottery.app.service.LotteryAppService;
//import com.laman.biz.lottery.app.service.QuestionnaireAppService;
//import com.laman.biz.lottery.app.service.ScannerAppService;
//import com.laman.web.common.dto.CPViewResultInfo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
///**
//* @Title: LotteryDrawController
//* @Description:
//* @Author: Away
//* @Date: 2018/5/23 12:24
//* @Copyright: 重庆拉曼科技有限公司
//* @Version: V1.0
//*/
//@Slf4j
//@RestController
//public class LotteryDrawController extends AbstractCommonController {
//
//    private final LotteryAppService lotteryAppService;
//
//    private final ScannerAppService scannerAppService;
//
//    private final QuestionnaireAppService questionnaireAppService;
//
//    @Autowired
//    public LotteryDrawController(LotteryAppService lotteryAppService, ScannerAppService scannerAppService, QuestionnaireAppService questionnaireAppService) {
//        this.lotteryAppService = lotteryAppService;
//        this.scannerAppService=scannerAppService;
//        this.questionnaireAppService = questionnaireAppService;
//    }
//
//    /**
//     * @Method:  getConf
//     * @Author: Away
//     * @Version: v1.0
//     * @See: 获取配置
//     * @Param: info 返回数据
//     * @Return: com.laman.web.common.dto.CPViewResultInfo
//     * @Date: 2018/5/23 14:53
//     */
//    @GetMapping(value = "/getConf.json",name = "抽奖-获取配置")
//    public CPViewResultInfo getConf(CPViewResultInfo info){
//        try{
//            info.newSuccess(lotteryAppService.findConf());
//        }catch (Exception e){
//            info.newFalse(e);
//            log.error("",e);
//        }
//        return info;
//    }
//
//    @PostMapping(value = "/saveInfo.json",name = "抽奖-保存数据")
//    public CPViewResultInfo saveInfo(CPViewResultInfo info,@RequestBody ScanerInfoDto scanerInfoDto){
//        try{
//            info.newSuccess(this.scannerAppService.saveInfo(scanerInfoDto));
//        }catch (Exception e){
//            info.newFalse(e);
//            log.error("",e);
//        }
//        return info;
//    }
//
//    @GetMapping(value = "/getAll.json",name = "抽奖-获取全部人")
//    public CPViewResultInfo findAll(CPViewResultInfo info){
//        try{
//            info.newSuccess(this.scannerAppService.findAll());
//        }catch (Exception e){
//            info.newFalse(e);
//            log.error("",e);
//        }
//        return info;
//    }
//
//    @PostMapping(value = "/doQues.json",name = "抽奖-问卷调查")
//    public CPViewResultInfo doQues(CPViewResultInfo info,QuestionnaireDto dto){
//        try {
//            info.newSuccess(this.questionnaireAppService.saveOrUpdate(dto));
//        }catch (Exception e){
//            info.newFalse(e);
//            log.error("",e);
//        }
//        return info;
//    }
//
//    @PostMapping(value = "/changePage.json",name = "抽奖-更改页面")
//    public CPViewResultInfo changePage(CPViewResultInfo info,Integer code){
//        try {
//            info.newSuccess(this.lotteryAppService.changePage(code));
//        }catch (Exception e){
//            info.newFalse(e);
//            log.error("",e);
//        }
//        return info;
//    }
//
//}
