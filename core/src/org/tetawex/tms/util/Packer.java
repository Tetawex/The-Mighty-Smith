package org.tetawex.tms.util;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
public class Packer {
    public static void main (String[] args) throws Exception {
        TexturePacker.process("assets/textures", "assets", "atlas");
    }
}
