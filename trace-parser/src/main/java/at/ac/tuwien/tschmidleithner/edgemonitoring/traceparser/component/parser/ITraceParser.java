package at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.component.parser;


import at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.component.parser.exception.ParserException;

import java.io.InputStream;

/**
 * Interface for parsing vehicle data
 */
public interface ITraceParser {

    /**
     * Parses vehicles and timesteps from an input stream and persists them.
     *
     * @param inputStream the stream that should be parsed
     * @throws ParserException is thrown whenever parsing problems occur
     */
    void parse(InputStream inputStream) throws ParserException;
}
