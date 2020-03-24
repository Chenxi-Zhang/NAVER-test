package naver_test.q1;

import java.util.ArrayList;
import java.util.List;

public class Task {

    private String name;

    private boolean executed;

    private List<Task> dependencies;

    public Task (String name, List<Task> dependencies) {
        this.name = name;
        this.dependencies = dependencies;
        this.executed = false;
    }

    public void execute() {
        executed = true;
        System.out.println(name);
    }

    private List<Task> executeWithAllDependencies() {
        List<Task> execOrder = new ArrayList<>();
        for (Task dependency : dependencies) {
            if (!dependency.executed) {
                execOrder.addAll(dependency.executeWithAllDependencies());
            }
        }
        if (!executed) {
            execute();
            execOrder.add(this);
        }
        return execOrder;
    }

    public static List<Task> executeOrder(Task... tasks) {
        List<Task> execOrder = new ArrayList<>(tasks.length);
        for (Task task : tasks) {
            execOrder.addAll(task.executeWithAllDependencies());
        }
        return execOrder;
    }
}
