package models;

import java.util.*;

public interface DBActions<T> {
	
	List<T> getAll();
	Boolean postData(T obj);
	Boolean updateData(T obj);
	Boolean deleteData(T obj);
	
}
