package filter;

import org.jetbrains.annotations.NotNull;
import tech.tablesaw.api.Table;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class error_filter {
    public static void err_get_by_string(@NotNull Table errors_table, String filter, String content) {
        if (errors_table.containsColumn(filter)) {
            Table result = errors_table.where(errors_table.stringColumn(filter).isEqualTo(content));
            System.out.println(result);
        } else {
            System.out.println("No such Column");
        }

    }

    public static void err_get_by_time(@NotNull Table errors_table, String start, String end) {
        job_filter.job_get_by_time(errors_table, start, end);
    }
}
