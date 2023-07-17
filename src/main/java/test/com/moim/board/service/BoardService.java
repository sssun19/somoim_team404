package test.com.moim.board.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.com.moim.board.model.BoardDAO;
import test.com.moim.board.model.Somoim_BoardVO;
import test.com.moim.board.model.Somoim_MemberVO;
import test.com.moim.board.model.Somoim_ScheduleVO;

import java.util.List;

@Service
@Slf4j
public class BoardService {

    @Autowired
    BoardDAO dao;

    public BoardService(){
        log.info("BoardService()....");
    }


    public List<Somoim_BoardVO> selectList(Somoim_BoardVO vo) {
        return dao.selectList(vo);
    }

    public Somoim_BoardVO selectJoin(Somoim_BoardVO vo) {
        return dao.selectJoin(vo);
    }

    public List<Somoim_ScheduleVO> sch_selelctList(Somoim_ScheduleVO vo) {
        return dao.sch_selectList(vo);
    }

    public int update(Somoim_BoardVO vo) {
        return dao.update(vo);
    }

    public int delete(Somoim_BoardVO vo) {
        return dao.delete(vo);    }

    public int Sch_insert(Somoim_ScheduleVO vo) {
        return dao.Sch_insert(vo);
    }

    public Somoim_ScheduleVO selectOne_schedule(Somoim_ScheduleVO vo) {
        return dao.selectOne_schedule(vo);
    }

    public int Sch_update(Somoim_ScheduleVO vo) {
        return dao.Sch_update(vo);
    }

    public int Sch_delete(Somoim_ScheduleVO vo) {
        return dao.Sch_delete(vo);
    }

    public int join_insert(Somoim_BoardVO vo) {
        return dao.join_insert(vo);
    }

    public int SCH_Part_Update(Somoim_ScheduleVO vo) { return dao.SCH_PART_UPDATE(vo);    }

    public int SCH_Part_Cancle(Somoim_ScheduleVO vo) {
        return dao.SCH_Part_Cancle(vo);
    }

    public List<Somoim_ScheduleVO> sch_selectList_part(Somoim_ScheduleVO vo) {
        return dao.sch_selectList_part(vo);
    }


    public Somoim_ScheduleVO selectPay(Somoim_ScheduleVO vo) {
        return dao.selectPay(vo);
    }


    public Somoim_MemberVO selectMember(Somoim_MemberVO mvo) {
        return dao.selectMember(mvo);
    }

    public List<Somoim_BoardVO> select_user_info() { return dao.select_user_info();}



	public List<Somoim_ScheduleVO> som_schedule_selectOne(Somoim_ScheduleVO vo) {
		log.info("somoim_schedule_selectOne.....somoim_num:{}", vo);
		return dao.som_schedule_selectOne(vo);
	}



    //------------
    public void good_count_up(Somoim_BoardVO vo){
        log.info("good count up 정상 작동");
         dao.good_count_up(vo);
    }

    public int good_count_down(Somoim_BoardVO vo){
        return dao.good_count_down(vo);
    }
    public void vvcountup(Somoim_BoardVO vo){
    dao.vvcountup(vo);

    }
    public Somoim_BoardVO select_all_goodList(Somoim_BoardVO vo){
        log.info("전체 좋아요 목록을 불러옵니다.");
        return dao.select_all_goodList(vo);
    }
    public void adding_good_count_list(Somoim_BoardVO vo){
        log.info("완료...{}", vo);
        dao.adding_good_count_list(vo);
    }
    public  void del_good_count_list(Somoim_BoardVO vo){
        log.info("완료...{}", vo);
         dao.del_good_count_list(vo);
    }
<<<<<<< HEAD

    public List<Somoim_BoardVO> JSON_SELECT_ALL(Somoim_BoardVO vo){
        return dao.JSON_SELECT_ALL(vo);
    }

=======
>>>>>>> 2b96f6a32ac736b3e59c81b58f8f5615dd1c7e7b
}
