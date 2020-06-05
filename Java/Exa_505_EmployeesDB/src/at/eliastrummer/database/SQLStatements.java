package at.eliastrummer.database;

public class SQLStatements {

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
