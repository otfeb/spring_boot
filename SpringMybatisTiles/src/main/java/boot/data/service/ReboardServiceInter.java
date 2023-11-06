package boot.data.service;

import java.util.List;

import boot.data.dto.ReboardDto;

public interface ReboardServiceInter {

	 public int getMaxNum(); 
	   public int getTotalCount(String searchcolumn,String searchword); //검색단어와 카테고리
	   //페이징 처리한 리스트 출력, 검색단어는 String / startPage perPage는 int라서 Object
	   public List<ReboardDto> getPagingList(String searchcolumn,String searchword,int startnum,int perpage); 
	   //public List<ReboardDto> getAllDatas(); //전체 리스트 출력
	   public void insertReboard(ReboardDto dto);
	   public void updateRestep(int regroup,int restep);
	   public void updateReadCount(int num);
	   public ReboardDto getData(int num);
	   public void updateReboard(ReboardDto dto);
	   public void deleteReboard(int num);
	   public void updateLikes(int num);
}
