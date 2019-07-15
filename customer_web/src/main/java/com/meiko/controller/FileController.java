package com.meiko.controller;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.meiko.domain.OFile;
import com.meiko.service.IProductService;
import com.meiko.utils.FileUtil;
import com.meiko.utils.Office2PDF;


@Controller
@RequestMapping("/file")
public class FileController {
    //base璺緞D:\a
    private final String BASE_PATH = "d:/a/";
    @Autowired
    private IProductService service;
    @RequestMapping("/findAll")
    public String findAll(Model model,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "pageSize",defaultValue = "3") Integer pageSize){
        //List<Product> products = service.findAll(page,pageSize);       
        List<OFile> oFiles = getFileList("C:/Users/yangzhiyu/Desktop/test");
        //System.out.println(oFiles.get(0));
        PageInfo<OFile> pageInfo=new PageInfo<OFile>(oFiles);
        //PageInfo<Product> pageInfo=new PageInfo<Product>(products);
      /*  System.out.println(products);

        System.out.println(pageInfo);*/
       model.addAttribute("productPageInfo",pageInfo);
        return  "product-list";
    }

    
    @RequestMapping("/download")
    public void download(HttpServletResponse response,OFile ofile) {
    	
    	try {
    		String path=ofile.getUrl()+"/"+ofile.getFileName();
            // path鏄寚娆蹭笅杞界殑鏂囦欢鐨勮矾寰勩��
            File file = new File(path);
            // 鍙栧緱鏂囦欢鍚嶃��
            String filename = file.getName();
            // 鍙栧緱鏂囦欢鐨勫悗缂�鍚嶃��
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 浠ユ祦鐨勫舰寮忎笅杞芥枃浠躲��
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 娓呯┖response
            response.reset();
            // 璁剧疆response鐨凥eader
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       

    	
    }
    @RequestMapping("/downLoadOnline")
    public void downLoadOnline(HttpServletResponse response, boolean isOnLine) throws Exception {
    	String filePath="D:\\a.txt";
        File f = new File(filePath);
        if (!f.exists()) {
            response.sendError(404, "File not found!");
            return;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;

        response.reset(); // 闈炲父閲嶈
        if (isOnLine) { // 鍦ㄧ嚎鎵撳紑鏂瑰紡
            URL u = new URL("file:///" + filePath);
            response.setContentType(u.openConnection().getContentType());
            response.setHeader("Content-Disposition", "inline; filename=" + f.getName());
            // 鏂囦欢鍚嶅簲璇ョ紪鐮佹垚UTF-8
        } else { // 绾笅杞芥柟寮�
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
        }
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        br.close();
        out.close();
    }
    
    
    @RequestMapping(value = "/read")
    public void readFile(HttpServletResponse res, OFile file
    		) throws Exception{
        InputStream in = null;
        OutputStream out = null;        
        String filePath =  fileHandler(file.getUrl()+"/"+file.getFileName());
        System.out.println(filePath);

       try{
           if(filePath != null){
               in = new FileInputStream(filePath);
           }
           res.setContentType("application/pdf");
           out = res.getOutputStream();
           byte[] b = new byte[1024];
           int len = 0;
           while((len = in.read(b)) != -1){
               out.write(b);
           }
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           if(in != null){
               in.close();
           }
           if(out != null){
               out.close();
           }
       }
    }
    /**
     * 鏂囦欢澶勭悊
     * @param fileName
     * @return
     */
    private String fileHandler(String fileName){
        String fileSuffix = FileUtil.getFileSuffix(fileName);
        System.out.println(fileSuffix);
        if("pdf".equals(fileSuffix))
        {
            return fileName;
        }
        else
        {
            return Office2PDF.openOfficeToPDF(fileName).getAbsolutePath();
        }

    }
    /**
     * 璇诲彇鏂囦欢澶规枃浠�
     * @param path
     * @return
     */
    public List<OFile> getFileList(String path) { 
    	  List<OFile> list = new ArrayList<OFile>(); 
    	  try { 
    	   File file = new File(path); 
    	   String[] filelist = file.list(); 
    	   for (int i = 0; i < filelist.length; i++) {
    		   OFile oFile = new OFile(String.valueOf(i),filelist[i],path);
    	    list.add(oFile); 
    	   } 
    	  } catch (Exception e) { 
    	   e.printStackTrace(); 
    	  } 
    	  return list; 
    	} 
}
