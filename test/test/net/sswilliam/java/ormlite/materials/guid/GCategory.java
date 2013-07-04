package test.net.sswilliam.java.ormlite.materials.guid;

import net.sswilliam.java.ormlite.annotation.Column;
import net.sswilliam.java.ormlite.annotation.ID;
import net.sswilliam.java.ormlite.annotation.IDGenerateStrategy;
import net.sswilliam.java.ormlite.annotation.Table;

@Table(name = "Category")
public class GCategory {

	@ID(strategy = IDGenerateStrategy.GUID,name = "category_id")
	public String categoryId;
	
	@Column(name = "category_name")
	public String categoryName;
	
	@Column(name = "category_description")
	public String categoryDescriptionString;
}
