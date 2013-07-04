package net.sswilliam.java.event4j;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class JEventDispatcher implements IJEventDispatcher {
	
	private HashMap<String, Vector<IJEventListener>> context;
	
	public JEventDispatcher(){
		context = new HashMap<String, Vector<IJEventListener>>();
	}
	
	@Override
	public void addEventListener(String eventType, Object listener) {
	
//		System.out.println(listener instanceof IJEventListener);
		IJEventListener acutalListener = listener instanceof IJEventListener? (IJEventListener)listener:new JEventListenerAnnotationWrapper(listener);
//		System.out.println("event added "+eventType);
		List<IJEventListener> listenerList;
		synchronized (context) {
			if(context.get(eventType) == null){
				synchronized (Vector.class) {
					if(context.get(eventType) == null){
						context.put(eventType, new Vector<IJEventListener>());
					}
				}
			}
			listenerList = context.get(eventType);
		}
		if(listenerList != null){
			synchronized (listenerList) {
				listenerList.add(acutalListener);
			}
		}else{
			throw new Error("Error occured at add event listener");
		}
	}

	@Override
	public void removeEventListener(String eventType, Object listener) {
		List<IJEventListener> listenerList;
		synchronized (context) {
			listenerList = context.get(eventType);
			if(context.get(eventType) != null){
				synchronized (context.get(eventType)) {
					
				}
			}
		}
		if(listenerList != null){
			synchronized (listenerList) {
				for (Iterator<IJEventListener> iterator = listenerList.iterator(); iterator
						.hasNext();) {
					IJEventListener type = iterator.next();
					if(type == listener){
						iterator.remove();
					}
				}
				if(listenerList.size() == 0){
					synchronized (context) {
						context.remove(eventType);
					}
				}
			}
		}
		
		
	}

	@Override
	public void dispatchEvent(JEvent event) {
		List<IJEventListener> listenerList;
		synchronized (event) {
			listenerList = context.get(event.getType());
		}
		if(listenerList != null){
			synchronized (listenerList) {
				for (Iterator<IJEventListener> iterator =listenerList.iterator(); iterator
						.hasNext();) {
					IJEventListener type = iterator.next();
					type.handleEvent(event);
				}
			}
		}
//		
		
	}

	@Override
	public boolean hasEventListener(String eventType, Object listener) {
		if(context.get(eventType) != null){
			synchronized (context.get(eventType)) {
				for (Iterator<IJEventListener> iterator = context.get(eventType).iterator(); iterator
						.hasNext();) {
					IJEventListener type = iterator.next();
					if(type == listener){
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean willTrigger(String eventType) {

		if(context.get(eventType) != null){
			return true;
		}
		return false;
	}

	
	
}
