package ex.g1.cau4;

class Employee{
    private final String fullName;
    private final boolean isManager;

    public Employee(String fullName, boolean isManager) {
        this.fullName = fullName;
        this.isManager = isManager;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean isManager() {
        return isManager;
    }
};
