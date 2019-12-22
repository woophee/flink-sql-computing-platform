package com.woophee.flinksql.service.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

public class SqlParser {
    public static List<SqlCommandCall> parse(String sqlText) {
        String[] sqls = sqlText.split(";");
        List<SqlCommandCall> calls = new ArrayList<>();
        for (String sql : sqls) {
            if (sql.trim().isEmpty() || sql.startsWith("--")) {
                // skip empty line and comment line
                continue;
            }
            Optional<SqlCommandCall> optionalCall = parseOne(sql);
            if (optionalCall.isPresent()) {
                calls.add(optionalCall.get());
            } else {
                throw new RuntimeException("Unsupported command '" + sql + "'");
            }
        }
        return calls;
    }

    private static Optional<SqlCommandCall> parseOne(String stmt) {
        // normalize
        stmt = stmt.trim();
        // remove ';' at the end
        if (stmt.endsWith(";")) {
            stmt = stmt.substring(0, stmt.length() - 1).trim();
        }

        // parse
        for (SqlCommand cmd : SqlCommand.values()) {
            final Matcher matcher = cmd.pattern.matcher(stmt);
            if (matcher.matches()) {
                final String[] groups = new String[matcher.groupCount()];
                for (int i = 0; i < groups.length; i++) {
                    groups[i] = matcher.group(i + 1);
                }
                return cmd.operandConverter.apply(groups)
                        .map((operands) -> new SqlCommandCall(cmd, operands));
            }
        }
        return Optional.empty();
    }
}
