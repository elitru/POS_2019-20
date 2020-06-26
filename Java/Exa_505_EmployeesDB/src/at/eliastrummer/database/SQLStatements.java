package at.eliastrummer.database;

public class SQLStatements {

    public static final String GET_ENTRIES = "select *, t.from_date as \"title_from\", t.to_date as \"title_to\", s.from_date as \"sal_from\", s.to_date as \"sal_to\", de.from_date as \"dep_from\", de.to_date as \"dep_to\""
            + "        from employees e\n"
            + "        inner join dept_emp de on de.emp_no = e.emp_no\n"
            + "        inner join departments d on d.dept_no = de.dept_no\n"
            + "        inner join salaries s on s.emp_no = e.emp_no\n"
            + "        inner join titles t on t.emp_no = e.emp_no\n"
            + "        where (\n"
            + "        		(e.gender = 'F' and {female})\n"
            + "        	or \n"
            + "        		(e.gender = 'M' and {male})\n"
            + "        	  )\n"
            + "	and\n"
            + "		e.birth_date < to_date('{birthdate}', 'MM-DD-YYYY')\n"
            + "	and\n"
            + "		d.dept_name like '%{department}%'"
            + "order by last_name ASC "
            + "limit {limit} "
            + "offset {offset};";

    public static final String GET_DEPARTMENTS = "select * from departments;";
    
    public static final String GET_MANAGERS_FOR_DEPARTMENT  = "select *\n"
            + "from employees e\n"
            + "inner join dept_manager dm on dm.emp_no = e.emp_no\n"
            + "inner join departments d on d.dept_no = dm.dept_no\n"
            + "where d.dept_name like '%{department}%';";

    public static final String UPDATE_EMPLOYEE = "UPDATE employees SET first_name='%s', last_name='%s', hire_date=TO_DATE('%s', 'DD.MM.YYYY') WHERE emp_no=%d;";
    
    /*
    
        select *, (
	select count(*)
	from dept_manager dm1
	where dm1.emp_no = e.emp_no
		and dm1.dept_no = d.dept_no
		and dm1.to_date = TO_DATE('01-01-9999', 'DD-MM-YYYY') 
        ) as "is_manager"
        from employees e
        inner join dept_manager dm on dm.emp_no = e.emp_no
        inner join dept_emp de on de.emp_no = e.emp_no
        inner join departments d on d.dept_no = dm.dept_no or d.dept_no = de.dept_no
        inner join salaries s on s.emp_no = e.emp_no
        inner join titles t on t.emp_no = e.emp_no
        where (e.gender = 'F'
		or e.gender = 'M')
	and
		e.birth_date < to_date('01-01-1970', 'MM-DD-YYYY')
	and
		d.dept_name = 'Marketing';
     */
}
