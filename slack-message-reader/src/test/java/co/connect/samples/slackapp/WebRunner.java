package co.connect.samples.slackapp;

import java.io.File;
import java.io.IOException;
import org.junit.Test;
import bathe.BatheBooter;

public class WebRunner {

    @Test
    public void run() throws IOException {
        if (!new File("src/test/resources").exists()) {
            throw new RuntimeException("Please ensure this test is run in the home directory of the project.");
        }

        new BatheBooter().runWithLoader(getClass().getClassLoader(), null, WebAppRunner.class.getName(),
                new String[]{"-Pclasspath:/war.properties"});
    }
}
