package at.dev4fun.a2_plf_preperation.io;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.stream.Collectors;

import at.dev4fun.a2_plf_preperation.beans.ThatThing;
import at.dev4fun.a2_plf_preperation.beans.Thing;
import at.dev4fun.a2_plf_preperation.beans.ThisThing;

public class IOHandler {

    private static Context ctx;

    public static void setCtx(Context ctx) {
        IOHandler.ctx = ctx;
    }

    public static List<Thing> getThings() throws IOException {
        List<Thing> things = null;

        InputStream is = ctx.getResources().getAssets().open("things.csv");
        things = new BufferedReader(new InputStreamReader(is))
                .lines()
                .skip(1)
                .map(line -> {
                    if(line.split(",")[0].equalsIgnoreCase("thing1")){
                        return new ThatThing(line);
                    }

                    return new ThisThing(line);
                })
                .collect(Collectors.toList());

        return things;
    }

    public static void Test(){
        try {
            ObjectInputStream ois = new ObjectInputStream(ctx.getResources().getAssets().open("test.ser"));
            List<Integer> test = (List<Integer>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
