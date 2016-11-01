package at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.component.simulator;

/**
 * Simulates vehicle traces by pushing sensor informations to an endpoint (e.g. cloudlets).
 * Assumes that vehicle traces are already persisted and reads them.
 */
public interface ISimulator {

    /**
     * Start vehicle simulation
     */
    void startSimulation();

    /**
     * Stops the simulation
     */
    void stopSimulation();
}
