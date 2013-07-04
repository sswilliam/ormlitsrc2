package test.net.sswilliam.java.ormlite;

import java.util.Date;

import net.sswilliam.java.ormlite.DBCreator;
import net.sswilliam.java.ormlite.DBInstance;

import org.junit.Assert;

import test.net.sswilliam.java.ormlite.materials.guid.Category;
import test.net.sswilliam.java.ormlite.materials.guid.GCategory;
import test.net.sswilliam.java.ormlite.materials.guid.GTask;
import test.net.sswilliam.java.ormlite.materials.guid.GThread;
import test.net.sswilliam.java.ormlite.materials.guid.Task;
import test.net.sswilliam.java.ormlite.materials.guid.Thread;

public class DBInstanceDeleteEnvConfig {

	/**
	 * @param args
	 */
	public static final String autoIncreaseDBPath = "net\\sswilliam\\java\\ormlite\\DBDeleteTest\\delete.db";
	public static final String guidDBPath = "net\\sswilliam\\java\\ormlite\\DBDeleteTest\\deleteguid.db";

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
			
			String sql1 = "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES (1, 'Task0', 'Task1 Desc', 1, '2013-05-21 14:57:01.042' ,1);\n";
			sql1 += "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES (2, 'Task0', 'Task0 Desc', 1, '2013-05-21 14:58:01.042' ,2);\n";
			sql1 +=  "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES (3, 'Task1', 'Task0 Desc', 2, '2013-05-21 14:59:01.042' ,5);\n";
			sql1 +=  "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES (4, 'Task1', 'Task0 Desc', 1, '2013-05-21 14:00:01.042' ,7);\n";
			sql1 +=  "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES (5, 'Task1', 'Task0 Desc', 2, '2013-05-21 14:01:01.042' ,8);";
			
			instance.executeNoQuery(sql1);
			
			String sql2 = "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES ('0f116722-0be5-4c1d-8caa-1df7f3e6c4e7', 'Task0', 'Task1 Desc', 1, '2013-05-21 14:57:01.042' ,1);\n";
			sql2 += "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES ('96742f9b-6678-4ca3-9279-8eaf90f92e3f', 'Task0', 'Task0 Desc', 1, '2013-05-21 14:58:01.042' ,2);\n";
			sql2 +=  "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES ('3d14dc93-0c13-4ddd-aace-0cfa6842c426', 'Task1', 'Task0 Desc', 2, '2013-05-21 14:59:01.042' ,5);\n";
			sql2 +=  "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES ('b6828194-5a19-410a-bcb9-7650a1a87c78', 'Task1', 'Task0 Desc', 1, '2013-05-21 14:00:01.042' ,7);\n";
			sql2 +=  "INSERT INTO Task (task_id, task_name, task_description, task_state, task_create_date,category_id) VALUES ('ed8a59a1-5bc5-47ec-86bd-6af6a2312599', 'Task1', 'Task0 Desc', 2, '2013-05-21 14:01:01.042' ,8);";
			
			instance2.executeNoQuery(sql2);
			
			instance.close();
			instance2.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
			
			// TODO: handle exception
		}

	}

}
