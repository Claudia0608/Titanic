package ies.tierno.Bote;

import java.util.Random;

public class Bote implements IBote {
    private final String id;
    private int mujeres, varones, niños;

    public Bote(String id) {
        this.id = id;
    }

    @Override
    public void contarPasajeros() {
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(4000) + 2000);
        } catch (InterruptedException ignored) {}

        int total = random.nextInt(100) + 1;
        mujeres = random.nextInt(total + 1);
        varones = random.nextInt(total - mujeres + 1);
        niños = total - mujeres - varones;
    }

    @Override
    public String obtenerResultado() {
        return String.format("%s,%d,%d,%d", id, mujeres, varones, niños);
    }

    public static void main(String[] args) {
        if (args.length != 1) System.exit(1);

        Bote bote = new Bote(args[0]);
        bote.contarPasajeros();
        System.out.println(bote.obtenerResultado());
    }
}
