package com.diudiu.applet.dto;

import com.diudiu.applet.entity.Banner;
import com.diudiu.applet.entity.Navigation;
import com.diudiu.applet.entity.Recommend;
import lombok.Data;

import java.util.List;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 12/9/18
 * @since 0.1
 */
@Data
public class IndexDto {
    private List<Banner> banner;

    private Recommend recommend;

    private Navigation navigation;
}
