package test.net.sswilliam.java.ormlite;

import java.util.Date;

import net.sswilliam.java.ormlite.DBCreator;
import net.sswilliam.java.ormlite.DBInstance;

import org.junit.Assert;

import test.net.sswilliam.java.ormlite.materials.TestConstants;
import test.net.sswilliam.java.ormlite.materials.guid.Category;
import test.net.sswilliam.java.ormlite.materials.guid.GCategory;
import test.net.sswilliam.java.ormlite.materials.guid.GTask;
import test.net.sswilliam.java.ormlite.materials.guid.GThread;
import test.net.sswilliam.java.ormlite.materials.guid.Task;
import test.net.sswilliam.java.ormlite.materials.guid.Thread;

public class DBInstanceSelectEnvConfig {

	/**
	 * @param args
	 */

	public static final String autoIncreaseDBPath = "net\\sswilliam\\java\\ormlite\\DBSelectorTest\\test.db";
	public static final String guidDBPath = "net\\sswilliam\\java\\ormlite\\DBSelectorTest\\testguid.db";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBCreator creator = new DBCreator(
				autoIncreaseDBPath, 
				new Class[]{
						Task.class,
						Thread.class,
						Category.class
				}
		);
		DBCreator creator2 = new DBCreator(
				guidDBPath, 
				new Class[]{
						GTask.class,
						GThread.class,
						GCategory.class
				}
		);
		try {

			creator.create();
			creator2.create();
			DBInstance instance = new DBInstance(autoIncreaseDBPath);
			DBInstance instance2 = new DBInstance(guidDBPath);
			instance.connect();
			instance2.connect();
			
			
			Task task = new Task();
			GTask task2 = new GTask();
			
			for(int i = 0;i<100;i++){
				task.taskName = task2.taskName = "task"+i;
				task.taskDescription = task2.taskDescription = "task "+i+" desc";
				task.taskState = task2.taskState = i%3;
				task.taskCreateDate=task2.taskCreateDate = new Date(new Date().getTime()+i*1000);
				
				instance.insertObject(task);
				instance2.insertObject(task2);
				
				task.taskName = null;
				task2.taskName = null;
				instance.insertObject(task);
				instance2.insertObject(task2);
				
				task.taskDescription = null;
				task2.taskDescription = null;
				instance.insertObject(task);
				instance2.insertObject(task2);
				
				task.taskState = -1;
				task2.taskState = -1;
				instance.insertObject(task);
				instance2.insertObject(task2);
			}
			instance.close();
			instance2.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
			
			// TODO: handle exception
		}
	}

}
