package com.welfare.lottery.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.welfare.lottery.entity.IdSegment;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author xuchengcheng
 * @since 2025/6/23
 */
@Slf4j
@Component
public class GenerateIdService {

    private static final int PRELOAD_PERCENTAGE = 10;

    @Resource
    private IdSegmentService idSegmentService;

    private Cache<String, SegmentRange> segmentCache;

    public static final String USER_ID = "user_id";

    @PostConstruct
    public void init() {
        segmentCache = Caffeine.newBuilder()
                .maximumSize(10)
                .build();
    }

    @Transactional
    public long generateId(String bizTag) {
        SegmentRange range = getOrFetchSegment(bizTag);
        return range.getNextId();
    }

    @Transactional
    public long generateUserId() {
        return generateId(USER_ID);
    }

    private SegmentRange getOrFetchSegment(String bizTag) {
        SegmentRange range = segmentCache.getIfPresent(bizTag);
        if (range == null || range.isExhausted()) {
            synchronized (this) {
                range = segmentCache.getIfPresent(bizTag);
                if (range == null || range.isExhausted()) {
                    range = fetchNewSegmentFromDb(bizTag);
                    segmentCache.put(bizTag, range);
                }
            }
        }
        return range;
    }

    private SegmentRange fetchNewSegmentFromDb(String bizTag) {
        IdSegment dbSegment = idSegmentService.getOne(new QueryWrapper<IdSegment>().eq("biz_tag", bizTag));
        if (dbSegment == null) {
            throw new RuntimeException("未找到对应bizTag: " + bizTag);
        }

        long nextMax = dbSegment.getCurrentMax() + dbSegment.getStep();
        IdSegment idSegment = new IdSegment();
        idSegment.setCurrentMax(nextMax);
        boolean success = idSegmentService.update(idSegment, new QueryWrapper<IdSegment>().eq("biz_tag", bizTag));

        if (!success) {
            throw new RuntimeException("更新ID段失败");
        }

        log.info("获取新段：{} -> [{} - {}]", bizTag, dbSegment.getCurrentMax(), nextMax - 1);
        return new SegmentRange(dbSegment.getCurrentMax(), dbSegment.getStep());
    }

    static class SegmentRange {

        private final AtomicLong start;
        private final long end;

        public SegmentRange(long start, long size) {
            this.start = new AtomicLong(start);
            this.end = start + size - 1;
        }

        public long getNextId() {
            long current = start.incrementAndGet();
            if (current > end) {
                throw new RuntimeException("当前段ID已用尽，请重新获取");
            }
            return current;
        }

        public boolean isExhausted() {
            return start.get() >= end - (end * PRELOAD_PERCENTAGE / 100);
        }
    }
}
