package helpers;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class Report
{
    /*
     * main method is called by Maven run during verify goal, to initiate the Cucumber reporting.
     *
     */
    public static void main(String args[]){
        GenerateMasterthoughtReport();
    }
    /*
     * GenerateMasterthoughtReport - method outputs the Cucumber based reporting under Report folder in target.
     * target\Report\cucumber-html-reports
     * overview-features.html - main report file having links and details of whole test run.
     */
    public static void GenerateMasterthoughtReport( )
    {
        try
        {
            File reportOutputDirectory = new File("target/Report");
            File dir = new File("target/cucumber-parallel");
            File[] txts = dir.listFiles(new FilenameFilter()
            {
                public boolean accept(File dir, String name)
                {
                    if(name.endsWith(".json"))
                        return true;
                    return false;
                }
            });
            List<String> list = new ArrayList<String>();
            for(File path:txts)
            {
                list.add(path.toString());
            }
            String buildProject = "mlctesting";
            boolean parallelTesting = true;
            Configuration configuration = new Configuration(reportOutputDirectory, buildProject);
            configuration.setParallelTesting(parallelTesting);
            ReportBuilder reportBuilder = new ReportBuilder(list, configuration);
            reportBuilder.generateReports();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}