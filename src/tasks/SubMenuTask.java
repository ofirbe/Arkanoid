package tasks;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.HashMap;
import animation.AnimationRunner;
import animation.Menu;
import animation.MenuAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameFlow;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the SubMenuTask class.
 */
public class SubMenuTask implements Task<Void> {
    private AnimationRunner runner;
    private String[] args;
    private GUI gui;
    private final biuoop.KeyboardSensor keyboard;
    private HashMap<String, String> name;
    private HashMap<String, String> adress;
    /**
     * constuctor.
     * @param g **GUI**
     * @param r **AnimationRunner**
     * @param k **KeyboardSensor**
     * @param a **String[]**
     */
    public SubMenuTask(GUI g, AnimationRunner r, KeyboardSensor k, String[] a) {
        this.runner = r;
        this.args = a;
        this.keyboard = k;
        this.gui = g;
        this.name = new HashMap<String, String>(); // e:easy-level
        this.adress = new HashMap<String, String>(); // e: path-to-level-file
    }
    /**
     * run the submenu.
     * @return **null**
     */
    public Void run() {
        Menu<Task<Void>> subMenu;
        GameFlow gameFlow = new GameFlow(runner, keyboard, gui);
        InputStream f = null;
        if (args.length != 0) {
            f = ClassLoader.getSystemClassLoader().getResourceAsStream(this.args[0]);
        }
        if (f == null) { // if the first argument isn't a file name.
            f = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
        }
        if (f != null) { // if the current file is a real file name.
            levelset(f); // make the maps for level-sets.
            subMenu = new MenuAnimation<Task<Void>>(keyboard);
            for (String key : name.keySet()) {
                subMenu.addSelection(key, this.name.get(key), new GameTask(gameFlow, this.adress.get(key)));
            }
            runner.run(subMenu);
            Task<Void> task = subMenu.getStatus();
            task.run();
        }
        return null;
    }
    /**
     * return the name of the levels set file.
     * @param file **InputStream for file**
     */
    public void levelset(InputStream file) {
        String line = null, key = null, value = null;
        try {
            java.io.Reader reader = new InputStreamReader(file);
            LineNumberReader readline = new LineNumberReader(reader);
            line = readline.readLine();
            while (line != null) {
                if (!(readline.getLineNumber() % 2 == 0)) { // ODD line
                    String[] a = line.split(":");
                    key = a[0];
                    value = a[1];
                    name.put(key, value);
                } else {
                    adress.put(key, line); // the key is from the last line.
                }
                line = readline.readLine();
            }
            readline.close();
            reader.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}