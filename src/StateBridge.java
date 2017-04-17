import com.google.gson.Gson;

import java.io.*;

public class StateBridge {

    public Board parse(){
        Gson gson = new Gson();
        Board grid = new Board();
        try{
            grid = gson.fromJson(new FileReader("src/test.json"), Board.class);
            return grid;

        }catch(IOException e)
        {
            System.out.println("File not found");
        }

        //Board grid = gson.fromJson(new InputStreamReader(System.in), Board.class);
        return grid;
    }

    public void convertNsend(int move){
        Gson gson = new Gson();
        OutputStreamWriter toilet = new OutputStreamWriter(System.out);
        try
        {
            toilet.write(gson.toJson(Integer.toString(move)));
            toilet.flush();
        }
        catch(IOException e)
        {
            System.out.println("It's clogged my dude");
        }
    }
}