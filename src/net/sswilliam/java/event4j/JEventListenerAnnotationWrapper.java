package net.sswilliam.java.event4j;

import java.lang.reflect.Method;


public class JEventListenerAnnotationWrapper implements IJEventListener {

	private Object core;
	public JEventListenerAnnotationWrapper(Object core){
		this.core = core;
	}
	
	@Override
	public void handleEvent(JEvent event) {
		Class<?> targetClass = core.getClass();
		Method[] methods = targetClass.getDeclaredMethods();
		for(int i = 0;i<methods.length;i++){
			Method method = methods[i];
			if(method.isAnnotationPresent(JEventHandler.class)){
				String[] events = method.getAnnotation(JEventHandler.class).value();
				for(int j = 0;j<events.length;j++){
					if(events[j].equals(event.getType())){
						Class<?>[] parameters = method.getParameterTypes();
						if(parameters.length > 1){
							System.out.println("only one parameter is allowed");
						}else{
							if(parameters.length == 0){
								try {
									method.invoke(core);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							if(parameters.length == 1){
								if(isJEvent(parameters[0])){
									try {
										method.invoke(core, event);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}else{
									System.out.println("parameter should be an instance of the JEvent");
								}
							}
						}
					}
				}
				
			}
			
		}
	}
	public boolean isJEvent(Class<?> clazz){
		if(clazz.getName().equals("java.lang.Object")){
			return false;
		}
		if(clazz.getName().equals("net.sswilliam.event4j.JEvent")){
			return true;
		}
		return isJEvent(clazz.getSuperclass());
	}

}
