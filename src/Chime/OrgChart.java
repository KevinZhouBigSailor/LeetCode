package Chime;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a string with employee id, name and manager name, print out all of them in the tree format.
 * <p>
 * Input:
 * "1:Max:4, 4:Ann:0, 2:Jim:4, 3:Tom:1"
 * 1 -> employee id
 * Max -> name
 * 4 -> manager id
 * <p>
 * Output:
 * Ann
 * - Max
 * -- Tom
 * - Jim
 */

public class OrgChart {
    public String printOrgChart(String input) {

        // Pre setting
        String[] orgs = input.split(",");
        int employeeNumber = orgs.length;
        ArrayList[] graph = new ArrayList[employeeNumber + 1];
        /*int[] degree = new int[employeeNumber + 1];
        Queue<Employee> queue = new LinkedList();*/
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < employeeNumber + 1; i++) {
            graph[i] = new ArrayList<Employee>();
        }
        List<Employee> employeeList = new ArrayList<>();
        for (String org : orgs) {
            employeeList.add(convertToEmployee(org));
        }
        // Generate topo graph
        for (int i = 0; i < employeeNumber; i++) {
            Employee curEmployee = employeeList.get(i);
            // if (curEmployee.getManagerId() != 0) degree[curEmployee.getId()]++;
            graph[curEmployee.getManagerId()].add(curEmployee);
        }

        /*for (int i = 0; i < employeeNumber; i++) {
            Employee curEmployee = employeeList.get(i);
            if (degree[curEmployee.getId()] == 0) queue.add(curEmployee);
        }*/
        // bfs
        /*while (queue.size() != 0) {
            Employee employee = queue.poll();
            sb.append("-" + employee.getName());
            for (int i = 0; i < graph[employee.getId()].size(); i++) {
                Employee pointer = (Employee) graph[employee.getId()].get(i);
                degree[pointer.getId()]--;
                if (degree[pointer.getId()] == 0)
                    queue.add(pointer);
            }
        }
        return sb.toString();*/
        // dfs
        /*List<List<Employee>> list = new ArrayList<>();
        dfs(list, new ArrayList<Employee>(), graph, 0);
        for (int i = 0; i < list.size(); i++) {
            List<Employee> employees = list.get(i);
            for (Employee employee : employees) {
                System.out.println(employee.getName());
            }
        }*/
        // preorder
        Employee Ann = new Employee(4, 0, "Ann");
        preorderTravese(graph, 0, Ann, sb);
        return sb.toString();
    }

    private void preorderTravese(ArrayList[] graph, int indent, Employee employee, StringBuilder sb) {
        sb.append(indent);
        sb.append(employee.getName());
        for (int i = 0; i < graph[employee.getId()].size(); i++) {
            Employee nextEmployee = (Employee) graph[employee.getId()].get(i);
            preorderTravese(graph, indent + 1, nextEmployee, sb);
        }
    }

    private Employee convertToEmployee(String org) {
        String[] attributes = org.split(":");
        return new Employee(Integer.valueOf(
                attributes[0]), Integer.valueOf(attributes[2]), attributes[1]);
    }

    private void dfs(List<List<Employee>> list, List<Employee> tempList, ArrayList[] graph, int employeeId) {
        if (graph[employeeId].size() == 0) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < graph[employeeId].size(); i++) {
                Employee employee = (Employee) graph[employeeId].get(i);
                tempList.add(employee);
                dfs(list, tempList, graph, employee.getId());
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Employee max = new Employee(1, 4, "Max");
        Employee Ann = new Employee(4, 0, "Ann");
        Employee Jim = new Employee(2, 1, "Jim");
        Employee Tom = new Employee(1, 3, "Tom");
        OrgChart instance = new OrgChart();
        System.out.println(instance.printOrgChart("1:Max:4,4:Ann:0,2:Jim:4,3:Tom:1"));
    }
}

class Employee {
    int id;
    int managerId;
    String name;

    public Employee(int id, int managerId, String name) {
        this.id = id;
        this.managerId = managerId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
