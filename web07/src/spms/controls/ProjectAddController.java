package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.ProjectDao;
import spms.vo.Project;
@Component("/project/add.do")
public class ProjectAddController implements Controller,DataBinding {
	ProjectDao projectDao;
	
	public ProjectAddController setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
		return this;
	}
	@Override
	public Object[] getDataBinders() {
		System.out.println("ProjectAddController getDataBinders실행");
		return new Object[]{
			"project",spms.vo.Project.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Project project = (Project) model.get("project");
		if(project.getTitle() == null) {
			System.out.println("ProjectAddController실행 입력폼요청(doGet)");
			return "/project/ProjectForm.jsp";
		}else {
			System.out.println("ProjectAddController실행 회원등록요청 (doPost)");
			projectDao.insert(project);
		}
		return "redirect:list.do";
	}

}
