import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeImportance_690 {
    class Employee {
        // It's the unique id of each node;
        // unique id of this employee
        public int id;
        // the importance value of this employee
        public int importance;
        // the id of direct subordinates
        public List<Integer> subordinates;
    }

    Map<Integer, Employee> employeeMap = new HashMap<>();

    public int getImportance(List<Employee> employees, int queryid) {
        for (Employee e : employees) {
            employeeMap.put(e.id, e);
        }
        return dfs(queryid);
    }

    public int dfs(int id) {
        Employee employee = employeeMap.get(id);
        int ans = employee.importance;
        for (Integer subId : employee.subordinates) {
            ans += dfs(subId);
        }
        return ans;
    }
}
