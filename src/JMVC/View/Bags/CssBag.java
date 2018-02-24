/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMVC.View.Bags;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author halse
 */
public class CssBag {
    
    public String LoadFile(String filename){
        String content;
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            content = String.join("\n", lines);
        } catch (IOException ex) {
            content = "";
        }
        return "<style>\n"+content+"\n</style>";
    }
    
}
