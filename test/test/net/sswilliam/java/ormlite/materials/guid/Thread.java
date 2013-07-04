package test.net.sswilliam.java.ormlite.materials.guid;

import java.util.Date;

import net.sswilliam.java.ormlite.annotation.Column;
import net.sswilliam.java.ormlite.annotation.ID;
import net.sswilliam.java.ormlite.annotation.Table;

@Table(name = "Thread")
public class Thread {

	@ID(name = "thread_id")
	public int threadId;
	
	@Column(name = "task_id")
	public int taskId;
	
	@Column(name = "begin_data")
	public Date beginDate;
	
	@Column(name = "end_date")
	public Date endDate;
	
	@Column(name = "thread_note")
	public String threadNote;
}
