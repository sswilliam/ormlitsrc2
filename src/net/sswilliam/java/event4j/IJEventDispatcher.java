package net.sswilliam.java.event4j;

public interface IJEventDispatcher {
	
	public void addEventListener(String eventType, Object listener);
	public void removeEventListener(String eventType, Object listener);
	
	public void dispatchEvent(JEvent event);
	
	public boolean willTrigger(String eventType);
	public boolean hasEventListener(String eventType, Object listener);
}
