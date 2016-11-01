package at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.component.parser.sumo;

import at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain.Data;
import at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain.Vehicle;
import at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.component.parser.exception.ParserException;
import at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.component.simulator.ISimulator;
import at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.service.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.util.Iterator;

@Component
public class SumoTraceParser implements ISumoTraceParser {

    private IVehicleService vehicleService;
    private ISimulator simulator;

    /**
     * SUMO XML trace schema
     */
    private static String XPATH_TIMESTEP_VEHICLES = "vehicle";
    private static String XML_TIMESTEP_ATTRIBUTE = "time";

    private static final String XML_EXPORT = "fcd-export";
    private static final String XML_TIMESTEP = "timestep";
    private static final String XML_TIMESTEP_TIME_ATTRIBUTE = "time";
    private static final String XML_TIMESTEP_VEHICLE = "vehicle";

    /**
     * Vehicle attributes
     */
    private static String XML_VEHICLE_ID_ATTRIBUTE = "id";
    private static String XML_VEHICLE_LONGITUDE_ATTRIBUTE = "x";
    private static String XML_VEHICLE_LATITUDE_ATTRIBUTE = "y";
    private static String XML_VEHICLE_ANGLE_ATTRIBUTE = "angle";
    private static String XML_VEHICLE_SPEED_ATTRIBUTE = "speed";

    @Autowired
    public SumoTraceParser(IVehicleService vehicleService, ISimulator simulator) {
        this.vehicleService = vehicleService;
        this.simulator = simulator;
    }

    @Override
    public void parse(InputStream inputStream) throws ParserException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();

        XMLEventReader xmlEventReader;
        try {
            xmlEventReader = xmlInputFactory.createXMLEventReader(inputStream);
        } catch (XMLStreamException e) {
            throw new ParserException(e);
        }

        Double currentTimestep = 0.0;
        while (xmlEventReader.hasNext()) {
            XMLEvent xmlEvent;

            try {
                xmlEvent = xmlEventReader.nextEvent();
            } catch (XMLStreamException e) {
                throw new ParserException(e);
            }

            if (xmlEvent.isStartElement()) {
                StartElement startElement = xmlEvent.asStartElement();

                if (startElement.getName().getLocalPart() == XML_TIMESTEP) {
                    // found timestep element

                    // Read the attributes from this tag and cache time attribute
                    Iterator<Attribute> attributes = startElement.getAttributes();
                    while (attributes.hasNext()) {
                        Attribute attribute = attributes.next();
                        if (attribute.getName().toString().equals(XML_TIMESTEP_TIME_ATTRIBUTE)) {
                            currentTimestep = Double.parseDouble(attribute.getValue());
                        }
                    }
                } else if (startElement.getName().getLocalPart() == XML_TIMESTEP_VEHICLE) {
                    // found vehicle element

                    Data data = new Data();
                    Vehicle vehicle = new Vehicle();

                    // Read the attributes from this tag and add properties
                    // to our data object
                    Iterator<Attribute> attributes = startElement.getAttributes();
                    while (attributes.hasNext()) {
                        Attribute attribute = attributes.next();
                        String attr = attribute.getName().toString();
                        if (attr.equals(XML_VEHICLE_ID_ATTRIBUTE)) {
                            vehicle.setId(Long.parseLong(attribute.getValue()));
                        } else if (attr.equals(XML_VEHICLE_LATITUDE_ATTRIBUTE)) {
                            String latitude = attribute.getValue();
                            data.setLatitude(Double.parseDouble(latitude));
                        } else if (attr.equals(XML_VEHICLE_LONGITUDE_ATTRIBUTE)) {
                            String longitude = attribute.getValue();
                            data.setLongitude(Double.parseDouble(longitude));
                        } else if (attr.equals(XML_VEHICLE_SPEED_ATTRIBUTE)) {
                            String speed = attribute.getValue();
                            data.setSpeed(Float.parseFloat(speed));
                        }
                    }

                    // persist vehicle and timestep
                    vehicleService.addTimestepEntry(vehicle.getId(), currentTimestep, data.getLatitude(), data.getLongitude(), data.getSpeed());
                }
            }
        }

        // finished
        simulator.startSimulation();
    }
}
