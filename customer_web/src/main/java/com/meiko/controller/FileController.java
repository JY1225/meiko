package com.meiko.controller;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.PageInfo;
import com.meiko.domain.Cust_jccjs_list;
import com.meiko.domain.Dir;
import com.meiko.domain.UserInfo;
import com.meiko.service.IFileService;
import com.meiko.service.IUserService;
import com.meiko.utils.FileUtil;
import com.meiko.utils.Office2PDF;


@Controller
@RequestMapping("/file")
public class FileController {
	@Autowired
    private IUserService service;
	@Autowired
    private IFileService fileservice;
   // private final String BASEPATH="http:\\192.1.8.35\\jccjs\\LIDAN" ;
	@RequestMapping("/findAll")
    public String findAll(Model model,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "pageSize",defaultValue = "3") Integer pageSize,
                          @RequestParam(name="fileName",required=false) String fileName){
     
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInfo user = service.findByUserName(name);
        
        List<Cust_jccjs_list> list = service.findFiles(page,pageSize,user.getId(),fileName); 
        for(int i = 0;i < list.size();i++) {
        	list.get(i).setUpload_filename(list.get(i).getUpload_filename().trim());
        }
        PageInfo<Cust_jccjs_list> pageInfo=new PageInfo<Cust_jccjs_list>(list);
        model.addAttribute("productPageInfo",pageInfo);
        return  "product-list";
    }

    
    @RequestMapping("/download")
    public String download(HttpServletResponse response,HttpServletRequest request,Cust_jccjs_list ofile) {
    	try {
    		Dir dir = fileservice.findDirByStatus(1);
    		String path=dir.getUrl().replace("\\", "/")+"/"+ofile.getUpload_filename();
            // 
    		
            File file = new File(path);
            // 
            String filename = file.getName();
            // 
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 
            response.reset();
            //
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            int down_loads = ofile.getDown_loads();
            ofile.setDown_loads(down_loads);
            fileservice.updateDownloads(ofile); 
            
            return null; 
        } catch (IOException ex) {
            ex.printStackTrace();
            //return null;
        }
		return null;
           	
    }
   
    
    
    @RequestMapping(value = "/read")
    public void readFile(HttpServletResponse res, Cust_jccjs_list ofile
    		) throws Exception{
        InputStream in = null;
        OutputStream out = null; 
        Dir dir = fileservice.findDirByStatus(1);
		String path=dir.getUrl().replace("\\", "/")+"/"+ofile.getUpload_filename().trim();
        String filePath =  fileHandler(path);
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
           int previews = ofile.getPreviews();
           ofile.setPreviews(previews);;
           fileservice.updatePreviews(ofile); 
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
     * 
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
    

    @RequestMapping("/findAllDir")
    public String findAllDir(Model model,
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize",defaultValue = "3") Integer pageSize,
            @RequestParam(name="fileName",required=false) String fileName) {
    	 List<Dir> oFiles = fileservice.findAll(page, pageSize,fileName);  
    	 PageInfo<Dir> pageInfo=new PageInfo<Dir>(oFiles);
    	 model.addAttribute("filePageInfo",pageInfo);
         return  "dir-list";
    	
    }
    
    @RequestMapping("/dirOnById")
    public String dirOnById(Model model,
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize",defaultValue = "3") Integer pageSize,
            @RequestParam(name="id",required=false) int id) throws IOException {
    	List<Dir> dirs = fileservice.findAll(page, pageSize,"");
    	List<Integer> status = new ArrayList<Integer>();
    	for(int i = 0;i < dirs.size();i++) {   		
    		if(dirs.get(i).getStatus()==1) {
    			status.add(dirs.get(i).getStatus());
    			break;
    		}
    	}
    	if(status.size()==0) {
    		String name = SecurityContextHolder.getContext().getAuthentication().getName();
    		fileservice.updateDirStausById(1,name,id); 
    		
    	}else {
    		JOptionPane.showMessageDialog(null,"已有开启目录，如需切换请先关闭该目录","错误",JOptionPane.PLAIN_MESSAGE);
    	}    
 
        return  "redirect:findAllDir";
    }
    
    @RequestMapping("/dirOffById")
    public String dirOffById(Model model,
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize",defaultValue = "3") Integer pageSize,
            @RequestParam(name="id",required=false) int id) {
    	String name = SecurityContextHolder.getContext().getAuthentication().getName();
    	fileservice.updateDirStausById(0,name,id);        

        return  "redirect:findAllDir";
    }
    
    @RequestMapping("/dirDeleById")
    public String dirDeleById(Model model,
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize",defaultValue = "3") Integer pageSize,
            @RequestParam(name="id",required=false) int id){
    	fileservice.deleteDirStausById(id);        
        return  "redirect:findAllDir";
    }
    
    @RequestMapping("/save")
    public String save(Dir dir){
    	List<Dir> dirs = fileservice.findAll(1, 3,"");
    	List<Integer> status = new ArrayList<Integer>();
    	for(int i = 0;i < dirs.size();i++) {   		
    		if(dirs.get(i).getStatus()==1) {
    			status.add(dirs.get(i).getStatus());
    			break;
    		}
    	}
    	if(status.size()==0 || (status.size()>0 && dir.getStatus()==0)) {
    		String name = SecurityContextHolder.getContext().getAuthentication().getName();
        	dir.setEditUser(name);
        	fileservice.save(dir);
    		
    	}else {
    		JOptionPane.showMessageDialog(null,"已有开启目录，如需添加请选择关闭状态","错误",JOptionPane.PLAIN_MESSAGE);
    	}    
    	
        return "redirect:findAllDir";
    }
}
