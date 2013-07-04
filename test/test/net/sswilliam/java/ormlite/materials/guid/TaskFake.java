package test.net.sswilliam.java.ormlite.materials.guid;

import java.util.Date;

import net.sswilliam.java.ormlite.annotation.Column;
import net.sswilliam.java.ormlite.annotation.ID;
import net.sswilliam.java.ormlite.annotation.Table;

@Table(name="task")
public class TaskFake {

	@ID(name="tid")
	public int id;
	
	@Column(name="tname")
	public String taskName;
	
	@Column(name="tpercent")
	public double taskPercent;
	
	@Column(name="tdata")
	public Date createDate;
	
}
