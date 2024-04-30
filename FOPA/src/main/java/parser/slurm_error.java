package parser;

import data_structure.data;
import formatter.time_formatter;
import tech.tablesaw.api.DateColumn;
import tech.tablesaw.api.IntColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static data_structure.data.*;
import static formatter.time_formatter.toLocalDateTime;

public class slurm_error {
    public static void add_error(String s_time, int association, String account, String user, String Partition) {
        LocalDateTime time = time_formatter.toLocalDateTime(s_time);
        Table error = Table.create("error_filter").addColumns(
                DateColumn.create("job_time", new LocalDate[]{time.toLocalDate()}),
                IntColumn.create("association", new int[]{association}),
                StringColumn.create("account", new String[]{account}),
                StringColumn.create("user", new String[]{user}),
                StringColumn.create("Partition", new String[]{Partition})
        );
        data.errors.append(error);
    }
}
