package com.lfm.test.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author liufuming
 * @date 2019/12/27 11:30
 **/
@Slf4j
public class CSVUtil {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("C:\\Users\\daqsoft\\Desktop\\consumeCode2.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String s = null;
            String old = "";
            String newCode= "";
            FileWriter fileWriter = new FileWriter("C:\\Users\\daqsoft\\Desktop\\consumeCode2.sql");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            int i = 0;
            do {
                s = bufferedReader.readLine();
                if (s != null && s.length() > 0){
                    log.debug(s);
                    String[] split = s.split(",");String h1 = split[0];
                    if (i == 0){
                        i++;
                        continue;
                    }else {
                        String[] split2 = split[0].split(":");
                        old = split2[1];
                        newCode = split[1];
                        bufferedWriter.newLine();
                        bufferedWriter.write("update tb_integral_order_tourist set ecode = '" + newCode + "' where ecode = '"+old+"';");
                        log.info("update tb_integral_order_tourist set ecode = '" + newCode + "' where ecode = '"+old+"';");
                    }
                }
            }while (s != null);
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
