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

public class slurm_job {
    public int JobId, InitPrio, usec, uid, CPUs;
    public String job, NodeList, Partition, status;
    public LocalDateTime start, end;

    public slurm_job(int JobId) {
        this.JobId = JobId;
    }

    public void add_time(String s_time) {
        //2022-06-01T09:10:34.641
        if (this.start == null) {
            this.start = time_formatter.toLocalDateTime(s_time);
        } else {
            this.end = time_formatter.toLocalDateTime(s_time);
        }
    }

    public void end(String s_status) {
        if (this.end == null) {
            this.end = this.start;
        }
        this.status = s_status;
        Table job = Table.create("Job table").addColumns(
                IntColumn.create("JobId", new int[]{this.JobId}),
                StringColumn.create("Main", new String[]{this.job}),
                IntColumn.create("InitPrio", new int[]{this.InitPrio}),
                IntColumn.create("usec", new int[]{this.usec}),
                IntColumn.create("uid", new int[]{this.uid}),
                StringColumn.create("NodeList", new String[]{this.NodeList}),
                IntColumn.create("CPUs", new int[]{this.CPUs}),
                StringColumn.create("Partition", new String[]{this.Partition}),
                StringColumn.create("status", new String[]{this.status}),
                DateColumn.create("start", new LocalDate[]{this.start.toLocalDate()}),
                DateColumn.create("end", new LocalDate[]{this.end.toLocalDate()})
        );
        data.jobs.append(job);
        data.job_map.remove(this.JobId);
    }
}