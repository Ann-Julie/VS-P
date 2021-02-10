import com.google.gson.Gson;

public class Test {
    public static void main(String[] args){

        System.out.println("moin");


        Gson gson = new Gson();
        Player player = new Player();
        String spieler = gson.toJson(player);
        System.out.println(spieler);

    }
}
