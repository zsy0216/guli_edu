package com.tassel.temp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zsy
 * @date: 2020/12/5 19:39
 */
public class Main {

    /**
     * 题目：
     * 输出按照优先级从高到低执行的进程名字符串序列，直至结束。（优先级一样按输入顺序执行）
     */
    @Test
    public void main() {
        Process p1 = new Process("p1", 3, 3);
        Process p2 = new Process("p2", 2, 2);
        Process p3 = new Process("p3", 1, 1);

        List<Process> processes = new ArrayList<>();
        processes.add(p1);
        processes.add(p2);
        processes.add(p3);

        while (processes.size() != 0) {
            Process max = getBiggest(processes);
            System.out.println(max);
            Process newMax = max;
            newMax.setRuntime(max.getRuntime() - 1);
            processes.remove(max);
            if (newMax.getRuntime() != 0) {
                newMax.setPriority(newMax.getPriority() - 1);
                processes.add(newMax);
            }
        }
    }

    public Process getBiggest(List<Process> processList) {
        Process max = processList.get(0);
        for (int i = 1; i < processList.size(); i++) {
            if (processList.get(i).getPriority() > max.getPriority()) {
                max = processList.get(i);
            }
        }
        return max;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Process {
    private String name;
    private int priority;
    private int runtime;

    @Override
    public String toString() {
        return name;
    }
}
