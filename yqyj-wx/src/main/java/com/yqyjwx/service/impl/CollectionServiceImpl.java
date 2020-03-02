package com.yqyjwx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqyjcommon.entity.Collection;
import com.yqyjwx.dao.CollectionDao;
import com.yqyjwx.service.CollectionService;
import org.springframework.stereotype.Service;

@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionDao, Collection> implements CollectionService {
}
