package net.sswilliam.java.event4j;

public class JEvent {
	private String eventType;
	private Object data;
	public JEvent (String eventType, Object data){
		this.eventType = eventType;
		this.data = data;
	}
	public String getType(){
		return this.eventType;
	}
	public Object getData(){
		return this.data;
	}

}
