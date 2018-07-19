package idv.david.dao;

import java.util.List;

public interface EmployeeDAO_interface {
	// 此介面定義對資料庫的相關存取抽象方法
	void add(Employee employee);
	void update(Employee employee);
	void delete(int empno);
	Employee findByPK(int empno);
	List<Employee> getAll();
}