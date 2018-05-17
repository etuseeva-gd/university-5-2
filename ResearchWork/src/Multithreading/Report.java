package Multithreading;

import java.util.ArrayList;
import java.util.List;

public class Report {
    private Integer numberOfFirstType;
    private Integer numberOfSecondType;
    private Integer numberOfCurrentGraph;

    private Long numberOfTrianglesOfFirstType;
    private Long numberOfTrianglesOfSecondType;

    private List<String> notCompleteChetwyndHilton = null;

    public Report() {
        this.numberOfFirstType = 0;
        this.numberOfSecondType = 0;
        this.numberOfCurrentGraph = 0;

        this.numberOfTrianglesOfFirstType = 0L;
        this.numberOfTrianglesOfSecondType = 0L;
        this.notCompleteChetwyndHilton = new ArrayList<>();
    }

    public synchronized void incrementFirstType() {
        this.numberOfFirstType++;
    }

    public synchronized void incrementSecondType() {
        this.numberOfSecondType++;
    }

    public synchronized void addTrianglesOfFirstType(int numberOfTriangle) {
        this.numberOfTrianglesOfFirstType += numberOfTriangle;
    }

    public synchronized void addTrianglesOfSecondType(int numberOfTriangle) {
        this.numberOfTrianglesOfSecondType += numberOfTriangle;
    }

    public synchronized void addNotCompleteChetwyndHilton(String strGraphView) {
        this.notCompleteChetwyndHilton.add(strGraphView);
    }

    public synchronized void incrementCurrentGraph() {
        this.numberOfCurrentGraph++;
        System.out.println(this.numberOfCurrentGraph);
    }

    public Integer getNumberOfFirstType() {
        return numberOfFirstType;
    }

    public Integer getNumberOfSecondType() {
        return numberOfSecondType;
    }

    public List<String> getNotCompleteChetwyndHilton() {
        return notCompleteChetwyndHilton;
    }

    public Long getNumberOfTrianglesOfFirstType() {
        return numberOfTrianglesOfFirstType;
    }

    public Long getNumberOfTrianglesOfSecondType() {
        return numberOfTrianglesOfSecondType;
    }

    @Override
    public String toString() {
        StringBuilder strReport = new StringBuilder();
        strReport.append("Количество графов:").append("\n")
                .append("  Класса 1: ").append(this.getNumberOfFirstType()).append("\n")
                .append("  Класса 2: ").append(this.getNumberOfSecondType()).append("\n")
                .append("Кол-во треугольнов для:").append("\n")
                .append("  Класса 1: ").append(this.getNumberOfTrianglesOfFirstType()).append("\n")
                .append("  Класса 2: ").append(this.getNumberOfTrianglesOfSecondType()).append("\n")
                .append("Кол-во графов для который не выполняется гипотеза = ").append(this.getNotCompleteChetwyndHilton().size()).append("\n");
        return String.valueOf(strReport);
    }
}