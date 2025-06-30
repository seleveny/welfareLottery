package com.welfare.lottery.response;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author xuchengcheng
 * 利用分页的封装模型
 * @since 2025/6/20
 */
@Data
public class PageResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 8347710908545444702L;

    private Long current;
    private Long size;
    private Long total;
    private List<T> records;
}
