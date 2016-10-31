package at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Logger;

@RunWith(SpringRunner.class)
public abstract class ApplicationTest {
    protected Logger log = Logger.getLogger(this.getClass().getName());
}
