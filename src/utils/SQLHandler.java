package utils;

import org.hibernate.Session;

public interface SQLHandler {
	public void invoke(Session session);
}
