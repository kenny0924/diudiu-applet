package com.diudiu.applet.dto;

import com.diudiu.applet.entity.Province;
import com.diudiu.applet.entity.RetailStore;
import lombok.Data;

import java.util.List;

@Data
public class RetailStoreDto {


    private List<Province> filters;

    private List<RetailStore> retailStores;
}
