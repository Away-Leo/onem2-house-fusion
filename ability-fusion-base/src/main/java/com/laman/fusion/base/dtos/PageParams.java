package com.laman.fusion.base.dtos;

/**
* @Title: PageParams
* @Description:  分页载体
* @Author: Away
* @Date: 2018/6/5 14:31
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public class PageParams {

    private int page;

    private int size;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
