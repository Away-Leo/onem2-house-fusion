package com.laman.biz.menu.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

/**
 * 认证费用价格表
 * Created by dujy on 2017/7/31.
 */
@Getter
@Setter
public class SysUrlsDto extends BaseDto{

    /**链接地址**/
    private String url;

    /**type**/
    private String urlMethod;

    /**连接描述**/
    private String urlName;

    /**所属菜单**/
    private String urlBelong;

    public SysUrlsDto(String url, String urlName,String urlBelong,String urlMethod) {
        this.url = url;
        this.urlName = urlName;
        this.urlBelong=urlBelong;
        this.urlMethod=urlMethod;
    }
    public SysUrlsDto() {
    }
}
