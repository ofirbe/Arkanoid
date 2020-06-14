package input;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import javax.imageio.ImageIO;
import sprites.Block;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the BlocksDefinitionReader class.
 */
public class BlocksDefinitionReader {
    private static BlocksFromSymbolsFactory fact;
    private static Integer width, height, hitPoints;
    private static ColorsParser cp;
    private static java.awt.Color c, stroke;
    private static java.awt.Image m;
    private static String symbol;
    private static HashMap<Integer, java.awt.Color> colors;
    private static HashMap<Integer, java.awt.Image> images;
    private static HashMap<String, Integer> defInt;
    private static HashMap<String, String> defStr;
    /**
     * creates Block Factory from inputed File stream. if invalid input or fails, returns null.
     * @param r **java.io.Reader**
     * @return fact **BlockFactory**
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader r) {
        fact = new BlocksFromSymbolsFactory();
        colors = new HashMap<Integer, java.awt.Color>();
        images = new HashMap<Integer, java.awt.Image>();
        defInt = new HashMap<String, Integer>();
        defStr = new HashMap<String, String>();
        cp = new ColorsParser();
        BufferedReader reader = null;
        int check = 0;
        try {
            reader = new BufferedReader(r);
            String line = reader.readLine();
            while (line != null) {
                width = null;
                height = null;
                hitPoints = null;
                c = null;
                stroke = null;
                m = null;
                symbol = null;
                while (line.startsWith("#") || line.startsWith(" ")) {
                    line = reader.readLine();
                }
                if (line.startsWith("default")) { // deal with default
                    genDef(line);
                } else if (line.startsWith("bdef")) {
                    colors = new HashMap<Integer, java.awt.Color>();
                    images = new HashMap<Integer, java.awt.Image>();
                    colors.clear();
                    images.clear();
                    String[] a = line.split(" ");
                    for (int i = a.length - 1; i >= 1; i--) {
                        String[] b = a[i].split(":");
                        String keyword = b[0];
                        if (keyword.startsWith("height")) {
                            try {
                                height = Integer.valueOf(b[1]);
                            } catch (NumberFormatException e) {
                                return null;
                            }
                        } else if (keyword.startsWith("symbol")) {
                            symbol = b[1];
                        } else if (keyword.startsWith("width")) {
                            try {
                                width = Integer.valueOf(b[1]);
                            } catch (NumberFormatException e) {
                                return null;
                            }
                        } else if (keyword.startsWith("hit_points")) {
                            try {
                                hitPoints = Integer.valueOf(b[1]);
                            } catch (NumberFormatException e) {
                                return null;
                            }
                        } else if (keyword.startsWith("fill")) {
                            check = fillGen(b);
                            if (check != 0) { // check if all good.
                                return null;
                            }
                        } else if (keyword.startsWith("stroke")) {
                            if (b[1].startsWith("color(")) {
                                String col = b[1].substring(6, b[1].length() - 1);
                                stroke = cp.colorFromString(col);
                                if (stroke == null) {
                                    return null;
                                }
                            }
                        }
                    }
                    // check if there is defaults.
                    if (width == null) {
                        if (defInt.containsKey("width")) {
                            width = defInt.get("width");
                        } else { // there is no value..-FAIL
                            return null;
                        }
                    }
                    if (height == null) {
                        if (defInt.containsKey("height")) {
                            height = defInt.get("height");
                        } else { // there is no value..-FAIL
                            return null;
                        }
                    }
                    if (hitPoints == null) {
                        if (defInt.containsKey("hit_points")) {
                            hitPoints = defInt.get("hit_points");
                        } else { // there is no value..-FAIL
                            return null;
                        }
                    }
                    if (stroke == null) {
                        if (defStr.containsKey("stroke")) {
                            String stroClr = defStr.get("stroke");
                            stroke = cp.colorFromString(stroClr.substring(6, stroClr.length() - 1));
                        } else { // there is no value..-FAIL
                            stroke = null;
                        }
                    }
                    if (!images.containsKey(-1) && !colors.containsKey(-1)) {
                        if (!images.containsKey(1) && !colors.containsKey(1)) {
                            if (defStr.containsKey("fill")) {
                                String v = defStr.get("fill");
                                java.awt.Color clr = cp.colorFromString(v);
                                String image = v.substring(6, v.length() - 1);
                                try {
                                    File pathToFile = new File(image);
                                    java.awt.Image img = ImageIO.read(pathToFile);
                                    if (clr != null) {
                                        colors.put(-1, clr);
                                    } else if (img != null) {
                                        images.put(-1, img);
                                    } else {
                                        return null;
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    BlockCreator bc = genBlockCr(width, height, hitPoints, stroke, colors, images);
                    fact.addBlockCreator(symbol, bc);
                } else if (line.startsWith("sdef")) {
                    genSpacer(line);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return fact;
    }
    /**
     * check the fill of the block from the file and save it at the maps.
     * @param b **String[]**
     * @return 0-all good / 1-problem- need to return null.
     */
    private static int fillGen(String[] b) {
        if (b[0].equals("fill")) {
            String x = b[1];
            if (x.startsWith("color(")) {
                String color = x.substring(6, x.length() - 1);
                c = cp.colorFromString(color);
                if (c == null) {
                    return 1;
                }
                colors.put(-1, c);
            } else if (x.startsWith("image(")) {
                String image = x.substring(6, x.length() - 1);
                try {
                    InputStream pathToFile = ClassLoader.getSystemClassLoader().getResourceAsStream(image);
                    m = ImageIO.read(pathToFile);
                    if (m == null) {
                        return 1;
                    }
                    images.put(-1, m);
                    pathToFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (b[0].startsWith("fill-")) {
            String[] st = b[0].split("-");
            String x = b[1];
            try {
                int k = Integer.parseInt(st[1]);
                if (x.startsWith("color(")) {
                    String color = x.substring(6, x.length() - 1);
                    c = cp.colorFromString(color);
                    if (c == null) {
                        return 1;
                    }
                    colors.put(k, c);
                } else if (x.startsWith("image(")) {
                    String image = x.substring(6, x.length() - 1);
                    try {
                        InputStream pathToFile = ClassLoader.getSystemClassLoader().getResourceAsStream(image);
                        m = ImageIO.read(pathToFile);
                        if (m == null) {
                            return 1;
                        }
                        images.put(k, m);
                        pathToFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (NumberFormatException e) {
                return 1;
            }
        }
        return 0;
    }
    /**
     * generate a default values.
     * @param line **String**
     */
    private static void genDef(String line) {
        String[] a = line.split(" ");
        for (int i = a.length - 1; i >= 1; i--) {
            String[] b = a[i].split(":");
            try {
                int num = Integer.parseInt(b[1]);
                defInt.put(b[0], num);
            } catch (NumberFormatException e) {
                defStr.put(b[0], b[1]);
            }
        }
    }
    /**
     * generate spacer at the map.
     * @param line **String**
     */
    private static void genSpacer(String line) {
        String key = null;
        String[] a = line.split(" ");
        for (int i = 0; i < a.length; i++) {
            String[] b = a[i].split(":");
            if (b[0].equals("symbol")) {
                key = b[1];
            } else if (b[0].equals("width")) {
                try {
                    Integer value = Integer.valueOf(b[1]);
                    if (key == null) {
                        System.out.println("key for spacer is invaild or not exist!");
                        return;
                    }
                    fact.addSpacer(key, value);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }
    /**
     * generates BlockCreator.
     * @param wid **witdh of block**
     * @param hei **hight of block**
     * @param hitPo **hit Points**
     * @param str **stroke color**
     * @param clr **colors map**
     * @param img **images map**
     * @return bc **BlockCreator**
     */
    public static BlockCreator genBlockCr(Integer wid, Integer hei, Integer hitPo, java.awt.Color str,
            HashMap<Integer, java.awt.Color> clr, HashMap<Integer, java.awt.Image> img) {
        BlockCreator bc = new BlockCreator() {
            public Block create(int xpos, int ypos) {
                Block block = new Block((double) xpos, (double) ypos, wid, hei, str, 5);
                block.setMaxHit(hitPo);
                block.setMapClr(clr);
                block.setMapImg(img);
                return block;
            }
        };
        return bc;
    }
}
