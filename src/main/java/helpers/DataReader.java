package helpers;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

public class DataReader {
    public Map<String, Map<String, String>> yamlReader(String fileName) {
        File yamlFileReader = new File("src//main//resources//data//" + fileName);
        FileInputStream fileInput = null;

        try {
            fileInput = new FileInputStream(yamlFileReader);
        } catch (FileNotFoundException var6) {
            var6.printStackTrace();
        }

        Yaml yaml = new Yaml();
        Map<String, Map<String, String>> yamlParsers = (Map)yaml.load(fileInput);
        return yamlParsers;
    }
}