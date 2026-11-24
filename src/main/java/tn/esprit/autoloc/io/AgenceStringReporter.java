package tn.esprit.autoloc.io;

public class AgenceStringReporter implements IAgenceReporter {
    StringBuilder builder = new StringBuilder();
    @Override
    public void putHeader(String header) {
        builder.append(header);
    }

    @Override
    public void putTarif(String header) {
        builder.append(header);
    }

    @Override
    public String getString() {
        return builder.toString();
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
