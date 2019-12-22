package com.woophee.flinksql.service.sql;

import com.woophee.flinksql.common.Constant;

import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;

public enum SqlCommand {


    INSERT_INTO(
            "(INSERT\\s+INTO.*)",
            Constant.SINGLE_OPERAND),

    CREATE_TABLE(
            "(CREATE\\s+TABLE.*)",
            Constant.SINGLE_OPERAND),

    SET(
            "SET(\\s+(\\S+)\\s*=(.*))?", // whitespace is only ignored on the left side of '='
            (operands) -> {
                if (operands.length < 3) {
                    return Optional.empty();
                } else if (operands[0] == null) {
                    return Optional.of(new String[0]);
                }
                return Optional.of(new String[]{operands[1], operands[2]});
            });

    public final Pattern pattern;
    public final Function<String[], Optional<String[]>> operandConverter;

    SqlCommand(String matchingRegex, Function<String[], Optional<String[]>> operandConverter) {
        this.pattern = Pattern.compile(matchingRegex, Constant.DEFAULT_PATTERN_FLAGS);
        this.operandConverter = operandConverter;
    }

    @Override
    public String toString() {
        return super.toString().replace('_', ' ');
    }

    public boolean hasOperands() {
        return operandConverter != Constant.NO_OPERANDS;
    }
}
