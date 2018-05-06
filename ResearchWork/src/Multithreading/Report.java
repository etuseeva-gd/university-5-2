package Multithreading;

public class Report {
    private Integer numberOfFirstType = null;
    private Integer numberOfSecondType = null;
    private Integer numberOfNeedToCheck = null;

    public Report() {
        this.numberOfFirstType = 0;
        this.numberOfSecondType = 0;
        this.numberOfNeedToCheck = 0;
    }

    public synchronized void incrementFirstType() {
        this.numberOfFirstType++;
    }

    public synchronized void incrementSecondType() {
        this.numberOfSecondType++;
    }

    public synchronized void incrementNeedToCheck() {
        this.numberOfNeedToCheck++;
    }

    public Integer getNumberOfFirstType() {
        return numberOfFirstType;
    }

    public Integer getNumberOfSecondType() {
        return numberOfSecondType;
    }

    public Integer getNumberOfNeedToCheck() {
        return numberOfNeedToCheck;
    }

    @Override
    public String toString() {
        StringBuilder strReport = new StringBuilder();
        strReport.append("Количество графов:").append("\n")
                .append("Типа 1: ").append(this.getNumberOfFirstType()).append("\n")
                .append("Типа 2: ").append(this.getNumberOfSecondType()).append("\n")
                .append("Прервано: ").append(this.getNumberOfNeedToCheck()).append("\n");
        return String.valueOf(strReport);
    }
}