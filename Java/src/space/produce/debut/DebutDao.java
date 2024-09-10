package space.produce.debut;

import java.util.List;

public interface DebutDao {
	/*	1. 데뷔조 추가
	 *  2. 데뷔조 업데이트
	 *  3. 데뷔조 삭제(id로)
	 *  4. 데뷔조 전체 조회
	 */
	
	//1. 데뷔조 추가
	boolean insert(Debut debut);
	
	
	//2. 데뷔조 업데이트
	boolean update(Debut debut);
	
	
	//3. 데뷔조 삭제(id로)
	boolean deleteById(int id);
	
	
	//4. 데뷔조 전체 조회
	public List<Debut> findAll();
	
	
}
