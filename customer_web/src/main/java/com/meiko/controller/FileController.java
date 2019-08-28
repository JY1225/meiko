package com.meiko.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.meiko.domain.Cust_jccjs_list;
import com.meiko.domain.Dir;
import com.meiko.domain.FileLog;
import com.meiko.domain.UserInfo;
import com.meiko.service.IFileLogService;
import com.meiko.service.IFileService;
import com.meiko.service.IUserService;
import com.meiko.utils.FileUtil;
import com.meiko.utils.Office2PDF;
import com.meiko.utils.ZipUtils;



@Controller
@RequestMapping("/file")
public class FileController {
	@Autowired
	private IUserService service;
	@Autowired
	private IFileService fileservice;
	@Autowired
	private IFileLogService fileLogService;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpServletRequest request;
	private static final String CHAR_SET = "GBK";
	private static final String BASE_DIR = "";

	@RequestMapping("/findAll")
	public String findAll(Model model, @RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(name = "fromData", required = false) String fromData,
			@RequestParam(name = "toData", required = false) String toData) {
		
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		UserInfo user = service.findByUserName(name);

		List<Cust_jccjs_list> list = service.findFiles(page, pageSize, user.getId(), fromData, toData);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setUpload_filename(list.get(i).getUpload_filename().trim());
		}
		PageInfo<Cust_jccjs_list> pageInfo = new PageInfo<Cust_jccjs_list>(list);
		model.addAttribute("productPageInfo", pageInfo);
		return "product-list";
	}


	@RequestMapping("/download")
	public void download(HttpServletResponse response, HttpServletRequest request, String upload_filename) {
		try {
			Dir dir = fileservice.findDirByStatus(1);
			String path = dir.getUrl().replace("\\", "/") + "/" + upload_filename.trim();
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

			List<Cust_jccjs_list> ofile = fileservice.findOneByName(upload_filename.trim());
			fileservice.updateDownloads(ofile.get(0).getRecid());
			Cust_jccjs_list one = fileservice.findByOne(ofile.get(0).getRecid());

			saveFileLog(one, "下载");

		} catch (IOException ex) {
			ex.printStackTrace();

		}

	}

	private void saveFileLog(Cust_jccjs_list one, String type) {
		int down_loads = one.getDown_loads();
		int previews = one.getPreviews();
		int custJccJsListId = one.getRecid();
		String upload_filename = one.getUpload_filename();
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		FileLog fileLog = new FileLog();
		fileLog.setCust_jccjs_list_id(custJccJsListId);
		fileLog.setDown_loads(down_loads);
		fileLog.setFile_name(upload_filename);
		fileLog.setLoadTime(new Date());
		fileLog.setType(type);
		fileLog.setLogin_name(name);
		fileLog.setPreviews(previews);
		fileLogService.save(fileLog);

	}

	@RequestMapping(value = "/read")
	public void readFile(HttpServletResponse res, Cust_jccjs_list ofile) throws Exception {
		InputStream in = null;
		OutputStream out = null;
		Dir dir = fileservice.findDirByStatus(1);
		String path = dir.getUrl().replace("\\", "/") + "/" + ofile.getUpload_filename().trim();
		String filePath = fileHandler(path);
		System.out.println(filePath);

		try {
			if (filePath != null) {
				in = new FileInputStream(filePath);
			}
			res.setContentType("application/pdf");
			out = res.getOutputStream();
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = in.read(b)) != -1) {
				out.write(b);
			}

			fileservice.updatePreviews(ofile.getRecid());
			Cust_jccjs_list one = fileservice.findByOne(ofile.getRecid());
			saveFileLog(one, "预览");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	private String fileHandler(String fileName) {
		String fileSuffix = FileUtil.getFileSuffix(fileName);
		System.out.println(fileSuffix);
		if ("pdf".equals(fileSuffix)) {
			return fileName;
		} else {
			return Office2PDF.openOfficeToPDF(fileName).getAbsolutePath();
		}

	}

	@RequestMapping("/findAllDir")
	public String findAllDir(Model model, @RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "pageSize", defaultValue = "3") Integer pageSize,
			@RequestParam(name = "fileName", required = false) String fileName) {
		List<Dir> oFiles = fileservice.findAll(page, pageSize, fileName);
		PageInfo<Dir> pageInfo = new PageInfo<Dir>(oFiles);
		model.addAttribute("filePageInfo", pageInfo);
		return "dir-list";

	}

	@RequestMapping("/dirOnById")
	@ResponseBody   
	public String dirOnById(Model model, @RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "pageSize", defaultValue = "3") Integer pageSize,
			@RequestParam(name = "id", required = false) int id,
			HttpServletResponse response) throws IOException {
		List<Dir> dirs = fileservice.findAll(page, pageSize, "");
		List<Integer> status = new ArrayList<Integer>();
		for (int i = 0; i < dirs.size(); i++) {
			if (dirs.get(i).getStatus() == 1) {
				status.add(dirs.get(i).getStatus());
				break;
			}
		}
		if (status.size() == 0) {
			String name = SecurityContextHolder.getContext().getAuthentication().getName();
			fileservice.updateDirStausById(1, name, id);
			return null;
		} else {
			return "已有开启目录，如需切换请先关闭该目录";
		}		
	}

	@RequestMapping("/dirOffById")
	public String dirOffById(Model model, @RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "pageSize", defaultValue = "3") Integer pageSize,
			@RequestParam(name = "id", required = false) int id) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		fileservice.updateDirStausById(0, name, id);

		return "redirect:findAllDir";
	}

	@RequestMapping("/dirDeleById")
	public String dirDeleById(Model model, @RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "pageSize", defaultValue = "3") Integer pageSize,
			@RequestParam(name = "id", required = false) int id) {
		fileservice.deleteDirStausById(id);
		return "redirect:findAllDir";
	}

	@RequestMapping("/save")
	@ResponseBody   
	public String save(Dir dir) {
		List<Dir> dirs = fileservice.findAll(1, 3, "");
		List<Integer> status = new ArrayList<Integer>();
		for (int i = 0; i < dirs.size(); i++) {
			if (dirs.get(i).getStatus() == 1) {
				status.add(dirs.get(i).getStatus());
				break;
			}
		}
		if (status.size() == 0 || (status.size() > 0 && dir.getStatus() == 0)) {
			String name = SecurityContextHolder.getContext().getAuthentication().getName();
			dir.setEditUser(name);
			fileservice.save(dir);
			return null;
		} else {
			return "已有开启目录，如需添加请选择关闭状态";			
		}

		//return "redirect:findAllDir";
	}

	/**
	 * 
	 * 
	 * 文件下载
	 * 
	 * @param request
	 * @param response
	 * @param filePath
	 *            文件路径
	 * @param filename
	 *            下载时文件名称
	 */

	@RequestMapping("/downLoadByNames")
	public void downLoadByNames(HttpServletResponse response,@RequestParam(name = "names") String[] names) {
		// System.out.println(Arrays.toString(names));
		String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
		try {
			String strZipPath = "../ptf/" + loginName +new Date().getTime()+ ".zip";
			File file = new File("../ptf");
			if (!file.isDirectory() && !file.exists()) {
				// 创建单层目录
				// f.mkdir();
				// 创建多层目录
				file.mkdirs();
			}
			ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(new File(strZipPath)));
			zipOutputStream.setEncoding(CHAR_SET);
			Dir dir = fileservice.findDirByStatus(1);
			String path = dir.getUrl().replace("\\", "/") + "/";
			List<File> files = new ArrayList<File>();
			for (int i = 0; i < names.length; i++) {
				// download(response,request,names[i]);
				files.add(new File(path + names[i]));
			}
			if (CollectionUtils.isEmpty(files) == false) {
				for (int i = 0, size = files.size(); i < size; i++) {
					ZipUtils.compress(files.get(i), zipOutputStream, BASE_DIR);
				}
			}
			// 冲刷输出流
			zipOutputStream.flush();
			// 关闭输出流
			zipOutputStream.close();
			File temp = new File(strZipPath);
			FileController.downLoadFile(request, response, strZipPath,temp.getName());	
			if (temp.exists()) {
				temp.delete();
			}
			
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void downLoadFile(HttpServletRequest request, HttpServletResponse response, String filePath,
			String filename) throws Exception{
		FileInputStream fs = null;
		OutputStream writer = null;
		try {
			File file = new File(filePath);
			// 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称 用于浏览器的下载框中自动显示的文件名
			/*
			 * String userAgent = request.getHeader("User-Agent"); if
			 * (userAgent.contains("MSIE") || userAgent.contains("Trident")) { filename =
			 * java.net.URLEncoder.encode(filename, "UTF-8"); } else { filename = new
			 * String(filename.getBytes("utf-8"), "iso8859-1"); }
			 */
			//response.reset();
			response.setHeader("Content-Type","application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename="+java.net.URLEncoder.encode(filename, "UTF-8"));
            response.setContentType("multipart/form-data");
			/*
			 * response.addHeader("Content-Disposition", "attachment;filename=" + filename);
			 * // response.setContentType("application/vnd.ms-excel");
			 * response.setContentType("multipart/form-data");
			 */
			byte[] buffer = new byte[1024];
			int len = 0;
			 fs = new FileInputStream(file);
			 writer = response.getOutputStream();
			//PrintWriter writer = response.getWriter();
			while ((len = fs.read(buffer)) != -1) {
				writer.write(buffer, 0, len);//(len);
			}
			fs.close();
			writer.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}finally {
            if(fs != null){
            	fs.close();
            }
            if(writer != null){
            	writer.close();
            }
        }
	}

}
