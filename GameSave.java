import java.io.*;

/**
 * Created by zam on 12/3/2014.
 */
public class GameSave {

    public void saveTerrain(String file_path, int[][] terrain) {

        try {
            File file = new File("src/resources/terrain/" + file_path + ".txt");

            if (file.createNewFile()) {
                System.out.println("Terrain File Created..");
            }
            else {
                System.out.println("Terrain File Exists..");
            }

            System.out.println("Attempting To Write to file....");
            FileWriter writer = new FileWriter(file);

            for (int[] rows : terrain) {
                writer.write(implode(",", rows) + "\n");
                System.out.println("Writting to file....");
            }

            writer.close();
            System.out.println("Closing File...");
            System.out.println("Absolute Path: " + file.getAbsolutePath());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String implode(String glue, int[] strArray)
    {
        String ret = "";
        for(int i=0;i<strArray.length;i++)
        {
            ret += (i == strArray.length - 1) ? strArray[i] : strArray[i] + glue;
        }
        return ret;
    }

}
