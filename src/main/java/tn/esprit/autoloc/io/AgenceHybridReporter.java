package tn.esprit.autoloc.io;

public class AgenceHybridReporter implements IAgenceReporter {
    StringBuilder builder = new StringBuilder();
    @Override
    public void putHeader(String header) {
        System.out.print(header);
    }

    @Override
    public void putTarif(String header) {
        builder.append(header);
    }

    @Override
    public String getString() {
        return builder.toString();
    }
}
