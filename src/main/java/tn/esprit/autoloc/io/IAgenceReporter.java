package tn.esprit.autoloc.io;

public interface IAgenceReporter {
    void putHeader(String header);
    void putTarif(String header);
    String getString();
}