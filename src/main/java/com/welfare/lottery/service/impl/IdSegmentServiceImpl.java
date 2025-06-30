package com.welfare.lottery.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.welfare.lottery.dao.IdSegmentDao;
import com.welfare.lottery.entity.IdSegment;
import com.welfare.lottery.service.IdSegmentService;
import org.springframework.stereotype.Service;

/**
 * (IdSegment)表服务实现类
 *
 * @author admin
 * @since 2025-06-23 19:02:15
 */
@Service("idSegmentService")
public class IdSegmentServiceImpl extends ServiceImpl<IdSegmentDao, IdSegment> implements IdSegmentService {

}

