package com.ssafy.happyhouse.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.dto.NoticeDto;
import com.ssafy.happyhouse.dto.ProductDto;
import com.ssafy.happyhouse.help.BoolResult;
import com.ssafy.happyhouse.help.NumberResult;
import com.ssafy.happyhouse.service.BoardService;
import com.ssafy.happyhouse.service.NoticeService;
import com.ssafy.happyhouse.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/happyhouse/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "SSAFY Resouces Management 2021")
public class BoardController {

	public static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private BoardService boardService;

	@Autowired
	private ProductService productService;

	// ================notice start====================
	@ApiOperation(value = "모든 공지사항.", response = List.class)
	@RequestMapping(value = "/noticelist", method = RequestMethod.GET)
	public ResponseEntity<List<NoticeDto>> noticelist() throws Exception {
		logger.info("1-------------noticelist-----------------------------" + new Date());
		List<NoticeDto> emps = noticeService.listArticle();
		if (emps.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<NoticeDto>>(emps, HttpStatus.OK);
	}

	@ApiOperation(value = "공지사항 번호로 찾기", response = NoticeDto.class)
	@RequestMapping(value = "/getNotice/{no}", method = RequestMethod.GET)
	public ResponseEntity<NoticeDto> getNotice(@PathVariable int no) throws Exception {
		logger.info("1-------------getNotice-----------------------------" + new Date());
		NoticeDto emp = noticeService.getArticle(no);
		if (emp == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<NoticeDto>(emp, HttpStatus.OK);
	}

	@ApiOperation(value = " 공지사항 수정 ", response = BoolResult.class)
	@RequestMapping(value = "/modifyNotice", method = RequestMethod.POST)
	public ResponseEntity<BoolResult> modifyNotice(@RequestBody NoticeDto noticeDto) throws Exception {
		logger.info("1-------------modifyNotice-----------------------------" + new Date());
		logger.info("1-------------modifyNotice-----------------------------" + noticeDto);
		boolean total = noticeService.modifyArticle(noticeDto);
		BoolResult nr = new BoolResult();
		nr.setCount(total);
		nr.setName("modifyNotice");
		nr.setState("succ");
		if (!total) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

	@ApiOperation(value = "공지사항 삭제", response = BoolResult.class)
	@RequestMapping(value = "/deleteNotice/{no}", method = RequestMethod.POST)
	public ResponseEntity<BoolResult> deleteNotice(@PathVariable int no) throws Exception {
		logger.info("1-------------deleteNotice-----------------------------" + new Date());
		logger.info("1-------------deleteNotice-----------------------------" + no);

		boolean total = noticeService.deleteArticle(no);
		BoolResult nr = new BoolResult();
		nr.setCount(total);
		nr.setName("deleteNotice");
		nr.setState("succ");
		if (!total) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

	@ApiOperation(value = " 공지사항 등록 ", response = NumberResult.class)
	@RequestMapping(value = "/addNotice", method = RequestMethod.POST)
	public ResponseEntity<NumberResult> addNotice(@RequestBody NoticeDto noticeDto) throws Exception {
		logger.info("5-------------addNotice-----------------------------" + new Date());
		logger.info("5-------------addNotice-----------------------------" + noticeDto);
		int total = noticeService.writeArticle(noticeDto);
		NumberResult nr = new NumberResult();
		nr.setCount(total);
		nr.setName("addNotice");
		nr.setState("succ");
		logger.info("5-------------addNotice-------------------------" + total);
		if (total <= 0) {
			nr.setCount(-1);
			nr.setName("addNotice");
			nr.setState("fail");
			// return new ResponseEntity(HttpStatus.NO_CONTENT);
			return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
		}
		return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
	}

	@ApiOperation(value = "userid로 공지사항 검색", response = List.class)
	@RequestMapping(value = "/findNoticeById/{userid}", method = RequestMethod.GET)
	public ResponseEntity<List<NoticeDto>> findNoticeById(@PathVariable String userid) throws Exception {
		logger.info("1-------------findNoticeById-----------------------------" + new Date());
		List<NoticeDto> emps = noticeService.findArticleById(userid);
		if (emps.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<NoticeDto>>(emps, HttpStatus.OK);
	}

	@ApiOperation(value = "제목으로 공지사항 검색", response = List.class)
	@RequestMapping(value = "/findNoticeByTitle/{title}", method = RequestMethod.GET)
	public ResponseEntity<List<NoticeDto>> findNoticeByTitle(@PathVariable String title) throws Exception {
		logger.info("1-------------findNoticeByTitle-----------------------------" + new Date());
		List<NoticeDto> emps = noticeService.findArticleByTitle(title);
		if (emps.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<NoticeDto>>(emps, HttpStatus.OK);
	}
	// ================notice end======================

	// ================board start====================
	@ApiOperation(value = "모든 게시판.", response = List.class)
	@RequestMapping(value = "/boardlist", method = RequestMethod.GET)
	public ResponseEntity<List<BoardDto>> boardlist() throws Exception {
		logger.info("1-------------boardlist-----------------------------" + new Date());
		List<BoardDto> emps = boardService.listArticle();
		if (emps.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<BoardDto>>(emps, HttpStatus.OK);
	}

	@ApiOperation(value = "게시판 번호로 찾기", response = BoardDto.class)
	@RequestMapping(value = "/getBoard/{no}", method = RequestMethod.GET)
	public ResponseEntity<BoardDto> getBoard(@PathVariable int no) throws Exception {
		logger.info("1-------------getBoard-----------------------------" + new Date());
		BoardDto emp = boardService.getArticle(no);
		if (emp == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<BoardDto>(emp, HttpStatus.OK);
	}

	@ApiOperation(value = " 게시판 수정 ", response = BoolResult.class)
	@RequestMapping(value = "/modifyBoard", method = RequestMethod.POST)
	public ResponseEntity<BoolResult> modifyBoard(@RequestBody BoardDto noticeDto) throws Exception {
		logger.info("1-------------modifyBoard-----------------------------" + new Date());
		logger.info("1-------------modifyBoard-----------------------------" + noticeDto);
		boolean total = boardService.modifyArticle(noticeDto);
		BoolResult nr = new BoolResult();
		nr.setCount(total);
		nr.setName("modifyBoard");
		nr.setState("succ");
		if (!total) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

	@ApiOperation(value = "게시판 삭제", response = BoolResult.class)
	@RequestMapping(value = "/deleteBoard/{no}", method = RequestMethod.POST)
	public ResponseEntity<BoolResult> deleteBoard(@PathVariable int no) throws Exception {
		logger.info("1-------------deleteBoard-----------------------------" + new Date());
		logger.info("1-------------deleteBoard-----------------------------" + no);

		boolean total = boardService.deleteArticle(no);
		BoolResult nr = new BoolResult();
		nr.setCount(total);
		nr.setName("deleteBoard");
		nr.setState("succ");
		if (!total) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

	@ApiOperation(value = " 게시판 등록 ", response = NumberResult.class)
	@RequestMapping(value = "/addBoard", method = RequestMethod.POST)
	public ResponseEntity<NumberResult> addBoard(@RequestBody BoardDto boardDto) throws Exception {
		logger.info("5-------------addBoard-----------------------------" + new Date());
		logger.info("5-------------addBoard-----------------------------" + boardDto);
		int total = boardService.writeArticle(boardDto);
		NumberResult nr = new NumberResult();
		nr.setCount(total);
		nr.setName("addBoard");
		nr.setState("succ");
		logger.info("5-------------addBoard-------------------------" + total);
		if (total <= 0) {
			nr.setCount(-1);
			nr.setName("addBoard");
			nr.setState("fail");
			// return new ResponseEntity(HttpStatus.NO_CONTENT);
			return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
		}
		return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
	}

	@ApiOperation(value = "userid로 게시판 검색", response = List.class)
	@RequestMapping(value = "/findBoardById/{userid}", method = RequestMethod.GET)
	public ResponseEntity<List<BoardDto>> findBoardById(@PathVariable String userid) throws Exception {
		logger.info("1-------------findBoardById-----------------------------" + new Date());
		List<BoardDto> emps = boardService.findArticleById(userid);
		if (emps.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<BoardDto>>(emps, HttpStatus.OK);
	}

	@ApiOperation(value = "제목으로 게시판 검색", response = List.class)
	@RequestMapping(value = "/findBoardByTitle/{title}", method = RequestMethod.GET)
	public ResponseEntity<List<BoardDto>> findBoardByTitle(@PathVariable String title) throws Exception {
		logger.info("1-------------findNoticeByTitle-----------------------------" + new Date());
		List<BoardDto> emps = boardService.findArticleByTitle(title);
		if (emps.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<BoardDto>>(emps, HttpStatus.OK);
	}
	// ================board end======================

	// ================product start====================
	@ApiOperation(value = "모든 매물.", response = List.class)
	@RequestMapping(value = "/productlist", method = RequestMethod.GET)
	public ResponseEntity<List<ProductDto>> productlist() throws Exception {
		logger.info("1-------------productlist-----------------------------" + new Date());
		List<ProductDto> emps = productService.searchAll();
		if (emps.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProductDto>>(emps, HttpStatus.OK);
	}

	@ApiOperation(value = "매물 번호로 찾기", response = ProductDto.class)
	@RequestMapping(value = "/getProduct/{no}", method = RequestMethod.GET)
	public ResponseEntity<ProductDto> getProduct(@PathVariable int no) throws Exception {
		logger.info("1-------------getProduct-----------------------------" + new Date());
		ProductDto emp = productService.search(no);
		if (emp == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ProductDto>(emp, HttpStatus.OK);
	}

	@ApiOperation(value = "제목으로 매물 검색", response = List.class)
	@RequestMapping(value = "/findProductByTitle/{title}", method = RequestMethod.GET)
	public ResponseEntity<List<ProductDto>> findProductByTitle(@PathVariable String title) throws Exception {
		logger.info("1-------------findProductByTitle-----------------------------" + new Date());
		List<ProductDto> emps = productService.searchTitle(title);
		if (emps.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProductDto>>(emps, HttpStatus.OK);
	}

	@ApiOperation(value = " 매물 수정 ", response = BoolResult.class)
	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	public ResponseEntity<BoolResult> updateProduct(@RequestBody ProductDto productDto) throws Exception {
		logger.info("1-------------updateProduct-----------------------------" + new Date());
		logger.info("1-------------updateProduct-----------------------------" + productDto);
		boolean total = productService.update(productDto);
		BoolResult nr = new BoolResult();
		nr.setCount(total);
		nr.setName("updateProduct");
		nr.setState("succ");
		if (!total) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

	@ApiOperation(value = "매물 삭제", response = BoolResult.class)
	@RequestMapping(value = "/deleteProduct/{no}", method = RequestMethod.POST)
	public ResponseEntity<BoolResult> deleteProduct(@PathVariable int no) throws Exception {
		logger.info("1-------------deleteProduct-----------------------------" + new Date());
		logger.info("1-------------deleteProduct-----------------------------" + no);

		boolean total = productService.delete(no);
		BoolResult nr = new BoolResult();
		nr.setCount(total);
		nr.setName("deleteProduct");
		nr.setState("succ");
		if (!total) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

	@ApiOperation(value = " 매물 등록 ", response = NumberResult.class)
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ResponseEntity<NumberResult> addProduct(@RequestBody ProductDto productDto) throws Exception {
		logger.info("5-------------addProduct-----------------------------" + new Date());
		logger.info("5-------------addProduct-----------------------------" + productDto);
		int total = productService.insert(productDto);
		NumberResult nr = new NumberResult();
		nr.setCount(total);
		nr.setName("addProduct");
		nr.setState("succ");
		logger.info("5-------------addProduct-------------------------" + total);
		if (total <= 0) {
			nr.setCount(-1);
			nr.setName("addProduct");
			nr.setState("fail");
			// return new ResponseEntity(HttpStatus.NO_CONTENT);
			return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
		}
		return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
	}

	@ApiOperation(value = "userid로 매물 검색", response = List.class)
	@RequestMapping(value = "/findProductById/{userid}", method = RequestMethod.GET)
	public ResponseEntity<List<ProductDto>> findProductById(@PathVariable String userid) throws Exception {
		logger.info("1-------------findProductById-----------------------------" + new Date());
		List<ProductDto> emps = productService.findArticleById(userid);
		if (emps.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProductDto>>(emps, HttpStatus.OK);
	}
	// ================product end======================

}
