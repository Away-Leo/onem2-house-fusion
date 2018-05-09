package com.onem2.fusion.base.base;

import com.onem2.fusion.base.dtos.BaseDto;
import com.onem2.fusion.base.entity.BaseEntity;
import com.onem2.fusion.base.enums.ENUM_EXCEPTION;
import com.onem2.fusion.base.util.ObjectHelper;
import com.onem2.fusion.base.util.ObjectProperUtil;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

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
    public BD findById(Long id, Class<BD> tclass) throws Exception{
        if(ObjectHelper.isNotEmpty(id)){
            BE source=this.CT.getOne(id);
            if(ObjectHelper.isNotEmpty(source)){
                return source.to(tclass);
            }else{
                return null;
            }
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
    public BD updateData(BD toUpdateData, Class<BE> tClass)throws Exception{
        if(ObjectHelper.isNotEmpty(toUpdateData)&&ObjectHelper.isNotEmpty(tClass)){
            if(ObjectHelper.isNotEmpty(toUpdateData.getId())){
                BE oldData=this.CT.getOne(toUpdateData.getId());
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
    protected BD saveOrUpdateData(BD data, Class<BE> tClass) throws Exception{
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

    /**
     * @Author: Away
     * @Title: toDtoList
     * @Description: 将域对象集合转换为数据传输体集合
     * @Param: source 待转换数据
     * @Param: tclass 目标数据类型
     * @Return: java.util.List<BD>
     * @Date: 2018/4/24 9:46
     * @Version: 2018/4/24 9:46
     */
    protected List<BD> toDtoList(List<BE> source, Class<BD> tclass){
        if(ObjectHelper.isNotEmpty(source)){
            List<BD> returnData=new ArrayList<>(source.size());
            for(BE temp:source){
                returnData.add(temp.to(tclass));
            }
            return returnData;
        }else{
            return null;
        }
    }

    /**
     * @Author: Away
     * @Title: toDto
     * @Description: 域对象转换为载体
     * @Param: be
     * @Param: tclass
     * @Return: BD
     * @Date: 2018/4/24 9:54
     * @Version: 2018/4/24 9:54
     */
    protected BD toDto(BE be, Class<BD> tclass){
        if(ObjectHelper.isNotEmpty(be)&&ObjectHelper.isNotEmpty(tclass)){
            return be.to(tclass);
        }else{
            return null;
        }
    }

    /**
     * @Author: Away
     * @Title: toDtoPage
     * @Description: 转换分页数据类型
     * @Param: sourceData
     * @Param: tClass
     * @Return: org.springframework.data.domain.PageImpl<T>
     * @Date: 2018/4/24 16:51
     * @Version: 2018/4/24 16:51
     */
    public <T> PageImpl<T> toDtoPage(Page<BE> sourceData, Class<T> tClass, Pageable pageable){
        if(ObjectHelper.isNotEmpty(sourceData)&&ObjectHelper.isNotEmpty(sourceData.getContent())&&sourceData.getContent().size()>0){
            List<T> returnList=new ArrayList<>(sourceData.getContent().size());
            for(BE temp:sourceData.getContent()){
                returnList.add(temp.to(tClass));
            }
            return new PageImpl<>(returnList,pageable,sourceData.getTotalElements());
        }else{
            return new PageImpl<>(new ArrayList<T>(0),pageable,0);
        }
    }

}
