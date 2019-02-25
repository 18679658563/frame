package com.e8.frame.tools;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-02-25
 * Time: 上午8:34
 */
@Data
public class Page<T> {

    private Integer page;

    private Integer size;

    private List<T> list;

    private Integer count;

    public static Map toResult(Page page){
        Map map = new HashMap();
        map.put("content",page.getList());
        map.put("totalElements",page.getCount());
        return map;
    }

}