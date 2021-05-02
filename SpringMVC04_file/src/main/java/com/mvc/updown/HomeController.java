package com.mvc.updown;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.mvc.updown.validate.FileValidator;
import com.mvc.updown.validate.UploadFile;

@Controller
public class HomeController {
	
	@Autowired
	private FileValidator fileValidator;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/form")
	public String uploadForm() {
		return "upload";
	}
	
	@RequestMapping("/upload")
	public String fileUpload(HttpServletRequest request, Model model, UploadFile uploadFile, BindingResult result) {
		
		// 파일 유효성 검사
		fileValidator.validate(uploadFile, result);
		if (result.hasErrors()) {
			return "upload";
			// 에러시 업로드 페이지 리턴
		}
		
		MultipartFile file = uploadFile.getMpfile();
		// uploadFile의 data가 post로 받은 데이터가 들어와 있어야한다.
		
		String name = file.getOriginalFilename();
		// 사용자가 업로드한 파일의 이름을 가지고옴
		
		UploadFile fileObj = new UploadFile();
		// 서버에 넣은 파일정보에 대한 내용의 객체생성
		fileObj.setName(name);
		// 서버쪽 객체에 업로드한 파일 이름을 넣음
		fileObj.setDesc(uploadFile.getDesc());
		// 서버쪽 객체에 내용을 넣음
		
		InputStream inputStream = null;// 데이터를 가지고 올 객체				
		OutputStream outputStream = null;// 데이터를 내보낼 객체
		
		try {
			
			inputStream = file.getInputStream(); // 사용자가 업로드한 파일의 내용을 읽어옴 
			String path = WebUtils.getRealPath(request.getSession().getServletContext(), "/resources/storage"); // 서버에 넣을 path 를 정함
			System.out.println("업로드 실제 경로 : " + path);
			
			//폴더 생성
			File storage = new File(path);
			if (!storage.exists()) {
				storage.mkdir();
			}
			
			//파일이 없을 경우 파일을 만듬 파일의 이름은 원래 경로
			File newFile = new File(path + "/" + name);
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			
			// 서버에 저장된 파일에 write 해줄 부분
			outputStream = new FileOutputStream(newFile);
			
			int read = 0;
			byte[] b = new byte[(int)file.getSize()];
			//읽어온 파일르 바이트 단위로 가지고옴
			
			// 읽어온 파일이 끝이 될때까지 서버내용부분에 저장
			while((read = inputStream.read(b)) != -1) {
				outputStream.write(b, 0, read);
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
				outputStream.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		model.addAttribute("fileObj", fileObj);
		
		return "download";
	}
	
	@ResponseBody
	@RequestMapping("/download")
	public byte[] fileDownload(HttpServletRequest request, HttpServletResponse response, String name) {
		
		byte[] down = null;
		
		
		try {
			String path = WebUtils.getRealPath(request.getSession().getServletContext(), "/resources/storage");
			File file = new File(path + "/" + name);
			// 서버에 있는 파일
			
			// 해당파일을 웹브라우저에서 읽어온다.
			down = FileCopyUtils.copyToByteArray(file);
			
			// 한글형식으로 읽어옴
			String filename = new String(file.getName().getBytes(), "8859_1");
			
			// setheader를 통해 첨부파일 명시 // 파일이름까지 설정
			// Content-Disposition : attachment -> file download 설정 (filename 설정)
			response.setHeader("Content-Disposition", "attachment; filename=\""+filename+"\"");
			
			// 길이만큼 응답한다.
			response.setContentLength(down.length);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return down;
	}
	
}
