package com.meiko.utils;
import java.io.File;
import java.util.regex.Pattern;

import org.jodconverter.OfficeDocumentConverter;
import org.jodconverter.office.DefaultOfficeManagerBuilder;
import org.jodconverter.office.OfficeException;
import org.jodconverter.office.OfficeManager;

/**
 * Created by lenovo12 on 2018/8/18.
 * doc docx ex.. ex..x ppt pptx
 */
public final class Office2PDF {
    private Office2PDF(){}

    /**
     * 灏唎ffice鏍煎紡鐨勬枃浠惰浆涓簆df
     * @param sourceFilePath 婧愭枃浠惰矾寰�
     * @return
     */
    public static File openOfficeToPDF(String sourceFilePath){
        return office2pdf(sourceFilePath);
    }

    /**
     * 灏唎ffice鏂囨。杞崲涓簆df鏂囨。
     * @param sourceFilePath 鍘熸枃浠惰矾寰�
     * @return
     */
    public static File office2pdf(String sourceFilePath){
        OfficeManager officeManager = null;
        
        try{
            if(StringUtil.isEmpty(sourceFilePath))
            {
                //鎵撳嵃鏃ュ織...
            	
                return null;
            }
            File sourceFile = new File(sourceFilePath);
            if(!sourceFile.exists())
            {
                //鎵撳嵃鏃ュ織...
                return null;
            }
            //System.out.println("123");
            String after_convert_file_path = getAfterConverFilePath(sourceFilePath);
            //鍚姩openOffice
            officeManager = getOfficeManager();
            OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
            return convertFile(sourceFile,after_convert_file_path,sourceFilePath,converter);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("杞崲寮傚父");
        }finally {
           if(officeManager != null){
               try {
                   officeManager.stop();
               } catch (OfficeException e) {
                   e.printStackTrace();
               }
           }
        }
        return null;
    }

    /**
     * 杞崲鏂囦欢
     * @param sourceFile 鍘熸枃浠�
     * @param after_convert_file_path 杞崲鍚庡瓨鏀句綅缃�
     * @param sourceFilePath 鍘熸枃浠惰矾寰�
     * @param converter 杞崲鍣�
     * @return
     */
    public static File convertFile(File sourceFile,
                           String after_convert_file_path,String sourceFilePath,OfficeDocumentConverter converter)  {
    	
        File outputFile = new File(after_convert_file_path);
        
        if(!outputFile.getParentFile().exists()){
            //濡傛灉涓婄骇鐩綍涓嶅瓨鍦ㄤ篃灏辨槸E:/pdfFile杩欎釜鏂囦欢澶逛笉瀛樺湪鍒欏垱寤轰竴涓�
            outputFile.getParentFile().mkdirs();
        }
        try {
				converter.convert(sourceFile,outputFile);
			} catch (OfficeException e) {
				
				e.printStackTrace();
			}
        return outputFile;
    }

    public static OfficeManager getOfficeManager(){
        DefaultOfficeManagerBuilder builder = new DefaultOfficeManagerBuilder();
        builder.setOfficeHome(getOfficeHome());
        OfficeManager officeManager = builder.build();
        try {
            officeManager.start();
        } catch (OfficeException e) {
            //鎵撳嵃鏃ュ織
            System.out.println("start openOffice Fail!");
            e.printStackTrace();
        }
        return officeManager;
    }

    /**
     * 鑾峰彇杞崲鍚庢枃浠跺瓨鏀剧殑璺緞
     * @param sourceFilePath 婧愭枃浠�
     * @return
     */
    public static String getAfterConverFilePath(String sourceFilePath){
        //鎴彇婧愭枃浠舵枃浠跺悕
        String sourceFileName = sourceFilePath.substring(sourceFilePath.lastIndexOf("/") + 1);
        File outputFile = new File("d:/pdfFile");
        // 假如目标路径不存在,则新建该路径
        if (!outputFile.exists()&&!outputFile.isDirectory()) {
            outputFile.mkdirs();
        }

        
        return "d:/pdfFile/" + sourceFileName.replaceAll("\\."+FileUtil.getFileSuffix(sourceFileName),".pdf");
    }

    /**
     * 鑾峰彇openOffice鐨勫畨瑁呯洰褰�
     * @return
     */
    public static String getOfficeHome(){
        String osName = System.getProperty("os.name");
        if(Pattern.matches("Windows.*",osName))
        {
            return "C:/Program Files (x86)/OpenOffice 4";
        }
        else if(Pattern.matches("Linux.*",osName))
        {
            return "/usr/temp";
        }
        else if (Pattern.matches("Mac.*",osName))
        {
            return "/Application/openOfficeSoft";
        }
        return null;
    }
}
