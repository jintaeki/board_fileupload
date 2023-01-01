package com.mycompany.webapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.BoardDao;
import com.mycompany.webapp.dto.Board;
import com.mycompany.webapp.dto.Pager;

@Service
public class BoardService {
	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
	
	@Autowired
	private BoardDao boardDao;
	
	public List<Board> getBoards(Pager pager) {
		return boardDao.selectByPage(pager);
	}
	
	public Board getBoard(int bno) {
		return boardDao.selectByBno(bno);
	}
	
	public int getTotalBoardNum() {
		return boardDao.count();
	}
	
	public void writeBoard(Board board) {
		boardDao.insert(board);
	}
	
	public void updateBoard(Board board) {
		boardDao.update(board);
	}
	
	public void removeBoard(int bno) {
		boardDao.deleteByBno(bno);
	}
}








