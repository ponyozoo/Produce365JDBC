package space.produce.debutMember;

import java.util.List;

public interface DebutMemberDao {
	/* 1. 데뷔멤버 추가
	 * 2. 데뷔멤버 삭제
	 * 3. 데뷔멤버 전체 조회
	 * 4. 데뷔멤버 그룹별 조회
	 * */
	
	//1. 데뷔멤버 추가
	boolean insert(DebutMember debutMember);
	
	//2. 데뷔멤버 삭제
	boolean deleteById(int idx);
	
	//3. 데뷔멤버 전체 조회
	public List<DebutMember> selectAll();
	
	//4. 데뷔멤버 그룹별 조회
	public List<DebutMember> selectByGroup();
	
}
