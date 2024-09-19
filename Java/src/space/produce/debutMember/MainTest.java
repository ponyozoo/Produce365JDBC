package space.produce.debutMember;

import java.util.ArrayList;
import java.util.List;

import space.produce.menu.DebutMenu;
import space.produce.trainee.JdbcTraineeDao;
import space.produce.trainee.Trainee;
import space.produce.trainee.TraineeDao;

public class MainTest {

	public static void main(String[] args) {
		
		DebutMemberDao debutMemberDao = new JDBCDebutMemberDao();
		DebutMember deMember = new DebutMember();
		
		
		/*
		deMember.setGroup(new Debut(4));
		deMember.setTrainee(new Trainee(1111));
		
		debutMemberDao.insert(deMember);
		*/
		
		//debutMemberDao.deleteById();
		/*
		List<DebutMember> deDebutMemberList = new ArrayList<DebutMember>();
		
		for(DebutMember debutMember : debutMemberDao.selectByGroup(4)) {
			System.out.println(debutMember);
		}*/
		
		
		
		DebutMenu debutMenu = new DebutMenu();
		
		
		//debutMenu.readDebut();
		
		//debutMenu.addDebut();
		
		//debutMenu.deleteDebut();
		
		//debutMenu.updateDebut();
		
		debutMenu.selectDebutMenu();
		
		/*
		TraineeDao traineedao = new JdbcTraineeDao();
		Trainee trainee = new Trainee();
		
		List<Trainee> traineeNoDebutList = traineedao.selectNoDebut();
		System.out.println(traineeNoDebutList);
		
		
		for(Trainee traineeNoDebut : traineeNoDebutList) {
			
			System.out.println(traineeNoDebut);
		}*/
		
		
	}
	
	
	
	
	
	
	

}
