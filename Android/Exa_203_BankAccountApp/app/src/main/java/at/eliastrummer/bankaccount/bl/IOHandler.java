package at.eliastrummer.bankaccount.bl;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class IOHandler {

    private static Context ctx;

    public static void init(Context ctx){
        IOHandler.ctx = ctx;
    }

    public static List<Account> getAccounts(){

        try {
            List<Account> result = new BufferedReader(new InputStreamReader(ctx.getAssets().open("account_data.csv")))
                            .lines()
                            .skip(1)
                            .map(line -> {
                                if(line.split(",")[1].toUpperCase().equals("GIRO")){
                                    return new GiroAccount(s);
                                }else{
                                    return new StudentAccount(s);
                                }
                            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
