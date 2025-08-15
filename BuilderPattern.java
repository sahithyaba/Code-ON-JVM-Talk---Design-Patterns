// BuilderPattern.java
class Report {
    String title;
    String[] sections;
    String footer;

    public void display() {
        System.out.println("Title: " + title);
        for (String s : sections) {
            System.out.println("Section: " + s);
        }
        System.out.println("Footer: " + footer);
    }
}

class ReportBuilder {
    private String title;
    private java.util.List<String> sections = new java.util.ArrayList<>();
    private String footer;

    public ReportBuilder title(String t) {
        this.title = t;
        return this;
    }

    public ReportBuilder section(String s) {
        this.sections.add(s);
        return this;
    }

    public ReportBuilder footer(String f) {
        this.footer = f;
        return this;
    }

    public Report build() {
        Report r = new Report();
        r.title = title;
        r.sections = sections.toArray(new String[0]);
        r.footer = footer;
        return r;
    }
}

public class BuilderPattern {
    public static void main(String[] args) {
        Report report = new ReportBuilder()
                .title("Sales Report")
                .section("Q1")
                .section("Q2")
                .footer("Confidential")
                .build();

        report.display();
    }
}
