package com.mycompany.webapp.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dto.Board;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	public static final Logger  logger= LoggerFactory.getLogger(BoardController.class);

	
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/boardWriteForm")
	public String boardWriteForm() {
	
		return "board/boardWriteForm";
	}
	
	@RequestMapping("/boardWrite")
	public String boardWrite(Board board) {
		MultipartFile mf = board.getBattach();
		if(mf.isEmpty()) {
			logger.info("비었음");
			
		}else {
			logger.info("있음");
			String originalFilename =mf.getOriginalFilename();
			String contentType= mf.getContentType();
			String savedFilename = UUID.randomUUID().toString()+ "-" + originalFilename ;
			board.setBattachoname(originalFilename);
			board.setBattachsname(savedFilename);
			board.setBattachtype(contentType);
			logger.info(board.toString());	
			String savaPath = "C:/Temp/uploadfiles";
			File file = new File(savaPath +"/"+savedFilename);
			try {
				mf.transferTo(file);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boardService.writeBoard(board);
		}
		return "redirect:/";
	}
	
	
	@GetMapping("/boardList")
	public String boardList(@RequestParam(defaultValue="1") int pageNo, Model model) {
		
		//페이징 대상이 되는 전체 행수
		int totalRows = boardService.getTotalBoardNum();
		
		//페이징 정보가 닮긴 Pager 객체 생성 (한 페이지에 담긴 행의 수, 한 블록에 5 페이지,)
		Pager pager = new Pager (5, 5, totalRows, pageNo);
		
		//해당 페이지의 행을 가져오기
		List<Board> boards = boardService.getBoards(pager);
		
		model.addAttribute("pager",pager);
		model.addAttribute("boards", boards);
		return "board/boardList";
	}
	
	   @GetMapping("/boardDetail")
	   public String boardDetail(int bno, Model model) {
	      Board board = boardService.getBoard(bno);
	      model.addAttribute("board", board);
	      return "board/boardDetail";
	   }
	
	   @GetMapping("/filedownload")
	   public void filedownload(int bno, HttpServletResponse response,
	         @RequestHeader("User-Agent") String userAgent) throws Exception {
	      //fileNo를 이용해서 DB에서 파일 정보를 가져오기
	      Board board = boardService.getBoard(bno);
	      String contentType = board.getBattachtype();
	      String origianFilename = board.getBattachoname();
	      String savedName = board.getBattachsname();      
	      //응답 바디의 데이터의 형식 설정
	      response.setContentType(contentType);      
	      //브라우저별로 한글 파일명을 변환
	      if(userAgent.contains("Trident") || userAgent.contains("MSIE")) {
	         //IE일 경우
	         origianFilename = URLEncoder.encode(origianFilename, "UTF-8");
	      } else {
	         //크롬, 엣지, 사라피일 경우
	         origianFilename = new String(origianFilename.getBytes("UTF-8"), "ISO-8859-1");
	      }      
	      //파일을 첨부로 다운로드하도록 설정
	      response.setHeader("Content-Disposition", "attachment; filename=\"" + origianFilename + "\"");
	      //파일로부터 데이터를 읽는 입력스트림 생성      
	      String filePath = "C:/Temp/uploadfiles/" + savedName;
	      File file = new File(filePath);
	      if(file.exists()) {
	         InputStream is = new FileInputStream(filePath);         
	         //응답 바디에 출력하는 출력스트림 얻기
	         OutputStream os = response.getOutputStream();         
	         //입력스트림 -> 출력스트림
	         FileCopyUtils.copy(is, os);
	         is.close();
	         os.flush();
	         os.close();
	      }
	   }
	   
}
