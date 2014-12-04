import java.io.*;

/**
 * Created by zam on 12/2/2014.
 */
public class GameOpen {

    public int[][] arrayItems = new int[50][50];

    public int[][] openTerrain(String file_path) {

        int numberOfLines = 10;
        String textData;

        int i = 0;
        int c = 0;

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = this.OpenFile(file_path);

            String line = null;

            while((line = bufferedReader.readLine()) != null) {
                String[] parseTextData = line.split(",");
                for (String s : parseTextData) {
                    arrayItems[i][c] = Integer.parseInt(s);
                    c++;
                }
                c = 0;
                i++;
            }

            return arrayItems;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayItems;

    }

    public BufferedReader OpenFile(String file_path) throws IOException {
        InputStream file = getClass().getResourceAsStream("resources/terrain/" + file_path + ".txt");
        InputStreamReader isr = new InputStreamReader(file);
        //FileReader fileReader = new FileReader(isr);
        BufferedReader bufferedReader = new BufferedReader(isr);
        return bufferedReader;
    }
}
