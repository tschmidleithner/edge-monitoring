package at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.component.parser.sumo;

import at.ac.tuwien.tschmidleithner.edgemonitoring.shared.domain.Vehicle;
import at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.component.parser.exception.ParserException;
import at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser.service.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.util.Iterator;

@Component
public class SumoTraceParser implements ISumoTraceParser {

    private IVehicleService vehicleService;

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
    public SumoTraceParser(IVehicleService vehicleService) {
        this.vehicleService = vehicleService;
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

        while (xmlEventReader.hasNext()) {
            Vehicle vehicle = new Vehicle();
            XMLEvent xmlEvent;

            try {
                xmlEvent = xmlEventReader.nextEvent();
            } catch (XMLStreamException e) {
                throw new ParserException(e);
            }

            /*
            TODO
            if (xmlEvent.isStartElement()) {
                StartElement startElement = xmlEvent.asStartElement();
                // If we have an item element, we create a new item
                if (startElement.getName().getLocalPart() == XML_TIMESTEP) {
                    vehicles = new ArrayList<>();
                    timestep = new Timestep<>();
                    // Read the attributes from this tag and add the time
                    // attribute to our object
                    Iterator<Attribute> attributes = startElement.getAttributes();
                    while (attributes.hasNext()) {
                        Attribute attribute = attributes.next();
                        if (attribute.getName().toString().equals(XML_TIMESTEP_TIME_ATTRIBUTE)) {
                            timestep.setTime(Double.parseDouble(attribute.getValue()));
                        }

                    }
                } else if (startElement.getName().getLocalPart() == XML_TIMESTEP_VEHICLE) {
                    Vehicle vehicle = new Vehicle();
                    GPSLocation location = new GPSLocation();
                    location.setVehicle(vehicle);

                    Iterator<Attribute> attributes = startElement.getAttributes();
                    while (attributes.hasNext()) {
                        Attribute attribute = attributes.next();
                        String attr = attribute.getName().toString();
                        if (attr.equals(XML_VEHICLE_ID_ATTRIBUTE)) {
                            vehicle.setId(Long.parseLong(attribute.getValue()));
                        } else if (attr.equals(XML_VEHICLE_LATITUDE_ATTRIBUTE)) {
                            String latitude = attribute.getValue();
                            location.setLatitude(Double.parseDouble(latitude));
                        } else if (attr.equals(XML_VEHICLE_LONGITUDE_ATTRIBUTE)) {
                            String longitude = attribute.getValue();
                            location.setLongitude(Double.parseDouble(longitude));
                        }
                    }
                    vehicle.setLocation(location);
                    vehicles.add(vehicle);
                }
            }

            // If we reach the end of an item element, we add it to the list
            if (xmlEvent.isEndElement()) {
                EndElement endElement = xmlEvent.asEndElement();
                if (endElement.getName().getLocalPart() == (XML_TIMESTEP) && timestep != null) {
                    timestep.setVehicles(vehicles);
                    timesteps.add(timestep);
                    System.out.println(timestep.getVehicles().size());
                }
            }*/

            vehicleService.save(vehicle);
        }
    }
}