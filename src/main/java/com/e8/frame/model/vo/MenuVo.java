package com.e8.frame.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:构建前端路由时用到
 * User: silence
 * Date: 2019-02-21
 * Time: 下午3:22
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuVo {

    private String name;

    private String path;

    private String redirect;

    private String component;

    private Boolean alwaysShow;

    private MenuMetaVo meta;

    private List<MenuVo> children;
}
