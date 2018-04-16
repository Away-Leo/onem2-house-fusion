package com.onem2.fusion.base.base;

import com.onem2.fusion.base.entity.BaseEntity;
import com.onem2.fusion.base.enums.ENUM_EXCEPTION;
import com.onem2.fusion.base.util.ObjectHelper;
import com.onem2.fusion.base.util.ObjectProperUtil;
import com.onem2.fusion.base.dtos.BaseDto;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Title: BaseDomainService.java
 * @Description: 基础域对象服务
 * @Author: Away
 * @Date: 2018/4/11 10:04
 * @Copyright: 重庆壹平方米网络科技有限公司
 * @Version: V1.0
 */
public abstract class BaseDomainService<CT extends BaseRepository<BE,Long>,BE extends BaseEntity,BD extends BaseDto> {

    @Autowired
    private CT CT;

    /**
     * @Author: Away
     * @Title: findById
     * @Description: 按照ID查找数据
     * @Param: id 待查找ID
     * @Return: BE
     * @Date: 2018/4/11 16:17
     * @Version: 2018/4/11 16:17
     */
    public BE findById(Long id) throws Exception{
        if(ObjectHelper.isNotEmpty(id)){
            return this.CT.getOne(id);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Author: Away
     * @Title: saveData
     * @Description: 公共保存方法
     * @Param: toSaveData 待保存dto数据
     * @Param: tClass 待保存数据对应的entity
     * @Return: BD 保存后的dto数据
     * @Date: 2018/4/11 15:25
     * @Version: 2018/4/11 15:25
     */
    public BD saveData(BD toSaveData,Class<BE> tClass)throws Exception{
        if(ObjectHelper.isNotEmpty(toSaveData)&&ObjectHelper.isNotEmpty(tClass)){
            if(ObjectHelper.isEmpty(toSaveData.getId())){
                BE saveData=tClass.newInstance();
                BeanUtils.copyProperties(toSaveData,saveData, "id","version");
                saveData=CT.saveEntity(saveData);
                BeanUtils.copyProperties(saveData,toSaveData);
                return toSaveData;
            }else{
                throw new BusinessException(ENUM_EXCEPTION.E10003.code,ENUM_EXCEPTION.E10003.msg);
            }
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Author: Away
     * @Title: updateData
     * @Description: 更新数据
     * @Param: toUpdateData 待更新数据
     * @Param: tClass dto对应域对象class
     * @Return: BD
     * @Date: 2018/4/11 16:11
     * @Version: 2018/4/11 16:11
     */
    public BD updateData(BD toUpdateData,Class<BE> tClass)throws Exception{
        if(ObjectHelper.isNotEmpty(toUpdateData)&&ObjectHelper.isNotEmpty(tClass)){
            if(ObjectHelper.isNotEmpty(toUpdateData.getId())){
                BE oldData=this.findById(toUpdateData.getId());
                if(ObjectHelper.isNotEmpty(oldData)){
                    BeanUtils.copyProperties(toUpdateData,oldData, "id");
                    oldData=this.CT.updateEntity(oldData);
                    return ObjectProperUtil.compareAndValue(oldData,toUpdateData,true,null);
                }else{
                    throw new BusinessException(ENUM_EXCEPTION.E10002.code,ENUM_EXCEPTION.E10002.msg);
                }
            }else{
                throw new BusinessException(ENUM_EXCEPTION.E10003.code,ENUM_EXCEPTION.E10003.msg);
            }
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Author: Away
     * @Title: saveOrUpdateData
     * @Description: 更细或保存数据
     * @Param: data 待操作数据
     * @Param: tClass dto对应域对象
     * @Return: BD
     * @Date: 2018/4/11 16:44
     * @Version: 2018/4/11 16:44
     */
    public BD saveOrUpdateData(BD data,Class<BE> tClass) throws Exception{
        if(ObjectHelper.isNotEmpty(data)&&ObjectHelper.isNotEmpty(tClass)){
            if(ObjectHelper.isNotEmpty(data.getId())){
                return this.updateData(data, tClass);
            }else{
                return this.saveData(data, tClass);
            }
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

}
