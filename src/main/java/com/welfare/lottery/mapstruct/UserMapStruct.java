package com.welfare.lottery.mapstruct;

import com.welfare.lottery.utils.PasswordUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.Objects;

/**
 * @author xuchengcheng
 * @since 2025/6/20
 * 用户模型的转换
 */
@Mapper(componentModel = "spring")
public interface UserMapStruct {

    @Named("intToBoolean")
    default Boolean intToBoolean(Integer value) {
        if (Objects.isNull(value)) {
            return null;
        }
        return value == 1;
    }

    // 自定义 Boolean -> Integer 转换方法（如果需要反向转换）
    @Named("booleanToInt")
    default Integer booleanToInt(Boolean value) {
        if (Objects.isNull(value)) {
            return null;
        }
        return BooleanUtils.isTrue(value) ? 1 : 0;
    }


    @Named("convertPoliceToInt")
    default Integer convertPoliceToInt(String police) {
        if ("是".equals(police)) {
            return 1;
        }
        return 0;
    }

    @Named("convertStatusToInt")
    default Boolean convertStatusToBoolean(String status) {
        if ("启用".equals(status)) {
            return true;
        }
        return false;
    }


    @Named("encryptPassword")
    default String encryptPassword(String password) {
        return PasswordUtils.encryptPassword(password);
    }
}
