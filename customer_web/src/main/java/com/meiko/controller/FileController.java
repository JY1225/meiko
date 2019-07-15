package com.meiko.controller;



import com.github.pagehelper.PageInfo;
import com.meiko.domain.Customer;
import com.meiko.service.IProductService;
import com.meiko.utils.FileUtil;
import com.meiko.utils.Office2PDF;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/file")
public class FileController {
    //base路径D:\a
    private final String BASE_PATH = "d:/a/";
    @Autowired
    private IProductService service;
    @RequestMapping("/findAll")
    public String findAll(Model model,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "pageSize",defaultValue = "3") Integer pageSize){
        List<Customer> products = service.findAll(page,pageSize);
       /* for(Product product:products){
            System.out.println(product);
        }*/
        PageInfo<Customer> pageInfo=new PageInfo<Customer>(products);
      /*  System.out.println(products);

        System.out.println(pageInfo);*/
       model.addAttribute("productPageInfo",pageInfo);
        return  "product-list";
    }
   
    
    @RequestMapping("/download")
    public void download(HttpServletResponse response) {
    	
    	try {
    		String path="D:\\a\\prelec.xls";
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
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

        response.reset(); // 非常重要
        if (isOnLine) { // 在线打开方式
            URL u = new URL("file:///" + filePath);
            response.setContentType(u.openConnection().getContentType());
            response.setHeader("Content-Disposition", "inline; filename=" + f.getName());
            // 文件名应该编码成UTF-8
        } else { // 纯下载方式
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
        }
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        br.close();
        out.close();
    }
    
    
    @RequestMapping("/read")
    public void readFile(HttpServletResponse res ) throws Exception{
        InputStream in = null;
        OutputStream out = null;
        String fileName="1.xls";
        String filePath =  fileHandler(fileName);
        //判断是pdf还是word还是excel
        //若是pdf直接读 否则转pdf 再读  //
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
     * 文件处理
     * @param fileName
     * @return
     */
    private String fileHandler(String fileName){
        String fileSuffix = FileUtil.getFileSuffix(fileName);
        System.out.println(fileSuffix);
        if("pdf".equals(fileSuffix))
        {
            return BASE_PATH + fileName;
        }
        else
        {
            return Office2PDF.openOfficeToPDF(BASE_PATH + fileName).getAbsolutePath();
        }

    }

}
