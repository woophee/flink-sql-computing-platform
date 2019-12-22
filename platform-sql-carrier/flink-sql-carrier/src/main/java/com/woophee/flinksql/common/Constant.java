package com.woophee.flinksql.common;

import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;

public class Constant {
    //sql解析
    public static final Function<String[], Optional<String[]>> NO_OPERANDS =
            (operands) -> Optional.of(new String[0]);

    public static final Function<String[], Optional<String[]>> SINGLE_OPERAND =
            (operands) -> Optional.of(new String[]{operands[0]});

    public static final int DEFAULT_PATTERN_FLAGS = Pattern.CASE_INSENSITIVE | Pattern.DOTALL;

    //与server联动的接口地址
    public static final String JOB_DETAIL_URL = ConfigUtil.getStringValue("job.detail.url");


}
