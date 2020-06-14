package input;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import biuoop.DrawSurface;
import geometry.Velocity;
import levels.LevelInformation;
import sprites.Block;
import sprites.Sprite;
/**
 * @author Ofir Ben Ezra.
 *         Implementation of the LevelSpecificationReader class.
 */
public class LevelSpecificationReader {
    private BufferedReader reader = null;
    /**
     * read the file and return list of levels.
     * @param r **Reader**
     * @return **list of levels**
     * @throws IOException **exception**
     */
    public List<LevelInformation> fromReader(java.io.Reader r) throws IOException {
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        List<String> levelsLine = new ArrayList<String>();
        try {
            reader = new BufferedReader(r);
            reader.readLine();
            String line = reader.readLine();
            while (line != null) {
                while (!(line.equals("END_LEVEL"))) {
                    if (!(line.equals("START_LEVEL"))) {
                        levelsLine.add(line);
                    }
                    line = reader.readLine();
                }
                line = reader.readLine();
                levels.add(genLevelsFromLine(levelsLine));
                levelsLine.clear();
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        reader.close();
        return levels;
    }
    /**
     * generate a level from a file.
     * @param levelsLine **List<String> of the level details.**
     * @return **level**
     */
    public LevelInformation genLevelsFromLine(List<String> levelsLine) {
        Integer padSpeed = null, padWidth = null, blkX = null, blkY = null, rowHeight = null, numBlk = null;
        ArrayList<Velocity> vel = new ArrayList<Velocity>();
        ArrayList<Block> blk = new ArrayList<Block>();
        java.awt.Image m = null;
        java.awt.Color c = null;
        Sprite background = null;
        String name = null;
        BlocksFromSymbolsFactory fact = null;
        ColorsParser cp = new ColorsParser();
        for (int i = 0; i < levelsLine.size(); i++) {
            String s = levelsLine.get(i);
            if (s.startsWith("level_name")) {
                String[] arr = s.split(":");
                name = arr[1];
            } else if (s.startsWith("ball_velocities")) {
                String[] arr = s.split(":");
                String[] z = arr[1].split(" ");
                for (int j = 0; j < z.length; j++) {
                    String[] v = z[j].split(",");
                    try {
                        int angle = Integer.parseInt(v[0]);
                        int speed = Integer.parseInt(v[1]);
                        Velocity ve = new Velocity(0, 0);
                        vel.add(ve.fromAngleAndSpeed(angle, speed));
                    } catch (NumberFormatException e) {
                        return null;
                    }
                }
            } else if (s.startsWith("background")) {
                String[] arr = s.split(":");
                String b = arr[1];
                if (b.startsWith("color(")) {
                    String color = b.substring(6, b.length() - 1);
                    c = cp.colorFromString(color);
                } else if (b.startsWith("image(")) {
                    String image = b.substring(6, b.length() - 1);
                    try {
                        InputStream pathToFile = ClassLoader.getSystemClassLoader().getResourceAsStream(image);
                        m = ImageIO.read(pathToFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (c != null) { // if background is color
                    final java.awt.Color fc = c;
                    final String fname = name;
                    background = new Sprite() {
                        public void drawOn(DrawSurface d) {
                            d.setColor(fc); // screen color
                            d.fillRectangle(20, 40, 800 - 40, 600 - 40);
                            d.setColor(java.awt.Color.BLACK);
                            d.drawText(580, 18, "Level Name: " + fname, 15); // level name
                        }
                        public void timePassed() {
                            // TODO Auto-generated method stub
                        }
                    };
                } else if (m != null) { // if background is an image
                    final java.awt.Image fm = m;
                    final String fname = name;
                    background = new Sprite() {
                        public void drawOn(DrawSurface d) {
                            d.drawImage(0, 20, fm);
                            d.setColor(java.awt.Color.BLACK);
                            d.drawText(550, 18, "Level Name: " + fname, 15); // level name
                        }
                        public void timePassed() {
                            // TODO Auto-generated method stub
                        }
                    };
                }
            } else if (s.startsWith("paddle_speed")) {
                String[] arr = s.split(":");
                try {
                    padSpeed = Integer.valueOf(arr[1]);
                } catch (NumberFormatException e) {
                    return null;
                }
            } else if (s.startsWith("paddle_width")) {
                String[] arr = s.split(":");
                try {
                    padWidth = Integer.valueOf(arr[1]);
                } catch (NumberFormatException e) {
                    return null;
                }
            } else if (s.startsWith("blocks_start_x")) {
                String[] arr = s.split(":");
                try {
                    blkX = Integer.valueOf(arr[1]);
                } catch (NumberFormatException e) {
                    return null;
                }
            } else if (s.startsWith("blocks_start_y")) {
                String[] arr = s.split(":");
                try {
                    blkY = Integer.valueOf(arr[1]);
                } catch (NumberFormatException e) {
                    return null;
                }
            } else if (s.startsWith("row_height")) {
                String[] arr = s.split(":");
                try {
                    rowHeight = Integer.valueOf(arr[1]);
                } catch (NumberFormatException e) {
                    return null;
                }
            } else if (s.startsWith("num_blocks")) {
                String[] arr = s.split(":");
                try {
                    numBlk = Integer.valueOf(arr[1]);
                } catch (NumberFormatException e) {
                    return null;
                }
            } else if (s.startsWith("block_definitions")) {
                String[] arr = s.split(":");
                InputStream b = ClassLoader.getSystemClassLoader().getResourceAsStream(arr[1]);
                java.io.Reader buff = new InputStreamReader(b);
                fact = BlocksDefinitionReader.fromReader(buff);
            } else if (s.startsWith("START_BLOCKS")) {
                List<String> blkLines = new ArrayList<String>();
                while (!(s.startsWith("END_BLOCKS"))) {
                    i++; // next step at the lines loop.
                    s = levelsLine.get(i);
                    if (!(s.equals("END_BLOCKS"))) {
                        blkLines.add(s);
                    }
                }
                blk = genblocks(blkLines, blkX, blkY, rowHeight, fact);
            }
        }
        if (padSpeed != null && padWidth != null && numBlk != null && name != null && background != null && blk != null
                && vel != null) {
            LevelInformation level = this.genLevel(padSpeed, padWidth, numBlk, name, background, blk, vel);
            return level;
        } else {
            return null;
        }
    }
    /**
     * generate block list for the game.
     * @param blkLines **List of the lines that show how to build the blocks**
     * @param blkX **where the x point of the first block**
     * @param blkY **where the y point of the first block**
     * @param rowHeight **what is the High of the blocks**
     * @param fact **contain the maps of the defenition of the block symboles**
     * @return blkList **List of the blocks**
     */
    private ArrayList<Block> genblocks(List<String> blkLines, Integer blkX, Integer blkY, Integer rowHeight,
            BlocksFromSymbolsFactory fact) {
        ArrayList<Block> blkList = new ArrayList<Block>();
        int x = blkX, y = blkY, hight = rowHeight;
        for (String line : blkLines) {
            for (int i = 0; i < line.length(); i++) {
                Character c = line.charAt(i);
                if (fact.isSpaceSymbol(c.toString())) {
                    x = x + fact.getSpaceWidth(c.toString());
                } else if (fact.isBlockSymbol(c.toString())) {
                    Block b = fact.getBlock(c.toString(), x, y);
                    blkList.add(b);
                    x = x + (int) b.getWidth();
                } else {
                    return null;
                }
            }
            y = y + hight;
            x = blkX;
        }
        return blkList;
    }
    /**
     * generate one level for the levels List.
     * @param padSpeed **Integer**
     * @param padWidth **Integer**
     * @param numBlk **Integer**
     * @param name **String**
     * @param background **Sprite**
     * @param blk **List of blocks**
     * @param vel **List of Velocities**
     * @return lvl **LevelInformation**
     */
    private LevelInformation genLevel(Integer padSpeed, Integer padWidth, Integer numBlk, String name,
            Sprite background, ArrayList<Block> blk, ArrayList<Velocity> vel) {
        LevelInformation lvl = new LevelInformation() {
            public int numberOfBalls() {
                return vel.size();
            }
            public List<Velocity> initialBallVelocities() {
                return vel;
            }
            public int paddleSpeed() {
                return padSpeed;
            }
            public int paddleWidth() {
                return padWidth;
            }
            public String levelName() {
                return name;
            }
            public Sprite getBackground() {
                return background;
            }
            public List<Block> blocks() {
                return blk;
            }
            public int numberOfBlocksToRemove() {
                return blk.size();
            }
        };
        return lvl;
    }
}