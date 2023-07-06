package test.com.moim.com_comments.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class som_comm_commentsDAOimpl implements som_comm_commentsDAO {

	@Autowired
	SqlSession sqlsession;
	
	
	public som_comm_commentsDAOimpl() {
		log.info("som_commentsDAOimpl()...constructor");
		
	
	}
	@Override
	public int insert(som_comm_commentsVO vo) {
		log.info(" 대댓글입니다. dao.inserct().... {}", vo );
		log.info("등록됩니다");
		
		return sqlsession.update("SOM_D_C_INSERT",vo);
	}

	@Override
	public int update(som_comm_commentsVO vo) {
		log.info(" 대댓글입니다   update()...{}",vo);
		return sqlsession.update("SOM_D_C_UPDATE",vo);
	}

	@Override
	public int delete(som_comm_commentsVO vo) {
		log.info("대댓글입니다   delete()...{}",vo);
		return sqlsession.delete("SOM_D_C_DELETE",vo);
	}

	@Override
	public List<som_comm_commentsVO> selectAll(som_comm_commentsVO vo) {
		log.info("대댓글입니다 selectAll()...{}",vo);
		return sqlsession.selectList("SOM_D_C_SELECT_ALL", vo);
	}
	@Override
	public void good_count_up(som_comm_commentsVO vo) {
//		log.info("vcountUp()....{}",vo);
//		sqlSession.update("B_VCOUNT_UP",vo);
		log.info("올라갑니다");
		
	}
	@Override
	public void som_board_num_down(som_comm_commentsVO vo) {
//		log.info("vcountUp()....{}",vo);
//		sqlSession.update("B_VCOUNT_UP",vo);
		log.info("내려갑니다");

	}

}
