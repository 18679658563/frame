package com.e8.frame.tools;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 分页工具
 * User: silence
 * Date: 2019-02-25
 * Time: 上午8:34
 */
@Data
public class PageUtil<T> {

    private Integer page;//当前页，默认从0开始

    private Integer size;//每页显示几条数据

    private Integer number;//从第几条数据开始，不包括此数

    private List<T> list;//分页中的数据集合

    private Integer count;//总数量

    public Integer getNumber(){
        return this.page*this.size;
    }

    /**
     * 将分页数据转化成对应的格式
     * @param page
     * @return
     */
    public static Map toResult(PageUtil page){
        Map map = new HashMap();
        map.put("content",page.getList());
        map.put("totalElements",page.getCount());
        return map;
    }

}