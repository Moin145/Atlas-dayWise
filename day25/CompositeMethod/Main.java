package CompositeMethod;

public class Main {
    public static void main(String[] args) {
        System.out.println("Composite Method DP - Structural DP");

        // Create leaf departments
        Company softwareCompany = new Software(1, "Software Development");
        Company hrDepartment = new HR(2, "Human Resources");

        // Create composite head
        CompanyHead companyHead = new CompanyHead(3, "ABC Company");

        // Add leaves to composite
        companyHead.addDepartment(softwareCompany);
        companyHead.addDepartment(hrDepartment);

        // Display hierarchy
        companyHead.displayName();
    }
}