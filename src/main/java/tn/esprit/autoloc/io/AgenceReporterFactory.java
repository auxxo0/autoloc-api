package tn.esprit.autoloc.io;

public class AgenceReporterFactory {
    public static IAgenceReporter createReport(Object caller) {
        return caller.getClass().isAnnotationPresent(HybridReport.class) ?
                new AgenceHybridReporter() : new AgenceStringReporter();
    }
}
