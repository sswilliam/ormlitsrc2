package test.net.sswilliam.java.ormlite.materials.guid;

import java.util.Date;

import net.sswilliam.java.ormlite.annotation.Column;
import net.sswilliam.java.ormlite.annotation.ID;
import net.sswilliam.java.ormlite.annotation.IDGenerateStrategy;
import net.sswilliam.java.ormlite.annotation.Table;

@Table(name="Task")
public class GTask {
	
	@ID(strategy = IDGenerateStrategy.GUID,name="task_id")
	public String id;
	
	@Column(name="task_name")
	public String taskName;
	
	@Column(name="task_description")
	public String taskDescription;
	
	@Column(name="task_state")
	public int taskState;
	
	@Column(name = "task_create_date")
	public Date taskCreateDate;
	
	@Column(name = "category_id")
	public String categoryId;
	
}
