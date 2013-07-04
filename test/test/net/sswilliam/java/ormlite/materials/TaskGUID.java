package test.net.sswilliam.java.ormlite.materials;

import java.util.Date;

import net.sswilliam.java.ormlite.annotation.Column;
import net.sswilliam.java.ormlite.annotation.ID;
import net.sswilliam.java.ormlite.annotation.IDGenerateStrategy;
import net.sswilliam.java.ormlite.annotation.Table;

@Table(name="Task")
public class TaskGUID {

	@ID(strategy = IDGenerateStrategy.GUID,name="task_id")
	public int id;
	
	@Column(name="task_name")
	public String taskName;
	
	@Column(name="task_description")
	public String taskDescription;
	
	@Column(name="task_state")
	public int taskState;
	
	@Column(name = "task_create_date")
	public Date taskCreateDate;
	
	@Column(name = "category_id")
	public int categoryId;
	
}
